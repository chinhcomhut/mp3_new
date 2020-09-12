package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.Singer;
import com.codegym.wbdlaptop.model.Song;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.service.Impl.PlayListServiceImpl;
import com.codegym.wbdlaptop.service.Impl.SongServiceImpl;
import com.codegym.wbdlaptop.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class SongAPI {
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private PlayListServiceImpl playListService;
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/song")
    public ResponseEntity<?> pageSong(@PageableDefault(sort = "nameSong", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Song> songPage = songService.findAll(pageable);
        if(songPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }
    @GetMapping("/song/{id}")
    public ResponseEntity<?> songById(@PathVariable Long id){
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
    @PutMapping("/song/{id}")
    public ResponseEntity<?> UpdateSong(@Valid @RequestBody Song song, @PathVariable Long id){
        Optional<Song> song1 = songService.findById(id);

        if(songService.existsByNameSong(song.getNameSong())){
            List<Song> songs = songService.findByNameSongContaining(song.getNameSong());
            for(Song song2: songs){
                if(song.getNameSinger().equals(song2.getNameSinger())){
                    return new ResponseEntity<>(new ResponseMessage("nosong"), HttpStatus.OK);
                }
            }
        }
        song1.get().setAvatarSong(song.getAvatarSong());
        song1.get().setLyrics(song.getLyrics());
        song1.get().setMp3Url(song.getMp3Url());
        song1.get().setNameCategory(song.getNameCategory());
        song1.get().setNameSinger(song.getNameSinger());
        song1.get().setNameSong(song.getNameSong());
        song1.get().setNameBand(song.getNameBand());
        songService.save(song1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

    }
    @PostMapping("/song")
    public ResponseEntity<?> createSong(@Valid @RequestBody Song song, Singer singer) throws IOException {

        if (song.getMp3Url() == null || song.getMp3Url()=="" ) {
            return new ResponseEntity<>(new ResponseMessage("nomp3url"), HttpStatus.OK);
        }
        if(song.getAvatarSong()==null || song.getAvatarSong()==""){
            return new ResponseEntity<>(new ResponseMessage("noavatar"), HttpStatus.OK);
        }

        if (song.getNameCategory() == null || song.getNameCategory() == "") {
            return new ResponseEntity<>(new ResponseMessage("nocategory"), HttpStatus.OK);
        }

        if (song.getNameSinger() == null && song.getNameBand()==null) {
            return new ResponseEntity<>(new ResponseMessage("nosingerband"), HttpStatus.OK);
        }

        if (songService.existsByNameSong(song.getNameSong())) {
            List<Song> song1 = songService.findByNameSongContaining(song.getNameSong());

            for (Song song2 : song1) {

                if (song2.getNameSinger().equals(song.getNameSinger())) {

                    return new ResponseEntity<>(new ResponseMessage("nosong"), HttpStatus.OK);
                }
            }
        }
        songService.save(song);

        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

    }
    @DeleteMapping("/song/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id){
        Optional<Song> song = songService.findById(id);
        if(!song.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    //    @GetMapping("/song-by-playlist/{id}")
//       public ResponseEntity songByPlayListId(@PathVariable Long id){
//        Optional<Playlist> playlist = playListService.findById(id);
//        List<Song> songs = songService.findAllByPlaylistId(playlist.get().getId());
//        return new ResponseEntity(songs, HttpStatus.OK);
//    }
//    @PutMapping("/add-song-by-playlist/{id}")
//    public ResponseEntity<?> updateSong(@PathVariable Long id,@Valid @RequestBody Song song){
//        Optional<Playlist> playlist = playListService.findById(id);
//        song.setPlaylist(playlist.get());
//        songService.save(song);
//        return new ResponseEntity<>(song, HttpStatus.CREATED);
//    }
    @PutMapping("/song")
    public ResponseEntity<?> updateSong(@RequestBody Song song){
        songService.save(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }
    @GetMapping("/song-by-user/{id}")
    public ResponseEntity<?> pageSongByUserId(@PathVariable Long id, @PageableDefault(sort = "nameSong", direction = Sort.Direction.ASC)Pageable pageable){
        Optional<User> user = userService.findById(id);
        Page<Song> songPage = songService.findAllByUserId(user.get().getId(),pageable);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(songPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }

}
