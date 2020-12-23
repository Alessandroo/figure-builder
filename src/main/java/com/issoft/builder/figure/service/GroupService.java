package com.issoft.builder.figure.service;

import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public Group updateGroup(Group group) {
        if (group.getId() < 1) {
            throw new IllegalArgumentException("Group has to have id.");
        }

        return groupRepository.save(group);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
