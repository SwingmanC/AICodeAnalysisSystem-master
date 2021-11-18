package org.nju.demo.controller;

import freemarker.template.TemplateException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.nju.demo.entity.*;
import org.nju.demo.pojo.DocWarning;
import org.nju.demo.pojo.Violation;
import org.nju.demo.service.*;
import org.nju.demo.utils.CmdUtil;
import org.nju.demo.utils.DocUtil;
import org.nju.demo.utils.SortUtil;
import org.nju.demo.utils.XMLUtil;
import org.nju.demo.utils.algorithm.Match;
import org.nju.demo.utils.algorithm.impl.ExactMatch;
import org.nju.demo.vo.ViolationVO;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private FViolationService fViolationService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private HttpSession session;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

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

    @RequestMapping("/view/violations/{versionId}")
    public String viewViolations(@PathVariable("versionId") int versionId){
        AVersion version = versionService.getVersion(versionId);
        session.setAttribute("version",version);
        return "violation_list";
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
    @RequestMapping("/f_violations/{flag}")
    public List<ViolationVO> getFViolations(@PathVariable("flag") int flag){
        AVersion version = (AVersion) session.getAttribute("version");
        List<FViolation> violationList = null;
        if (flag == 0) violationList = fViolationService.getFViolationsByVersionId(version.getId());
        else violationList = fViolationService.getTrueFViolations(version.getId());
        List<ViolationVO> violationVOList = new ArrayList<>();
        for(FViolation violation : violationList){
            ViolationVO violationVO = new ViolationVO();
            Pattern pattern = patternService.getPatternByPatternName(violation.getType());
            violationVO.setId(violation.getId());
            violationVO.setType(violation.getType());
            violationVO.setClassname(violation.getClassname());
            violationVO.setMethodName(violation.getMethodName());
            violationVO.setPriority(violation.getPriority());

            int trueNum = pattern.gettNum();
            int falseNum = pattern.getfNum();

            if (trueNum == 0 && falseNum == 0){
                violationVO.setLikelihood(0);
                violationVO.setVariance(1);
            }
            else{
                long n = patternService.countByCategoryId(pattern.getCategoryId());
                double likelihood = trueNum*1.0/(trueNum+falseNum);
                double variance = likelihood*(1-likelihood)/n;

                violationVO.setLikelihood(likelihood);
                violationVO.setVariance(variance);
            }

            violationVOList.add(violationVO);
        }
        return violationVOList;
    }

//    @ResponseBody
//    @RequestMapping("/f_true_violations")
//    public List<ViolationVO> getTrueFViolations(){
//        AVersion version = (AVersion) session.getAttribute("version");
//        List<FViolation> violationList = fViolationService.getTrueFViolations(version.getId());
//        List<ViolationVO> violationVOList = new ArrayList<>();
//        for(FViolation violation : violationList){
//            ViolationVO violationVO = new ViolationVO();
//            Pattern pattern = patternService.getPatternByPatternName(violation.getType());
//            violationVO.setId(violation.getId());
//            violationVO.setType(violation.getType());
//            violationVO.setClassname(violation.getClassname());
//            violationVO.setMethodName(violation.getMethodName());
//            violationVO.setPriority(violation.getPriority());
//
//            int trueNum = pattern.gettNum();
//            int falseNum = pattern.getfNum();
//            long n = patternService.countByCategoryId(pattern.getCategoryId());
//            double likelihood = trueNum*1.0/(trueNum+falseNum);
//            double variance = likelihood*(1-likelihood)/n;
//
//            violationVO.setLikelihood(likelihood);
//            violationVO.setVariance(variance);
//
//            violationVOList.add(violationVO);
//        }
//        return violationVOList;
//    }

    @ResponseBody
    @RequestMapping("/f_violation/{violationId}")
    public FViolation getFViolation(@PathVariable("violationId") int violationId){
        return fViolationService.getFViolation(violationId);
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
    public String addVersion(@RequestParam("version") String version,
                          @RequestParam("javaFiles") MultipartFile[] files) throws IOException {
        AVersion aVersion = new AVersion();
        AUser user = (AUser) session.getAttribute("user");
        Project project = (Project) session.getAttribute("project");

        if(files != null && files.length > 0){
            for (int i=0;i<files.length;++i){
//                map.put("filename",files[i].getName());
//                map.put("contentType",files[i].getContentType());
//                map.put("fileSize",files[i].getSize());
                String fileName = files[i].getOriginalFilename();
                String[] paths = fileName.split("/");
                String filePath = UPLOADED_FOLDER + "\\data\\"+user.getUsername()+"\\"+project.getProjectName()+"\\"+version+"\\";
                File file = null;
                for (int j=0;j<paths.length-1;++j){
                    filePath += paths[j];
                    filePath += "\\";
                }
                file = new File(filePath);
                if (!file.exists()) file.mkdirs();
                filePath += paths[paths.length-1];
                file = new File(filePath);
                files[i].transferTo(file);
            }
        }
        aVersion.setVersion(version);
        aVersion.setLastId(-1);
        aVersion.setFilePath("data/"+user.getUsername()+"/"+project.getProjectName()+"/"+version);
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
    @RequestMapping("/f_scan")
    public int f_scan(@RequestParam("versionId") int versionId){
        if (fViolationService.getFViolationsByVersionId(versionId).size() != 0) return 2;

        Project project = (Project) session.getAttribute("project");
        AVersion aVersion = versionService.getVersion(versionId);
        if (aVersion.getLastId().equals(-1)) return 3;
        if (!aVersion.getLastId().equals(0) && fViolationService.getFViolationsByVersionId(aVersion.getLastId()).size() == 0) return 4;

        try{
            String filePath = CmdUtil.generateFindBugsXML(project.getProjectName(),aVersion.getVersion(),aVersion.getFilePath());
            InputStream file = new FileInputStream(filePath);
            List<FViolation> v2 = XMLUtil.getFViolations(file,versionId);

            int lastVersionId = aVersion.getLastId();
            AVersion c_version = versionService.getVersion(lastVersionId);

            List<FViolation> v1 = null;
            if(c_version != null) v1 = fViolationService.getFViolationsByVersionId(c_version.getId());
            Match match = new ExactMatch();
            List<FViolation> res = match.mark1(v1,v2);
//            System.out.println(res.size());

            for (FViolation fViolation : v2){
                fViolationService.addFViolation(fViolation);
            }

            if (res != null){
                for(FViolation violation : res) fViolationService.updateFViolation(violation);
                Map<String,Integer> tNums = SortUtil.countNum(res,"True");
                Map<String,Integer> fNums = SortUtil.countNum(res,"False");
                for(Map.Entry<String,Integer> entry:tNums.entrySet()){
                    String patternName = entry.getKey();
                    int trueNum = entry.getValue();
                    int falseNum = 0;
                    if (fNums.containsKey(patternName)) falseNum = fNums.get(patternName);
                    Pattern pattern = patternService.getPatternByPatternName(patternName);
                    pattern.settNum(pattern.gettNum()+trueNum);
                    pattern.setfNum(pattern.getfNum()+falseNum);
                    patternService.updatePattern(pattern);
                }
                for (Map.Entry<String,Integer> entry:fNums.entrySet()){
                    String patternName = entry.getKey();
                    if (tNums.containsKey(patternName)) continue;
                    else{
                        int falseNum = entry.getValue();
                        Pattern pattern = patternService.getPatternByPatternName(patternName);
                        pattern.setfNum(pattern.getfNum()+falseNum);
                        patternService.updatePattern(pattern);
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
    public String generate(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {

        AUser user = (AUser) session.getAttribute("user");
        Project project = (Project) session.getAttribute("project");
        AVersion version = (AVersion) session.getAttribute("version");

        List<FViolation> violationList = fViolationService.getFViolationsByVersionId(version.getId());
        ATemplate template = templateService.getUsedTemplate(user.getId());

        if (template == null) return "null";

        List<DocWarning> warningList = new ArrayList<>();
        for (FViolation violation : violationList){
            DocWarning warning = new DocWarning();
            warning.setId(violation.getId());
            warning.setType(violation.getType());
            warning.setClassName(violation.getClassname());
            warning.setSourcePath(violation.getSourcePath());

            String methodName = violation.getMethodName();
            if (methodName.equals("<init>")) warning.setMethodName("init");
            else warning.setMethodName(methodName);

            warning.setPriority(violation.getPriority());
            warning.setStartLine(violation.getStartLine());
            warning.setEndLine(violation.getEndLine());
            warning.setDescription("这是一个漏洞");
            warning.setAdvice("建议立刻进行修复");
            warningList.add(warning);
        }
        DocUtil.generateWord(warningList,project.getProjectName(),version.getVersion(),user.getUsername(),template.getFilePath());

        String filePath = UPLOADED_FOLDER+"/src/main/resources/static/doc/"+user.getUsername()+"/"+project.getProjectName()+"/"+version.getVersion()+".doc";
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try{
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename="+version.getVersion()+".doc");
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

//    @RequestMapping("/compute")
//    public String compute(){
//        AVersion version = (AVersion) session.getAttribute("version");
//        List<FViolation> violationList = fViolationService.getFViolationsByVersionId(version.getId());
//        Map<String,Double> likelihoods = SortUtil.computeLikelihood(violationList);
//        for(Map.Entry<String,Double> entry : likelihoods.entrySet()){
//            String patternName = entry.getKey();
//            Double likelihood = entry.getValue();
//            Pattern pattern = patternService.getPatternByPatternName(patternName);
//            long n = patternService.countByCategoryId(pattern.getCategoryId());
//            Double variance = SortUtil.computeVariance(likelihood,n);
////            System.out.println(likelihood+" "+variance+" "+n);
//            Double lastLikelihood = pattern.getLikelihood();
//            Double lastVariance = pattern.getVariance();
//            if (lastLikelihood == 0) pattern.setLikelihood(likelihood);
//            else pattern.setLikelihood((likelihood+lastLikelihood)/2);
//            if (lastVariance == 0) pattern.setVariance(variance);
//            else pattern.setVariance((variance+lastVariance)/2);
//            patternService.updatePattern(pattern);
//        }
//        return "redirect:/view/violations/"+version.getId();
//    }

}
