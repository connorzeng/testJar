package com.connor.proxy.impl;

import com.connor.proxy.ForumService;
import com.connor.tool.PerformanceMonitor;
import com.connor.tool.SleepUtils;

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
