package com.demo.angularpractice.account.result;

import com.demo.angularpractice.account.domain.BaseResParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户
 *
 * @author dzy
 */
@Setter
@Getter
public class ResourceResult extends BaseResParam {
    private Integer id;
    private String name;
}
