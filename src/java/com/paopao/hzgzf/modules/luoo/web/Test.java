package com.paopao.hzgzf.modules.luoo.web;

import java.nio.charset.Charset;

/**
 * @author Luoo2
 * @create 2016-07-21 22:45
 */

public class Test {

    public static void main(String[] args) {
        System.out.println("当前JRE：" + System.getProperty("java.version"));
        System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset());
    }
}
