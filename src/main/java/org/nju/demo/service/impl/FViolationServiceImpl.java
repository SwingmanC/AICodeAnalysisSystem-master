package org.nju.demo.service.impl;

import org.nju.demo.dao.FViolationMapper;
import org.nju.demo.entity.FViolation;
import org.nju.demo.entity.FViolationExample;
import org.nju.demo.service.FViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FViolationServiceImpl implements FViolationService {

    @Autowired
    private FViolationMapper violationMapper;

    @Override
    public List<FViolation> getFViolationsByVersionId(int versionId) {

        FViolationExample violationExample = new FViolationExample();
        FViolationExample.Criteria criteria = violationExample.createCriteria();

        criteria.andVersionIdEqualTo(versionId);

        return violationMapper.selectByExample(violationExample);
    }

    @Override
    public List<FViolation> getTrueFViolations(int versionId) {

        FViolationExample violationExample = new FViolationExample();
        FViolationExample.Criteria criteria = violationExample.createCriteria();

        criteria.andVersionIdEqualTo(versionId).andStateNotEqualTo("false");

        return violationMapper.selectByExample(violationExample);
    }

    @Override
    public FViolation getFViolation(int id) {
        return violationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addFViolation(FViolation violation) {
        return violationMapper.insert(violation);
    }

    @Override
    public int updateFViolation(FViolation violation) {

        return violationMapper.updateByPrimaryKeySelective(violation);
    }
}
