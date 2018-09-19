package com.neusoft.security.rabc.entity;

/**
 * 登录用户信息实体类
 * @author siss
 *
 */
public class UserDetailsInfo {
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String passWord;
	
	/**
	 * 角色 ADMIN管理员默认全部权限  USER用户配置权限 ROLE默认权限
	 */
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
