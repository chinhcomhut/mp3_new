package com.codegym.wbdlaptop.service;

import java.util.Optional;

public interface ILineService {
    Optional<Line> findById(Long id);

    Iterable<Line> findAll();

    Line save(Line line);

    void delete(Long id);

    Iterable<Line> findLinesByNameContaining(String line_name);
}
