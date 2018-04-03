package com.demo.angularpractice.account.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 使用Dozer转换pojo
 *
 * @author dzy
 */
@Component
public class EntityUtils {
    public static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    /**
     * 对象复制,两个Bean之间的属性名称要相同
     *
     * @param source 原对象
     * @param target 要复制到的对象
     */
    public static <T> T copy(Object source, Class<T> target) {
        return dozerBeanMapper.map(source, target);
    }

    /**
     * list复制,两个Bean之间的属性名称要相同
     *
     * @param source 原对象
     * @param target 目标对象
     * @param <T>    目标对象的类型
     * @return
     */
    public static <T> List<T> copyList(Collection source, Class<T> target) {
        ArrayList<T> result = Lists.newArrayList();
        for (Object o : source) {
            T t = dozerBeanMapper.map(o, target);
            result.add(t);
        }
        return result;
    }

}
