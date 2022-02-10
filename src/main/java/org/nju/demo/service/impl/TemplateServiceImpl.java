package org.nju.demo.service.impl;

import org.nju.demo.dao.ATemplateMapper;
import org.nju.demo.entity.ATemplate;
import org.nju.demo.entity.ATemplateExample;
import org.nju.demo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private ATemplateMapper templateMapper;

    @Override
    public List<ATemplate> getTemplateListByUserId(int userId) {
        ATemplateExample example = new ATemplateExample();
        ATemplateExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);

        return templateMapper.selectByExample(example);
    }

    @Override
    public ATemplate getTemplate(String templateId) {
        return templateMapper.selectByPrimaryKey(templateId);
    }

    @Override
    public ATemplate getUsedTemplate(int userId) {
        ATemplateExample example = new ATemplateExample();
        ATemplateExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);
        criteria.andStateEqualTo(1);

        List<ATemplate> templateList = templateMapper.selectByExample(example);
        if (templateList.size() != 0) return templateList.get(0);
        else return null;
    }

    @Override
    public int addTemplate(ATemplate template) {
        return templateMapper.insert(template);
    }

    @Override
    public int updateTemplate(ATemplate template) {
        return templateMapper.updateByPrimaryKey(template);
    }

    @Override
    public int deleteTemplate(String templateId) {
        return templateMapper.deleteByPrimaryKey(templateId);
    }
}
