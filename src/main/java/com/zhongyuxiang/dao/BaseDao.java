package com.zhongyuxiang.dao;

import com.zhongyuxiang.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
public class BaseDao {

    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

}
