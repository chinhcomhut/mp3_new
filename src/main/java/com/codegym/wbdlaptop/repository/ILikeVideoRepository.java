package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.LikeVideo;
import com.codegym.wbdlaptop.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeVideoRepository extends JpaRepository<LikeVideo, Long> {
    List<LikeVideo> findByUsernameContaining(String username);
}
