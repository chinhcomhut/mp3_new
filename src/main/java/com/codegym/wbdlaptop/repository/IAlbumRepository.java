package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {
    Boolean existsByNameAlbum(String nameAlbum);
}
