package com.neusoft.security.rabc;

import java.util.HashMap;
import java.util.List;

import com.neusoft.security.rabc.entity.UserDetailsInfo;

public interface UserResourceInterface {
	/**
	 * 用户资源
	 * @return
	 */
	public List<HashMap<String, String>> setUserResource();
	
	/**
	 * 用户信息
	 * @return
	 */
	public UserDetailsInfo setUserInfo();
}
