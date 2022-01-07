package org.nju.demo.controller;

import org.nju.demo.entity.Knowledge;
import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternLk;
import org.nju.demo.pojo.vo.KnowledgeVO;
import org.nju.demo.pojo.vo.PatternVO;
import org.nju.demo.service.KnowledgeService;
import org.nju.demo.service.PatternService;
import org.nju.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RepoController {

    @Autowired
    private PatternService patternService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/view/repo")
    public String viewRepo(){
        return "pattern_list";
    }

    @RequestMapping("/view/knowledge/{patternId}")
    public String viewKnowledge(@PathVariable("patternId") String patternId){
        PatternLk pattern = patternService.getPatternLk(patternId);
        session.setAttribute("pattern",pattern);
        return "knowledge_list";
    }

    @ResponseBody
    @RequestMapping("/patterns")
    public List<PatternVO> getPatterns(){
        List<PatternLk> patternList = patternService.getFalsePatternList();
        List<PatternVO> patternVOList = new ArrayList<>();
        for(PatternLk pattern:patternList){
            PatternVO patternVO = new PatternVO();
            patternVO.setPatternId(pattern.getPatternLkId());
            patternVO.setPatternName(pattern.getPatternName());

            int trueNum = pattern.gettNum();
            int falseNum = pattern.getfNum();
            double likelihood = trueNum*1.0/(trueNum+falseNum);

            patternVO.setLikelihood(likelihood);
            patternVOList.add(patternVO);
        }
        return patternVOList;
    }

    @ResponseBody
    @RequestMapping("/knowledgeVOs")
    public List<KnowledgeVO> getKnowledgeVOs(){
        PatternLk pattern = (PatternLk) session.getAttribute("pattern");
        List<Knowledge> knowledgeList = knowledgeService.getKnowledgeList(pattern.getPatternLkId());
        List<KnowledgeVO> knowledgeVOList = new ArrayList<>();
        for(Knowledge knowledge : knowledgeList){
            KnowledgeVO knowledgeVO = new KnowledgeVO();
            knowledgeVO.setKnowledgeId(knowledge.getKnowledgeId());
            knowledgeVO.setKnowledgeName(knowledge.getKnowledgeName());
            knowledgeVO.setPatternName(pattern.getPatternName());
            knowledgeVOList.add(knowledgeVO);
        }
        return knowledgeVOList;
    }

    @ResponseBody
    @RequestMapping("/knowledge/{id}")
    public Knowledge getKnowLedge(@PathVariable("id") String id){
        return knowledgeService.getKnowledge(id);
    }

    @RequestMapping("/addKnowledge")
    public String addKnowledge(@RequestParam("knowledgeName") String knowledgeName,
                               @RequestParam("content") String content){
        PatternLk pattern = (PatternLk) session.getAttribute("pattern");
        System.out.println(content);

        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeId(StringUtil.generateStringId());
        knowledge.setKnowledgeName(knowledgeName);
        knowledge.setContent(content);
        knowledge.setPatternId(pattern.getPatternLkId());

        knowledgeService.addKnowledge(knowledge);
        return "redirect:/view/knowledge/"+pattern.getPatternLkId();
    }

    @ResponseBody
    @RequestMapping("/deleteKnowledge/{id}")
    public int deleteKnowledge(@PathVariable("id") String id){
        return knowledgeService.deleteKnowledge(id);
    }

}
