package org.nju.demo.controller;

import org.nju.demo.config.Constants;
import org.nju.demo.entity.ATemplate;
import org.nju.demo.entity.AUser;
import org.nju.demo.service.TemplateService;
import org.nju.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private HttpSession session;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    @RequestMapping("/view/templates")
    public String viewTemplates(){
        return "template_list";
    }

    @ResponseBody
    @RequestMapping("/templates")
    public List<ATemplate> getTemplates(){
        AUser user = (AUser) session.getAttribute("user");
        return templateService.getTemplateListByUserId(user.getId());
    }

    @RequestMapping("/addTemplate")
    public String addTemplate(@RequestParam("templateName") String templateName,
                              @RequestParam("templateFile") MultipartFile templateFile) throws IOException {
        ATemplate template = new ATemplate();
        AUser user = (AUser) session.getAttribute("user");

        if (!templateFile.isEmpty()){
            String fileName = templateFile.getOriginalFilename();
            int index = fileName.lastIndexOf('.');
            if (!fileName.substring(index+1).equals("ftl")) return "redirect:/view/templates";
            String filePath = UPLOADED_FOLDER+"/ftl/"+user.getUsername()+"/";
            File file =  new File(filePath);
            if (!file.exists()) file.mkdirs();
            filePath += fileName;
            file = new File(filePath);
            templateFile.transferTo(file);
        }
        template.setTemplateId(StringUtil.generateStringId());
        template.setTemplateName(templateName);
        template.setFilePath(user.getUsername()+"/"+templateFile.getOriginalFilename());
        template.setUserId(user.getId());
        template.setCreateTime(new Date());
        templateService.addTemplate(template);
        return "redirect:/view/templates";
    }

    @RequestMapping("/start/t/{templateId}")
    public String startTemplate(@PathVariable("templateId") String templateId){
        AUser user = (AUser) session.getAttribute("user");
        ATemplate template = templateService.getTemplate(templateId);
        ATemplate lastTemplate = templateService.getUsedTemplate(user.getId());
        if (lastTemplate != null){
            lastTemplate.setState(Constants.TemplateState.DISABLE);
            templateService.updateTemplate(lastTemplate);
        }
        template.setState(Constants.TemplateState.ENABLE);
        templateService.updateTemplate(template);

        return "redirect:/view/templates";
    }

    @ResponseBody
    @RequestMapping("/delete/template/{id}")
    public int deleteTemplate(@PathVariable("id") String templateId){
        return templateService.deleteTemplate(templateId);
    }
}
