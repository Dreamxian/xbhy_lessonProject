package com.zhongyuxiang.service;

import com.zhongyuxiang.dao.DeptDao;
import com.zhongyuxiang.entity.Dept;


import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/23
 * @Description
 */
public class DeptService {

    private DeptDao deptDao = new DeptDao();

    public List<Dept> listAll() {
        return deptDao.listAll();
    }

}
