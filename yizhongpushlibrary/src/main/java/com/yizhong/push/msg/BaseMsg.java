package com.yizhong.push.msg;

import java.io.Serializable;

/**
 * 
 * @描述 : 消息基类
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:43:39
 * 必须实现序列,serialVersionUID 一定要有
 */
public abstract class BaseMsg  implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	//用户名，每种消息处理时都需要验证
    private String username;
	//密码，每种消息处理时都需要验证
    private String password;
    //消息类型
    private MsgType type;
    //必须唯一，否者会出现channel调用混乱
    private String registerId;
    
	public BaseMsg() {
		super();
		username = NettyConstants.USERNAME;
		password = NettyConstants.PASSWORD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
    
}
