package com.demo.mq.vo;

import java.io.Serializable;

public class ParamVo implements Serializable {

	private static final long serialVersionUID = 5982892641450425814L;
	private Long userId;
	private String userName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
