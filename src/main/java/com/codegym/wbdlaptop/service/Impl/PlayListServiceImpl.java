package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Playlist;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.IPlayListRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayListServiceImpl implements IPlayListService {
    @Autowired
    private IPlayListRepository playListRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public Page<Playlist> findAllByUserId(Long userId, Pageable pageable) {
        return playListRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public void delete(Long id) {
        playListRepository.deleteById(id);
    }

    @Override
    public Playlist save(Playlist playlist) {
        User user = userDetailsService.getCurrentUser();
        playlist.setUser(user);
        return playListRepository.save(playlist);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playListRepository.findById(id);
    }

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        return playListRepository.findAll(pageable);
    }

    @Override
    public Page<Playlist> findByNameAlbumContaining(String nameAlbum, Pageable pageable) {
        return playListRepository.findByNameAlbumContaining(nameAlbum,pageable);
    }

    @Override
    public Page<Playlist> findByNameSingerContaining(String nameSinger, Pageable pageable) {
        return playListRepository.findByNameSingerContaining(nameSinger,pageable);
    }

    @Override
    public Page<Playlist> findByNameCategoryContaining(String nameCategory, Pageable pageable) {
        return playListRepository.findByNameCategoryContaining(nameCategory,pageable);
    }

    @Override
    public Page<Playlist> findByNameBandContaining(String nameBand, Pageable pageable) {
        return playListRepository.findByNameBandContaining(nameBand,pageable);
    }
}
