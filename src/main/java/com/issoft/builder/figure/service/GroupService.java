package com.issoft.builder.figure.service;

import com.issoft.builder.figure.factory.FigureRepositoryFactory;
import com.issoft.builder.figure.model.Element;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.repository.FigureRepository;
import com.issoft.builder.figure.repository.GroupRepository;
import com.issoft.builder.figure.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GroupService {
    private final FigureRepositoryFactory figureRepositoryFactory;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(FigureRepositoryFactory figureRepositoryFactory, GroupRepository groupRepository) {
        this.figureRepositoryFactory = figureRepositoryFactory;
        this.groupRepository = groupRepository;
    }

    public Group updateGroup(Group group) {
        if (group.getId() < 1) {
            throw new IllegalArgumentException("Group has to have id.");
        }

        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group addGroup(long parentGroupId, int position, Group group) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );
        if (position < 0) {
            position = Optional.ofNullable(parentGroup.getChildGroups())
                    .stream().flatMap(Collection::stream)
                    .map(Element::getPosition)
                    .mapToInt(value -> value).max().orElse(-1);
            position += 1;
        } else {
            int finalPosition = position;
            Optional.ofNullable(parentGroup.getChildGroups())
                    .stream().flatMap(Collection::stream)
                    .filter(element -> element.getPosition() >= finalPosition)
                    .forEach(Element::shiftRight);
        }

        group.setPosition(position);
        group.setParent(parentGroup);
        return saveGroup(group);

    }

    public Figure addFigure(long parentGroupId, int position, Figure figure) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );
        if (position < 0) {
            position = Optional.ofNullable(parentGroup.getChildFigures())
                    .stream().flatMap(Collection::stream)
                    .map(Element::getPosition)
                    .mapToInt(value -> value).max().orElse(-1);
            position += 1;
        } else {
            int finalPosition = position;
            Optional.ofNullable(parentGroup.getChildFigures())
                    .stream().flatMap(Collection::stream)
                    .filter(element -> element.getPosition() >= finalPosition)
                    .forEach(Element::shiftRight);
        }

        figure.setPosition(position);
        figure.setGroup(parentGroup);
        FigureRepository figureRepository = figureRepositoryFactory.findRepository(figure);
        return (Figure) figureRepository.save(figure);
    }

    public void moveGroup(long parentGroupId, int oldPosition, int newPosition) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );
        Group oldGroup = Optional.ofNullable(parentGroup.getChildGroups())
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() == oldPosition)
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("element to move is not exist")
                );
        Group newGroup = Optional.ofNullable(parentGroup.getChildGroups())
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() == newPosition)
                .findFirst().orElse(null);

        oldGroup.setPosition(newPosition);
        groupRepository.save(oldGroup);
        if (newGroup != null) {
            newGroup.setPosition(oldPosition);
            groupRepository.save(newGroup);
        }
    }

    public void moveFigure(long parentGroupId, int oldPosition, int newPosition) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );
        Figure figureToMove = Optional.ofNullable(parentGroup.getChildFigures())
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() == oldPosition)
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("element to move is not exist")
                );
        Figure figureInNewPosition = Optional.ofNullable(parentGroup.getChildFigures())
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() == newPosition)
                .findFirst().orElse(null);

        figureToMove.setPosition(newPosition);
        FigureRepository figureRepositoryForOldPosition = figureRepositoryFactory.findRepository(figureToMove);
        figureRepositoryForOldPosition.save(figureToMove);

        if (figureInNewPosition != null) {
            figureInNewPosition.setPosition(oldPosition);
            FigureRepository figureRepositoryForNewPosition = figureRepositoryFactory.findRepository(figureInNewPosition);
            figureRepositoryForNewPosition.save(figureInNewPosition);
        }
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
}
