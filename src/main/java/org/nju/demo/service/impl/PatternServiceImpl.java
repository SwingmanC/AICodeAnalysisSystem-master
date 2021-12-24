package org.nju.demo.service.impl;

import org.nju.demo.dao.PatternInfoMapper;
import org.nju.demo.dao.PatternLkMapper;
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

    @Override
    public PatternInfo getPatternInfoByPatternName(String patternName) {
        PatternInfoExample example = new PatternInfoExample();
        PatternInfoExample.Criteria criteria = example.createCriteria();

        criteria.andPatternNameEqualTo(patternName);
        return patternInfoMapper.selectByExample(example).get(0);
    }

    @Override
    public PatternInfoWithBLOBs getPatternInfo(String patternId) {
        PatternInfoExample example = new PatternInfoExample();
        PatternInfoExample.Criteria criteria = example.createCriteria();

        criteria.andPatternIdEqualTo(patternId);

        List<PatternInfoWithBLOBs> patternInfoList = patternInfoMapper.selectByExampleWithBLOBs(example);
        if (patternInfoList.size() > 0)
            return patternInfoList.get(0);
        else return null;
    }

    @Override
    public PatternLk getPatternLikelihood(String patternId) {
        PatternLkExample example = new PatternLkExample();
        PatternLkExample.Criteria criteria = example.createCriteria();

        criteria.andPatternIdEqualTo(patternId);

        List<PatternLk> patternLkList = patternLkMapper.selectByExample(example);
        if (patternLkList.size() > 0)
            return patternLkList.get(0);
        else return null;
    }

    @Override
    public PatternInfoWithBLOBs getPattern(int id) {
        return patternInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PatternLk getPatternLk(int id) {
        return patternLkMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PatternLk> getPatternLkList() {
        PatternLkExample example = new PatternLkExample();
        return patternLkMapper.selectByExample(example);
    }

    @Override
    public List<PatternInfo> getPatternInfoList() {
        PatternInfoExample example = new PatternInfoExample();
        return patternInfoMapper.selectByExample(example);
    }

    @Override
    public int updatePatternLikelihood(PatternLk pattern) {
        return patternLkMapper.updateByPrimaryKeySelective(pattern);
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
