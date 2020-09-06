package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ISingerService {
    Boolean existsByNameSinger(String nameSinger);
    Page<Singer> findAllByUserId(Long userId, Pageable pageable);
    Singer save(Singer singer);
    void delete(Long id);
    Optional<Singer> findById(Long id);
    Page<Singer> findAll(Pageable pageable);
    List<Singer> findAll();
}
