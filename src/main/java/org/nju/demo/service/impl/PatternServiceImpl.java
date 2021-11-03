package org.nju.demo.service.impl;

import org.nju.demo.dao.PatternMapper;
import org.nju.demo.entity.Pattern;
import org.nju.demo.entity.PatternExample;
import org.nju.demo.service.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatternServiceImpl implements PatternService {

    @Autowired
    private PatternMapper patternMapper;

    @Override
    public Pattern getPatternByPatternName(String patternName) {
        PatternExample example = new PatternExample();
        PatternExample.Criteria criteria = example.createCriteria();

        criteria.andPatternNameEqualTo(patternName);

        return patternMapper.selectByExample(example).get(0);
    }

    @Override
    public int updatePattern(Pattern pattern) {
        return patternMapper.updateByPrimaryKey(pattern);
    }

    @Override
    public long countByCategoryId(int categoryId) {
        PatternExample example = new PatternExample();
        PatternExample.Criteria criteria = example.createCriteria();

        criteria.andCategoryIdEqualTo(categoryId);

        return patternMapper.countByExample(example);
    }
}
