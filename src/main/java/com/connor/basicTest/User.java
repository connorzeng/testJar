package com.connor.basicTest;

public class User implements Cloneable{
	
	
	public static void main(String[] args) {
		
		
		User u = new User();
		try {
			User x = (User) u.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
