package com.zhongyuxiang.dao;

import com.zhongyuxiang.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/23
 * @Description
 */
public class UserDao extends BaseDao {
    public List<User> listAll(String name,Integer page){
        String sql="select * from user where username like ? limit ?,?";
       return template.query(sql,new BeanPropertyRowMapper<>(User.class),"%"+name+"%",(page-1)*5,5);
    }

    public Integer getCount(){
        String sql="select count(*) from user";
        try {
            return template.queryForObject(sql,Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

    public void addUser(User user){
        String sql="INSERT INTO user (username,password,email,real_name,age,sex,desciption,register_time,dept_id)"+
                "VALUES(?,?,?,?,?,?,?,?,?) ";
        template.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getRealName(),user.getAge(),user.getSex(),user.getDescription(),user.getRegisterTime(),user.getDeptId());
    }

    public User getUserByUserName(String userName){
        String sql="SELECT * from user where username=?";
        try {
            return  template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),userName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Integer id){
        String sql="SELECT * from user where id=?";
        try {
            return  template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Integer id){
        String sql="delete from user where id=?";
        template.update(sql,id);
    }
}
