package com.connor.basic.proxy.impl;

import com.connor.basic.proxy.ForumService;
import com.connor.util.PerformanceMonitor;
import com.connor.util.SleepUtils;

public class ForumServiceImpl implements ForumService{
	
	
	
	@Override
	public void removeTopic(long topicId) {
		PerformanceMonitor.begin("removeTopic");
		SleepUtils.second(1);
		PerformanceMonitor.end();
	}
	@Override
	public void removeForum(long forumId) {
		PerformanceMonitor.begin("removeForum");
		SleepUtils.second(1);
		PerformanceMonitor.end();
	}
}
