package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.service.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/figures")
public class FigureController {
    private final FigureService figureService;

    @Autowired
    public FigureController(FigureService figureService) {
        this.figureService = figureService;
    }

    @PostMapping
    public Figure updateFigure(@RequestBody Figure figure) {
        return figureService.updateFigure(figure);
    }

    @DeleteMapping
    public void deleteFigure(@RequestBody Figure figure) {
        figureService.deleteFigure(figure);
    }
}
