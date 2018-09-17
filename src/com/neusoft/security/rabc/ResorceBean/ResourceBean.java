package com.neusoft.security.rabc.ResorceBean;

/**
 * 用户有权限访问资源实体类
 * @author Siss
 *
 */
public class ResourceBean {
	/**
	 * 请求url地址
	 */
	private String url;
	
	/**
	 * 请求方式 GET POST ALL
	 */
	private String method;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	};
	
	
	
}
