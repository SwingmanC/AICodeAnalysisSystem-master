package org.nju.demo.utils;

import org.nju.demo.entity.FIssue;
import org.nju.demo.entity.FIssueWithBLOBs;
import org.nju.demo.entity.FViolation;
import org.nju.demo.pojo.BugInstance;
import org.nju.demo.pojo.Method;
import org.nju.demo.pojo.WarningIssue;
import org.nju.demo.service.FIssueService;
import org.nju.demo.service.impl.FIssueServiceImpl;
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
import java.util.List;

public class XMLUtil {
	public static List<BugInstance> getBugs(InputStream file) throws Exception {
	    List<BugInstance> bugInstances = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

        Document document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("BugInstance");

        for(int i=0;i<nodeList.getLength();++i){
            Element bug = (Element) nodeList.item(i);
            BugInstance bugInstance = new BugInstance();
            bugInstance.setType(bug.getAttribute("type"));
            bugInstance.setPriority(Integer.parseInt(bug.getAttribute("priority")));
            bugInstance.setCategory(bug.getAttribute("category"));

            Element classElement = (Element) bug.getElementsByTagName("Class").item(0);
            bugInstance.setClassname(classElement.getAttribute("classname"));
            Element sourceElement = (Element) classElement.getElementsByTagName("SourceLine").item(0);
            bugInstance.setSourcePath(sourceElement.getAttribute("sourcepath"));
            if (!sourceElement.getAttribute("start").equals("")) bugInstance.setStart(Integer.parseInt(sourceElement.getAttribute("start")));
            if (!sourceElement.getAttribute("end").equals("")) bugInstance.setEnd(Integer.parseInt(sourceElement.getAttribute("end")));

            NodeList methodList = bug.getElementsByTagName("Method");
            for(int j=0;j<methodList.getLength();++j){
                Element methodElement = (Element) methodList.item(j);
                Method method = new Method();
                method.setName(methodElement.getAttribute("name"));
                method.setSignature(methodElement.getAttribute("signature"));
                Element methodSource = (Element) methodElement.getElementsByTagName("SourceLine").item(0);
                if(methodSource == null) continue;
                if(!methodSource.getAttribute("start").equals("")) method.setStartLine(Integer.parseInt(methodSource.getAttribute("start")));
                if(!methodSource.getAttribute("end").equals("")) method.setEndLine(Integer.parseInt(methodSource.getAttribute("end")));
                bugInstance.add(method);
            }
            bugInstances.add(bugInstance);
        }

        return bugInstances;
    }

    public static List<FViolation> getFViolations(InputStream file,int versionId) throws Exception {
	    List<FViolation> violationList = new ArrayList<>();
        List<BugInstance> bugInstances = XMLUtil.getBugs(file);
//        System.out.println(bugInstances.size());

	    for(BugInstance bugInstance:bugInstances){
	        List<Method> methods = bugInstance.getMethodList();
	        for(Method method:methods){
	            FViolation violation = new FViolation();
	            violation.setVersionId(versionId);
	            violation.setType(bugInstance.getType());
	            violation.setCategory(bugInstance.getCategory());
	            violation.setPriority(bugInstance.getPriority());
	            violation.setClassname(bugInstance.getClassname());
	            violation.setSourcePath(bugInstance.getSourcePath());
	            violation.setMethodName(method.getName());
	            violation.setSignature(method.getSignature());
	            violation.setStartLine(method.getStartLine());
	            violation.setEndLine(method.getEndLine());
	            violationList.add(violation);
            }
        }

        return violationList;
    }

    public static List<FIssueWithBLOBs> getIssues(InputStream file) throws ParserConfigurationException, IOException, SAXException {
	    List<FIssueWithBLOBs> warningIssueList = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

        Document document = builder.parse(file);
        Element element = document.getDocumentElement();
        Element reportSection = (Element) element.getElementsByTagName("ReportSection").item(2);
        Element subSection = (Element) reportSection.getElementsByTagName("SubSection").item(0);
        Element issueListing = (Element) subSection.getElementsByTagName("IssueListing").item(0);
        Element chart = (Element) issueListing.getElementsByTagName("Chart").item(0);
        NodeList groupSection = chart.getElementsByTagName("GroupingSection");

        for(int i=0;i<groupSection.getLength();++i){
            Element group = (Element) groupSection.item(i);
            Element summary = (Element) group.getElementsByTagName("MajorAttributeSummary").item(0);
            Element metaInfo = (Element) summary.getElementsByTagName("MetaInfo").item(2);
            Element value = (Element) metaInfo.getElementsByTagName("Value").item(0);
            String recommendation = value.getTextContent();
            NodeList issueList = group.getElementsByTagName("Issue");
            for(int j=0;j<issueList.getLength();++j){
                Element issue = (Element) issueList.item(j);
                FIssueWithBLOBs warningIssue = new FIssueWithBLOBs();
                warningIssue.setiId(issue.getAttribute("iid"));
                Element category = (Element) issue.getElementsByTagName("Category").item(0);
                Element kingdom = (Element) issue.getElementsByTagName("Kingdom").item(0);
                Element description = (Element) issue.getElementsByTagName("Abstract").item(0);
                Element priority = (Element) issue.getElementsByTagName("Friority").item(0);
                Element primary = (Element) issue.getElementsByTagName("Primary").item(0);
                Element fileName = (Element) primary.getElementsByTagName("FileName").item(0);
                Element filePath = (Element) primary.getElementsByTagName("FilePath").item(0);
                Element startLine = (Element) primary.getElementsByTagName("LineStart").item(0);
                Element snippet = (Element) primary.getElementsByTagName("Snippet").item(0);
                Element targetFunction = (Element) primary.getElementsByTagName("TargetFunction").item(0);
                warningIssue.setCategory(category.getTextContent());
                warningIssue.setKingdom(kingdom.getTextContent());
                warningIssue.setDescription(description.getTextContent());
                warningIssue.setPriority(priority.getTextContent());
                warningIssue.setFileName(fileName.getTextContent());
                warningIssue.setFilePath(filePath.getTextContent());
                warningIssue.setStartLine(Integer.parseInt(startLine.getTextContent()));
                warningIssue.setSnippet(snippet.getTextContent());
                warningIssue.setTargetFunction(targetFunction.getTextContent());
                warningIssue.setRecommendation(recommendation);
                warningIssueList.add(warningIssue);
            }
        }

	    return warningIssueList;
    }
}
