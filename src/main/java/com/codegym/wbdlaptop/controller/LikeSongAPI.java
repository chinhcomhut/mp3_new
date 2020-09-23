package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.model.LikeSong;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.Impl.LikeSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
public class LikeSongAPI {
    @Autowired
    private LikeSongServiceImpl likeSongService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @GetMapping("/like-song-by-user")
    public ResponseEntity<?> listLikeSongByUsername(){
        User user = userDetailsService.getCurrentUser();
        List<LikeSong> likeSongs = likeSongService.findByUsernameContaining(user.getUsername());
        if(likeSongs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(likeSongs,HttpStatus.OK);
    }
}
