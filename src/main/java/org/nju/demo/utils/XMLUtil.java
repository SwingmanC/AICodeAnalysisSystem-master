package org.nju.demo.utils;

import org.nju.demo.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtil {

    public static Element getElement(InputStream file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document.getDocumentElement();
    }

    public static List<String> getSummary(InputStream file) throws ParserConfigurationException, IOException, SAXException {
        List<String> res = new ArrayList<>();
        Element element = getElement(file);
        Element reportSection = (Element) element.getElementsByTagName("ReportSection").item(1);
        Element subSection = (Element) reportSection.getElementsByTagName("SubSection").item(1);
        Element issueListing = (Element) subSection.getElementsByTagName("IssueListing").item(0);
        Element chart = (Element) issueListing.getElementsByTagName("Chart").item(0);
        NodeList groupSection = chart.getElementsByTagName("GroupingSection");

        for (int i=0;i<groupSection.getLength();++i){
            Element group = (Element) groupSection.item(i);
            Element title = (Element) group.getElementsByTagName("groupTitle").item(0);
            res.add(title.getTextContent());
        }

        return res;
    }

    public static Map<String,List> getInfo(InputStream file) throws ParserConfigurationException, IOException, SAXException {
	    List<IssueBasic> issueBasicList = new ArrayList<>();
	    List<IssueSource> issueSourceList = new ArrayList<>();
	    List<PatternInfoWithBLOBs> patternInfoList = new ArrayList<>();
	    Map<String,List> res = new HashMap<>();

        Element element = getElement(file);
        Element reportSection = (Element) element.getElementsByTagName("ReportSection").item(2);
        Element subSection = (Element) reportSection.getElementsByTagName("SubSection").item(0);
        Element issueListing = (Element) subSection.getElementsByTagName("IssueListing").item(0);
        Element chart = (Element) issueListing.getElementsByTagName("Chart").item(0);
        NodeList groupSection = chart.getElementsByTagName("GroupingSection");

        for(int i=0;i<groupSection.getLength();++i){
            String explanation,recommendation,tip;
            String ruleId = "";
            String patternName = "";

            Element group = (Element) groupSection.item(i);
            Element summary = (Element) group.getElementsByTagName("MajorAttributeSummary").item(0);

            NodeList metaInfoList = summary.getElementsByTagName("MetaInfo");

            Element metaInfo1 = (Element) metaInfoList.item(1);
            Element value1 = (Element) metaInfo1.getElementsByTagName("Value").item(0);
            explanation = value1.getTextContent();
            Element metaInfo2 = (Element) metaInfoList.item(2);
            Element value2 = (Element) metaInfo2.getElementsByTagName("Value").item(0);
            recommendation = value2.getTextContent();
            if (metaInfoList.getLength() > 3){
                Element metaInfo3 = (Element) metaInfoList.item(3);
                Element value3 = (Element) metaInfo3.getElementsByTagName("Value").item(0);
                tip = value3.getTextContent();
            }
            else tip = "";
            NodeList issueList = group.getElementsByTagName("Issue");
            for(int j=0;j<issueList.getLength();++j){
                Element issue = (Element) issueList.item(j);
                IssueBasic issueBasic = new IssueBasic();
                issueBasic.setIssueId(issue.getAttribute("iid"));
                issueBasic.setPatternId(issue.getAttribute("ruleID"));
                if (ruleId.equals("")) ruleId = issue.getAttribute("ruleID");
                Element category = (Element) issue.getElementsByTagName("Category").item(0);
                Element kingdom = (Element) issue.getElementsByTagName("Kingdom").item(0);
                Element description = (Element) issue.getElementsByTagName("Abstract").item(0);
                Element priority = (Element) issue.getElementsByTagName("Friority").item(0);
                Element primary = (Element) issue.getElementsByTagName("Primary").item(0);
                Element sinkFileName = (Element) primary.getElementsByTagName("FileName").item(0);
                Element sinkFilePath = (Element) primary.getElementsByTagName("FilePath").item(0);
                Element sinkStartLine = (Element) primary.getElementsByTagName("LineStart").item(0);
                Element sinkSnippet = (Element) primary.getElementsByTagName("Snippet").item(0);
                Element sinkTargetFunction = (Element) primary.getElementsByTagName("TargetFunction").item(0);
                if (patternName.equals("")) patternName = category.getTextContent();
                issueBasic.setKingdom(kingdom.getTextContent());
                issueBasic.setDescription(description.getTextContent());
                issueBasic.setPriority(priority.getTextContent());
                issueBasic.setFileName(sinkFileName.getTextContent());
                issueBasic.setFilePath(sinkFilePath.getTextContent());
                issueBasic.setStartLine(Integer.parseInt(sinkStartLine.getTextContent()));
                issueBasic.setSnippet(sinkSnippet.getTextContent());
                issueBasic.setTargetFunction(sinkTargetFunction.getTextContent());

                Element source = (Element) issue.getElementsByTagName("Source").item(0);

                if(source != null){
                    IssueSource issueSource = new IssueSource();

                    Element sourceFileName = (Element) source.getElementsByTagName("FileName").item(0);
                    Element sourceFilePath = (Element) source.getElementsByTagName("FilePath").item(0);
                    Element sourceStartLine = (Element) source.getElementsByTagName("LineStart").item(0);
                    Element sourceSnippet = (Element) source.getElementsByTagName("Snippet").item(0);
                    Element sourceTargetFunction = (Element) source.getElementsByTagName("TargetFunction").item(0);
                    issueSource.setIssueId(issueBasic.getIssueId());
                    issueSource.setFileName(sourceFileName.getTextContent());
                    issueSource.setFilePath(sourceFilePath.getTextContent());
                    issueSource.setStartLine(Integer.parseInt(sourceStartLine.getTextContent()));
                    issueSource.setSnippet(sourceSnippet.getTextContent());
                    issueSource.setTargetFunction(sourceTargetFunction.getTextContent());
                    issueSourceList.add(issueSource);
                }

                issueBasicList.add(issueBasic);
            }
            PatternInfoWithBLOBs patternInfo = new PatternInfoWithBLOBs();
            patternInfo.setPatternId(ruleId);
            patternInfo.setPatternName(patternName);
            patternInfo.setExplanation(explanation);
            patternInfo.setRecommendation(recommendation);
            patternInfo.setTip(tip);
            patternInfoList.add(patternInfo);
        }

        res.put("issueBasicList",issueBasicList);
        res.put("issueSourceList",issueSourceList);
        res.put("patternInfoList",patternInfoList);
	    return res;
    }
}
