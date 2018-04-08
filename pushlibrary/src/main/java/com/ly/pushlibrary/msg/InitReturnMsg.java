package com.ly.pushlibrary.msg;

/**
 * 
 * @描述 : 初始化返回
 * @创建者：yuanwei
 * @创建时间： 2018年3月26日下午3:51:42
 *
 */
public class InitReturnMsg extends BaseMsg {
	
    public InitReturnMsg() {
        super();
        setType(MsgType.INITRETURN);
    }
    
}
