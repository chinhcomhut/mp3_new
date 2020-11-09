package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.LikeHocLapTrinh;
import com.codegym.wbdlaptop.repository.HocLapTrinhRepository;
import com.codegym.wbdlaptop.repository.ILikeHocLapTrinhRepository;
import com.codegym.wbdlaptop.service.ILikeHocLapTrinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeHocLapTrinhServiceImpl implements ILikeHocLapTrinhService {
    @Autowired
    ILikeHocLapTrinhRepository likeHocLapTrinhRepository;
    @Override
    public List<LikeHocLapTrinh> findByUsernameContaining(String username) {
        return likeHocLapTrinhRepository.findByUsernameContaining(username);
    }

    @Override
    public void delete(Long id) {
    likeHocLapTrinhRepository.deleteById(id);
    }

    @Override
    public LikeHocLapTrinh save(LikeHocLapTrinh likeHocLapTrinh) {
        return likeHocLapTrinhRepository.save(likeHocLapTrinh);
    }

    @Override
    public Optional<LikeHocLapTrinh> findById(Long id) {
        return likeHocLapTrinhRepository.findById(id);
    }
}
