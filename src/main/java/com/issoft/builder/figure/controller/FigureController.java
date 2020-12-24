package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.service.FigureService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Update figure properties.")
    @PostMapping
    public Figure updateFigure(@RequestBody Figure figure) {
        return figureService.updateFigure(figure);
    }

    @ApiOperation("Delete figure from group.")
    @DeleteMapping
    public void deleteFigure(long id) {
        figureService.deleteFigure(id);
    }
}
