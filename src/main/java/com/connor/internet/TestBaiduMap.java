package com.connor.internet;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.connor.util.HttpUtils;

/**
 * 百度地图API请求
 * @author connor_zeng
 *
 */
public class TestBaiduMap {
	

	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("location", "39.9,116.30");
		params.put("key", "X6ABZ-KUEKQ-2SV5P-G745A-BPBQT-TFBS6");
		params.put("get_poi", "0");
		String url = "http://apis.map.qq.com/ws/geocoder/v1/";
		String reslult = HttpUtils.URLGet(url, params, "utf-8");
		JSONObject ob = (JSONObject)JSONObject.parse(reslult);
		if ("0".equals(ob.get("status").toString())){
			JSONObject component =  ob.getJSONObject("result").getJSONObject("address_component");
			System.out.println(component.get("province"));
		}
		
		getTest();
		
		
		System.out.println("用时:" + ((System.currentTimeMillis() - begin)/1000)+ " 秒");
		
	}

	private static void getTest() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("location", "34.9,116.30");
		params.put("key", "HUOBZ-ET3RF-Z5XJO-JHFLN-5MIXS-UXBYJ");
		params.put("get_poi", "0");
		String url = "http://apis.map.qq.com/ws/geocoder/v1/";
		String reslult = HttpUtils.URLGet(url, params, "utf-8");
		JSONObject ob = (JSONObject)JSONObject.parse(reslult);
		if ("0".equals(ob.get("status").toString())){
			JSONObject component =  ob.getJSONObject("result").getJSONObject("address_component");
			System.out.println(component.get("province"));
		}
	}
	
	
	
	
}
