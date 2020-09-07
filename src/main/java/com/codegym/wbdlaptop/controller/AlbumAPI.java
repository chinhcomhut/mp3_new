package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.Album;
import com.codegym.wbdlaptop.model.Playlist;
import com.codegym.wbdlaptop.service.Impl.AlbumServiceImpl;
import com.codegym.wbdlaptop.service.Impl.PlayListServiceImpl;
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
public class AlbumAPI {
    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    PlayListServiceImpl playListService;
    @PostMapping("/album")
    public ResponseEntity<?> createAlbum(@Valid @RequestBody Album album){
        if(album.getNameAlbum()==null||album.getNameAlbum()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
        }
        if(album.getAvatarAlbum()==null||album.getAvatarAlbum()==""){
            return new ResponseEntity<>(new ResponseMessage("noavatar"), HttpStatus.OK);
        }
        if(albumService.existsByNameAlbum(album.getNameAlbum())){
            return new ResponseEntity<>(new ResponseMessage("noalbum"), HttpStatus.OK);
        }
        albumService.save(album);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/album")
    public ResponseEntity<?> pageAlbum(@PageableDefault(sort = "nameAlbum", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Album> albums = albumService.findAll(pageable);
        if(albums.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable Long id){
        Optional<Album> album = albumService.findById(id);
        if(!album.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(album, HttpStatus.OK);
    }
    @PutMapping("/album/{id}")
    public ResponseEntity<?> updateAlbum(@PathVariable Long id, @Valid @RequestBody Album album){
        Optional<Album> album1 = albumService.findById(id);
        if(!album1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(album.getNameAlbum()==null||album.getNameAlbum()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
        }
        if(albumService.existsByNameAlbum(album.getNameAlbum())){
            return new ResponseEntity<>(new ResponseMessage("noalbum"), HttpStatus.OK);
        }
        album1.get().setNameAlbum(album.getNameAlbum());
        album1.get().setAvatarAlbum(album.getAvatarAlbum());
        albumService.save(album1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id){
        Optional<Album> album = albumService.findById(id);
        if(!album.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        albumService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/playlist-by-album/{id}")
    public ResponseEntity<?> pagePlayList(@PathVariable Long id, @PageableDefault(sort = "namePlayList", direction = Sort.Direction.ASC)Pageable pageable){
        Optional<Album> album = albumService.findById(id);
        if(!album.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Playlist> playlistPage = playListService.findByNameAlbumContaining(album.get().getNameAlbum(),pageable);
        return new ResponseEntity<>(playlistPage, HttpStatus.OK);
    }
    @GetMapping("/list-album")
    public ResponseEntity<?> getListAlbum(){
        List<Album> albums = albumService.findAll();
        if(albums.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
