package com.issoft.builder.figure.service;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.repository.FigureRepository;
import org.springframework.stereotype.Service;

@Service
public class FigureService {
    private final FigureRepository figureRepository;

    public FigureService(FigureRepository figureRepository) {
        this.figureRepository = figureRepository;
    }

    public Figure updateFigure(Figure figure) {
        if (figure.getId() < 1) {
            throw new IllegalArgumentException("Figure has to have id.");
        }
        return saveFigure(figure);
    }

    public Figure saveFigure(Figure figure) {
        return figureRepository.save(figure);
    }

    public void deleteFigure(Figure figure) {
        figureRepository.deleteById(figure.getId());
    }
}
