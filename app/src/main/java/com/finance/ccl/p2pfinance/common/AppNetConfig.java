package com.finance.ccl.p2pfinance.common;

/**
 * Created by ccl on 18-4-11.
 * 配置程序当中的接口请求类地址
 *
 */

public class AppNetConfig {
    public static final String HOST = "192.168.56.1";

    public static final String BASEURL = "http://"+HOST+":8080/P2PInvest/";

    public static final String LOGIN = BASEURL+"login";

    public static final String PRODUCT = BASEURL+"product";

    public static final String INDEX = BASEURL+"index";

    //..............................................
}
