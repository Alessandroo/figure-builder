package com.issoft.builder.figure.repository;

import com.issoft.builder.figure.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FigureRepository<T extends Figure> extends JpaRepository<T, Long> {

}
