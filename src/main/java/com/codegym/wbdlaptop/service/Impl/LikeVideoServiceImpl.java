package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.LikeVideo;
import com.codegym.wbdlaptop.repository.ILikeVideoRepository;
import com.codegym.wbdlaptop.service.ILikeVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LikeVideoServiceImpl implements ILikeVideoService {
    @Autowired
    private ILikeVideoRepository likeVideoRepository;
    @Override
    public List<LikeVideo> findByUsernameContaining(String username) {
        return likeVideoRepository.findByUsernameContaining(username);
    }

    @Override
    public void delete(Long id) {
    likeVideoRepository.deleteById(id);
    }

    @Override
    public Optional<LikeVideo> findById(Long id) {
        return likeVideoRepository.findById(id);
    }

    @Override
    public LikeVideo save(LikeVideo likeVideo) {
        return likeVideoRepository.save(likeVideo);
    }
}
