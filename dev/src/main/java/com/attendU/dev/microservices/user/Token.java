package com.attendU.dev.microservices.user;

import com.attendU.dev.microservices.bean.TokenBean;

import lombok.Getter;

public class Token {
	@Getter
	private long uid;
	@Getter
	private String token;
	private String secret;

	public Token(String sec, long id) {
		uid = id;
		secret = sec;
	}

	public Token(String sec, TokenBean tokenBean) {
		uid = tokenBean.getUid();
		token = tokenBean.getToken();
	}

}
