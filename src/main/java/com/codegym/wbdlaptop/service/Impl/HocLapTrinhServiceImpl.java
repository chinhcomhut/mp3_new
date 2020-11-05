package com.codegym.wbdlaptop.service.Impl;

import com.codegym.wbdlaptop.model.HocLapTrinh;
import com.codegym.wbdlaptop.model.User;
import com.codegym.wbdlaptop.repository.HocLapTrinhRepository;
import com.codegym.wbdlaptop.security.service.UserDetailsServiceImpl;
import com.codegym.wbdlaptop.service.IHocLapTrinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HocLapTrinhServiceImpl implements IHocLapTrinhService {
    @Autowired
    HocLapTrinhRepository hocLapTrinhRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    public Page<HocLapTrinh> findAll(Pageable pageable) {
        return hocLapTrinhRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    hocLapTrinhRepository.deleteById(id);
    }

    @Override
    public HocLapTrinh save(HocLapTrinh hocLapTrinh) {
        User user = userDetailsService.getCurrentUser();
        hocLapTrinh.setUser(user);
        return hocLapTrinhRepository.save(hocLapTrinh);
    }

    @Override
    public Optional<HocLapTrinh> findById(Long id) {
        return hocLapTrinhRepository.findById(id);
    }

    @Override
    public Boolean existsByNameVideo(String nameVideo) {
        return hocLapTrinhRepository.existsByNameVideo(nameVideo);
    }
}
