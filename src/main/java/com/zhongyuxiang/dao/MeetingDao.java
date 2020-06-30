package com.zhongyuxiang.dao;

import com.zhongyuxiang.entity.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/30
 * @Description
 */
public class MeetingDao extends BaseDao {
    public List<Meeting> listAll(Meeting m, Page page){
        String sql="";
        return template.query(sql, new BeanPropertyRowMapper<>(Meeting.class),
                "%" + m + "%", (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
    }
}
