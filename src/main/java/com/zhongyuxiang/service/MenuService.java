package com.zhongyuxiang.service;

import com.zhongyuxiang.entity.Menu;
import com.zhongyuxiang.dao.MenuDao;

import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
