package com.connor.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * URL Test
 * @author connor_zeng
 */
public class URLConnection {
	
	public static void main(String[] args) {
		
		try {
			URL baidu = new URL("http://www.baidu.com");
			java.net.URLConnection bdc = baidu.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(bdc.getInputStream()));
			String readLine;
			while ((readLine = br.readLine()) != null){
				System.out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
