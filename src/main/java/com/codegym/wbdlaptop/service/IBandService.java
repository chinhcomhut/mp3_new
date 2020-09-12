package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBandService {
    Boolean existsByNameBand(String nameBand);
    Page<Band> findAllByUserId(Long userId, Pageable pageable);
    void delete(Long id);
    Band save(Band band);
    Optional<Band> findById(Long id);
    Page<Band> findAll(Pageable pageable);
    List<Band> findAll();
}
