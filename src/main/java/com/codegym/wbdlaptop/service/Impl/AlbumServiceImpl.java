package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Album;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.IAlbumRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    private IAlbumRepository albumRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public Boolean existsByNameAlbum(String nameAlbum) {
        return albumRepository.existsByNameAlbum(nameAlbum);
    }

    @Override
    public void delete(Long id) {
    albumRepository.deleteById(id);
    }

    @Override
    public Album save(Album album) {
        User user = userDetailsService.getCurrentUser();
        album.setUser(user);
        return albumRepository.save(album);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
