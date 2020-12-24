package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation("Add new group to exist after the specified position or at the end of the list.")
    @PutMapping("/addGroup")
    public Group addGroup(long parentGroup,
                          @RequestParam(required = false, defaultValue = "-1") int position,
                          @RequestBody Group group) {
        return groupService.addGroup(parentGroup, position, group);
    }

    @ApiOperation("Add new figure to exist group after the specified position or at the end of the list.")
    @PutMapping("/addFigure")
    public Figure addFigure(long parentGroup,
                            @RequestParam(required = false, defaultValue = "-1") int position,
                            @RequestBody Figure figure) {
        return groupService.addFigure(parentGroup, position, figure);
    }

    @ApiOperation("Change group position under same parent.")
    @PostMapping("/moveGroup")
    public void moveGroup(long parentGroup, int oldPosition, int newPosition) {
        groupService.moveGroup(parentGroup, oldPosition, newPosition);
    }

    @ApiOperation("Change figure position under same parent.")
    @PostMapping("/moveFigure")
    public void moveFigure(long parentGroup, int oldPosition, int newPosition) {
        groupService.moveFigure(parentGroup, oldPosition, newPosition);
    }

    @ApiOperation("Update group properties. Modification children properties is not allowed here.")
    @PostMapping
    public Group updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @ApiOperation("Delete group and its child elements.")
    @DeleteMapping
    public void deleteGroup(Long id) {
        groupService.deleteGroup(id);
    }
}
