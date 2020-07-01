package com.zhongyuxiang.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @auth zyx
 * @date 2020/6/24
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        //excel表2007
        Workbook wb2=new XSSFWorkbook();
        //创建工作薄
        Sheet sheet= wb2.createSheet("工作薄");
        //创建行
        Row r1=sheet.createRow(0);
        //创建列存放数据
        Cell c1=r1.createCell(0);
        c1.setCellValue("马云");
        Cell c2=r1.createCell(1);
        c2.setCellValue("55");
    }
}
