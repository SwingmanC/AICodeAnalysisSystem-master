package org.nju.demo.controller;

import freemarker.template.TemplateException;
import org.nju.demo.entity.*;
import org.nju.demo.pojo.dto.IssueInfoDO;
import org.nju.demo.pojo.dto.IssueSourceDO;
import org.nju.demo.pojo.dto.PatternInfoDO;
import org.nju.demo.pojo.vo.IssueDocVO;
import org.nju.demo.pojo.vo.IssueVO;
import org.nju.demo.pojo.vo.PatternDocVO;
import org.nju.demo.service.*;
import org.nju.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private PatternService patternService;
    
    @Autowired
    private IssueService issueService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private HttpSession session;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    @RequestMapping("/view/projects")
    public String viewProjects(){
        return "project_list";
    }

    @RequestMapping("/view/versions/{projectId}")
    public String viewVersions(@PathVariable("projectId") String projectId,
                               Model model){
        Project project = projectService.getProject(projectId);
        List<AVersion> versionList = versionService.getVersionsByProjectId(projectId);
        System.out.println(project.getProjectName());
        session.setAttribute("project",project);
        if (versionList.size() != 0)
            model.addAttribute("versionList",versionList);
        return "version_list";
    }

    @RequestMapping("/back_versions")
    public String backVersions(Model model){
        Project project = (Project) session.getAttribute("project");
        List<AVersion> versionList = versionService.getVersionsByProjectId(project.getProjectId());
        if (versionList.size() != 0)
            model.addAttribute("versionList",versionList);
        return "version_list";
    }

    @RequestMapping("/view/issues/{versionId}")
    public String viewIssues(@PathVariable("versionId") String versionId){
        AVersion version = versionService.getVersion(versionId);
        System.out.println(version.getVersionName());
        session.setAttribute("version",version);
        return "issue_list";
    }

    @ResponseBody
    @RequestMapping("/projects")
    public List<Project> getProjects(){
        AUser user = (AUser) session.getAttribute("user");
        return projectService.getProjects(user.getId());
    }

    @ResponseBody
    @RequestMapping("/versions")
    public List<AVersion> getVersions(){
        Project project = (Project) session.getAttribute("project");
        return versionService.getVersionsByProjectId(project.getProjectId());
    }

    @ResponseBody
    @RequestMapping("/issues/{flag}")
    public List<IssueVO> getIssueList(@PathVariable("flag") int flag,
                                      @RequestParam(value = "priority",required = false) String priority,
                                      @RequestParam(value = "kingdom",required = false) String kingdom){
        AVersion version = (AVersion) session.getAttribute("version");
        if (priority == null) priority = "";
        if (kingdom == null) kingdom = "";
        List<IssueBasic> issueBasicList = issueService.getIssueList(version.getVersionId(),priority,kingdom,flag);
        List<IssueVO> issueVOList = new ArrayList<>();
        for(IssueBasic issueBasic : issueBasicList){
            IssueVO issueVO = new IssueVO();
            PatternLk patternLk = patternService.getPatternLk(issueBasic.getPatternId());
            issueVO.setIssueId(issueBasic.getIssueId());
            issueVO.setPatternName(patternLk.getPatternName());
            issueVO.setKingdom(issueBasic.getKingdom());
            issueVO.setPriority(issueBasic.getPriority());

            int trueNum = patternLk.gettNum();
            int falseNum = patternLk.getfNum();

            if (trueNum == 0 && falseNum == 0) issueVO.setLikelihood(0);
            else{
                double likelihood = trueNum*1.0/(trueNum+falseNum);

                issueVO.setLikelihood(likelihood);
                issueVO.setState(issueBasic.getState());
            }

            issueVOList.add(issueVO);
        }
        return issueVOList;
    }

    @ResponseBody
    @RequestMapping("/start/issue/{issueId}")
    public int startIssue(@PathVariable("issueId") String issueId){
        IssueBasic issue = issueService.getIssue(issueId);
        PatternLk pattern = patternService.getPatternLk(issue.getPatternId());

        if (issue.getState().equals("False")) pattern.setfNum(pattern.getfNum()-1);
        pattern.settNum(pattern.gettNum()+1);
        patternService.updatePatternLikelihood(pattern);

        issue.setState("True");
        return issueService.updateIssue(issue);
    }

    @ResponseBody
    @RequestMapping("/end/issue/{issueId}")
    public int endIssue(@PathVariable("issueId") String issueId){
        IssueBasic issue = issueService.getIssue(issueId);
        PatternLk pattern = patternService.getPatternLk(issue.getPatternId());

        if (issue.getState().equals("True")) pattern.settNum(pattern.gettNum()-1);
        pattern.setfNum(pattern.getfNum()+1);
        patternService.updatePatternLikelihood(pattern);

        issue.setState("False");
        return issueService.updateIssue(issue);
    }

    @ResponseBody
    @RequestMapping("/issue/{issueId}")
    public IssueBasic getIssueBasic(@PathVariable("issueId") String issueId){
        return issueService.getIssue(issueId);
    }

    @ResponseBody
    @RequestMapping("/source/{issueId}")
    public IssueSource getSourceInfo(@PathVariable("issueId") String issueId){
        return issueService.getSourceInfo(issueId);
    }

    @ResponseBody
    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    public int addProjects(@RequestParam("projectName") String projectName,
                           @RequestParam("description") String description){
        Project project = new Project();
        AUser user = (AUser) session.getAttribute("user");

        project.setProjectId(StringUtil.generateStringId());
        project.setUserId(user.getId());
        project.setProjectName(projectName);
        project.setDescription(description);

        return projectService.addProject(project);
    }

    @RequestMapping("/addVersion")
    public String addVersion(@RequestParam("versionName") String versionName,
                             @RequestParam("reportFile") MultipartFile reportFile) throws IOException {
        AVersion aVersion = new AVersion();
        AUser user = (AUser) session.getAttribute("user");
        Project project = (Project) session.getAttribute("project");

        String fileName = "default.xml";
        if (reportFile != null){
            fileName = reportFile.getOriginalFilename();
            String filePath = UPLOADED_FOLDER + "\\data\\"+user.getUsername()+"\\"+project.getProjectName()+"\\";
            File file = new File(filePath);
            if (!file.exists()) file.mkdirs();
            filePath+=fileName;
            file = new File(filePath);
            reportFile.transferTo(file);
        }
        aVersion.setVersionId(StringUtil.generateStringId());
        aVersion.setVersionName(versionName);
        aVersion.setLastId("Null");
        aVersion.setFilePath("data/"+user.getUsername()+"/"+project.getProjectName()+"/"+fileName);
        aVersion.setProjectId(project.getProjectId());
        versionService.addVersion(aVersion);
        return "redirect:/back_versions";
    }

    @ResponseBody
    @RequestMapping("/editVersion")
    public int editVersion(@RequestParam("versionId") String versionId,
                           @RequestParam(value = "lastId") String lastId){
        AVersion version = versionService.getVersion(versionId);
        version.setLastId(lastId);
        return versionService.updateVersion(version);
    }

    @ResponseBody
    @RequestMapping("/scan")
    public int scan(@RequestParam("versionId") String versionId){
        if (issueService.getIssueList(versionId,"","",0).size() != 0) return 2;

        Project project = (Project) session.getAttribute("project");
        AVersion aVersion = versionService.getVersion(versionId);
        if (aVersion.getLastId().equals("Null")) return 3;
        if (!aVersion.getLastId().equals("First") && issueService.getIssueList(aVersion.getLastId(),"","",0).size() == 0) return 4;

        try{
            InputStream file = new FileInputStream(UPLOADED_FOLDER+"/"+aVersion.getFilePath());
            Map<String,List> res = XMLUtil.getInfo(file);

            List<IssueInfoDO> issueInfoList = res.get("issueInfoList");
            List<PatternInfoDO> patternInfoList = res.get("patternInfoList");

            for(PatternInfoDO patternInfoDO : patternInfoList){
                if (patternService.getPatternByPatternName(patternInfoDO.getPatternName()) == null){
                    PatternLk patternLk = new PatternLk();
                    PatternInfoWithBLOBs patternInfo = new PatternInfoWithBLOBs();
                    patternLk.setPatternLkId(StringUtil.generateStringId());
                    patternLk.setPatternName(patternInfoDO.getPatternName());
                    patternInfo.setPatternInfoId(StringUtil.generateStringId());
                    patternInfo.setPatternLkId(patternLk.getPatternLkId());
                    patternInfo.setExplanation(patternInfoDO.getExplanation());
                    patternInfo.setRecommendation(patternInfoDO.getRecommendation());
                    patternInfo.setTip(patternInfoDO.getTip());
                    patternService.addPatternLk(patternLk);
                    patternService.addPatternInfo(patternInfo);
                }
            }

            List<IssueBasic> issueBasicList = new ArrayList<>();
            for(IssueInfoDO issueInfoDO : issueInfoList){
                PatternLk pattern = patternService.getPatternByPatternName(issueInfoDO.getPatternName());

                IssueBasic issueBasic = new IssueBasic();
                issueBasic.setIssueId(StringUtil.generateStringId());
                issueBasic.setPatternId(pattern.getPatternLkId());
                issueBasic.setKingdom(issueInfoDO.getKingdom());
                issueBasic.setDescription(issueInfoDO.getDescription());
                issueBasic.setPriority(issueInfoDO.getPriority());
                issueBasic.setFileName(issueInfoDO.getFileName());
                issueBasic.setFilePath(issueInfoDO.getFilePath());
                issueBasic.setStartLine(issueInfoDO.getStartLine());
                issueBasic.setSnippet(issueInfoDO.getSnippet());
                issueBasic.setTargetFunction(issueInfoDO.getTargetFunction());
                issueBasic.setVersionId(versionId);
                issueBasicList.add(issueBasic);
                issueService.addIssue(issueBasic);

                if (issueInfoDO.getIssueSourceDO() != null){
                    IssueSourceDO issueSourceDO = issueInfoDO.getIssueSourceDO();
                    IssueSource issueSource = new IssueSource();
                    issueSource.setIssueSourceId(StringUtil.generateStringId());
                    issueSource.setIssueBasicId(issueBasic.getIssueId());
                    issueSource.setFileName(issueSourceDO.getFileName());
                    issueSource.setFilePath(issueSourceDO.getFilePath());
                    issueSource.setStartLine(issueSourceDO.getStartLine());
                    issueSource.setSnippet(issueSourceDO.getSnippet());
                    issueSource.setTargetFunction(issueSourceDO.getTargetFunction());
                    issueService.addSourceInfo(issueSource);
                }
            }

            String lastVersionId = aVersion.getLastId();
            if (!lastVersionId.equals("First")){
                List<IssueBasic> lastIssueList = issueService.getIssueList(lastVersionId,"","",0);
                issueService.compare(lastIssueList,issueBasicList);
                Map<String,Integer> tNums = SortUtil.countNum(lastIssueList,"True");
                Map<String,Integer> fNums = SortUtil.countNum(lastIssueList,"False");
                for(Map.Entry<String,Integer> entry:tNums.entrySet()){
                    String patternId = entry.getKey();
                    int trueNum = entry.getValue();
                    int falseNum = 0;
                    if (fNums.containsKey(patternId)) falseNum = fNums.get(patternId);
                    PatternLk pattern = patternService.getPatternLk(patternId);
                    pattern.settNum(pattern.gettNum()+trueNum);
                    pattern.setfNum(pattern.getfNum()+falseNum);
                    patternService.updatePatternLikelihood(pattern);
                }
                for (Map.Entry<String,Integer> entry:fNums.entrySet()){
                    String patternId = entry.getKey();
                    if (tNums.containsKey(patternId)) continue;
                    else{
                        int falseNum = entry.getValue();
                        PatternLk pattern = patternService.getPatternLk(patternId);
                        pattern.setfNum(pattern.getfNum()+falseNum);
                        patternService.updatePatternLikelihood(pattern);
                    }
                }
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/generate")
    public String generate(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, ParserConfigurationException, SAXException {
        AUser user = (AUser) session.getAttribute("user");
        Project project = (Project) session.getAttribute("project");
        AVersion version = (AVersion) session.getAttribute("version");

        ATemplate template = templateService.getUsedTemplate(user.getId());

        if (template == null) return "null";

        String filePath = UPLOADED_FOLDER+"/src/main/resources/static/doc/"+user.getUsername()+"/"+project.getProjectName()+"/"+version.getVersionName()+".doc";
        File file = new File(filePath);
        if (!file.exists()){
            InputStream report = new FileInputStream(UPLOADED_FOLDER+"/"+version.getFilePath());
            List<String> patternNameList = XMLUtil.getSummary(report);
            List<PatternDocVO> patternDocVOList = new ArrayList<>();
            for(String patternName:patternNameList){
                PatternLk patternLk = patternService.getPatternByPatternName(patternName);
                List<IssueBasic> issueBasicList = issueService.getIssueListByPatternId(version.getVersionId(),patternLk.getPatternLkId());
                List<IssueDocVO> issueDocVOList = new ArrayList<>();
                for(IssueBasic issueBasic:issueBasicList){
                    IssueDocVO issueDocVO = new IssueDocVO();
                    issueDocVO.setPriority(issueBasic.getPriority());
                    issueDocVO.setKingdom(issueBasic.getKingdom());
                    issueDocVO.setFilePath(issueBasic.getFilePath());
                    issueDocVO.setTargetFunction(issueBasic.getTargetFunction());
                    issueDocVO.setDescription(issueBasic.getDescription());
                    issueDocVO.setSnippet(issueBasic.getSnippet());
                    issueDocVO.setState(issueBasic.getState());
                    issueDocVOList.add(issueDocVO);
                }
                PatternDocVO patternDocVO = new PatternDocVO();
                PatternInfoWithBLOBs patternInfo = patternService.getPatternInfo(patternLk.getPatternLkId());
                int tNum = patternLk.gettNum();
                int fNum = patternLk.getfNum();
                double likelihood = tNum*1.0/(tNum+fNum);
                patternDocVO.setPatternName(patternLk.getPatternName());
                patternDocVO.setExplanation(patternInfo.getExplanation());
                patternDocVO.setRecommendation(patternInfo.getRecommendation());
                patternDocVO.setTip(patternInfo.getTip());
                patternDocVO.setIssueDocVOList(issueDocVOList);
                patternDocVO.setLikelihood(likelihood);
                patternDocVOList.add(patternDocVO);
            }
            DocUtil.generateDoc(patternDocVOList,project.getProjectName(),version.getVersionName(),user.getUsername(),template.getFilePath());
        }

        StringBuilder sb = new StringBuilder();
        try{
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename="+version.getVersionName()+".doc");
            FileReader fr = new FileReader(file);
            char[] buffer = new char[23];
            int length;
            while ((length = fr.read(buffer)) != -1) {
                sb.append(buffer,0,length);
            }
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

}
