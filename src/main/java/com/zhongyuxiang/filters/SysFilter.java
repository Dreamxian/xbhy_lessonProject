package com.zhongyuxiang.filters;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhongyuxiang.constants.SysConstant;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.enums.SysEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
@WebFilter("/*")
public class SysFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session =request.getSession();

        String uri =request.getRequestURI();

        if (uri.endsWith("/index.jsp")){
            Cookie[] cookies=request.getCookies();
            if (cookies!=null){
                for (Cookie c:cookies){
                    String cookieName=c.getName();
                    if (SysEnum.COOKIE_LOGIN_NAME.getValue().equals(cookieName)){
                       String cookieValue= c.getValue();
                        //将cookie中的信息放入session
                       cookieValue= URLDecoder.decode(cookieValue,"utf-8");
                        User user=JSON.parseObject(cookieValue,new TypeReference<User>(User.class){});
                        session.setAttribute(SysConstant.SESSION_LOGIN,user);
                        session.setMaxInactiveInterval(30*60);
                        //有cookie直接跳转成功页面
                        filterChain.doFilter(request, response);
                        request.getRequestDispatcher("/jsp/common/main.jsp").forward(request,response);
                        return;
                    }
                }
            }

        }else  if (uri.endsWith("/") ||uri.endsWith("/login")
                ||uri.endsWith("/forget.jsp")||uri.endsWith("/email")||uri.endsWith("/menu")
                ||uri.endsWith("/login/forget")){

        }else {
            Object obj=session.getAttribute(SysConstant.SESSION_LOGIN);
            if (obj==null){
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }else {

            }
        }


        filterChain.doFilter(request, response);
    }
}
