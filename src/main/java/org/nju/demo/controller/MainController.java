package org.nju.demo.controller;

import freemarker.template.TemplateException;
import org.nju.demo.entity.*;
import org.nju.demo.pojo.vo.IssueDocVO;
import org.nju.demo.pojo.vo.IssueVO;
import org.nju.demo.pojo.vo.PatternDocVO;
import org.nju.demo.service.*;
import org.nju.demo.utils.CmdUtil;
import org.nju.demo.utils.DocUtil;
import org.nju.demo.utils.SortUtil;
import org.nju.demo.utils.XMLUtil;
import org.nju.demo.utils.algorithm.Match;
import org.nju.demo.utils.algorithm.impl.ExactMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.print.Doc;
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
    private RuleService ruleService;

    @Autowired
    private HttpSession session;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    @RequestMapping("/test")
    public String test(){
        return "index";
    }

    @RequestMapping("/view/projects")
    public String viewProjects(){
        return "project_list";
    }

    @RequestMapping("/view/versions/{projectId}")
    public String viewVersions(@PathVariable("projectId") int projectId,
                               Model model){
        Project project = projectService.getProjectById(projectId);
        List<AVersion> versionList = versionService.getVersionsByProjectId(projectId);
        session.setAttribute("project",project);
        model.addAttribute("versionList",versionList);
        return "version_list";
    }

    @RequestMapping("/back_versions")
    public String backVersions(Model model){
        Project project = (Project) session.getAttribute("project");
        List<AVersion> versionList = versionService.getVersionsByProjectId(project.getId());
        model.addAttribute("versionList",versionList);
        return "version_list";
    }

    @RequestMapping("/view/issues/{versionId}")
    public String viewIssues(@PathVariable("versionId") int versionId){
        AVersion version = versionService.getVersion(versionId);
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
        return versionService.getVersionsByProjectId(project.getId());
    }

    @ResponseBody
    @RequestMapping("/issues/{flag}")
    public List<IssueVO> getIssueList(@PathVariable("flag") int flag,
                                      @RequestParam(value = "priority",required = false) String priority,
                                      @RequestParam(value = "kingdom",required = false) String kingdom){
        AVersion version = (AVersion) session.getAttribute("version");
        if (priority == null) priority = "";
        if (kingdom == null) kingdom = "";
        List<IssueBasic> issueBasicList = issueService.getIssueList(version.getId(),priority,kingdom,flag);
        List<IssueVO> issueVOList = new ArrayList<>();
        for(IssueBasic issueBasic : issueBasicList){
            IssueVO issueVO = new IssueVO();
            PatternInfo patternInfo = patternService.getPatternInfo(issueBasic.getPatternId());//可优化
            PatternLk patternLk = patternService.getPatternLikelihood(issueBasic.getPatternId());
            issueVO.setId(issueBasic.getId());
            issueVO.setIssueId(issueBasic.getIssueId());
            issueVO.setPatternName(patternInfo.getPatternName());
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
    @RequestMapping("/start/issue/{id}")
    public int startViolation(@PathVariable("id") int id){
        IssueBasic issue = issueService.getIssue(id);
        PatternLk pattern = patternService.getPatternLikelihood(issue.getPatternId());

        if (issue.getState().equals("False")) pattern.setfNum(pattern.getfNum()-1);
        pattern.settNum(pattern.gettNum()+1);
        patternService.updatePatternLikelihood(pattern);

        issue.setState("True");
        return issueService.updateIssue(issue);
    }

    @ResponseBody
    @RequestMapping("/end/issue/{id}")
    public int endViolation(@PathVariable("id") int id){
        IssueBasic issue = issueService.getIssue(id);
        PatternLk pattern = patternService.getPatternLikelihood(issue.getPatternId());

        if (issue.getState().equals("True")) pattern.settNum(pattern.gettNum()-1);
        pattern.setfNum(pattern.getfNum()+1);
        patternService.updatePatternLikelihood(pattern);

        issue.setState("False");
        return issueService.updateIssue(issue);
    }

    @ResponseBody
    @RequestMapping("/issue/{id}")
    public IssueBasic getIssueBasic(@PathVariable("id") int id){
        return issueService.getIssue(id);
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
        aVersion.setVersionName(versionName);
        aVersion.setLastId(-1);
        aVersion.setFilePath("data/"+user.getUsername()+"/"+project.getProjectName()+"/"+fileName);
        aVersion.setProjectId(project.getId());
        versionService.addVersion(aVersion);
        return "redirect:/back_versions";
    }

    @ResponseBody
    @RequestMapping("/editVersion")
    public int editVersion(@RequestParam("versionId") int versionId,
                           @RequestParam(value = "lastId") int lastId){
        AVersion version = versionService.getVersion(versionId);
        version.setLastId(lastId);
        return versionService.updateVersion(version);
    }

    @ResponseBody
    @RequestMapping("/scan")
    public int scan(@RequestParam("versionId") int versionId){
        if (issueService.getIssueList(versionId,"","",0).size() != 0) return 2;

        Project project = (Project) session.getAttribute("project");
        AVersion aVersion = versionService.getVersion(versionId);
        if (aVersion.getLastId().equals(-1)) return 3;
        if (!aVersion.getLastId().equals(0) && issueService.getIssueList(aVersion.getLastId(),"","",0).size() == 0) return 4;

        try{
            InputStream file = new FileInputStream(UPLOADED_FOLDER+"/"+aVersion.getFilePath());
            Map<String,List> res = XMLUtil.getInfo(file);

            List<IssueBasic> issueBasicList = res.get("issueBasicList");
            List<IssueSource> issueSourceList = res.get("issueSourceList");
            List<PatternInfoWithBLOBs> patternInfoList = res.get("patternInfoList");

            int lastVersionId = aVersion.getLastId();
            if (lastVersionId != 0){
                AVersion last_version = versionService.getVersion(lastVersionId);
                List<IssueBasic> lastIssueList = issueService.getIssueList(lastVersionId,"","",0);
                issueService.compare(lastIssueList,issueBasicList);
                Map<String,Integer> tNums = SortUtil.countNum(lastIssueList,"True");
                Map<String,Integer> fNums = SortUtil.countNum(lastIssueList,"False");
                for(Map.Entry<String,Integer> entry:tNums.entrySet()){
                    String patternId = entry.getKey();
                    int trueNum = entry.getValue();
                    int falseNum = 0;
                    if (fNums.containsKey(patternId)) falseNum = fNums.get(patternId);
                    PatternLk pattern = patternService.getPatternLikelihood(patternId);
                    pattern.settNum(pattern.gettNum()+trueNum);
                    pattern.setfNum(pattern.getfNum()+falseNum);
                    patternService.updatePatternLikelihood(pattern);
                }
                for (Map.Entry<String,Integer> entry:fNums.entrySet()){
                    String patternId = entry.getKey();
                    if (tNums.containsKey(patternId)) continue;
                    else{
                        int falseNum = entry.getValue();
                        PatternLk pattern = patternService.getPatternLikelihood(patternId);
                        pattern.setfNum(pattern.getfNum()+falseNum);
                        patternService.updatePatternLikelihood(pattern);
                    }
                }
            }

            for (PatternInfoWithBLOBs patternInfo:patternInfoList){
                if (patternService.getPatternLikelihood(patternInfo.getPatternId()) == null) {
                    PatternLk patternLk = new PatternLk();
                    patternLk.setPatternId(patternInfo.getPatternId());
                    patternService.addPatternInfo(patternInfo);
                    patternService.addPatternLk(patternLk);
                }
            }

            for (IssueBasic issueBasic : issueBasicList){
                issueBasic.setVersionId(versionId);
                System.out.println(issueBasic.getIssueId()+" "+issueBasic.getPatternId());
                issueService.addIssue(issueBasic);
            }

            for (IssueSource issueSource : issueSourceList){
                issueService.addSourceInfo(issueSource);
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
                PatternInfo patternInfo = patternService.getPatternInfoByPatternName(patternName);
                List<IssueBasic> issueBasicList = issueService.getIssueListByPatternId(version.getId(),patternInfo.getPatternId());
                List<IssueDocVO> issueDocVOList = new ArrayList<>();
                for(IssueBasic issueBasic:issueBasicList){
                    IssueDocVO issueDocVO = new IssueDocVO();
                    issueDocVO.setIssueId(issueBasic.getIssueId());
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
                PatternInfoWithBLOBs patternInfoWithBLOBs = patternService.getPatternInfo(patternInfo.getPatternId());
                PatternLk patternLk = patternService.getPatternLikelihood(patternInfo.getPatternId());
                int tNum = patternLk.gettNum();
                int fNum = patternLk.getfNum();
                double likelihood = tNum*1.0/(tNum+fNum);
                patternDocVO.setPatternId(patternInfoWithBLOBs.getPatternId());
                patternDocVO.setPatternName(patternInfoWithBLOBs.getPatternName());
                patternDocVO.setExplanation(patternInfoWithBLOBs.getExplanation());
                patternDocVO.setRecommendation(patternInfoWithBLOBs.getRecommendation());
                patternDocVO.setTip(patternInfoWithBLOBs.getTip());
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
