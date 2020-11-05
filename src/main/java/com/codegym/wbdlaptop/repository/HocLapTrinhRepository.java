package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.HocLapTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HocLapTrinhRepository extends JpaRepository<HocLapTrinh, Long> {
    Boolean existsByNameVideo(String nameVideo);
}
