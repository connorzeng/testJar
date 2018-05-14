package com.connor2.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class BioClient {

	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		Socket client = new Socket("localhost", 8080);
		//获取网络输入流
		//BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
		InputStream in = client.getInputStream();
		
		//获取网络输出流
		PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		
		//屏幕输入
		BufferedReader pr = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
		
		while(true){
			String inStr = pr.readLine();
			
			out.print(inStr);
			out.flush();
			if (inStr.equals("end")) {  
                break;  
            }
			byte[] buffer = new byte[1024];
			in.read(buffer);
			String sa = new String(buffer);
			System.out.println(sa);
		}
		
		client.close();  
	}

}
