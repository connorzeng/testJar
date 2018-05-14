package com.connor2.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
		
	
	private int blockSize = 4096;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private Selector selector;
	int flag;
	
	public NIOServer(int port) throws IOException{
		//1.打开serverSocketChannel, 设置是否阻塞
		ServerSocketChannel serverSocketChannel =  ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		//2.绑定IP和端口
		serverSocket.bind(new InetSocketAddress(port));
		//3.注册selcector
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("server start");
	}
	
	//4.监听
	public void listen() throws IOException{
		while(true){
			selector.select();
			Set<SelectionKey> selectKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator =  selectKeys.iterator();
			while(iterator.hasNext()){
				SelectionKey selectKey = iterator.next();
				selectKeys.remove(selectKey);
				handler(selectKey);
			}
		}
	}
	
	//开启NIOServer
	public static void main(String[] args) throws IOException {
		new NIOServer(8080).listen();
	}

	private void handler(SelectionKey selectKey) throws IOException {
		ServerSocketChannel ssc = null;
		SocketChannel clientChannel = null;
		if (selectKey.isAcceptable()){
			//注册客户端端
			ssc = (ServerSocketChannel) selectKey.channel(); 
			clientChannel = ssc.accept(); 
	        clientChannel.configureBlocking(false); 
	        clientChannel.register(selector, SelectionKey.OP_READ); 
	        System.out.println("a new client connected"); 
		} else if (selectKey.isReadable()){
			handleRead(clientChannel, selectKey);
		} else if (selectKey.isWritable()){
			clientChannel = (SocketChannel) selectKey.channel();
			//向客户端写数据
			String sendText = "i am from server,flag = " + flag++;
			sendBuffer.clear();
			sendBuffer.put(sendText.getBytes());
			sendBuffer.flip();
			clientChannel.write(sendBuffer);
			//注册一个OP_READ,让客户端的key.isReadable()监听到
			clientChannel.register(selector, SelectionKey.OP_READ);
		}
	}

	private void handleRead(SocketChannel clientChannel,SelectionKey selectKey) throws IOException {
		//读取客户端数据
		clientChannel = (SocketChannel) selectKey.channel();
		StringBuffer buff = new StringBuffer();
		int count = 0;
		receiveBuffer.clear();
		while ((count = clientChannel.read(receiveBuffer)) > 0){
			buff.append(new String(receiveBuffer.array(),0,count));
		}
		System.out.println(buff.toString());
		//注册一个OP_WRITE,让客户端的key.isReadable()监听到
		clientChannel.register(selector, SelectionKey.OP_WRITE);
	}
}
