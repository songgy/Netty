package com.yizhong.push.client;

import android.content.Context;
import android.util.Log;


import com.yizhong.push.msg.InitMsg;
import com.yizhong.push.msg.NettyConstants;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @描述 : 客户端引导程序
 * @创建者：yuanwei
 * @创建时间： 2018年3月27日上午11:39:37
 */
public class NettyClientBootstrap {


    private int port;
    private String host;
    private SocketChannel socketChannel;
    public static Context context;

    public NettyClientBootstrap(Context context, int port, String host) throws InterruptedException {
        this.context = context;
        this.port = port;
        this.host = host;
        start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(host, port);//需要验证
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new IdleStateHandler(20, NettyConstants.heartbeatTime, 0));//需要验证
                socketChannel.pipeline().addLast(new ObjectEncoder());
                socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(new NettyClientHandler());
            }
        });
        ChannelFuture future = bootstrap.connect(host, port).sync();
        if (future.isSuccess()) {
            socketChannel = (SocketChannel) future.channel();
            Log.e("tag", "################## connect server succ ##################");
        }
    }

    /**
     * @param port
     * @param host
     * @param app
     * @param device
     * @throws Exception
     * @描述 : 初始化链接
     * @创建者：yuanwei
     * @创建时间： 2018年3月27日上午11:44:23
     * @用于：
     */
    public static void init(Context context,int port, String host, String app, String device) throws Exception {
        NettyClientBootstrap bootstrap = new NettyClientBootstrap(context,port, host);
        InitMsg initMsg = new InitMsg();
        initMsg.setApp(app);
        initMsg.setDevice(device);
        bootstrap.socketChannel.writeAndFlush(initMsg);
    }

    public static void main(String[] args) throws Exception {
//        NettyClientBootstrap.init(9999, "192.168.18.28", "ANDROID", "HUAWEI-001");
    }

}
