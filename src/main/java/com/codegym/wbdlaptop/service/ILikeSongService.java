package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.LikeSong;

import java.util.List;
import java.util.Optional;

public interface ILikeSongService {

    void delete(Long id);
    LikeSong save(LikeSong likeSong);
    Optional<LikeSong> findById(Long id);
    List<LikeSong> findByNameSongContaining(String nameSong);
    List<LikeSong> findByUsernameContaining(String username);
}
