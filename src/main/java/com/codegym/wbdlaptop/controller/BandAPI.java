package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.*;
import com.codegym.wbdlaptop.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class BandAPI {
    @Autowired
    private BandServiceImpl bandService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private PlayListServiceImpl playListService;
    @Autowired
    private VideoServiceImpl videoService;
    @PostMapping("/band")
    public ResponseEntity<?> createBand(@Valid @RequestBody Band band){
        if(band.getNameBand()==null||band.getNameBand()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
        }
        if(band.getAvatarBand()==null||band.getAvatarBand()==""){
            return new ResponseEntity<>(new ResponseMessage("noavatar"), HttpStatus.OK);
        }
        if(bandService.existsByNameBand(band.getNameBand())){
            return new ResponseEntity<>(new ResponseMessage("noband"), HttpStatus.OK);
        }
        bandService.save(band);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/band")
    public ResponseEntity<?> pageBand(@PageableDefault(sort = "nameBand",direction = Sort.Direction.ASC)Pageable pageable){
        Page<Band> bands = bandService.findAll(pageable);
        if(bands.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
    @GetMapping("/band/{id}")
    public ResponseEntity<?> getBandById(@PathVariable Long id){
        Optional<Band> band = bandService.findById(id);
        if(!band.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(band, HttpStatus.OK);
    }
    @PutMapping("/band/{id}")
    public ResponseEntity<?> updateBand(@PathVariable Long id, @Valid @RequestBody Band band){
        Optional<Band> band1 = bandService.findById(id);
        if(!band1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(band.getNameBand()==null||band.getNameBand()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"),HttpStatus.OK);
        }
        if(bandService.existsByNameBand(band.getNameBand())){
            return new ResponseEntity<>(new ResponseMessage("noband"), HttpStatus.OK);
        }
        band1.get().setNameBand(band.getNameBand());
        band1.get().setAvatarBand(band.getAvatarBand());
        bandService.save(band1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
    @DeleteMapping("/band/{id}")
    public ResponseEntity<?> deleteBand(@PathVariable Long id){
        Optional<Band> band = bandService.findById(id);
        if(!band.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bandService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/band-by-user/{id}")
    public ResponseEntity<?> pageBandByUserId(@PathVariable Long id, @PageableDefault(sort = "nameBand",direction = Sort.Direction.ASC)Pageable pageable){
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Band> bands = bandService.findAllByUserId(user.get().getId(),pageable);
        if(bands.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
    @GetMapping("/song-by-band/{id}")
    public ResponseEntity<?> pageSongByBandId(@PathVariable Long id, @PageableDefault(sort = "nameSong", direction = Sort.Direction.ASC)Pageable pageable){
        Optional<Band> band = bandService.findById(id);
        if(!band.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Song> songPage = songService.findByNameBandContaining(band.get().getNameBand(),pageable);
        if(songPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage,HttpStatus.OK);
    }
    @GetMapping("/playlist-by-band/{id}")
    public ResponseEntity<?> pagePlayListByBandId(@PathVariable Long id, @PageableDefault(sort = "namePlayList", direction = Sort.Direction.ASC)Pageable pageable){
        Optional<Band> band = bandService.findById(id);
        if(!band.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Playlist> playlistPage = playListService.findByNameBandContaining(band.get().getNameBand(), pageable);
        if(playlistPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlistPage, HttpStatus.OK);
    }
    @GetMapping("/video-by-band/{id}")
    public ResponseEntity<?> pageVideoByBand(@PathVariable Long id, @PageableDefault(sort = "nameVideo", direction = Sort.Direction.ASC)Pageable pageable){
        Optional<Band> band = bandService.findById(id);
        if(!band.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Video> videos = videoService.findByNameBandContaining(band.get().getNameBand(),pageable);
        if(videos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(videos,HttpStatus.OK);
    }
    @GetMapping("list-band")
    public ResponseEntity<?> listBand(){
        List<Band> bands = bandService.findAll();
        if(bands.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }
}
