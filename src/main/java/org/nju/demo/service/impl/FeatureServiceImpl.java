package org.nju.demo.service.impl;

import org.nju.demo.config.Constants;
import org.nju.demo.dao.IssueBasicMapper;
import org.nju.demo.dao.PatternLkMapper;
import org.nju.demo.entity.IssueBasic;
import org.nju.demo.entity.PatternLk;
import org.nju.demo.pojo.vo.IssueFeature;
import org.nju.demo.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private PatternLkMapper patternLkMapper;

    @Override
    public List<IssueFeature> getIssueFeature(List<IssueBasic> issueBasicList) {
        List<IssueFeature> issueFeatureList = new ArrayList<>();

        Map<String,Integer> issueNumByMethod = countIssueNumMethod(issueBasicList);
        Map<String,Integer> issueNumByClass = countIssueNumClass(issueBasicList);

        for (IssueBasic issueBasic : issueBasicList){
            String fileName = issueBasic.getFileName();
            if (!fileName.endsWith("java")) continue;
            IssueFeature issueFeature = new IssueFeature();
            issueFeature.setIssueId(issueBasic.getIssueId());
            issueFeature.setPriority(countPriority(issueBasic.getPriority()));
            issueFeature.setKingdom(countKingdom(issueBasic.getKingdom()));
            issueFeature.setIssueNumMethod(issueNumByMethod.get(formatMethodName(issueBasic.getTargetFunction())));
            issueFeature.setIssueNumClass(issueNumByClass.get(formatFileName(issueBasic.getFileName())));
            issueFeature.setForCount(issueNumByClass.get(formatFileName(fileName)));
            issueFeature.setStartLine(issueBasic.getStartLine());
            issueFeature.setLineLength(countLineLength(issueBasic.getSnippet()));
            issueFeature.setPatternLikelihood(countPatternLikelihood(issueBasic.getPatternId()));
            issueFeature.setForCount(countForCount(issueBasic.getSnippet()));
            if (issueBasic.getState().equals("True")) issueFeature.setLabel(1);
            else issueFeature.setLabel(0);
            issueFeatureList.add(issueFeature);
        }

        return issueFeatureList;
    }

    @Override
    public int countPriority(String priority) {
        if (priority.equals(Constants.Priority.LOW)) return 1;
        else if (priority.equals(Constants.Priority.MEDIUM)) return 2;
        else if (priority.equals(Constants.Priority.HIGH)) return 3;
        else return 4;
    }

    @Override
    public int countKingdom(String kingdom) {
        String[] kingdoms = Constants.Type.fortify;
        for (int i=0;i<kingdoms.length;++i){
            if (kingdom.equals(kingdoms[i])) return i+1;
        }
        return 0;
    }

    @Override
    public Map<String, Integer> countIssueNumMethod(List<IssueBasic> issueBasicList) {
        Map<String,Integer> hm = new HashMap<>();

        for (IssueBasic issueBasic:issueBasicList){
            String fileName = issueBasic.getFileName();
            if (!fileName.endsWith("java")) continue;
            String methodName = formatMethodName(issueBasic.getTargetFunction());
            hm.put(methodName,hm.getOrDefault(methodName,0)+1);
        }

        return hm;
    }

    @Override
    public Map<String, Integer> countIssueNumClass(List<IssueBasic> issueBasicList) {
        Map<String,Integer> hm = new HashMap<>();

        for (IssueBasic issueBasic:issueBasicList){
            String fileName = issueBasic.getFileName();
            if (!fileName.endsWith("java")) continue;
            String className = formatFileName(fileName);
            hm.put(className,hm.getOrDefault(className,0)+1);
        }

        return hm;
    }

    @Override
    public int countLineLength(String snippet) {
        int res = 0;
        if (snippet == null) return 0;
        for (int i=0;i<snippet.length();++i){
            if (snippet.charAt(i)=='\n') res++;
        }
        return res+1;
    }

    @Override
    public int countForCount(String snippet) {
        if (snippet == null) return 0;
        String[] strs = snippet.split(" ");
        int res = 0;
        for (String s : strs){
            if (s.equals("for")) res++;
        }
        return res;
    }

    @Override
    public double countPatternLikelihood(String patternId) {
        PatternLk patternLk = patternLkMapper.selectByPrimaryKey(patternId);
        int trueNum = patternLk.gettNum();
        int falseNum = patternLk.getfNum();
        if (trueNum==0&&falseNum==0) return 0;
        return trueNum*1.0/(trueNum+falseNum);
    }

    @Override
    public boolean isValid(Character c) {
        if (c>='a'&&c<='z') return true;
        else if (c>='A'&&c<='Z') return true;
        else if (c>='0'&&c<='9') return true;
        else if (c=='_'||c=='$') return true;
        return false;
    }

    @Override
    public String formatMethodName(String methodName) {
        int index = methodName.indexOf("(");
        if (index==-1) return methodName;
        int start = index-1;
        for (;start>=0;--start){
            if (!isValid(methodName.charAt(start))) break;
        }
        return methodName.substring(start+1,index);
    }

    @Override
    public String formatFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(0,index);
    }
}
