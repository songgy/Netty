package com.ly.pushlibrary.msg;

/**
 * 
 * @描述 : 常量
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:44:53
 *
 */
public class NettyConstants {
	
	//所有请求需要验证的用户名密码（服务端，客户端）
    public static final String USERNAME = "higo";
    public static final String PASSWORD = "YZkj1995";
    //推送消息时需要验证的Key
    public static final String PUSHKEY = "ec668f68d53a8f00b72a2972f861f4b7";
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    //客户端心跳频率
    public static final int heartbeatTime = 30;
    //客户端失跳多少次算死掉
    public static final int dead = 10;

}
