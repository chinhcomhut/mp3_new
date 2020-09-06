package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.request.*;
import com.codegym.wbdlaptop.message.response.JwtResponse;
import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.Role;
import com.codegym.wbdlaptop.model.RoleName;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.security.jwt.JwtAuthTokenFilter;
import com.codegym.wbdlaptop.security.jwt.JwtProvider;
import com.codegym.wbdlaptop.security.service.UserPrinciple;
import com.codegym.wbdlaptop.service.IRoleService;
import com.codegym.wbdlaptop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                userDetails.getId() , userDetails.getName(), userDetails.getEmail(), userDetails.getAvatar() ,
                userDetails.getAuthorities()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("nouser"),
                    HttpStatus.OK);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("noemail"),
                    HttpStatus.OK);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePasswordForm changePasswordForm) {
        String jwt = jwtAuthTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        User user;
        try {
            user = userService
                    .findByUsername(username)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User Not Found with -> username:" + username));
            boolean matches = passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword());
            if (changePasswordForm.getNewPassword() != null) {
                if (matches) {
                    user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
                    userService.save(user);
                } else {
                    return new ResponseEntity(new ResponseMessage("no"), HttpStatus.OK);
                }
            }
            return new ResponseEntity(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangeAvatar changeAvatar){
        String jwt = jwtAuthTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        User user;
        try {
            if(changeAvatar.getAvatar()==null){
                return new ResponseEntity(new ResponseMessage("no"), HttpStatus.OK);
            } else{
                user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Fount with -> username:"+username));
                user.setAvatar(changeAvatar.getAvatar());
                userService.save(user);

            }
            return new ResponseEntity(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangeProfileForm changeProfile){
//        BufferedWriter writer = new BufferedWriter(new FileWriter("profile.txt"));
//        writer.newLine();
        String jwt = jwtAuthTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        User user;

        try {

            if (userService.existsByUsername(changeProfile.getUsername())) {

                return new ResponseEntity(new ResponseMessage("nouser"), HttpStatus.OK);
            }
            if(userService.existsByEmail(changeProfile.getEmail())){
                return new ResponseEntity(new ResponseMessage("noemail"), HttpStatus.OK);
            }
//          User user = userDetailsService.getCurrentUser();
            user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Fount with -> username:"+username));
            user.setName(changeProfile.getName());
//                user.setAvatar(changeProfile.getAvatar());
            user.setUsername(changeProfile.getUsername());
            user.setEmail(changeProfile.getEmail());
            userService.save(user);
//            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage("excetion"), HttpStatus.OK);
        }

    }
//    @PutMapping("/update-password/{id}")
//    public ResponseEntity<?>updatePassword(@Valid @RequestBody PasswordForm passForm, @PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//
//        if (user == null ){
//            return new ResponseEntity<>(new ResponseMessage("Not found user"),HttpStatus.NOT_FOUND);
//        }
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(passForm.getUsername(), passForm.getCurrentPassword()));
//
//            user.get().setPassword(passwordEncoder.encode(passForm.getNewPassword()));
//
//            userService.save(user.get());
//
//            return new ResponseEntity<>(new ResponseMessage("Change password successful"),HttpStatus.OK);
//        } catch (Exception e) {
//            throw new RuntimeException("Fail!");
//        }
//    }
}
