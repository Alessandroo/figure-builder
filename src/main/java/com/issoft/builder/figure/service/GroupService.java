package com.issoft.builder.figure.service;

import com.issoft.builder.figure.model.Element;
import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.repository.FigureRepository;
import com.issoft.builder.figure.repository.GroupRepository;
import com.issoft.builder.figure.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final FigureRepository figureRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(FigureRepository figureRepository,
                        GroupRepository groupRepository) {
        this.figureRepository = figureRepository;
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
            position = calculateLastPosition(parentGroup.getChildGroups()) + 1;
        } else {
            shiftElementsAfterElementToInsert(parentGroup.getChildGroups(), position);
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
            position = calculateLastPosition(parentGroup.getChildFigures()) + 1;
        } else {
            shiftElementsAfterElementToInsert(parentGroup.getChildFigures(), position);
        }

        figure.setPosition(position);
        figure.setGroup(parentGroup);
        return figureRepository.save(figure);
    }

    private int calculateLastPosition(List<? extends Element> elements) {
        return Optional.ofNullable(elements)
                .stream().flatMap(Collection::stream)
                .map(Element::getPosition)
                .mapToInt(value -> value).max().orElse(-1);
    }

    private void shiftElementsAfterElementToInsert(List<? extends Element> elements, int position) {
        Optional.ofNullable(elements)
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() >= position)
                .forEach(Element::shiftRight);
    }

    public void moveGroup(long parentGroupId, int oldPosition, int newPosition) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );

        Group groupToMove = (Group) moveElement(parentGroup.getChildFigures(), oldPosition, newPosition);
        groupRepository.save(groupToMove);
    }

    public void moveFigure(long parentGroupId, int oldPosition, int newPosition) {
        Group parentGroup = groupRepository.findById(parentGroupId).orElseThrow(
                () -> new IllegalArgumentException("parent group is not exist")
        );

        Figure figureToMove = (Figure) moveElement(parentGroup.getChildFigures(), oldPosition, newPosition);
        figureRepository.save(figureToMove);
    }

    private Element moveElement(List<? extends Element> elements, int oldPosition, int newPosition) {
        Element figureToMove = Optional.ofNullable(elements)
                .stream().flatMap(Collection::stream)
                .filter(element -> element.getPosition() == oldPosition)
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("element to move is not exist")
                );

        shiftElementsAfterElementToInsert(elements, newPosition);
        figureToMove.setPosition(newPosition);

        return figureToMove;
    }

    public Group saveGroup(Group parentGroup) {
        parentGroup = groupRepository.save(parentGroup);

        for (ListUtils.EnumeratedItem<Figure> figureItem : ListUtils.enumerate(parentGroup.getChildFigures())) {
            Figure figure = figureItem.item;
            figure.setGroup(parentGroup);
            figure.setPosition(figureItem.index);

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
