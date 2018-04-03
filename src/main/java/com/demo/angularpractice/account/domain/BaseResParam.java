package com.demo.angularpractice.account.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回的基类
 *
 * @author dzy
 */
@Getter
@Setter
public class BaseResParam implements Serializable {
    private static final long serialVersionUID = -7047962066851484973L;

    /**
     * 为空时，是否抛异常
     */
    protected Boolean isNullError = true;

    /**
     * 分页当前页数（从1开始）
     */
    protected Integer pageCurrent;
    /**
     * 分页每页个数
     */
    protected Integer pageSize;

    /**
     * 时间范围查询参数--开始时间
     */
    protected Date beginTime;

    /**
     * 时间范围查询参数--结束时间
     */
    protected Date endTime;
    /**
     * 返回查询的总数
     */
    protected String count;

    /**
     * 排序字段
     */
    protected String orderField;

    /**
     * 返回字符串形式时间 格式yyyy-MM-dd HH24:mm:ss
     */
    protected String stringTime;


}
