package com.zhongyuxiang.dao;

import com.zhongyuxiang.entity.Dept;
import com.zhongyuxiang.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/23
 * @Description
 */
public class DeptDao extends BaseDao {
    public List<Dept> listAll(){
        String sql="select * from dept";
       return template.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }


}
