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
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    private DeptService deptService = new DeptService();

    /***
     * @decription 查询
     * @author zyx
     * @date 2020/6/26 18:40
     * @params [request, response]
     * @return void
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String name = request.getParameter("username");
        name = name == null ? "" : name;
        //当前页
        String pageStr = request.getParameter("page");

        request.setAttribute("username", name);

        request.setAttribute("page", userService.listAll(name, pageStr));
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }

//    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //查询条件
//        String name = request.getParameter("username");
//        name = name == null ? "" : name;
//
//        Page page = new Page();
//
//        String pageStr = request.getParameter("page");
//        if (!StringUtils.isEmpty(pageStr)) {
//            page.setPageCurrent(Integer.valueOf(pageStr));
//        }
//
//        //总记录数
//        Integer count = userService.getCount();
//        page.setCount(userService.getCount());
//
//        //总数据
//        List<User> list = userService.listAll(name, page);
//
//        request.setAttribute("list", list);
//        request.setAttribute("username", name);
//        request.setAttribute("page", page);
//        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
//    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.addUser(user);
        response.sendRedirect("/user/list");
    }

    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("userName");
        //name==null || "".equals(name)
        if (StringUtils.isEmpty(name)) {
            return;
        }
        boolean b = userService.getUserByUserName(name);
        if (b) {
            out.write("1");
        } else {
            //已存在
            out.write("0");
        }
        out.close();
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数据回显
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        User user = userService.getUserById(Integer.valueOf(id));
        List<Dept> deptList = deptService.listAll();

        request.setAttribute("user", user);
        request.setAttribute("deptList", deptList);
        request.getRequestDispatcher("/jsp/user/update.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        userService.delete(Integer.valueOf(id));
        response.sendRedirect("/user/list");
    }

}
