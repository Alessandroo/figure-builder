package com.issoft.builder.figure.repository;

import com.issoft.builder.figure.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureRepository extends JpaRepository<Figure, Long> {

}
