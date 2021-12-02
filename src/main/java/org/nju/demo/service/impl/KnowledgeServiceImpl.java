package org.nju.demo.service.impl;

import org.nju.demo.dao.KnowledgeMapper;
import org.nju.demo.entity.Knowledge;
import org.nju.demo.entity.KnowledgeExample;
import org.nju.demo.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public List<Knowledge> getKnowledgeList(int patternId) {
        KnowledgeExample example = new KnowledgeExample();
        KnowledgeExample.Criteria criteria = example.createCriteria();

        criteria.andPatternIdEqualTo(patternId);

        return knowledgeMapper.selectByExample(example);
    }

    @Override
    public Knowledge getKnowledge(int id) {
        return knowledgeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addKnowledge(Knowledge knowledge) {
        return knowledgeMapper.insert(knowledge);
    }

    @Override
    public int deleteKnowledge(int id) {
        return knowledgeMapper.deleteByPrimaryKey(id);
    }
}
