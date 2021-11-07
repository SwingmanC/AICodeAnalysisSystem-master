package org.nju.demo.service;

import org.nju.demo.entity.ATemplate;

import java.util.List;

public interface TemplateService {

    List<ATemplate> getTemplateListByUserId(int userId);

    ATemplate getTemplate(int id);

    ATemplate getUsedTemplate(int userId);

    int addTemplate(ATemplate template);

    int updateTemplate(ATemplate template);
}
