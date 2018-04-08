package com.ly.pushlibrary.msg;

/**
 * 
 * @描述 : 推送消息
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:54:27
 *
 */
public class PushMsg extends BaseMsg {
	
	private String auth;
	private String text;
	
    public PushMsg() {
        super();
        setType(MsgType.PUSH);
    }

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
}
