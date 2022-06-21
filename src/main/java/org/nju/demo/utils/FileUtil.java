package org.nju.demo.utils;

import org.nju.demo.pojo.vo.IssueFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FileUtil {

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");
    public static void generateArff(List<IssueFeature> issueFeatureList,File file){
        try{
            if(!file.exists()) file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("@relation feature");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute priority numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute kingdom numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute issue_num_method numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute issue_num_class numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute start_line numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute line_length numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute pattern_likelihood numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute for_count numeric");
            bufferedWriter.newLine();
            bufferedWriter.write("@attribute label {0,1}");
            bufferedWriter.newLine();
            bufferedWriter.write("@data");
            for (IssueFeature issueFeature : issueFeatureList){
                bufferedWriter.newLine();
                bufferedWriter.write(issueFeature.getPriority()+",");
                bufferedWriter.write(issueFeature.getKingdom()+",");
                bufferedWriter.write(issueFeature.getIssueNumMethod()+",");
                bufferedWriter.write(issueFeature.getIssueNumClass()+",");
                bufferedWriter.write(issueFeature.getStartLine()+",");
                bufferedWriter.write(issueFeature.getLineLength()+",");
                bufferedWriter.write(issueFeature.getPatternLikelihood()+",");
                bufferedWriter.write(issueFeature.getForCount()+",");
                bufferedWriter.write(String.valueOf(issueFeature.getLabel()));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void generateTrainArff(String username,List<IssueFeature> issueFeatureList){
        File arffFile = new File(UPLOADED_FOLDER+"/data/"+username+"/train.arff");
        generateArff(issueFeatureList,arffFile);
    }
    public static void generateTestArff(String username,List<IssueFeature> issueFeatureList){
        File arffFile = new File(UPLOADED_FOLDER+"/data/"+username+"/test.arff");
        generateArff(issueFeatureList,arffFile);
    }
}
