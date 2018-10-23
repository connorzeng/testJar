package com.connor.high.lambda;

/**
 * 签到流水
 * @author connor
 *
 */
public class ClockIn {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String id;
	
	private String userName;
	
	private String userId;
	
	
	
}
