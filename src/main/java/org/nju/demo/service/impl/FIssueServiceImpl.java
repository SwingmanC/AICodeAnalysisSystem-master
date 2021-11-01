package org.nju.demo.service.impl;

import org.nju.demo.dao.FIssueMapper;
import org.nju.demo.entity.FIssue;
import org.nju.demo.entity.FIssueExample;
import org.nju.demo.entity.FIssueWithBLOBs;
import org.nju.demo.service.FIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FIssueServiceImpl implements FIssueService {

    @Autowired
    private FIssueMapper fIssueMapper;

    @Override
    public long countByReportId(int reportId) {
        FIssueExample example = new FIssueExample();
        FIssueExample.Criteria criteria =  example.createCriteria();

        criteria.andReportIdEqualTo(reportId);

        return fIssueMapper.countByExample(example);
    }

    @Override
    public List<FIssue> getIssueByReportId(int reportId) {
        FIssueExample example = new FIssueExample();
        FIssueExample.Criteria criteria =  example.createCriteria();

        criteria.andReportIdEqualTo(reportId);

        return fIssueMapper.selectByExample(example);
    }

    @Override
    public FIssueWithBLOBs getIssueByIssueId(int issueId) {
        return fIssueMapper.selectByPrimaryKey(issueId);
    }

    @Override
    public int addIssue(FIssueWithBLOBs issue) {
        return fIssueMapper.insert(issue);
    }
}
