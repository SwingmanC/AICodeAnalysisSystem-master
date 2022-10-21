package org.nju.demo.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.nju.demo.pojo.dto.IssueInfoDTO;
import org.nju.demo.pojo.dto.IssueSourceDTO;
import org.nju.demo.pojo.dto.PatternInfoDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static Element getElement(InputStream file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document.getDocumentElement();
    }

    public static void parseXmlFile(InputStream xmlFile,String excelFile) throws ParserConfigurationException, IOException, SAXException, WriteException {
        String[] title = {"iid","rule_id","pattern_name","priority","kingdom","file_name","file_path","start_line","target_function","description","snippet","source_file_name","source_file_path","source_start_line","source_target_function","source_snippet","pattern_description"};
        File file = new File(excelFile);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        Label label=null;
        //设置列名
        for (int i=0; i<title.length;i++) {
            label = new Label(i, 0, title[i]);
            sheet.addCell(label);
        }

        Element element = getElement(xmlFile);
        Element reportSection = (Element) element.getElementsByTagName("ReportSection").item(2);
        Element subSection = (Element) reportSection.getElementsByTagName("SubSection").item(0);

        if(subSection.getElementsByTagName("IssueListing").getLength() == 0){
            subSection = (Element) reportSection.getElementsByTagName("SubSection").item(1);
        }

        Element issueListing = (Element) subSection.getElementsByTagName("IssueListing").item(0);
        Element chart = (Element) issueListing.getElementsByTagName("Chart").item(0);
        NodeList groupSection = chart.getElementsByTagName("GroupingSection");

        int count = 1;
//        System.out.println(groupSection.getLength());
        for(int i=0;i<groupSection.getLength();++i){
            String explanation,recommendation,tip;
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
//            System.out.println(issueList.getLength());
            for(int j=0;j<issueList.getLength();++j){
                Element issue = (Element) issueList.item(j);
                String iid = issue.getAttribute("iid");
                String ruleId = issue.getAttribute("ruleID");
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
                label = new Label(0,count,iid);
                sheet.addCell(label);
                label = new Label(1,count,ruleId);
                sheet.addCell(label);
                label = new Label(2,count,category.getTextContent());
                sheet.addCell(label);
                label = new Label(3,count,priority.getTextContent());
                sheet.addCell(label);
                label = new Label(4,count,kingdom.getTextContent());
                sheet.addCell(label);
                label = new Label(5,count,sinkFileName.getTextContent());
                sheet.addCell(label);
                label = new Label(6,count,sinkFilePath.getTextContent());
                sheet.addCell(label);
                label = new Label(7,count,sinkStartLine.getTextContent());
                sheet.addCell(label);
                label = new Label(8,count,sinkTargetFunction.getTextContent());
                sheet.addCell(label);
                label = new Label(9,count,description.getTextContent());
                sheet.addCell(label);
                label = new Label(10,count,sinkSnippet.getTextContent());
                sheet.addCell(label);


                Element source = (Element) issue.getElementsByTagName("Source").item(0);

                if(source != null){
                    Element sourceFileName = (Element) source.getElementsByTagName("FileName").item(0);
                    Element sourceFilePath = (Element) source.getElementsByTagName("FilePath").item(0);
                    Element sourceStartLine = (Element) source.getElementsByTagName("LineStart").item(0);
                    Element sourceSnippet = (Element) source.getElementsByTagName("Snippet").item(0);
                    Element sourceTargetFunction = (Element) source.getElementsByTagName("TargetFunction").item(0);
                    label = new Label(11,count,sourceFileName.getTextContent());
                    sheet.addCell(label);
                    label = new Label(12,count,sourceFilePath.getTextContent());
                    sheet.addCell(label);
                    label = new Label(13,count,sourceStartLine.getTextContent());
                    sheet.addCell(label);
                    label = new Label(14,count,sourceTargetFunction.getTextContent());
                    sheet.addCell(label);
                    label = new Label(15,count,sourceSnippet.getTextContent());
                    sheet.addCell(label);
                }
                label = new Label(16,count,explanation);
                sheet.addCell(label);
                count++;
            }
        }
        workbook.write();
        workbook.close();
    }

    public static void main(String[] args) throws IOException, WriteException, ParserConfigurationException, SAXException {
        InputStream xmlFile = new FileInputStream("/Users/sunchen/Documents/源代码误报智能分析系统/评估数据/third.xml");
        String excelFile = "/Users/sunchen/Documents/源代码误报智能分析系统/评估数据/导出数据/third.xls";
        parseXmlFile(xmlFile,excelFile);
    }
}
