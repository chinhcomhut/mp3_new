package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.HocLapTrinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IHocLapTrinhService {
    Page<HocLapTrinh> findAll(Pageable pageable);
    void delete(Long id);
    HocLapTrinh save(HocLapTrinh hocLapTrinh);
    Optional<HocLapTrinh> findById(Long id);
    Boolean existsByNameVideo(String nameVideo);
}
