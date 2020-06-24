package com.zhongyuxiang.entity;

/**
 * @auth zyx
 * @date 2020/6/24
 * @Description
 */
public class Page {
    //总页数
    private Integer pageCount;
    //记录数
    private Integer count;
    //当前页数
    private Integer pageCurrent;
    //每页显示数量
    private Integer size=5;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
