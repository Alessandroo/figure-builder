package com.issoft.builder.figure.service;

import com.issoft.builder.figure.factory.FigureRepositoryFactory;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.repository.FigureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FigureService {
    private final FigureRepositoryFactory figureRepositoryFactory;

    public FigureService(FigureRepositoryFactory figureRepositoryFactory) {
        this.figureRepositoryFactory = figureRepositoryFactory;
    }

    public Figure updateFigure(@RequestBody Figure figure) {
        if (figure.getId() < 1) {
            throw new IllegalArgumentException("Figure has to have id.");
        }
        return saveFigure(figure);
    }

    public Figure saveFigure(Figure figure) {
        FigureRepository figureRepository = figureRepositoryFactory.findRepository(figure);
        return (Figure) figureRepository.save(figure);
    }

    public void deleteFigure(Figure figure) {
        FigureRepository figureRepository = figureRepositoryFactory.findRepository(figure);
        figureRepository.delete(figure);
    }
}
