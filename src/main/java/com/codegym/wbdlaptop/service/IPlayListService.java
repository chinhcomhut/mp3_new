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
}
