package org.nju.demo.utils;

import com.csvreader.CsvReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CmdUtil {

    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    public static String generateFindBugsXML(String projectName,String version,String path) throws IOException {
        //windows
        String filePath = "E:/tools/findbugs-3.0.1/output/"+projectName+"/"+version+".xml";
        String command = "E:/tools/findbugs-3.0.1/bin/findbugs.bat -textui -low -progress -xml -output "+filePath+" "+path;
        return getCmd(filePath, command);
        //macos
//        String strDir = UPLOADED_FOLDER+"/tmp/"+projectName;
//        File dir = new File(strDir);
//        if (!dir.exists()) dir.mkdirs();
//        String filePath = UPLOADED_FOLDER+"/tmp/"+projectName+"/"+version+".xml";
//        String abPath = UPLOADED_FOLDER+"/"+path;
//        String command = "java -jar "+UPLOADED_FOLDER+"/tools/findbugs-3.0.1/lib/findbugs.jar -textui -low -progress -xml -output "+filePath+" "+abPath;
//        return getCmd(filePath, command);
    }

    public static String generatePMDXML(String projectName,String version,String path) throws IOException{
        String filePath = "E:/tools/pmd/output/"+projectName+"/"+version+".xml";
        String command = "E:/tools/pmd/bin/pmd.bat -d "+path+" -f xml -R E:/tools/pmd/rules.xml -r "+filePath;
        return getCmd(filePath, command);
    }

    public static String generateFeature(String projectName,String version,String path) throws IOException{
        String filePath = "E:/tools/JHawk/output/"+projectName+"/"+version+".csv";
        String command = "java -jar E:/tools/JHawk/JHawkCommandLine/JHawkCommandLine.jar -f .*\\.java -p E:/tools/JHawk/JHawkCommandLine/jhawk.properties -r -l pcm -s "+path+" -raw m -c "+filePath+" -system "+projectName;
        return getCmd(filePath,command);
    }

    private static String getCmd(String filePath, String command) throws IOException {
        //windows
        ProcessBuilder pb = new ProcessBuilder("cmd","/c", command);
        Process process = pb.start();
        //macos
//        Process process = Runtime.getRuntime().exec(command);
        Scanner scanner = new Scanner(process.getInputStream());
        while(scanner.hasNextLine()) System.out.println(scanner.nextLine());
        scanner.close();
        return filePath;
    }

    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("java -jar /Users/sunchen/IntelliJProject/AICodeAnalysisSystem-master/tools/findbugs-3.0.1/lib/findbugs.jar -textui -low -progress -xml -output 1.xml target");
        Scanner scanner = new Scanner(p.getInputStream());
        while(scanner.hasNextLine()) System.out.println(scanner.nextLine());
        scanner.close();
    }

}
