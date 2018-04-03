package com.demo.angularpractice.account.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求的基类
 *
 * @author dzy
 */
@Getter
@Setter
public class BaseReqParam implements Serializable {
    private static final long serialVersionUID = 2080391103044981101L;

    /**
     * 为空时，是否抛异常
     */
    protected Boolean isNullError = true;

    /**
     * 模糊搜索的文本
     */

    protected String searchText;


    /**
     * 分页当前页数（从1开始）
     */
    protected Integer pageCurrent = 0;
    /**
     * 分页每页个数
     */
    protected Integer pageSize = 0;

    /**
     * 时间范围查询参数--开始时间
     */
    protected Date beginTime;

    protected String beginTimeStr;

    /**
     * 时间范围查询参数--结束时间
     */
    protected Date endTime;

    protected String endTimeStr;
    /**
     * 返回查询的总数
     */
    protected String count;

    /**
     * 排序字段
     */

    protected String sort;

    /**
     * order By
     */
    protected String orderBy;

    /**
     * 返回字符串形式时间 格式yyyy-MM-dd HH24:mm:ss
     */
    protected String stringTime;
}
