package com.connor.basic.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

public class MapTest {

	public static void main(String[] args) {

		Hashtable<HashKey, String> table = new Hashtable<HashKey, String>();
		table.put(new HashKey("2"), "hello");
		table.put(new HashKey("3"), "silly");
		System.out.println(table.get(new HashKey("2")));//必须重写equals方法,必须要重新HashCode
		
		HashMap<HashKey, String> hashMap = new HashMap<HashKey, String>();
		hashMap.put(new HashKey("2"), "hello");
		hashMap.put(new HashKey("3"), "silly");
		System.out.println(hashMap.get(new HashKey("2")));
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
	}
}


class HashKey{
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashKey(String id){
		this.id = id;
	}
	
	/*@Override
	public boolean equals(Object obj) {
		if (obj instanceof HashKey){
			HashKey temp = (HashKey) obj;
			if (temp.getId().equalsIgnoreCase(this.getId())){
				return true;
			}
		}
		return false;
	}*/

	@Override
	public int hashCode() {
		return Integer.valueOf(id) + 100;
	}
}