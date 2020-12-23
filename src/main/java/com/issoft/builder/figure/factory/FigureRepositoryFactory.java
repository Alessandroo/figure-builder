package com.issoft.builder.figure.factory;

import com.issoft.builder.figure.model.Circle;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Square;
import com.issoft.builder.figure.model.Triangle;
import com.issoft.builder.figure.repository.CircleRepository;
import com.issoft.builder.figure.repository.FigureRepository;
import com.issoft.builder.figure.repository.SquareRepository;
import com.issoft.builder.figure.repository.TriangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FigureRepositoryFactory {
    private final CircleRepository circleRepository;

    private final SquareRepository squareRepository;

    private final TriangleRepository triangleRepository;

    @Autowired
    public FigureRepositoryFactory(CircleRepository circleRepository,
                                   SquareRepository squareRepository,
                                   TriangleRepository triangleRepository) {
        this.circleRepository = circleRepository;
        this.squareRepository = squareRepository;
        this.triangleRepository = triangleRepository;
    }

    public FigureRepository findRepository(Figure figure) {
        if (figure instanceof Circle) {
            return circleRepository;
        }
        if (figure instanceof Square) {
            return squareRepository;
        }
        if (figure instanceof Triangle) {
            return triangleRepository;
        }
        throw new IllegalArgumentException("Figure has undefined type.");
    }
}
