package org.nju.demo.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.nju.demo.pojo.vo.IssueDocVO;
import org.nju.demo.pojo.vo.PatternDocVO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocUtil {

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    public static void generateDoc(List<PatternDocVO> patternList, String projectName, String versionName, String username, String filePath) throws IOException, TemplateException {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("projectName",projectName);
        dataMap.put("version",versionName);
        dataMap.put("patternList",patternList);

        //Configuration用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

        //configuration.setClassForTemplateLoading(this.getClass(),""); //指定路径的第一种方式(根据某个类的相对路径指定)
        configuration.setDirectoryForTemplateLoading(new File(UPLOADED_FOLDER+"/ftl"));

        File outFile;// 输出文档路径及名称
        String path = UPLOADED_FOLDER+"/src/main/resources/static/doc/"+username+"/"+projectName+"/";
        outFile = new File(path);
        if (!outFile.exists()) outFile.mkdirs();
        path += versionName+".doc";
        outFile = new File(path);
        Template t = configuration.getTemplate(filePath, "utf-8");//以utf-8的编码读取ftl文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        t.process(dataMap, out);
        out.close();
    }
}
