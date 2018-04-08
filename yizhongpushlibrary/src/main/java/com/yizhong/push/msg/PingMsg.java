package com.yizhong.push.msg;

/**
 * 
 * @描述 : 心跳检测的消息类型
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:54:08
 *
 */
public class PingMsg extends BaseMsg {
	
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
    
}
