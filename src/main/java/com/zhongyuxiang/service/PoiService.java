package com.zhongyuxiang.service;

import com.zhongyuxiang.dao.UserDao;
import com.zhongyuxiang.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/7/1
 * @Description
 */
public class PoiService {
    private UserDao userDao=new UserDao();

    public Workbook exportUser(String username){
        List<User> list=userDao.listForExport(username);

        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet("工作薄");
        String[] heads={"部门","用户名","真实姓名","年龄","性别"};

        Row r1=sheet.createRow(0);
        for (int i = 0; i <heads.length ; i++) {
            r1.createCell(i).setCellValue(heads[i]);
        }

        for (int i=1;i<list.size();i++){
            Row r=sheet.createRow(i);
            User user=list.get(i-1);
            r.createCell(0).setCellValue(user.getDeptName()==null?"":user.getDeptName());
            r.createCell(1).setCellValue(user.getUsername()==null?"":user.getUsername());
            r.createCell(2).setCellValue(user.getRealName()==null?"":user.getRealName());
            r.createCell(3).setCellValue(user.getAge()==null?"":user.getAge().toString());
            r.createCell(4).setCellValue(user.getSexName()==null?"":user.getSexName());
        }
        return wb;
    }
}
