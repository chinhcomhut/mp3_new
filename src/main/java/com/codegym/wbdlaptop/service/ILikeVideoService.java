package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.LikeVideo;

import java.util.List;
import java.util.Optional;

public interface ILikeVideoService {
    List<LikeVideo> findByUsernameContaining(String username);
    void delete(Long id);
    Optional<LikeVideo> findById(Long id);
    LikeVideo save(LikeVideo likeVideo);
}
