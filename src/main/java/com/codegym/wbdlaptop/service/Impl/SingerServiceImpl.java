package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Singer;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.ISingerRepository;
import com.codegym.wbdlaptop.repository.ISongRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl implements ISingerService {
    @Autowired
    private ISingerRepository singerRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public Boolean existsByNameSinger(String nameSinger) {
        return singerRepository.existsByNameSinger(nameSinger);
    }

    @Override
    public Page<Singer> findAllByUserId(Long userId, Pageable pageable) {
        return singerRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Singer save(Singer singer) {
        User user = userDetailsService.getCurrentUser();
        singer.setUser(user);
        return singerRepository.save(singer);
    }

    @Override
    public void delete(Long id) {
    singerRepository.deleteById(id);
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Page<Singer> findAll(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }

    @Override
    public List<Singer> findAll() {
        return singerRepository.findAll();
    }
}
