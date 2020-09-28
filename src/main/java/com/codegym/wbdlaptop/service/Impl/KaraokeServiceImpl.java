package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Karaoke;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.IKaraokeRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IKaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KaraokeServiceImpl implements IKaraokeService {
    @Autowired
    private IKaraokeRepository karaokeRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public void delete(Long id) {
        karaokeRepository.deleteById(id);
    }

    @Override
    public Karaoke save(Karaoke karaoke) {
        User user = userDetailsService.getCurrentUser();
        karaoke.setUser(user);
        return karaokeRepository.save(karaoke);
    }

    @Override
    public Optional<Karaoke> findById(Long id) {
        return karaokeRepository.findById(id);
    }

    @Override
    public Page<Karaoke> findAll(Pageable pageable) {
        return karaokeRepository.findAll(pageable);
    }

    @Override
    public List<Karaoke> findAll() {
        return karaokeRepository.findAll();
    }
}
