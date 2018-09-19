package com.neusoft.security.rabc.implents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.neusoft.security.rabc.UserResourceInterface;
import com.neusoft.security.rabc.entity.UserDetailsInfo;

public class NuesoftUserResource implements UserResourceInterface{

	@Override
	public List<HashMap<String, String>> setUserResource() {
		HashMap<String, String> url = new HashMap<>(),url1 = new HashMap<>();
		url.put("url", "/rabc/resourceTest");
		url.put("method", "GET");
		
		url1.put("url","/static/images/");
		url1.put("method", "GET");
		List<HashMap<String, String>> userResource = new ArrayList<>();
		userResource.add(url);
		userResource.add(url1);
		return userResource;
	}

	@Override
	public UserDetailsInfo setUserInfo() {
		UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
		userDetailsInfo.setPassWord("123456");
		userDetailsInfo.setUserName("admin");
		userDetailsInfo.setRole("Role");
		return userDetailsInfo;
	}
	
}
