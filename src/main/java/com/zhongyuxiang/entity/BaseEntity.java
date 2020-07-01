package com.zhongyuxiang.entity;

import java.io.Serializable;

/**
 * @auth zyx
 * @date 2020/6/30
 * @Description
 */
public class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1561563135468L;

    /*
     * 创建时间
     */
    private String createTime;

    /*
     * 创建人
     */
    private Integer createBy;

    /*
     * 是否删除 1是，0否
     */
    private Integer delFlag;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
