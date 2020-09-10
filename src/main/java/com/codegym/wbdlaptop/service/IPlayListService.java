package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlayListService {
    Page<Playlist> findAllByUserId(Long userId, Pageable pageable);
    void delete(Long id);
    Playlist save(Playlist playlist);
    Optional<Playlist> findById(Long id);
    Page<Playlist> findAll(Pageable pageable);
    Page<Playlist> findByNameAlbumContaining(String nameAlbum, Pageable pageable);
    Page<Playlist> findByNameSingerContaining(String nameSinger, Pageable pageable);
    Page<Playlist> findByNameCategoryContaining(String nameCategory, Pageable pageable);
    Page<Playlist> findByNameBandContaining(String nameBand, Pageable pageable);
}
