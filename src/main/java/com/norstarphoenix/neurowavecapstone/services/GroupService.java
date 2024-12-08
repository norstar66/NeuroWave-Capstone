package com.norstarphoenix.neurowavecapstone.services;

import com.norstarphoenix.neurowavecapstone.models.Group;
import com.norstarphoenix.neurowavecapstone.repositories.GroupRepository;
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

    // Retrieve all groups
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    // Find a specific group by name
    public Group findGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    // Save a group (e.g., for creating new groups)
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }
}
