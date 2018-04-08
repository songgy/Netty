package com.ly.pushlibrary.util;


import com.ly.pushlibrary.msg.NettyConstants;

/**
 * 
 * @描述 : 推送工具类
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午4:12:49
 *
 */
public class PushUtil {
	
	//客户端在线状态（客户端单线程）
    public static String state;
    //客户端registerId（客户端单线程）
    public static String registerId;
    //客户端心跳数（客户端单线程）
    public static int heartbeat = 0;
	
	/**
	 * 
	 * @描述 : 返回在线状态
	 * @创建者：yuanwei
	 * @创建时间： 2018年3月27日下午3:14:34
	 * @用于：
	 *
	 * @return
	 */
	public static String getState() {
		if (heartbeat >= NettyConstants.dead) {
			state = NettyConstants.OFFLINE;
		} else {
			state = NettyConstants.ONLINE;
		}
		return state;
	}
	
	/**
	 * 
	 * @描述 : 客户端是否在线
	 * @创建者：yuanwei
	 * @创建时间： 2018年3月27日下午3:24:15
	 * @用于：
	 *
	 * @return
	 */
	public static boolean showOnOff() {
		if (heartbeat >= NettyConstants.dead) {
			return false;
		} else {
			return true;
		}
	}

}
