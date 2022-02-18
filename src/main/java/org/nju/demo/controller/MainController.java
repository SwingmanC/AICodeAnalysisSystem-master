package org.nju.demo.controller;

import freemarker.template.TemplateException;
import org.nju.demo.constant.Type;
import org.nju.demo.entity.*;
import org.nju.demo.pojo.dto.IssueInfoDTO;
import org.nju.demo.pojo.dto.IssueSourceDTO;
import org.nju.demo.pojo.dto.PatternInfoDTO;
import org.nju.demo.pojo.vo.*;
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
    private KnowledgeService knowledgeService;

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
//        System.out.println(project.getProjectName());
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
//        System.out.println(version.getVersionName());
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
    @RequestMapping("/issues")
    public List<IssueVO> getIssueList(@RequestParam(value = "priority",required = false) String priority,
                                      @RequestParam(value = "kingdom",required = false) int kingdom,
                                      @RequestParam(value = "state",required = false) String state){
        AVersion version = (AVersion) session.getAttribute("version");
        String kd;
        if (kingdom == 0) kd = "";
        else kd = Type.fortify[kingdom-1];
        List<IssueBasic> issueBasicList = issueService.getIssueList(version.getVersionId(),priority,kd,state,0);
        List<IssueVO> issueVOList = new ArrayList<>();
        for(IssueBasic issueBasic : issueBasicList){
            IssueVO issueVO = new IssueVO();
            PatternLk patternLk = patternService.getPatternLk(issueBasic.getPatternId());
            issueVO.setIssueId(issueBasic.getIssueId());
            issueVO.setPatternName(patternLk.getPatternName());
            issueVO.setFileName(issueBasic.getFileName());
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

        List<AVersion> versionList = versionService.getVersionsByVersionName(project.getProjectId(),versionName);
        if (versionList.size()!=0) return "redirect:/back_versions";

        String fileName = "default.xml";
        if (reportFile != null){
            fileName = reportFile.getOriginalFilename();
            int index = fileName.indexOf('.');
            if (!fileName.substring(index+1).equals("xml")) return "redirect:/back_versions";
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
    @RequestMapping("/version")
    public int getVersion(@RequestParam("versionName") String versionName){
        Project project = (Project) session.getAttribute("project");
        List<AVersion> versionList = versionService.getVersionsByVersionName(project.getProjectId(),versionName);
        if (versionList.size()!=0) return 1;
        else return 0;
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
        if (issueService.getIssueList(versionId,"","","",-1).size() != 0) return 2;

        Project project = (Project) session.getAttribute("project");
        AVersion aVersion = versionService.getVersion(versionId);
        if (aVersion.getLastId().equals("Null")) return 3;
        if (!aVersion.getLastId().equals("First") && issueService.getIssueList(aVersion.getLastId(),"","","",-1).size() == 0) return 4;

        try{
            InputStream file = new FileInputStream(UPLOADED_FOLDER+"/"+aVersion.getFilePath());
            Map<String,List> res = XMLUtil.getInfo(file);

            List<IssueInfoDTO> issueInfoList = res.get("issueInfoList");
            List<PatternInfoDTO> patternInfoList = res.get("patternInfoList");

            for(PatternInfoDTO patternInfoDTO : patternInfoList){
                if (patternService.getPatternByPatternName(patternInfoDTO.getPatternName()) == null){
                    PatternLk patternLk = new PatternLk();
                    PatternInfoWithBLOBs patternInfo = new PatternInfoWithBLOBs();
                    patternLk.setPatternLkId(StringUtil.generateStringId());
                    patternLk.setPatternName(patternInfoDTO.getPatternName());
                    patternInfo.setPatternInfoId(StringUtil.generateStringId());
                    patternInfo.setPatternLkId(patternLk.getPatternLkId());
                    patternInfo.setExplanation(patternInfoDTO.getExplanation());
                    patternInfo.setRecommendation(patternInfoDTO.getRecommendation());
                    patternInfo.setTip(patternInfoDTO.getTip());
                    patternService.addPatternLk(patternLk);
                    patternService.addPatternInfo(patternInfo);
                }
            }

            List<IssueBasic> issueBasicList = new ArrayList<>();
            for(IssueInfoDTO issueInfoDTO : issueInfoList){
                PatternLk pattern = patternService.getPatternByPatternName(issueInfoDTO.getPatternName());

                IssueBasic issueBasic = new IssueBasic();
                issueBasic.setIssueId(StringUtil.generateStringId());
                issueBasic.setPatternId(pattern.getPatternLkId());
                issueBasic.setKingdom(issueInfoDTO.getKingdom());
                issueBasic.setDescription(issueInfoDTO.getDescription());
                issueBasic.setPriority(issueInfoDTO.getPriority());
                issueBasic.setFileName(issueInfoDTO.getFileName());
                issueBasic.setFilePath(issueInfoDTO.getFilePath());
                issueBasic.setStartLine(issueInfoDTO.getStartLine());
                issueBasic.setSnippet(issueInfoDTO.getSnippet());
                issueBasic.setTargetFunction(issueInfoDTO.getTargetFunction());
                issueBasic.setVersionId(versionId);
                issueBasicList.add(issueBasic);
                issueService.addIssue(issueBasic);

                if (issueInfoDTO.getIssueSourceDTO() != null){
                    IssueSourceDTO issueSourceDTO = issueInfoDTO.getIssueSourceDTO();
                    IssueSource issueSource = new IssueSource();
                    issueSource.setIssueSourceId(StringUtil.generateStringId());
                    issueSource.setIssueBasicId(issueBasic.getIssueId());
                    issueSource.setFileName(issueSourceDTO.getFileName());
                    issueSource.setFilePath(issueSourceDTO.getFilePath());
                    issueSource.setStartLine(issueSourceDTO.getStartLine());
                    issueSource.setSnippet(issueSourceDTO.getSnippet());
                    issueSource.setTargetFunction(issueSourceDTO.getTargetFunction());
                    issueService.addSourceInfo(issueSource);
                }
            }

            String lastVersionId = aVersion.getLastId();
            if (!lastVersionId.equals("First")){
                List<IssueBasic> lastIssueList = issueService.getIssueList(lastVersionId,"","","",-1);
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

        String filePath = UPLOADED_FOLDER+"/doc/"+user.getUsername()+"/"+project.getProjectName()+"/"+version.getVersionName()+".doc";
        File file = new File(filePath);
        if (!file.exists()){
//            List<String> patternNameList = XMLUtil.getSummary(report);
            List<PatternLk> patternLkList = patternService.getPatternLkList();
            List<PatternDocVO> patternDocVOList = new ArrayList<>();
            int i=1;
            for(PatternLk patternLk:patternLkList){
                List<IssueBasic> issueBasicList = issueService.getIssueListByPatternId(version.getVersionId(),patternLk.getPatternLkId());
                if (issueBasicList.size() == 0) continue;
                List<IssueDocVO> issueDocVOList = new ArrayList<>();
                for(IssueBasic issueBasic:issueBasicList){
                    IssueDocVO issueDocVO = new IssueDocVO();
                    issueDocVO.setId(i);
                    issueDocVO.setPriority(issueBasic.getPriority());
                    issueDocVO.setKingdom(issueBasic.getKingdom());
                    issueDocVO.setFilePath(issueBasic.getFilePath());
                    issueDocVO.setTargetFunction(issueBasic.getTargetFunction());
                    issueDocVO.setDescription(issueBasic.getDescription());
                    if (issueBasic.getSnippet() != null) issueDocVO.setSnippet(issueBasic.getSnippet());
                    else issueDocVO.setSnippet("");
                    issueDocVO.setState(issueBasic.getState());
                    issueDocVOList.add(issueDocVO);
                    i++;
                }
                PatternDocVO patternDocVO = new PatternDocVO();
                PatternInfoWithBLOBs patternInfo = patternService.getPatternInfoByPatternLkId(patternLk.getPatternLkId());
                int tNum = patternLk.gettNum();
                int fNum = patternLk.getfNum();
                double likelihood;
                if (tNum == 0 && fNum == 0) likelihood = 0;
                else likelihood = tNum*1.0/(tNum+fNum);
                patternDocVO.setPatternName(patternLk.getPatternName());
                patternDocVO.setExplanation(patternInfo.getExplanation());
                patternDocVO.setRecommendation(patternInfo.getRecommendation());
                if (patternInfo.getTip().length() != 0) patternDocVO.setTip(patternInfo.getTip());
                else patternDocVO.setTip("暂无");
                patternDocVO.setIssueDocVOList(issueDocVOList);
                patternDocVO.setLikelihood(likelihood);
                patternDocVOList.add(patternDocVO);
            }
            DocUtil.generateDoc(patternDocVOList,project.getProjectName(),version.getVersionName(),user.getUsername(),template.getFilePath());
        }

        StringBuilder sb = new StringBuilder();
        try{
            response.setContentType("application/x-download;charset=UTF-8");
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

    @ResponseBody
    @RequestMapping("/issue/info/{issueId}")
    public IssueInfoVO getIssueInfo(@PathVariable("issueId") String issueId){
        IssueInfoVO issueInfoVO = new IssueInfoVO();
        IssueBasic issueBasic = issueService.getIssue(issueId);
        IssueSource issueSource = issueService.getSourceInfo(issueBasic.getIssueId());
        PatternLk patternLk = patternService.getPatternLk(issueBasic.getPatternId());
        PatternInfoWithBLOBs patternInfo = patternService.getPatternInfoByPatternLkId(issueBasic.getPatternId());
        List<Knowledge> knowledgeList = knowledgeService.getKnowledgeList(patternLk.getPatternLkId());
        issueInfoVO.setPatternId(patternLk.getPatternLkId());
        issueInfoVO.setPatternName(patternLk.getPatternName());
        issueInfoVO.setKingdom(issueBasic.getKingdom());
        issueInfoVO.setFileName(issueBasic.getFileName());
        issueInfoVO.setFilePath(issueBasic.getFilePath());
        issueInfoVO.setStartLine(issueBasic.getStartLine());
        issueInfoVO.setSnippet(issueBasic.getSnippet());
        issueInfoVO.setTargetFunction(issueBasic.getTargetFunction());
        issueInfoVO.setDescription(issueBasic.getDescription());
        issueInfoVO.setExplanation(patternInfo.getExplanation());
        issueInfoVO.setRecommendation(patternInfo.getRecommendation());
        issueInfoVO.setTip(patternInfo.getTip());
        issueInfoVO.setKnowledgeList(knowledgeList);
        if (issueSource != null) issueInfoVO.setIssueSource(issueSource);
        return issueInfoVO;
    }

}
