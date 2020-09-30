package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {
    Boolean existsByNameVideo(String nameVideo);
    Page<Video> findByNameBandContaining(String nameBand, Pageable pageable);
    Page<Video> findByNameCategoryContaining(String nameCategory, Pageable pageable);
    Page<Video> findByNameSingerContaining(String nameSinger, Pageable pageable);
    List<Video> findByNameVideoContaining(String nameVideo);
}
