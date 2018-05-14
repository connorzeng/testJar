package com.connor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.connor.bean.People;
import com.connor.bean.Person;

public  class TestMain {
	
	private  String test = "";
	
	public static void main(String[] args) throws InterruptedException {
		
		Father son = new Son();
		son.print();
		
		List<String> a = new ArrayList<String>();
        a.add(new String("1aaaaa"));
        List<String> b = new LinkedList<String>();
        b.add(new String("1bbbbb"));
        b.add(new String("1bbbbb"));
        //LinkedList,不能在遍历的时候进行remove,长度会出现错误,遗漏数据
        for (String c:b){
             b.remove(c);
        }
        //ArrayList不能在遍历的时候进行remove,抛出异常.
        for (String c:a){
             a.remove(c);
        }
  
		
		//TestMap
		long one = System.currentTimeMillis();
		Map<Integer,String> treeMap = new TreeMap<Integer,String>();
		for (int i=0; i<10000; i++){
			//treeMap.put(i,"一");
		}
		//treeMap.put(null, "");
		long two = System.currentTimeMillis();
		Map<Integer,String> HashMap = new HashMap<Integer,String>();
		for (int i=0; i<10000000; i++){
			HashMap.put(i,"一");
		}
		long three = System.currentTimeMillis();
		treeMap.get(2000);
		HashMap.get(2000);
		System.out.println("trueMap:" + (two-one) + ";HashMap:" + (three - two ));
		
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

class Father{
	
	public Father() {
		init();
	}

	protected void init() {
	}

	public void print() {
		System.out.println("2");
	}
}

class Son extends Father{
	private String b1 = null;
	
	protected void init(){
		this.b1 = "0";
		print();
	}
	
	public void print() {
		System.out.println(b1);
	}
}
