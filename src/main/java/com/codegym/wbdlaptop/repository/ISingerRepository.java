package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Boolean existsByNameSinger(String nameSinger);
    Page<Singer> findAllByUserId(Long userId, Pageable pageable);
}
