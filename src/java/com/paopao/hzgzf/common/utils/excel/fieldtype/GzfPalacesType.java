/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.utils.excel.fieldtype;

import com.paopao.hzgzf.common.utils.SpringContextHolder;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;

/**
 * 苑转换
 * 
 * @author TU
 * @version $Id: GzfPalacesType.java, v 0.1 2016-3-13 下午12:51:16 TU Exp $
 */
public class GzfPalacesType {

    private static GzfPalacesService gzfPalacesService = SpringContextHolder
                                                           .getBean(GzfPalacesService.class);

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        return gzfPalacesService.getByName(val);
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null && ((GzfPalaces) val).getName() != null) {
            return ((GzfPalaces) val).getName();
        }
        return "";
    }
}
