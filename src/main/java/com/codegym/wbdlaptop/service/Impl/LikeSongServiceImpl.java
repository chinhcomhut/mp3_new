package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.LikeSong;
import com.codegym.wbdlaptop.repository.ILikeSongRepository;
import com.codegym.wbdlaptop.service.ILikeSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeSongServiceImpl implements ILikeSongService {
    @Autowired
    private ILikeSongRepository likeSongRepository;


    @Override
    public void delete(Long id) {
likeSongRepository.deleteById(id);
    }

    @Override
    public LikeSong save(LikeSong likeSong) {
        return likeSongRepository.save(likeSong);
    }

    @Override
    public Optional<LikeSong> findById(Long id) {
        return likeSongRepository.findById(id);
    }

    @Override
    public List<LikeSong> findByNameSongContaining(String nameSong) {
        return likeSongRepository.findByNameSongContaining(nameSong);
    }

    @Override
    public List<LikeSong> findByUsernameContaining(String username) {
        return likeSongRepository.findByUsernameContaining(username);
    }


}
