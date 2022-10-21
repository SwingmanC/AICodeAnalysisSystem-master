package org.nju.demo.service.impl;

import org.nju.demo.dao.ProjectMapper;
import org.nju.demo.entity.Project;
import org.nju.demo.entity.ProjectExample;
import org.nju.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public int addProject(Project project) {
        return projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjects(int userId) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();

        criteria.andUserIdEqualTo(userId);
        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public List<Project> getProjectsByProjectName(int userId,String projectName) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();

        criteria.andProjectNameEqualTo(projectName);
        criteria.andUserIdEqualTo(userId);
        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public Project getProject(String projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public int deleteProject(String projectId) {
        return projectMapper.deleteByPrimaryKey(projectId);
    }
}
