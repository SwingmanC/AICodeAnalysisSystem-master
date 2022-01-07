package org.nju.demo.service.impl;

import org.nju.demo.dao.IssueBasicMapper;
import org.nju.demo.dao.IssueSourceMapper;
import org.nju.demo.entity.*;
import org.nju.demo.service.IssueService;
import org.nju.demo.utils.algorithm.Match;
import org.nju.demo.utils.algorithm.impl.ExactMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueBasicMapper issueBasicMapper;

    @Autowired
    private IssueSourceMapper issueSourceMapper;

    @Override
    public List<IssueBasic> getIssueList(String versionId,String priority,String kingdom,String state,int flag) {
        IssueBasicExample example = new IssueBasicExample();
        IssueBasicExample.Criteria criteria = example.createCriteria();

        criteria.andVersionIdEqualTo(versionId);
        if (priority.length() > 0) criteria.andPriorityEqualTo(priority);
        if (kingdom.length() > 0) criteria.andKingdomEqualTo(kingdom);
        if (state.length() > 0) criteria.andStateEqualTo(state);
        if (flag != -1) criteria.andFlagEqualTo(flag);

        return issueBasicMapper.selectByExample(example);
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
    public int addIssue(IssueBasic issueBasic) {
        return issueBasicMapper.insert(issueBasic);
    }

    @Override
    public int addSourceInfo(IssueSource issueSource) {
        return issueSourceMapper.insert(issueSource);
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
                    lastIssue.setState("False");
                    break;
                }
            }
            if (flag == 0) continue;
            else{
                for(IssueBasic issueBasic:issueBasicList){
                    if (lastIssue.getFilePath().equals(issueBasic.getFilePath())){
                        flag--;
                        lastIssue.setState("True");
                        break;
                    }
                }
            }
            if (flag == 1) lastIssue.setState("Unknown");
        }
        for(IssueBasic lastIssue:lastIssueList) updateIssue(lastIssue);
    }
}
