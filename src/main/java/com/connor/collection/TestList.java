package com.connor.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestList {
	
	public static void main(String[] args) {
		
		Set<Connor> sets = new HashSet<>();
		sets.add(new Connor("1",1));
		sets.add(new Connor("2",2));
		sets.add(new Connor("3",3));
		sets.add(new Connor("4",4));
		sets.add(null);
		sets.add(null);
		sets.remove(null);
		
		//SET里面插入NULL值, 会陷入死循环
		Iterator<Connor> i = sets.iterator();
		while(i.hasNext()){
			System.out.println(i.next().getAge());
		}
		
		
		/*for (Connor connor:sets){
			if (connor != null)
			if (connor.getName().equals("1")){
				sets.remove(new Connor("4",4));
			}
			sets.remove(null);
		}*/
		System.out.println(sets);
		
		/*a.add(new Connor("2",2));
		a.add(new Connor("3",3));
		a.add(new Connor("4",4));*/
		/*for (int j=0;j<a.size();j++){
			a.remove(a.get(j));
		}*/
		
		
		//不能在遍历容器的时候进行remove,
		//遍历容器时需要谨慎进行add操作
		List<Connor> a = new ArrayList<Connor>();
		a.add(new Connor("1aaaaa",1));
		List<Connor> b = new LinkedList<Connor>();
		b.add(new Connor("1bbbbb",1));
		b.add(new Connor("1bbbbb",2));
		//LinkedList,不能在遍历的时候进行remove,长度会出现错误,遗漏数据
		for (Connor c:b){
			b.remove(c);
		}
		//ArrayList不能在遍历的时候进行remove,抛出异常.
		for (Connor c:a){
			a.remove(c);
		}
		
		
		
		//HashMap可以使用null为key或者value
		Map<String,Object> map = new HashMap<>();
		map.put(null, "1");
		map.put("1", null);
		
		System.out.println(map.get(null));
		System.out.println(map.get("1"));
		
		map.remove(null);
		System.out.println(map.get(null));
		
	}
}


class Connor{
	public Connor(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}