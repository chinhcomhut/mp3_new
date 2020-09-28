package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.Karaoke;
import com.codegym.wbdlaptop.repository.IKaraokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class KaraokeAPI {
    @Autowired
    private IKaraokeRepository karaokeRepository;
    @GetMapping("/karaoke")
    public ResponseEntity<?> pageKaraoke(@PageableDefault(sort = "nameSong",direction = Sort.Direction.ASC)Pageable pageable){
        Page<Karaoke> karaokes = karaokeRepository.findAll(pageable);
        if(karaokes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(karaokes,HttpStatus.OK);
    }
    @GetMapping("/karaoke/{id}")
    public ResponseEntity<?> getKaraokeById(@PathVariable Long id){
        Optional<Karaoke> karaoke = karaokeRepository.findById(id);
        if(!karaoke.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(karaoke, HttpStatus.OK);
    }
    @PutMapping("/karaoke/{id}")
    public ResponseEntity<?> updateKaraoke(@PathVariable Long id, @Valid @RequestBody Karaoke karaoke){
        Optional<Karaoke> karaoke1 = karaokeRepository.findById(id);
        if(!karaoke1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(karaoke.getNameSong()==""||karaoke.getNameSong()==null){
            return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
        }
        if(karaoke.getLinkYoutube()==null||karaoke.getLinkYoutube()==""){
            return new ResponseEntity<>(new ResponseMessage("nolink"), HttpStatus.OK);
        }
        karaoke1.get().setNameSong(karaoke.getNameSong());
        karaoke1.get().setLinkYoutube(karaoke.getLinkYoutube());
        karaokeRepository.save(karaoke1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
    @DeleteMapping("/karaoke/{id}")
    public ResponseEntity<?> deleteKaraoke(@PathVariable Long id){
        Optional<Karaoke> karaoke = karaokeRepository.findById(id);
        if(!karaoke.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        karaokeRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/karaoke")
    public ResponseEntity<?> createKaraoke(@Valid @RequestBody Karaoke karaoke){
        if(karaoke.getNameSong()==null||karaoke.getNameSong()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"),HttpStatus.OK);
        }
        if(karaoke.getLinkYoutube()==null||karaoke.getLinkYoutube()==""){
            return new ResponseEntity<>(new ResponseMessage("nolink"),HttpStatus.OK);
        }
        karaokeRepository.save(karaoke);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/list-karaoke")
    public ResponseEntity<?> listKaraoke(){
        List<Karaoke> karaokes = karaokeRepository.findAll();
        if(karaokes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(karaokes,HttpStatus.OK);
    }
}
