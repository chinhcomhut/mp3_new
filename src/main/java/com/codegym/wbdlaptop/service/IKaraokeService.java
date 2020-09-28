package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Karaoke;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IKaraokeService {
    void delete(Long id);
    Karaoke save(Karaoke karaoke);
    Optional<Karaoke> findById(Long id);
    Page<Karaoke> findAll(Pageable pageable);
    List<Karaoke> findAll();
}
