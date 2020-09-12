package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.Band;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.IBandRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BandServiceImpl implements IBandService {
    @Autowired
    private IBandRepository bandRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public Boolean existsByNameBand(String nameBand) {
        return bandRepository.existsByNameBand(nameBand);
    }

    @Override
    public Page<Band> findAllByUserId(Long userId, Pageable pageable) {
        return bandRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public void delete(Long id) {
bandRepository.deleteById(id);
    }

    @Override
    public Band save(Band band) {
        User user = userDetailsService.getCurrentUser();
        band.setUser(user);
        return bandRepository.save(band);
    }

    @Override
    public Optional<Band> findById(Long id) {
        return bandRepository.findById(id);
    }

    @Override
    public Page<Band> findAll(Pageable pageable) {
        return bandRepository.findAll(pageable);
    }

    @Override
    public List<Band> findAll() {
        return bandRepository.findAll();
    }
}
