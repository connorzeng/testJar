package com.connor2.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class NIOClient {
	
	private static int blockSize = 4096;
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private static Selector selector;
	static int flag = 1;
	
	public static void main(String[] args) throws IOException {
		
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		selector = Selector.open();
		sc.register(selector, SelectionKey.OP_CONNECT);
		sc.connect(new InetSocketAddress(8080));
		Set<SelectionKey> selectKeys = null;
		while(true){
			selector.select();
			selectKeys = selector.selectedKeys();
			Iterator<SelectionKey> itrerators =  selectKeys.iterator();
			while(itrerators.hasNext()){
				SelectionKey key = itrerators.next();
				handle(key);
			}
		}
	}

	private static void handle(SelectionKey key) throws IOException {
		SocketChannel client = null;
		if (key.isConnectable()){
			client = (SocketChannel)key.channel();
			System.out.println("客户端已经连接");
			if (client.isConnectionPending()){
				client.finishConnect();
				sendBuffer.clear();
				sendBuffer.put("我是客户端".getBytes());
				sendBuffer.flip();
				client.write(sendBuffer);
			}
			//注册一个OP_WRITE,让客户端的key.isReadable()监听到
			client.register(selector, SelectionKey.OP_READ);
		}else if (key.isReadable()){
			client = (SocketChannel) key.channel();
			StringBuffer buff = new StringBuffer();
			int count = 0;
			receiveBuffer.clear();
			while ((count = client.read(receiveBuffer)) > 0){
				buff.append(new String(receiveBuffer.array(),0,count));
			}
			System.out.println(buff.toString());
			//注册一个OP_WRITE,让客户端的key.isReadable()监听到
			client.register(selector, SelectionKey.OP_WRITE);
		}else if (key.isWritable()){
			client = (SocketChannel) key.channel();
			//向客户端写数据
			String sendText = "i am from client,flag = " + flag++;
			sendBuffer.clear();
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();
			client.write(sendBuffer);
			client.close();
			//注册一个OP_WRITE,让客户端的key.isReadable()监听到
			//client.register(selector, SelectionKey.OP_READ);
		}
		
	}
}
