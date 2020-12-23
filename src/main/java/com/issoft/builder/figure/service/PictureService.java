package com.issoft.builder.figure.service;

import com.issoft.builder.figure.factory.FigureRepositoryFactory;
import com.issoft.builder.figure.model.Circle;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.model.Picture;
import com.issoft.builder.figure.model.Square;
import com.issoft.builder.figure.model.Triangle;
import com.issoft.builder.figure.repository.FigureRepository;
import com.issoft.builder.figure.repository.GroupRepository;
import com.issoft.builder.figure.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    private final FigureRepositoryFactory figureRepositoryFactory;

    private final GroupRepository groupRepository;

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(FigureRepositoryFactory figureRepositoryFactory,
                          GroupRepository groupRepository,
                          PictureRepository pictureRepository) {
        this.figureRepositoryFactory = figureRepositoryFactory;
        this.groupRepository = groupRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<Picture> getPictures() {
        return pictureRepository.findAll(Sort.by(Sort.Direction.DESC, "modificationDate"));
    }

    public List<String> getPictureNames() {
        return pictureRepository.getPictureNamesOrderedByModificationDate();
    }

    public Picture updatePicture(Picture picture) {
        if (picture.getId() < 1) {
            throw new IllegalArgumentException("Picture has to have id.");
        }

        return pictureRepository.save(picture);
    }

    public Picture savePicture(Picture picture) {
        Group rootGroup = picture.getGroup();
        saveGroup(rootGroup);
        return pictureRepository.save(picture);
    }

    public Group saveGroup(Group parentGroup) {
        parentGroup = groupRepository.save(parentGroup);
        if (parentGroup.getChildFigures() != null) {
            for (Figure figure : parentGroup.getChildFigures()) {
                figure.setGroup(parentGroup);
                FigureRepository figureRepository = figureRepositoryFactory.findRepository(figure);
                figureRepository.save(figure);
            }
        }
        if (parentGroup.getChildGroups() != null) {
            for (Group group : parentGroup.getChildGroups()) {
                group.setParent(parentGroup);
                saveGroup(group);
            }
        }
        return parentGroup;
    }

    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
