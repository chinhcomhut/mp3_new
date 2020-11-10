package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.model.LikeHocLapTrinh;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.Impl.LikeHocLapTrinhServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class LikeHocLapTrinhAPI {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    LikeHocLapTrinhServiceImpl likeHocLapTrinhService;
    @GetMapping("/hlt-by-user")
    public ResponseEntity<?> getListLikeHLT(){
        User user = userDetailsService.getCurrentUser();
        List<LikeHocLapTrinh> likeHocLapTrinhs = likeHocLapTrinhService.findByUsernameContaining(user.getUsername());
        if(likeHocLapTrinhs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(likeHocLapTrinhs,HttpStatus.OK);
    }
}
