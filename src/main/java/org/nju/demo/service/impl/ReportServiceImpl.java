package org.nju.demo.service.impl;

import org.nju.demo.dao.ReportMapper;
import org.nju.demo.entity.Report;
import org.nju.demo.entity.ReportExample;
import org.nju.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Report> getReportListByUserId(int userId) {
        ReportExample example = new ReportExample();
        ReportExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);

        return reportMapper.selectByExample(example);
    }

    @Override
    public Report getReport(int id) {
        return reportMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addReport(Report report) {
        return reportMapper.insert(report);
    }
}
