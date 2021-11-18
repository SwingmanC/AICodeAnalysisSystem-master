package org.nju.demo.controller;

import org.nju.demo.entity.*;
import org.nju.demo.service.FIssueService;
import org.nju.demo.service.ReportService;
import org.nju.demo.service.RuleService;
import org.nju.demo.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private FIssueService fIssueService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private HttpSession session;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    @RequestMapping("/view/reports")
    public String viewReports(){
        return "report_list";
    }

    @RequestMapping("/view/issue/{reportId}")
    public String viewIssue(@PathVariable("reportId") int reportId){
        Report report = reportService.getReport(reportId);
        session.setAttribute("report",report);
        return "issue_list";
    }

    @RequestMapping("/view/detail/{issueId}")
    public String viewDetail(@PathVariable("issueId") int issueId,
                             Model model){
        FIssueWithBLOBs issue = fIssueService.getIssueByIssueId(issueId);
        model.addAttribute("issue",issue);
        return "issue_detail";
    }

    @ResponseBody
    @RequestMapping("/reports")
    public List<Report> getReports(){
        AUser user = (AUser) session.getAttribute("user");
        return reportService.getReportListByUserId(user.getId());
    }

    @RequestMapping("/addReport")
    public String addReport(@RequestParam("reportName") String reportName,
                            @RequestParam("reportFile") MultipartFile reportFile) throws IOException, ParserConfigurationException, SAXException {
        Report report = new Report();
        AUser user = (AUser) session.getAttribute("user");

        if (reportFile != null){
            String fileName = reportFile.getOriginalFilename();
            String filePath = UPLOADED_FOLDER+"/report/"+user.getUsername()+"/";
            File file =  new File(filePath);
            if (!file.exists()) file.mkdirs();
            filePath += fileName;
            file = new File(filePath);
            reportFile.transferTo(file);
        }
        report.setReportName(reportName);
        report.setFilePath("report/"+user.getUsername()+"/"+reportFile.getOriginalFilename());
        report.setUploadTime(new Date());
        report.setUserId(user.getId());
        reportService.addReport(report);
        return "redirect:/view/reports";
    }

    @ResponseBody
    @RequestMapping("/issues")
    public List<FIssue> getIssues() throws IOException, SAXException, ParserConfigurationException {
        Report report = (Report) session.getAttribute("report");
        if (fIssueService.countByReportId(report.getId()) == 0){
            InputStream inputStream = new FileInputStream(UPLOADED_FOLDER+"/"+report.getFilePath());
            List<FIssueWithBLOBs> issueWithBLOBsList = XMLUtil.getIssues(inputStream);
            for(FIssueWithBLOBs fIssueWithBLOBs : issueWithBLOBsList){
                fIssueWithBLOBs.setReportId(report.getId());
                fIssueService.addIssue(fIssueWithBLOBs);
            }
        }
        AUser user = (AUser) session.getAttribute("user");
        List<FIssue> issueList = fIssueService.getIssueByReportId(report.getId());
        List<ARule> ruleList = ruleService.getUsedRules(user.getId());
        List<FIssue> res = new ArrayList<>();
        for(FIssue issue:issueList){
            int flag = 1;
            for (ARule rule : ruleList){
                if (issue.getCategory().equals(rule.getPattern())||
                    issue.getKingdom().equals(rule.getCategory())||
                    issue.getPriority().equals(rule.getPriority())||
                    issue.getFileName().equals(rule.getFileName())||
                    issue.getTargetFunction().equals(rule.getFunctionName())){
                    flag--;
                    break;
                }
            }
            if (flag == 1) res.add(issue);
        }
        return res;
    }

}
