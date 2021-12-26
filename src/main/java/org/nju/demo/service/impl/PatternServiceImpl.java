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
    public List<PatternLk> getPatternLkList() {
        PatternLkExample example = new PatternLkExample();
        return patternLkMapper.selectByExample(example);
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
