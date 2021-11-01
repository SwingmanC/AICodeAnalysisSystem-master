package org.nju.demo.service;

import org.nju.demo.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> getReportListByUserId(int userId);

    Report getReport(int id);

    int addReport(Report report);

}
