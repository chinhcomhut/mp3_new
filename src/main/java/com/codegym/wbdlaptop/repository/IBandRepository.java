package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBandRepository extends JpaRepository<Band, Long> {
    Boolean existsByNameBand(String nameBand);
    Page<Band> findAllByUserId(Long userId, Pageable pageable);
}
