package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.LikeHocLapTrinh;

import java.util.List;
import java.util.Optional;

public interface ILikeHocLapTrinhService {
    List<LikeHocLapTrinh> findByUsernameContaining(String username);
    void delete(Long id);
    LikeHocLapTrinh save(LikeHocLapTrinh likeHocLapTrinh);
    Optional<LikeHocLapTrinh> findById(Long id);
}
