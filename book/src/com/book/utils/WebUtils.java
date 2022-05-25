package com.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T>T copyParamToBean(T bean, Map value){
        try {
            BeanUtils.populate(bean,value);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int parseInt(String strInt,int defaultValue) {
        try {
             return Integer.parseInt(strInt);
        } catch (Exception e) {
      //      e.printStackTrace();
        }
        return defaultValue;
    }
}
