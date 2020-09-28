package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Karaoke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKaraokeRepository extends JpaRepository<Karaoke, Long> {

}
