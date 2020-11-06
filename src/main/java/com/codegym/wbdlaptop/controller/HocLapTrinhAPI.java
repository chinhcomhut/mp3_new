package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.response.ResponseMessage;
import com.codegym.wbdlaptop.model.HocLapTrinh;
import com.codegym.wbdlaptop.service.Impl.HocLapTrinhServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class HocLapTrinhAPI {
    @Autowired
    private HocLapTrinhServiceImpl hocLapTrinhService;
    @GetMapping("/hoc-lap-trinh")
    public ResponseEntity<?> pageHocLapTrinh(@PageableDefault(sort = "nameVideo", direction = Sort.Direction.ASC)Pageable pageable){
        Page<HocLapTrinh> lapTrinhPage = hocLapTrinhService.findAll(pageable);
        if(lapTrinhPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lapTrinhPage, HttpStatus.OK);
    }
    @GetMapping("/hoc-lap-trinh/{id}")
    public ResponseEntity<?> getHocLapTrinhById(@PathVariable Long id){
        Optional<HocLapTrinh> hocLapTrinh = hocLapTrinhService.findById(id);
        if(!hocLapTrinh.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hocLapTrinh,HttpStatus.OK);
    }
    @PostMapping("/hoc-lap-trinh")
    public ResponseEntity<?> createHocLapTrinh(@Valid @RequestBody HocLapTrinh hocLapTrinh){
        if(hocLapTrinh.getNameVideo()==null||hocLapTrinh.getNameVideo()==""){
            return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
        }
        if(hocLapTrinh.getLinkYoutube()==null||hocLapTrinh.getLinkYoutube()==""){
            return new ResponseEntity<>(new ResponseMessage("nolink"), HttpStatus.OK);
        }
        if(hocLapTrinhService.existsByNameVideo(hocLapTrinh.getNameVideo())){
            return new ResponseEntity<>(new ResponseMessage("nohoclaptrinh"), HttpStatus.OK);
        }
        hocLapTrinhService.save(hocLapTrinh);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PutMapping("/hoc-lap-trinh/{id}")
    public ResponseEntity<?> updateHocLapTrinhById(@Valid @RequestBody HocLapTrinh hocLapTrinh,@PathVariable Long id){
    Optional<HocLapTrinh> hocLapTrinh1 = hocLapTrinhService.findById(id);
    if(!hocLapTrinh1.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(hocLapTrinh.getNameVideo()==null||hocLapTrinh.getNameVideo()==""){
        return new ResponseEntity<>(new ResponseMessage("noname"), HttpStatus.OK);
    }
    if(hocLapTrinh.getLinkYoutube()==null||hocLapTrinh.getLinkYoutube()==""){
        return new ResponseEntity<>(new ResponseMessage("nolink"), HttpStatus.OK);
    }
    hocLapTrinh1.get().setNameVideo(hocLapTrinh.getNameVideo());
    hocLapTrinh1.get().setLinkYoutube(hocLapTrinh.getLinkYoutube());
    hocLapTrinhService.save(hocLapTrinh1.get());
    return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @DeleteMapping("/hoc-lap-trinh/{id}")
    public ResponseEntity<?> deleteHocLapTrinh(@PathVariable Long id){
        Optional<HocLapTrinh> hocLapTrinh = hocLapTrinhService.findById(id);
        if(!hocLapTrinh.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hocLapTrinhService.delete(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
}
