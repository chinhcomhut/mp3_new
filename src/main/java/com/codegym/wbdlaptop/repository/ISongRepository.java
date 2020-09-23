package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
Boolean existsByNameSong(String nameSong);
Page<Song> findAllByUserId(Long userId, Pageable pageable);
Page<Song> findByNameSingerContaining(String nameSinger, Pageable pageable);
Page<Song> findByNameCategoryContaining(String nameCategory, Pageable pageable);
List<Song> findByNameSongContaining(String nameSong);
Page<Song> findByNameBandContaining(String nameBand, Pageable pageable);
//Page<Song> findAllByNameSongOrderByLikeSongAsc(int likeSong, Pageable pageable);
}
