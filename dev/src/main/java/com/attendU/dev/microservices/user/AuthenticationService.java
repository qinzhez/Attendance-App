package com.attendU.dev.microservices.user;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.attendU.dev.microservices.bean.TokenBean;

public class AuthenticationService {
	private UserMapper userMapper;
	private SqlSession sqlSession;
	private static String SECRET = "";

	public AuthenticationService(UserMapper mapper, SqlSession session) {
		userMapper = mapper;
		sqlSession = session;
	}

	/**
	 * Login a user, check the username and password, valid the token.
	 * @param username
	 * @param password
	 * @return result of login or not
	 */
	public Token login(String username, String password) {
		Token token = get(username, password);
		if (token == null)
			return null;

		if (validToken(token))
			return null;

		return token;

	}

	public boolean login(Token token) {
		if(validToken(token))
			return true;
		return false;
	}

	private Token get(String username, String password) {
		Map<String, Object> userInfo = userMapper.getUserbyName(username);
		if (!userInfo.get("password").equals(password))
			return null;

		TokenBean tokenBean = userMapper.getAuth((long) userInfo.get("uid"));

		Token ret;
		if (tokenBean == null)
			ret = new Token(SECRET, (long) userInfo.get("uid"));
		else
			ret = new Token(SECRET, tokenBean);
		return ret;
	}

	private boolean validToken(Token token) {
		if (token == null)
			return false;

		TokenBean tokenBean = userMapper.getAuth(token.getUid());

		if (tokenBean == null)
			return false;

		if (!tokenBean.getToken().equals(token.getToken()))
			return false;

		if (!tokenBean.getExpiration().after(new Date()))
			return false;
		return true;
	}
}
