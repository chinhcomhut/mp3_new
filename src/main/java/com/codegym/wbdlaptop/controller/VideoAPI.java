package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.*;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.Impl.LikeSongServiceImpl;
import com.codegym.wbdlaptop.service.Impl.LikeVideoServiceImpl;
import com.codegym.wbdlaptop.service.Impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class VideoAPI {
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private LikeVideoServiceImpl likeVideoService;
    @GetMapping("/video")
    public ResponseEntity<?> pageVideo(@PageableDefault(sort = "nameVideo",direction = Sort.Direction.ASC)Pageable pageable){
        Page<Video> videos = videoService.findAll(pageable);
        if(videos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(videos,HttpStatus.OK);
    }
    @GetMapping("/video/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable Long id){
        Optional<Video> video = videoService.findById(id);
        if(!video.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(video,HttpStatus.OK);
    }
    @PostMapping("/video")
    public ResponseEntity<?> createVideo(@Valid @RequestBody Video video, Singer singer) throws IOException {

        if (video.getLinkYoutube() == null || video.getLinkYoutube()=="" ) {
            return new ResponseEntity<>(new ResponseMessage("nolink"), HttpStatus.OK);
        }
        if (video.getNameCategory() == null || video.getNameCategory() == "") {
            return new ResponseEntity<>(new ResponseMessage("nocategory"), HttpStatus.OK);
        }

        if ((video.getNameSinger() == ""||video.getNameSinger()==null) && (video.getNameBand()==""||video.getNameBand()==null)) {
            return new ResponseEntity<>(new ResponseMessage("nosingerband"), HttpStatus.OK);
        }
        if (videoService.existsByNameVideo(video.getNameVideo())) {
            List<Video> video1 = videoService.findByNameVideoContaining(video.getNameVideo());

            for (Video video2 : video1) {

                if (video2.getNameSinger().equals(video.getNameSinger())) {

                    return new ResponseEntity<>(new ResponseMessage("novideo"), HttpStatus.OK);
                }
            }
        }
        videoService.save(video);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PutMapping("/video/{id}")
    public ResponseEntity<?> UpdateSong(@Valid @RequestBody Video video, @PathVariable Long id){
        Optional<Video> video1 = videoService.findById(id);

        if(videoService.existsByNameVideo(video.getNameVideo())){
            List<Video> videos = videoService.findByNameVideoContaining(video.getNameVideo());
            for(Video video2: videos){
                if(video.getNameSinger().equals(video2.getNameSinger())){
                    return new ResponseEntity<>(new ResponseMessage("novideo"), HttpStatus.OK);
                }
            }
        }
        video1.get().setNameVideo(video.getNameVideo());
        video1.get().setLinkYoutube(video.getLinkYoutube());
        video1.get().setNameBand(video.getNameBand());
        video1.get().setNameCategory(video.getNameCategory());
        video1.get().setNameSinger(video.getNameSinger());
        videoService.save(video1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @DeleteMapping("/video/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        Optional<Video> video = videoService.findById(id);
        if(!video.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        videoService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
    @GetMapping("/video-like-up/{id}")
    public ResponseEntity<?> getVideoLikedById(@PathVariable("id") Long id) {
        try {

            Video video = videoService.findById(id).orElseThrow(EntityNotFoundException::new);
            User user = userDetailsService.getCurrentUser();
            List<LikeVideo> likeVideos = likeVideoService.findByUsernameContaining(user.getUsername());
            if(likeVideos.size()==0){
                LikeVideo likeVideo = new LikeVideo();
                likeVideo.setNameVideo(video.getNameVideo());
                likeVideo.setUsername(user.getUsername());
                likeVideoService.save(likeVideo);
                video.setLikeVideo(video.getLikeVideo()+ 1);
                videoService.save(video);
                return new ResponseEntity<>(video, HttpStatus.OK);
            } else {
                for(int i = 0; i<likeVideos.size();i++){
                    if(likeVideos.get(i).getNameVideo().equals(video.getNameVideo())){
                        likeVideoService.delete(likeVideos.get(i).getId());
                        video.setLikeVideo(video.getLikeVideo()-1);
                        videoService.save(video);
                        return new ResponseEntity<>(video, HttpStatus.OK);
                    }
                }
            }
            LikeVideo likeVideo = new LikeVideo();
            likeVideo.setNameVideo(video.getNameVideo());
            likeVideo.setUsername(user.getUsername());
            likeVideoService.save(likeVideo);
            video.setLikeVideo(video.getLikeVideo()+ 1);
            videoService.save(video);
            return new ResponseEntity<>(video, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/count-view-video/{id}")
    public ResponseEntity<?> getSongListenById(@PathVariable("id") Long id){
        try {
            Video video = videoService.findById(id).orElseThrow(EntityNotFoundException::new);
            video.setViewVideo(video.getViewVideo()+0.5);
            videoService.save(video);
            return new ResponseEntity<>(video,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
