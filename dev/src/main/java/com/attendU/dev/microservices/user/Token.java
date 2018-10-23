package com.attendU.dev.microservices.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Date;

import com.attendU.dev.microservices.bean.TokenBean;

import lombok.Getter;

public class Token {
	@Getter
	private long uid;
	@Getter
	private String token;
	private String secret;

	@Getter
	private Date createTime;
	@Getter
	private Date expiration;

	public Token(String sec, long id) {
		uid = id;
		secret = sec;
		createTime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createTime);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		expiration = calendar.getTime();
		token = tokenGenerate(Long.toString(createTime.getTime()), Long.toString(uid));
	}

	public Token(String sec, TokenBean tokenBean) {
		uid = tokenBean.getUid();
		token = tokenBean.getToken();
		secret = sec;
		createTime = tokenBean.getCreateTime();
		expiration = tokenBean.getExpiration();
	}

	private String tokenGenerate(String time, String uid) {
		String token = time+secret+uid;
        String tokenMd5="";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            Encoder base = Base64.getEncoder();
            tokenMd5 = base.encodeToString(md5);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return tokenMd5;
	}
}
