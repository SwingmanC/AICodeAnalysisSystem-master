package org.nju.demo.service;

import org.nju.demo.entity.Project;

import java.util.List;

public interface ProjectService {

    int addProject(Project project);

    List<Project> getProjects(int userId);

    Project getProject(String projectId);

    int deleteProject(String projectId);

}
