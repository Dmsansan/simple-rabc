package com.neusoft.security.rabc.neusoftrabc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * rabc资源匹配实现类
 * @author neusoft
 *
 */
@Component("NeusoftRabc")
public class NeusoftRabc{
	/**
	 * 动态资源匹配
	 * @param request
	 * @param resource
	 * @return
	 */
	public boolean hasPermission(HttpServletRequest request,List<HashMap<String, String>> userResource){
		boolean hasPermission = false;
		
		for(HashMap<String, String> urlResource : userResource) {
			if(urlResource.get("url").equals("/") || urlResource.get("url").equals("/*")) {//配置所有权限访问
				hasPermission = true;
				break;
			}
			if(request.getServletPath().contains(urlResource.get("url"))
					&&(urlResource.get("method").equals(request.getMethod()) || urlResource.get("method").equals("ALL"))) 
			{
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}
	
	/**
	 * 静态资源访问
	 * @param request
	 * @param userUrlSet
	 * @return
	 */
	public boolean staticHasPermission(HttpServletRequest request,List<HashMap<String, String>> userResource) {		
		boolean permission = false;
		for(HashMap<String, String> urlResource : userResource) {
			//用户配置以folder/*结尾
			if(urlResource.get("url").indexOf("/*") != -1) {
				if(request.getServletPath().indexOf(urlResource.get("url").substring(0,urlResource.get("url").indexOf("/*")+1)) != -1) {
					permission = true;
					break;
				}
			}else {
				//用户配置以folder/或folder结尾
				if(request.getServletPath().contains(urlResource.get("url"))) {
					permission = true;
					break;
				}	
			}
		}
		return permission;
	}
}
