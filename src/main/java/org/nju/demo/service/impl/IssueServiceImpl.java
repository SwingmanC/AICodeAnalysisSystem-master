package org.nju.demo.service.impl;

import org.nju.demo.config.Constants;
import org.nju.demo.dao.IssueBasicMapper;
import org.nju.demo.dao.IssueSourceMapper;
import org.nju.demo.dao.VersionPatternRelMapper;
import org.nju.demo.entity.*;
import org.nju.demo.pojo.dto.IssueDTO;
import org.nju.demo.pojo.vo.IssueItem;
import org.nju.demo.pojo.vo.ProblemItem;
import org.nju.demo.service.IssueService;
import org.nju.demo.utils.algorithm.Match;
import org.nju.demo.utils.algorithm.impl.ExactMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueBasicMapper issueBasicMapper;

    @Autowired
    private IssueSourceMapper issueSourceMapper;

    @Autowired
    private VersionPatternRelMapper versionPatternRelMapper;

    @Override
    public List<IssueBasic> getIssueList(String versionId,String priority,String patternId,String state,int flag) {
        IssueBasicExample example = new IssueBasicExample();
        IssueBasicExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId);
        if (priority.length() > 0) criteria.andPriorityEqualTo(priority);
        if (patternId.length() > 0) criteria.andPatternIdEqualTo(patternId);
        if (state.length() > 0) criteria.andStateEqualTo(state);
        if (flag != Constants.IsFilter.IGNORE) criteria.andFlagEqualTo(flag);

        return issueBasicMapper.selectByExample(example);
    }

    @Override
    public List<IssueDTO> getIssueItemList(String versionId, String priority,int flag) {
        return issueBasicMapper.selectIssueByVersionIdAndPriority(versionId,priority,flag);
    }

    @Override
    public List<IssueBasic> getIssueListByPatternId(String versionId, String patternId) {
        IssueBasicExample example = new IssueBasicExample();
        IssueBasicExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId)
                .andPatternIdEqualTo(patternId);

        return issueBasicMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public IssueBasic getIssue(String issueId) {
        return issueBasicMapper.selectByPrimaryKey(issueId);
    }

    @Override
    public IssueSource getSourceInfo(String issueId) {
        IssueSourceExample example = new IssueSourceExample();
        IssueSourceExample.Criteria criteria = example.createCriteria();

        criteria.andIssueBasicIdEqualTo(issueId);

        List<IssueSource> issueSourceList = issueSourceMapper.selectByExample(example);
        if (issueSourceList.size() > 0) return issueSourceList.get(0);
        else return null;
    }

    @Override
    public int countTrueIssueByPriority(String versionId, String priority) {
        IssueBasicExample example = new IssueBasicExample();
        IssueBasicExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId)
                .andPriorityEqualTo(priority)
                .andStateEqualTo(Constants.IssueState.TRUE);

        return (int)issueBasicMapper.countByExample(example);
    }

    @Override
    public int countTrueIssueByPattern(String versionId, String patternId) {
        IssueBasicExample example = new IssueBasicExample();
        IssueBasicExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId)
                .andPatternIdEqualTo(patternId)
                .andStateEqualTo(Constants.IssueState.TRUE);

        return (int)issueBasicMapper.countByExample(example);
    }

    @Override
    public int addIssue(IssueBasic issueBasic) {
        return issueBasicMapper.insert(issueBasic);
    }

    @Override
    public int addSourceInfo(IssueSource issueSource) {
        return issueSourceMapper.insert(issueSource);
    }

    @Override
    public int addRelation(VersionPatternRel versionPatternRel) {
        VersionPatternRel last = versionPatternRelMapper.selectLastRecord();
        if(last==null) versionPatternRel.setId(1);
        else versionPatternRel.setId(last.getId()+1);
        versionPatternRelMapper.insert(versionPatternRel);
        return versionPatternRel.getId();
    }

    @Override
    public int updateIssue(IssueBasic issueBasic) {
        return issueBasicMapper.updateByPrimaryKeySelective(issueBasic);
    }

    @Override
    public void compare(List<IssueBasic> lastIssueList, List<IssueBasic> issueBasicList) {
        Match match = new ExactMatch();
        for (IssueBasic lastIssue:lastIssueList){
            int flag = 1;
            for (IssueBasic issueBasic:issueBasicList){
                if (match.mark(issueBasic,lastIssue) == 0){
                    flag--;
                    lastIssue.setState(Constants.IssueState.FALSE);
                    break;
                }
            }
            if (flag == 0) continue;
            else{
                for(IssueBasic issueBasic:issueBasicList){
                    if (lastIssue.getFilePath().equals(issueBasic.getFilePath())){
                        flag--;
                        lastIssue.setState(Constants.IssueState.TRUE);
                        break;
                    }
                }
            }
            if (flag == 1) lastIssue.setState(Constants.IssueState.UNKNOWN);
        }
        for(IssueBasic lastIssue:lastIssueList) updateIssue(lastIssue);
    }

    @Override
    public List<ProblemItem> getProblemItemList(List<IssueDTO> issueList) {
        List<ProblemItem> problemItemList = new ArrayList<>();
        Map<String,List<IssueItem>> hm = new HashMap<>();

        for (IssueDTO issue : issueList){
            IssueItem issueItem = new IssueItem();
            issueItem.setIssueId(issue.getIssueId());
            issueItem.setFileName(issue.getFileName());
            issueItem.setKingdom(issue.getKingdom());
            issueItem.setStartLine(issue.getStartLine());
            issueItem.setState(issue.getState());
            issueItem.setTargetFunction(issue.getTargetFunction());
            List<IssueItem> issueItemList = null;
            if (hm.containsKey(issue.getPatternName())) issueItemList = hm.get(issue.getPatternName());
            else issueItemList = new ArrayList<>();
            issueItemList.add(issueItem);
            hm.put(issue.getPatternName(),issueItemList);
        }

        for(Map.Entry<String,List<IssueItem>> entry : hm.entrySet()){
            ProblemItem problemItem = new ProblemItem();
            problemItem.setPatternName(entry.getKey());
            problemItem.setIssueItemList(entry.getValue());
            problemItem.setIssueNum();
            problemItemList.add(problemItem);
        }

        return problemItemList;
    }
}
