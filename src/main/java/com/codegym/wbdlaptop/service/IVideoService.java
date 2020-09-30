package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IVideoService {
    Boolean existsByNameVideo(String nameVideo);
    Page<Video> findByNameBandContaining(String nameBand, Pageable pageable);
    Page<Video> findByNameCategoryContaining(String nameCategory, Pageable pageable);
    Page<Video> findByNameSingerContaining(String nameSinger, Pageable pageable);
    Page<Video> findAll(Pageable pageable);
    void  delete(Long id);
    Video save(Video video);
    Optional<Video> findById(Long id);
    List<Video> findByNameVideoContaining(String nameVideo);
}
