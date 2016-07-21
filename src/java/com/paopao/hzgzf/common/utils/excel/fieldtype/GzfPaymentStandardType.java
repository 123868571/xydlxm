/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.utils.excel.fieldtype;

import com.paopao.hzgzf.common.utils.SpringContextHolder;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;

public class GzfPaymentStandardType {

    private static GzfPaymentStandardService gzfPaymentStandardService = SpringContextHolder
                                                                           .getBean(GzfPaymentStandardService.class);

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        return gzfPaymentStandardService.getByName(val);
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
