package com.connor.proxy;

import com.connor.proxy.impl.ForumServiceBlankImpl;

public class TestProxy {

    public static void main(String[] args) {
        /*// 静态代码测试
		ForumServiceImpl fs = new ForumServiceImpl();
		fs.removeForum(12l);
		fs.removeTopic(12l);

		// JDK动态代理测试--只能代理接口
		ForumServiceBlankImpl fsb = new ForumServiceBlankImpl();
		ForumHandler handler = new ForumHandler(fsb);
		ForumService fsbProxy = (ForumService) Proxy.newProxyInstance(fsb.getClass().getClassLoader(),fsb.getClass().getInterfaces(), handler);
		fsbProxy.removeForum(55l);
		fsbProxy.removeTopic(5l);*/

        // CGlib
		ForumImplMethodProxy proxy = new ForumImplMethodProxy();
		ForumServiceBlankImpl fsService = (ForumServiceBlankImpl) proxy.getProxy(ForumServiceBlankImpl.class);
		fsService.removeForum(5l);
		fsService.removeTopic(5l);

		//Spring ProxyFactory封装



    }

}
