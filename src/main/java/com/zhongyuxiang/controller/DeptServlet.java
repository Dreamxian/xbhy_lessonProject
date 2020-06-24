package com.zhongyuxiang.controller;

import com.alibaba.fastjson.JSON;
import com.zhongyuxiang.entity.Dept;
import com.zhongyuxiang.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptService deptService=new DeptService();

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> list=deptService.listAll();
        response.getWriter().write(JSON.toJSONString(list));
    }

}
