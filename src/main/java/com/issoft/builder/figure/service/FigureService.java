package com.issoft.builder.figure.service;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.repository.FigureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FigureService {
    private final FigureRepository figureRepository;

    public FigureService(FigureRepository figureRepository) {
        this.figureRepository = figureRepository;
    }

    public Figure updateFigure(Figure figure) {
        Figure figureToUpdate = figureRepository.findById(figure.getId()).orElseThrow(
                () -> new IllegalArgumentException("Figure is not exist")
        );
        BeanUtils.copyProperties(figure, figureToUpdate, "group");

        return figureRepository.save(figureToUpdate);
    }

    public void deleteFigure(long id) {
        figureRepository.deleteById(id);
    }
}
