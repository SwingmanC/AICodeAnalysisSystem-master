package org.nju.demo.utils;

import com.csvreader.CsvReader;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.nju.demo.vo.Feature;
import org.nju.demo.vo.Warning;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<Warning> readWarningsFromExcel(String path,int index) throws FileNotFoundException {
        List<Warning> warningList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);

        try{
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

            HSSFSheet sheet = workbook.getSheetAt(index);
            int first = sheet.getFirstRowNum();
            int last = sheet.getLastRowNum();

            for(int i=first;i<=last;++i){
                HSSFRow row = sheet.getRow(i);
                Warning warning = new Warning();
                warning.setProjectName(row.getCell(0).getStringCellValue());
                warning.setCommitKey(row.getCell(1).getStringCellValue());
                warning.setClassName(row.getCell(2).getStringCellValue());
                warning.setSourcePath(row.getCell(3).getStringCellValue());
                warning.setType(row.getCell(4).getStringCellValue());
                warning.setCategory(row.getCell(5).getStringCellValue());
                warning.setPriority((int)row.getCell(6).getNumericCellValue());
                warning.setMethodName(row.getCell(7).getStringCellValue());
                warning.setSignature(row.getCell(8).getStringCellValue());
                warning.setStartLine((int)row.getCell(9).getNumericCellValue());
                warning.setEndLine((int)row.getCell(10).getNumericCellValue());
                warning.setState(row.getCell(11).getStringCellValue());
                warningList.add(warning);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return warningList;
    }

    public static List<Feature> readFeaturesFromCsv(String path) throws FileNotFoundException {
        List<Feature> featureList = new ArrayList<>();
        CsvReader csvReader = new CsvReader(path,',');

        try{
            csvReader.readHeaders();
            while(csvReader.readRecord()){
                Feature feature = new Feature();
                feature.setSystem(csvReader.get(0));
                feature.setPackageName(csvReader.get(1));
                feature.setClassName(csvReader.get(2));
                feature.setMethodName(csvReader.get(3));
                StringBuilder str = new StringBuilder("");
                for(int j=4;j<=30;++j){
                    str.append(csvReader.get(j));
                    str.append(" ");
                }
                feature.setMetrics(str.toString());
                featureList.add(feature);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return featureList;
    }

    public static void match(List<Warning> warningList,List<Feature> featureList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int count = 0;
        String projectName = warningList.get(0).getProjectName();
        String commitKey = warningList.get(0).getCommitKey();

        for(Warning warning : warningList){
            String[] classes = warning.getClassName().split("\\.");
            String classname = classes[classes.length-1];
            for(Feature feature : featureList){
                if(feature.getClassName().equals(classname) && feature.getMethodName().equals(warning.getMethodName())){
                    System.out.println(classname+' '+feature.getClassName());
                    HSSFRow row = sheet.createRow(count++);
                    row.createCell(0).setCellValue(warning.getType());
                    row.createCell(1).setCellValue(feature.getMetrics());
                    row.createCell(2).setCellValue(warning.getState());
                    break;
                }
            }
        }
        workbook.setSheetName(0,"match");
        FileOutputStream outputStream = new FileOutputStream("E:/tools/result/"+projectName+"/"+commitKey+".xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        String path = "E:\\学习资料\\硕士\\源代码误报智能分析系统\\1.xlsx";
        List<String> list = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);
            int first = sheet.getFirstRowNum();
            int last = sheet.getLastRowNum();

            for(int i=first;i<=last;++i){
                XSSFRow row = sheet.getRow(i);
                int start = row.getFirstCellNum();
                int end = row.getLastCellNum();
                for (int j=start;j<=end;++j){
                    XSSFCell cell = row.getCell(j);
                    if (cell == null) continue;
                    else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
                        System.out.print(cell.getStringCellValue()+" ");
                        list.add(cell.getStringCellValue());
                    }
                    else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
                        System.out.println(cell.getNumericCellValue() +" ");
                        list.add(Double.toString(cell.getNumericCellValue()));
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
