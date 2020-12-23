package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PutMapping("/addGroup")
    public Group addGroup(long parentGroup,
                          @RequestParam(required = false, defaultValue = "-1") int position,
                          @RequestBody Group group) {
        return groupService.addGroup(parentGroup, position, group);
    }

    @PutMapping("/addFigure")
    public Figure addFigure(long parentGroup,
                            @RequestParam(required = false, defaultValue = "-1") int position,
                            @RequestBody Figure figure) {
        return groupService.addFigure(parentGroup, position, figure);
    }

    @PostMapping("/moveGroup")
    public void moveGroup(long parentGroup, int oldPosition, int newPosition) {
        groupService.moveGroup(parentGroup, oldPosition, newPosition);
    }

    @PostMapping("/moveFigure")
    public void moveFigure(long parentGroup, int oldPosition, int newPosition) {
        groupService.moveFigure(parentGroup, oldPosition, newPosition);
    }

    @PostMapping
    public Group updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping
    public void deleteGroup(Long id) {
        groupService.deleteGroup(id);
    }
}
