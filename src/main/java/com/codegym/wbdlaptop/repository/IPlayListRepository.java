package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayListRepository extends JpaRepository<Playlist, Long> {
    Page<Playlist> findAllByUserId(Long userId, Pageable pageable);
    Page<Playlist> findByNameAlbumContaining(String nameAlbum, Pageable pageable);
}
