package com.connor.basic.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.connor.util.PerformanceMonitor;

public class ForumImplMethodProxy implements MethodInterceptor{
	
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		PerformanceMonitor.begin(obj.getClass().getName() + "--" + method.getName());
		Object result = proxy.invokeSuper(obj, args);
		PerformanceMonitor.end();
		return result;
	}
}
