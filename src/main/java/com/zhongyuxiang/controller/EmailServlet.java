package com.zhongyuxiang.controller;

import com.zhongyuxiang.constants.SysConstant;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.service.UserService;
import com.zhongyuxiang.utils.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/email")
public class EmailServlet extends BaseServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=  request.getParameter("username");
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
        if (!(email==null||"".equals(email))){
            //随即验证
            String randomCode=Math.random()+"";
            randomCode= randomCode.substring(randomCode.length()-5,randomCode.length()-1);
            boolean b= EmailUtil.send(email,randomCode);
            if (b){
                session.setAttribute(SysConstant.SESSION_CODE,randomCode);
                session.setMaxInactiveInterval(60);
                out.write("1");
                return;
            }
        }
        out.write("0");

    }
}
