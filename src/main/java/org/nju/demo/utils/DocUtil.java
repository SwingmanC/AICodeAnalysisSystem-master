package org.nju.demo.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.nju.demo.pojo.DocWarning;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocUtil {

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    public static void generateWord(List<DocWarning> warningList, String projectName, String version, String username,String filePath) throws IOException, TemplateException {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("projectName",projectName);
        dataMap.put("version",version);
        dataMap.put("warningList",warningList);

        //Configuration用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

        //configuration.setClassForTemplateLoading(this.getClass(),""); //指定路径的第一种方式(根据某个类的相对路径指定)
        configuration.setDirectoryForTemplateLoading(new File(UPLOADED_FOLDER+"/ftl"));

        File outFile;// 输出文档路径及名称
        String path = UPLOADED_FOLDER+"/src/main/resources/static/doc/"+username+"/"+projectName+"/";
        outFile = new File(path);
        if (!outFile.exists()) outFile.mkdirs();
        path += version+".doc";
        outFile = new File(path);
        if (!outFile.exists()){
            Template t = configuration.getTemplate(filePath, "utf-8");//以utf-8的编码读取ftl文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            t.process(dataMap, out);
            out.close();
        }
    }

    public static void main(String[] args) throws IOException, TemplateException {
        System.out.println(UPLOADED_FOLDER);
//         要填充的数据, 注意map的key要和word中${xxx}的xxx一致
        Map<String, Object> dataMap = new HashMap<>();
        List<DocWarning> warningList = new ArrayList<>();
        DocWarning warning = new DocWarning();
        warning.setId(1);
        warning.setType("EI_EXPOSE_REP2");
        warning.setDescription("这是个漏洞");
        warning.setClassName("org.apache.lens.api.DateTime");
        warning.setSourcePath("org/apache/lens/api/DateTime.java");
        warning.setMethodName("init");
        warning.setPriority(2);
        warning.setStartLine(51);
        warning.setEndLine(51);
        warning.setAdvice("暂无");
        warningList.add(warning);
        warningList.add(warning);
        dataMap.put("projectName","lens");
        dataMap.put("version","2018.5.25");
        dataMap.put("warningList",warningList);

        //Configuration用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

        //configuration.setClassForTemplateLoading(this.getClass(),""); //指定路径的第一种方式(根据某个类的相对路径指定)
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\lenovo\\IdeaProjects\\AICodeAnalysisSystem\\report")); //指定路径的第二种方式,我的路径是D:\a\template.ftl

        File outFile = new File("C:\\Users\\lenovo\\IdeaProjects\\AICodeAnalysisSystem\\src\\main\\resources\\static\\doc\\jsfnsc\\lens\\1.doc");// 输出文档路径及名称
        Template t = configuration.getTemplate("template.ftl", "utf-8");//以utf-8的编码读取ftl文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        t.process(dataMap, out);
        out.close();
    }
}
