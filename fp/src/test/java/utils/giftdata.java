package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class giftdata {
    public static HashMap<String, String> storeValues=new HashMap<>();
    public static List<HashMap<String,String>> data(String filepath, String sheetName) throws IOException{
        List<HashMap<String,String>> mydata=new ArrayList<>();
        FileInputStream file=new FileInputStream(filepath);
        XSSFWorkbook workbook=new XSSFWorkbook(file);
        XSSFSheet sheet=workbook.getSheet("sheet1");
        int totalRows=sheet.getLastRowNum();

        XSSFRow headerRow=sheet.getRow(0);

        for(int i=1;i<=totalRows;i++){
            XSSFRow currentRow=sheet.getRow(i);

            HashMap<String,String > currentHash= new HashMap<String, String>();
            for(int j=0;j<currentRow.getLastCellNum();j++){
                String key=headerRow.getCell(j).toString();
                XSSFCell currentCell= currentRow.getCell(j);
                String value=(currentCell==null)?"":currentCell.toString();
                currentHash.put(key,value);
            }
            mydata.add(currentHash);
        }
        workbook.close();
        file.close();
        return mydata;
    }
    public static void writeResult(String filepath,String sheetName,int rowIndex, String actualMessage, String result) throws IOException {
        FileInputStream file=new FileInputStream(filepath);
        XSSFWorkbook workbook=new XSSFWorkbook(file);
        XSSFSheet sheet=workbook.getSheet(sheetName);
        XSSFRow headerRow=sheet.getRow(0);
        XSSFRow dataRow=sheet.getRow(rowIndex+1);

        int actualMsg=-1;
        int resultCol=-1;
        for(int i=0;i<headerRow.getLastCellNum();i++) {
            String header = headerRow.getCell(i).toString();
            if (header.equalsIgnoreCase("actualMessage")) {
                actualMsg = i;
            }

            if (header.equalsIgnoreCase("result")) {
                resultCol = i;
            }
        }


            //create columns if not present
            if(actualMsg==-1){
                actualMsg=headerRow.getLastCellNum();
                headerRow.createCell(actualMsg).setCellValue("Actual Message");
            }
            if(resultCol==-1){
                resultCol=headerRow.getLastCellNum();
                headerRow.createCell(resultCol).setCellValue("result");
            }

            dataRow.createCell(actualMsg).setCellValue(actualMessage);
            dataRow.createCell(resultCol).setCellValue(result);
            FileOutputStream fos=new FileOutputStream(filepath);
            workbook.write(fos);
            workbook.close();
            file.close();
            fos.close();

        }

}

