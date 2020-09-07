package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAlbumService {
    Boolean existsByNameAlbum(String nameAlbum);
    void delete(Long id);
    Album save(Album album);
    Optional<Album> findById(Long id);
    Page<Album> findAll(Pageable pageable);
    List<Album> findAll();
}
