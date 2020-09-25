package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Song;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.ISongRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongRepository songRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Boolean existsByNameSong(String nameSong) {
        return songRepository.existsByNameSong(nameSong);
    }

    @Override
    public Page<Song> findAllByUserId(Long userId, Pageable pageable) {
        return songRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Song> findByNameSingerContaining(String nameSinger, Pageable pageable) {
        return songRepository.findByNameSingerContaining(nameSinger, pageable);
    }

    @Override
    public Page<Song> findByNameCategoryContaining(String nameCategory, Pageable pageable) {
        return songRepository.findByNameCategoryContaining(nameCategory, pageable);
    }

    @Override
    public Song save(Song song) {
        User user = userDetailsService.getCurrentUser();
        song.setUser(user);
        return songRepository.save(song);
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findByNameSongContaining(String nameSong) {
        return songRepository.findByNameSongContaining(nameSong);
    }

    @Override
    public Page<Song> findByNameBandContaining(String nameBand, Pageable pageable) {
        return songRepository.findByNameBandContaining(nameBand, pageable);
    }

//    @Override
//    public Page<Song> findAllByNameSongOrderByLikeSongAsc(int likeSong, Pageable pageable) {
//        return songRepository.findAllByNameSongOrderByLikeSongAsc(likeSong,pageable);
//    }
}
