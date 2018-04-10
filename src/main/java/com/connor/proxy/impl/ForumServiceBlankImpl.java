package com.connor.proxy.impl;

import com.connor.proxy.ForumService;
import com.util.SleepUtils;

public final class ForumServiceBlankImpl implements ForumService{

	@Override
	public void removeTopic(long topicId) {
		System.out.println("removeTopic");
		SleepUtils.second(2);;
	}

	@Override
	public void removeForum(long forumId) {
		System.out.println("removeForum");
		SleepUtils.second(2);
	}
	
	
	/**
	 * Only for test
	 */
	protected void getName(){
		
	}
}
