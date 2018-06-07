package com.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.logging.LoggingHandler;

public class ServerTemp {

	public static void main(String[] args) {

		ServerBootstrap server = new ServerBootstrap();
		// 指定线程数为1
		EventLoopGroup parentGroup = new NioEventLoopGroup(1);
		// 指定线程数>=1 && <=CPU Core
		EventLoopGroup childGroup = new NioEventLoopGroup(2);

		server.group(parentGroup, childGroup).handler(new LoggingHandler())
				.childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel channel) throws Exception {
					}
				});
		

	}
}
