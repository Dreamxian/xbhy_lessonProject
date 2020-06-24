package com.zhongyuxiang.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class User2Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith("list")) {
            list(request, response);
        } else if (uri.endsWith("add")) {
            add(request, response);
        } else if (uri.endsWith("delete")) {
            add(request, response);
        }

    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
