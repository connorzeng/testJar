package com.connor.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.connor.util.PerformanceMonitor;

public class ForumHandler implements InvocationHandler{
	
	private Object target;
	
	public ForumHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		PerformanceMonitor.begin(proxy.getClass().getName() + " method:" + method.getName());
		Object result = method.invoke(target, args);
		PerformanceMonitor.end();
		return result;
	}
	
	public String getName(String name){
		return null;
	}
	public String getName(){
		return null;
	}
	
}
