package com.zhongyuxiang.controller;

import com.alibaba.fastjson.JSON;
import com.zhongyuxiang.entity.Dept;
import com.zhongyuxiang.service.DeptService;
import com.zhongyuxiang.service.PoiService;
import com.zhongyuxiang.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet {

    private PoiService poiService=new PoiService();

    public void exportUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String username= request.getParameter("username");
        username=username==null?"":username;

        Workbook wb=  poiService.exportUser(username);
      response.setHeader("Content-Disposition","attachment;filename="+new String("用户.xlsx".getBytes("utf-8"),"iso-8859-1"));
      response.setContentType("application/ynd.ms-excel;charset=UTF-8");

      OutputStream os= response.getOutputStream();
      wb.write(os);
      os.flush();
      os.close();
    }

}
