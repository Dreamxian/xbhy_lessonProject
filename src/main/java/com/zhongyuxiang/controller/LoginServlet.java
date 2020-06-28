package com.zhongyuxiang.controller;

import com.alibaba.fastjson.JSON;
import com.zhongyuxiang.constants.SysConstant;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.enums.SysEnum;
import com.zhongyuxiang.service.UserService;
import com.zhongyuxiang.utils.MdUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private UserService userService = new UserService();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String remember=request.getParameter("remember");
        HttpSession session = request.getSession();
        User user = userService.checkLogin(name, password);
        if (user == null) {
            response.sendRedirect("/index.jsp");
        } else {
            //登录信息放入session中
            session.setAttribute(SysConstant.SESSION_LOGIN, user);
            session.setMaxInactiveInterval(30 * 60);

            //选中记住
            if ("1".equals(remember)){
                Cookie c=new Cookie(SysEnum.COOKIE_LOGIN_NAME.getValue(), URLEncoder.encode(JSON.toJSONString(user),"utf-8"));
                c.setMaxAge(7*24*60*60);
                c.setPath("/");
                response.addCookie(c);
            }
            request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
        }
    }


    /**
     * @return void
     * @decription 忘记密码
     * @author zyx
     * @date 2020/6/28 14:17
     * @params [request, response]
     */
    protected void forget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String newPs = request.getParameter("uewPs");
        String code = request.getParameter("code");
        //判断账号
        boolean b = userService.getUserByUserName(name);

        HttpSession session = request.getSession();
        //判断验证码
        Object obj = session.getAttribute(SysConstant.SESSION_CODE);
        if (obj != null) {
            //把session中的验证码和前端的验证做比较
            if (code.equals(obj.toString()) && !b) {
                userService.updatePs(name, MdUtil.md5(newPs));
                response.sendRedirect("/index.jsp");
            } else {
                response.sendRedirect("/jsp/login/forget.jsp");
            }
        }


    }
}
