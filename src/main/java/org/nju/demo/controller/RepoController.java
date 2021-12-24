package org.nju.demo.controller;

import org.nju.demo.entity.Knowledge;
import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternLk;
import org.nju.demo.pojo.vo.KnowledgeVO;
import org.nju.demo.pojo.vo.PatternVO;
import org.nju.demo.service.KnowledgeService;
import org.nju.demo.service.PatternService;
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
    public String viewKnowledge(@PathVariable("patternId") int id){
        PatternInfo pattern = patternService.getPattern(id);
        session.setAttribute("pattern",pattern);
        return "knowledge_list";
    }

    @ResponseBody
    @RequestMapping("/patterns")
    public List<PatternVO> getPatterns(){
        List<PatternInfo> patternList = patternService.getPatternInfoList();
        List<PatternVO> patternVOList = new ArrayList<>();
        for(PatternInfo pattern:patternList){
            PatternVO patternVO = new PatternVO();
            patternVO.setId(pattern.getId());
            patternVO.setPatternName(pattern.getPatternName());

            PatternLk patternLk = patternService.getPatternLikelihood(pattern.getPatternId());

            int trueNum = patternLk.gettNum();
            int falseNum = patternLk.getfNum();
            double likelihood = trueNum*1.0/(trueNum+falseNum);

            patternVO.setLikelihood(likelihood);
            patternVOList.add(patternVO);
        }
        return patternVOList;
    }

    @ResponseBody
    @RequestMapping("/knowledgeVOs")
    public List<KnowledgeVO> getKnowledgeVOs(){
        PatternInfo pattern = (PatternInfo) session.getAttribute("pattern");
        List<Knowledge> knowledgeList = knowledgeService.getKnowledgeList(pattern.getId());
        List<KnowledgeVO> knowledgeVOList = new ArrayList<>();
        for(Knowledge knowledge : knowledgeList){
            KnowledgeVO knowledgeVO = new KnowledgeVO();
            knowledgeVO.setId(knowledge.getId());
            knowledgeVO.setKnowledgeName(knowledge.getKnowledgeName());
            knowledgeVO.setPatternName(pattern.getPatternName());
            knowledgeVOList.add(knowledgeVO);
        }
        return knowledgeVOList;
    }

    @ResponseBody
    @RequestMapping("/knowledge/{id}")
    public Knowledge getKnowLedge(@PathVariable("id") int id){
        return knowledgeService.getKnowledge(id);
    }

    @RequestMapping("/addKnowledge")
    public String addKnowledge(@RequestParam("knowledgeName") String knowledgeName,
                               @RequestParam("content") String content){
        PatternInfo pattern = (PatternInfo) session.getAttribute("pattern");

        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeName(knowledgeName);
        knowledge.setContent(content);
        knowledge.setPatternId(pattern.getId());

        knowledgeService.addKnowledge(knowledge);
        return "redirect:/view/knowledge/"+pattern.getId();
    }

    @ResponseBody
    @RequestMapping("/deleteKnowledge/{id}")
    public int deleteKnowledge(@PathVariable("id") int id){
        return knowledgeService.deleteKnowledge(id);
    }

}
