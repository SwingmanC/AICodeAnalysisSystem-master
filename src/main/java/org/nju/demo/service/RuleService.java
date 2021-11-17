package org.nju.demo.service;

import org.nju.demo.entity.ARule;

import java.util.List;

public interface RuleService {

    List<ARule> getRules(int userId);

    ARule getRule(int id);

    List<ARule> getUsedRules(int userId);

    int addRule(ARule rule);

    int updateRule(ARule rule);

    int deleteRule(int id);
}
