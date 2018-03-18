package com.connor;

import com.connor.bean.People;
import com.connor.bean.Person;

import java.util.HashMap;
import java.util.Map;

public  class TestMain {
	
	private  String test = "";
	
	
	public static void main(String[] args) {
		
		//
		
		
		
		/*// 静态代码测试
		ForumServiceImpl fs = new ForumServiceImpl();
		fs.removeForum(12l);
		fs.removeTopic(12l);
		
		// JDK动态代理测试 
		ForumServiceBlankImpl fsb = new ForumServiceBlankImpl();
		ForumHandler handler = new ForumHandler(fsb);
		ForumService fsbProxy = (ForumService) Proxy.newProxyInstance(fsb.getClass().getClassLoader(),fsb.getClass().getInterfaces(), handler);
		fsbProxy.removeForum(55l);
		fsbProxy.removeTopic(5l);*/
		
		// CGlib
		/*ForumImplMethodProxy proxy = new ForumImplMethodProxy();
		ForumServiceBlankImpl fsService = (ForumServiceBlankImpl) proxy.getProxy(ForumServiceBlankImpl.class);
		fsService.removeForum(5l);
		fsService.removeTopic(5l);*/
		
		//HashCode
		Person p1 = new Person("凯包子",7);
		Person p2 = new Person("凯包子",7);
		System.out.println(p1.equals(p2) + " P1,hashCode:" + p1.hashCode() + " P2,hashCode:" + p2.hashCode());
		People peo1 = new People("凯包子",7);
		Map map =new  HashMap<Object, String>();
		map.put(p1, "222");
		map.put(p2, "333");
		System.out.println(map.get(p1));
		System.out.println(map.get(p2));
		System.out.println(map.get(new Person("凯包子",7)));
		System.out.println(map.get(new People("凯包子",7)));
		
		
		byte s12 = 2;
		short s1 = 1;
		byte b1 = 1;
		char char1 = 'A';
		long long1 = 11l;
		Integer i1 = new Integer(123333);
		System.out.println(Integer.class.isPrimitive());
		System.out.println();
		Integer i12 = new Integer(1);
		int i2 = 1;
		System.out.println(i1 == i12);
		System.out.println(i1 == i2);
		Double d1 = new Double(1);
		
		System.out.println(getName());
		
		
	}
	
	private static String getName(){
		try {
			System.out.println("hello1");
			return "connor";
		} finally {
			System.out.println("hello");
		}
	}
}
