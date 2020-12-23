package com.issoft.builder.figure.service;

import com.issoft.builder.figure.factory.FigureRepositoryFactory;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.model.Picture;
import com.issoft.builder.figure.repository.FigureRepository;
import com.issoft.builder.figure.repository.GroupRepository;
import com.issoft.builder.figure.repository.PictureRepository;
import com.issoft.builder.figure.utils.ListUtils;
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

        for (ListUtils.EnumeratedItem<Figure> figureItem : ListUtils.enumerate(parentGroup.getChildFigures())) {
            Figure figure = figureItem.item;
            figure.setGroup(parentGroup);
            figure.setPosition(figureItem.index);

            FigureRepository figureRepository = figureRepositoryFactory.findRepository(figure);
            figureRepository.save(figure);
        }

        for (ListUtils.EnumeratedItem<Group> groupItem : ListUtils.enumerate(parentGroup.getChildGroups())) {
            Group group = groupItem.item;
            group.setParent(parentGroup);
            group.setPosition(groupItem.index);

            saveGroup(group);
        }

        return parentGroup;
    }

    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
