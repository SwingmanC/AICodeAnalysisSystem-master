package org.nju.demo.service;

import org.nju.demo.entity.IssueBasic;
import org.nju.demo.pojo.vo.IssueFeature;

import java.util.List;
import java.util.Map;

public interface FeatureService {

    List<IssueFeature> getIssueFeature(List<IssueBasic> issueBasicList);

    int countPriority(String priority);

    int countKingdom(String kingdom);

    Map<String,Integer> countIssueNumMethod(List<IssueBasic> issueBasicList);

    Map<String,Integer> countIssueNumClass(List<IssueBasic> issueBasicList);

    int countLineLength(String snippet);

    int countForCount(String snippet);

    double countPatternLikelihood(String patternId);

    boolean isValid(Character c);

    String formatMethodName(String methodName);

    String formatFileName(String fileName);
}
