package org.nju.demo.service.impl;

import org.nju.demo.dao.AVersionMapper;
import org.nju.demo.entity.AVersion;
import org.nju.demo.entity.AVersionExample;
import org.nju.demo.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private AVersionMapper versionMapper;

    @Override
    public List<AVersion> getVersionsByProjectId(int projectId) {

        AVersionExample versionExample = new AVersionExample();
        AVersionExample.Criteria criteria = versionExample.createCriteria();

        criteria.andProjectIdEqualTo(projectId);
        return versionMapper.selectByExample(versionExample);
    }

    @Override
    public AVersion getVersion(int id) {
        return versionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addVersion(AVersion version) {
        return versionMapper.insert(version);
    }

    @Override
    public int updateVersion(AVersion version) {
        return versionMapper.updateByPrimaryKeySelective(version);
    }

    @Override
    public int deleteVersionById(int id) {
        return versionMapper.deleteByPrimaryKey(id);
    }
}
