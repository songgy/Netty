package com.yizhong.push.client;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import com.yizhong.push.helper.BroadcastHelper;
import com.yizhong.push.msg.BaseMsg;
import com.yizhong.push.msg.InitReturnMsg;
import com.yizhong.push.msg.MsgType;
import com.yizhong.push.msg.NettyConstants;
import com.yizhong.push.msg.PingMsg;
import com.yizhong.push.msg.PushMsg;
import com.yizhong.push.util.PushUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;


/**
 * @描述 : 客户端处理类
 * @创建者：yuanwei
 * @创建时间： 2018年3月27日上午11:49:05
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE://一段时间没有写操作10s心跳，发起心跳
                    PingMsg pingMsg = new PingMsg();
                    pingMsg.setRegisterId(PushUtil.registerId);
                    ctx.writeAndFlush(pingMsg);
                    PushUtil.heartbeat += 1;
                    if (PushUtil.showOnOff()) {
                        /**
                         * do something...
                         * 如果过多PING没有回应，则认为掉线，发起登陆
                         * NettyClientBootstrap.init(9999, "127.0.0.1", "ANDROID", "HUAWEI-001");
                         */
                    }
                    break;
                default:
                    break;
            }
        }
    }

/*    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        MsgType msgType = baseMsg.getType();
        switch (msgType) {
            case INIT://客户端发起非INIT请求后，如果服务器判断用户未登陆，则返回INIT，要求登陆
                PushUtil.state = NettyConstants.OFFLINE;
                //NettyClientBootstrap.init(9999, "127.0.0.1", "ANDROID", "HUAWEI-001");
                Log.e("CLIENT-INIT:state=", PushUtil.state);
                break;
            case INITRETURN://登陆成功回应
                InitReturnMsg initReturnMsg = (InitReturnMsg) baseMsg;
                PushUtil.state = NettyConstants.ONLINE;
                PushUtil.registerId = initReturnMsg.getRegisterId();
                PushUtil.heartbeat = 0;
                *//**
     * do somthing...
     * 客户端调用接口保存RegId
     *//*
                Log.e("CLIENTRN:registerId=", PushUtil.registerId);
                break;
            case PING://接受回应心跳
                PushUtil.heartbeat -= 1;
                Log.e("CLIENT-PING:heartbeat=", PushUtil.heartbeat + "");
                break;
            case PUSH:
                //收到服务端的推送
                PushMsg pushMsg = (PushMsg) baseMsg;
                if (NettyConstants.PUSHKEY.equals(pushMsg.getAuth())) {
                    */

    /**
     * do somthing...
     * 广播
     *//*
                    Log.e("CLIENT-PUSH:registerId=", pushMsg.getRegisterId() + ", text=" + pushMsg.getText());
                }
                break;
            default:
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        MsgType msgType = baseMsg.getType();
        Log.e("NettyClientHandler",
                "channelRead0(NettyClientHandler.java:101)" + msgType);
        switch (msgType) {
            case INIT://客户端发起非INIT请求后，如果服务器判断用户未登陆，则返回INIT，要求登陆
                PushUtil.state = NettyConstants.OFFLINE;
                //NettyClientBootstrap.init(9999, "127.0.0.1", "ANDROID", "HUAWEI-001");
                Log.e("CLIENT-INIT:state=", PushUtil.state);
                break;
            case INITRETURN://登陆成功回应
                InitReturnMsg initReturnMsg = (InitReturnMsg) baseMsg;
                PushUtil.state = NettyConstants.ONLINE;
                PushUtil.registerId = initReturnMsg.getRegisterId();
                PushUtil.heartbeat = 0;
                /**
                 * do somthing...
                 * 客户端调用接口保存RegId
                 */
                Log.e("CLIENTRN:registerId=", PushUtil.registerId);
                break;
            case PING://接受回应心跳
                PushUtil.heartbeat -= 1;
                Log.e("CLIENT-PING:heartbeat=", PushUtil.heartbeat + "");
                break;
            case PUSH:
                //收到服务端的推送
                PushMsg pushMsg = (PushMsg) baseMsg;
                if (NettyConstants.PUSHKEY.equals(pushMsg.getAuth())) {
                    /**
                     * do somthing...
                     * 广播
                     */
                    BroadcastHelper.send(NettyClientBootstrap.context,pushMsg.getText());
                    Log.e("CLIENT-PUSH:registerId=", pushMsg.getRegisterId() + ", text=" + pushMsg.getText());
                }
                break;
            default:
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }
}
