package org.nju.demo.service;

import org.nju.demo.entity.Knowledge;

import java.util.List;

public interface KnowledgeService {

    List<Knowledge> getKnowledgeList(int patternId);

    Knowledge getKnowledge(int id);

    int addKnowledge(Knowledge knowledge);

    int deleteKnowledge(int id);

}
