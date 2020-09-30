package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.model.Video;
import com.codegym.wbdlaptop.repository.IVideoRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements IVideoService {
    @Autowired
    private IVideoRepository videoRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public Boolean existsByNameVideo(String nameVideo) {
        return videoRepository.existsByNameVideo(nameVideo);
    }

    @Override
    public Page<Video> findByNameBandContaining(String nameBand, Pageable pageable) {
        return videoRepository.findByNameBandContaining(nameBand,pageable);
    }

    @Override
    public Page<Video> findByNameCategoryContaining(String nameCategory, Pageable pageable) {
        return videoRepository.findByNameCategoryContaining(nameCategory,pageable);
    }

    @Override
    public Page<Video> findByNameSingerContaining(String nameSinger, Pageable pageable) {
        return videoRepository.findByNameSingerContaining(nameSinger,pageable);
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    videoRepository.deleteById(id);
    }

    @Override
    public Video save(Video video) {
        User user = userDetailsService.getCurrentUser();
        video.setUser(user);
        return videoRepository.save(video);
    }

    @Override
    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }

    @Override
    public List<Video> findByNameVideoContaining(String nameVideo) {
        return videoRepository.findByNameVideoContaining(nameVideo);
    }
}
