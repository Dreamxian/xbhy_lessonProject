package com.zhongyuxiang.service;

import com.zhongyuxiang.dao.UserDao;
import com.zhongyuxiang.entity.Page;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.utils.MdUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/24
 * @Description
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /***
     * @decription 查询用户
     * @author zyx
     * @date 2020/6/26 17:33
     * @params []
     * @return java.util.List<com.zhongyuxiang.entity.User>
     */
    public Page listAll(String name, String pageStr) {
        Page page = new Page<User>();
        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPageCurrent(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(userDao.getCount(name));
        //总数据
        List<User> list = userDao.listAll(name, page);
        page.setData(list);
        return page;
    }

//    public List<User> listAll2(String name, Page page) {
//        return userDao.listAll(name, page);
//    }

//    public Integer getCount() {
//        return userDao.getCount();
//    }

    public void addUser(User user) {
        user.setId(null);
        user.setPassword(MdUtil.md5(user.getPassword()));
        user.setRegisterTime(new Date());
        userDao.addUser(user);
    }

    /***
     * @decription 验证用户名是否存在
     * @author zyx
     * @date 2020/6/26 19:50
     * @params [userName]
     * @return com.zhongyuxiang.entity.User
     */
    public boolean getUserByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            return true;
        }
        //账号已存在
        return false;
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public  User checkLogin(String name,String password){
        User user=new User();
        user.setUsername(name);
        user.setPassword(MdUtil.md5(password));
        return userDao.checkLogin(user);
    }


    /***
     * @decription 根据账号修改密码
     * @author zyx
     * @date 2020/6/28  15:20
     * @params [userName]
     * @return void
     */
    public void updatePs(String username,String newPs){
        userDao.updatePs(username,newPs);
    }

    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id, pic);
    }

    public User getUserWXOpenId(String wxOpenid) {
       return userDao.getUserWXOpenId(wxOpenid);
    }
}
