package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.LikeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ILikeSongRepository extends JpaRepository<LikeSong, Long> {
    List<LikeSong> findByNameSongContaining(String nameSong);
    List<LikeSong> findByUsernameContaining(String username);
}
