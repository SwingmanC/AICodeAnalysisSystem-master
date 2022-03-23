package org.nju.demo.service.impl;

import org.nju.demo.dao.PatternInfoMapper;
import org.nju.demo.dao.PatternLkMapper;
import org.nju.demo.dao.VersionPatternRelMapper;
import org.nju.demo.entity.*;
import org.nju.demo.service.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatternServiceImpl implements PatternService {

    @Autowired
    private PatternInfoMapper patternInfoMapper;

    @Autowired
    private PatternLkMapper patternLkMapper;

    @Autowired
    private VersionPatternRelMapper versionPatternRelMapper;

    @Override
    public PatternInfoWithBLOBs getPatternInfoByPatternLkId(String patternLkId) {
        PatternInfoExample example = new PatternInfoExample();
        PatternInfoExample.Criteria criteria = example.createCriteria();

        criteria.andPatternLkIdEqualTo(patternLkId);

        List<PatternInfoWithBLOBs> patternInfoList = patternInfoMapper.selectByExampleWithBLOBs(example);
        if (patternInfoList.size() > 0)
            return patternInfoList.get(0);
        else return null;
    }

    @Override
    public PatternInfoWithBLOBs getPatternInfo(String patternInfoId) {
        return patternInfoMapper.selectByPrimaryKey(patternInfoId);
    }

    @Override
    public PatternLk getPatternLk(String patternLkId) {
        return patternLkMapper.selectByPrimaryKey(patternLkId);
    }

    @Override
    public PatternLk getPatternByPatternName(String patternName) {
        PatternLkExample example = new PatternLkExample();
        PatternLkExample.Criteria criteria = example.createCriteria();

        criteria.andPatternNameEqualTo(patternName);

        List<PatternLk> patternLkList = patternLkMapper.selectByExample(example);
        if (patternLkList.size() > 0)
            return patternLkList.get(0);
        else return null;
    }

    @Override
    public List<PatternLk> getPatternLkList() {
        PatternLkExample example = new PatternLkExample();
        return patternLkMapper.selectByExample(example);
    }

    @Override
    public List<PatternLk> getPatternListByKeyword(String keyword) {
        return patternLkMapper.selectByKeyword(keyword);
    }

    @Override
    public List<PatternLk> getFalsePatternList() {
        PatternLkExample example = new PatternLkExample();
        PatternLkExample.Criteria criteria = example.createCriteria();

        criteria.andFNumGreaterThan(0);

        return patternLkMapper.selectByExample(example);
    }

    @Override
    public int addRelation(VersionPatternRel versionPatternRel) {
        return versionPatternRelMapper.insert(versionPatternRel);
    }

    @Override
    public int updatePatternLikelihood(PatternLk pattern) {
        return patternLkMapper.updateByPrimaryKeySelective(pattern);
    }

    @Override
    public int updatePatternInfo(PatternInfoWithBLOBs patternInfo) {
        return patternInfoMapper.updateByPrimaryKeyWithBLOBs(patternInfo);
    }

    @Override
    public int addPatternInfo(PatternInfoWithBLOBs patternInfo) {
        return patternInfoMapper.insert(patternInfo);
    }

    @Override
    public int addPatternLk(PatternLk patternLk) {
        return patternLkMapper.insert(patternLk);
    }
}
