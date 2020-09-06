package com.codegym.wbdlaptop.repository;

import com.codegym.wbdlaptop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByNameCategory(String nameCategory);
    Page<Category> findAllByUserId(Long userId, Pageable pageable);
}
