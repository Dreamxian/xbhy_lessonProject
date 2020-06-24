package com.zhongyuxiang.controller;

import com.zhongyuxiang.entity.Dept;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.service.DeptService;
import com.zhongyuxiang.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserService();
    private DeptService deptService=new DeptService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=  request.getParameter("username");
        if(name==null){
            name="";
        }

        //总记录数
        Integer count = userService.getCount();
        //总页数
        Integer pageCount = count % 5 == 0 ? count / 5 : count / 5 + 1;
        String pageStr = request.getParameter("page");
        Integer page = 1;
        if (!StringUtils.isEmpty(pageStr)) {
            page = Integer.valueOf(pageStr);
            if (page <= 0) {
                page = 1;
            }
            if (page >= pageCount) {
                page = pageCount;
            }
        }
        //总数据
        List<User> list = userService.listAll(name, page);
        request.setAttribute("list", list);
        request.setAttribute("username", name);
        //当前页
        request.setAttribute("page", page);
        //总记录数
        request.setAttribute("count", count);
        //总页数
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]>map= request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.addUser(user);
        response.sendRedirect("/user/list");
    }

    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("userName");
        if (StringUtils.isEmpty(name)) {
            return;
        }
        boolean b = userService.getUserByUserName(name);
        if (b) {
            out.write("1");
        } else {
            out.write("0");
        }
        out.close();
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        if(StringUtils.isEmpty(id)){
            return;
        }
        User user=userService.getUserById(Integer.valueOf(id));
        List<Dept> deptList= deptService.listAll();
        request.setAttribute("user",user);
        request.setAttribute("deptList",deptList);
        request.getRequestDispatcher("/jsp/user/update.jsp").forward(request,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        if(StringUtils.isEmpty(id)){
            return;
        }
        userService.delete(Integer.valueOf(id));
        response.sendRedirect("/user/list");
    }


}
