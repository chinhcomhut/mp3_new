package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.model.LikeSong;
import com.codegym.wbdlaptop.model.LikeVideo;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.Impl.LikeVideoServiceImpl;
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
public class LikeVideoAPI {
    @Autowired
    private LikeVideoServiceImpl likeVideoService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @GetMapping("/like-video-by-user")
    public ResponseEntity<?> listLikeVideoByUsername(){
        User user = userDetailsService.getCurrentUser();
        List<LikeVideo> likeVideos = likeVideoService.findByUsernameContaining(user.getUsername());
        if(likeVideos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(likeVideos,HttpStatus.OK);
    }
}
