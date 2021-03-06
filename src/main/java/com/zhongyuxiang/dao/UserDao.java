package com.zhongyuxiang.dao;

import com.zhongyuxiang.entity.Page;
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

    public List<User> listAll(String username, Page page) {
        String sql = "SELECT " +
                " d.name deptName, " +
                " u.id id, " +
                " u.dept_id deptId, " +
                " u.username username, " +
                " u.email email, " +
                " u.real_name realName, " +
                " u.age age, " +
                " u.sex sex, " +

                "case when sex=1 then \"男\" " +
                " when sex=0 then \"女\" " +
                " else \"其他\" end sexName," +

                " u.desciption desciption, " +
                " u.register_time registerTime  " +
                "FROM " +
                " USER u " +
                " LEFT JOIN dept d ON u.dept_id = d.id  " +
                "WHERE " +
                " username LIKE ?  LIMIT ?,?";

        return template.query(sql, new BeanPropertyRowMapper<>(User.class),
                "%" + username + "%", (page.getPageCurrent() - 1) * page.getSize(), page.getSize());

    }

    public Integer getCount(String username) {
        String sql = "select count(*) from user where username like ?";
        try {
            return template.queryForObject(sql, Integer.class, "%" + username + "%");
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public void addUser(User user) {
        String sql = "INSERT INTO USER ( username, password, email, real_name, age, sex, desciption, register_time,dept_id,wx_openid )" +
                "values (?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(),
                user.getAge(), user.getSex(), user.getDescription(), user.getRegisterTime(), user.getDeptId(),user.getWxOpenid());
    }

    public User getUserByUserName(String userName) {
        String sql = "select * from user where username=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public User getUserById(Integer id) {
        String sql = "select * from user where id=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    public  User checkLogin(User user){
        String sql = "select * from user where username=? and password=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updatePs(String username, String newPs) {
        String sql = "update user set password=? where username=? ";
        template.update(sql, newPs, username);
    }

    public void updatePic(Integer id, String pic) {
        String sql = "update user set pic=? where id=? ";
        template.update(sql, pic, id);
    }


    public User getUserWXOpenId(String wxOpenid) {
        String sql = "select * from user where wx_openid=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), wxOpenid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<User> listForExport(String username){
        String sql="SELECT "+
                "d.name deptName,"+
                "u.username username,"+
                "u.real_name realName,"+
                "u.age age,"+
                "u.sex sex, "+
                "case when sex=1 then \"男\" "+
                " when sex=0 then \"女\" "+
                " else \"其他\" end sexName "+
                "FROM "+
                "user u "+
                "LEFT JOIN dept d ON u.dept_id=d.id "+
                "WHERE "+
                "u.username LIKE ?";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),"%"+username+"%");
    }
}
