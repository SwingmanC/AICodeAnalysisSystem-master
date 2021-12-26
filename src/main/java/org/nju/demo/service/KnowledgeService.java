package org.nju.demo.service;

import org.nju.demo.entity.Knowledge;

import java.util.List;

public interface KnowledgeService {

    List<Knowledge> getKnowledgeList(String patternId);

    Knowledge getKnowledge(String knowledgeId);

    int addKnowledge(Knowledge knowledge);

    int deleteKnowledge(String knowledgeId);

}
