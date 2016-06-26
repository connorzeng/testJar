package com.connor.proxy.impl;

import com.connor.proxy.ForumService;
import com.util.SleepUtils;

public class ForumServiceBlankImpl implements ForumService{

	@Override
	public void removeTopic(long topicId) {
		
		SleepUtils.second(2);;
	}

	@Override
	public void removeForum(long forumId) {
		SleepUtils.second(2);
	}
	
	
	/**
	 * Only for test
	 */
	protected void getName(){
		
	}
}
