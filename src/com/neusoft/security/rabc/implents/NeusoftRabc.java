package com.neusoft.security.rabc.implents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.neusoft.security.rabc.ResorceBean.ResourceBean;


/**
 * rabc接口实现类
 * @author neusoft
 *
 */
@Component("NeusoftRabc")
public class NeusoftRabc{
	private AntPathMatcher antPathMatcher= new AntPathMatcher();
	
	public boolean hasPermission(HttpServletRequest request,List<ResourceBean> resource){
		String requestURL = request.getServletPath(); 
		String requestMETHOD = request.getMethod();

		boolean hasPermission = false;
		
		ResourceBean userResource = new ResourceBean();
		userResource.setUrl(requestURL);
		userResource.setMethod(requestMETHOD);
		for(ResourceBean urlResource : resource) {
			if(antPathMatcher.match(urlResource.getUrl(),requestURL)&&(urlResource.getMethod().equals(requestMETHOD) || urlResource.getMethod().equals("ALL"))) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}
	
}
