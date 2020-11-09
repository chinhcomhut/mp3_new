package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.LikeHocLapTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeHocLapTrinhRepository extends JpaRepository<LikeHocLapTrinh, Long> {
    List<LikeHocLapTrinh> findByUsernameContaining(String username);
}
