package com.zhongyuxiang.service;

import com.zhongyuxiang.dao.UserDao;
import com.zhongyuxiang.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/23
 * @Description
 */
public class UserService {
    private UserDao userDao=new UserDao();

    public List<User> listAll(String name,Integer page){
        return userDao.listAll(name,page);
    }

    public Integer getCount(){
        return userDao.getCount();
    }

    public void addUser(User user){
        user.setId(null);
        Date date=new Date();
        user.setRegisterTime(date);
        userDao.addUser(user);
    }

    public boolean getUserByUserName(String userName){
        User user=userDao.getUserByUserName(userName);
        if(user==null){
            return true;
        }
       return false;
    }

    public User getUserById(Integer id){
         return userDao.getUserById(id);
    }

    public void delete(Integer id){
        userDao.delete(id);
    }
}
