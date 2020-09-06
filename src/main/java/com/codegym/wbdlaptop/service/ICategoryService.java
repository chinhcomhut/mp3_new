package com.codegym.wbdlaptop.service;

import com.codegym.wbdlaptop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Boolean existsByNameCategory(String nameCategory);
    Page<Category> findAllByUserId(Long userId, Pageable pageable);
    Category save(Category category);
    void delete(Long id);
    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable pageable);
    List<Category> findAll();
}
