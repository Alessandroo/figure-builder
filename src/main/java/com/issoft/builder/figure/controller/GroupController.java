package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Figure;
import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.service.FigureService;
import com.issoft.builder.figure.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping
    public Group updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @PutMapping
    public Group saveGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @DeleteMapping
    public void deleteGroup(Long id) {
        groupService.deleteGroup(id);
    }
}
