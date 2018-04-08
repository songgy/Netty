package com.yizhong.push.msg;

/***
 * 
 * @描述 : 初始化
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:49:20
 *
 */
public class InitMsg extends BaseMsg {
	
	//IOS,ANDROID
	private String app;
	//设备码
	private String device;
	
    public InitMsg() {
        super();
        setType(MsgType.INIT);
    }

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
    
}
