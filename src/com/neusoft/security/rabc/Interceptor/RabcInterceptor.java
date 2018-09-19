package com.neusoft.security.rabc.Interceptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.neusoft.security.rabc.UserResourceInterface;
import com.neusoft.security.rabc.entity.UserDetailsInfo;
import com.neusoft.security.rabc.neusoftrabc.NeusoftRabc;
import com.neusoft.security.rabc.implents.NuesoftUserResource;

public class RabcInterceptor implements HandlerInterceptor{
	
	private String[] staticUrls;
    
	public void setStaticUrls(String[] staticUrls) {

        this.staticUrls = staticUrls;

    }
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean arg = true;
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		UserResourceInterface userResourceInterface = new NuesoftUserResource();
		
		//用户信息
		UserDetailsInfo userDetailsInfo = userResourceInterface.setUserInfo();
		
		//登录账号资源getUserResource
		List<HashMap<String, String>> userResource = userResourceInterface.setUserResource();

		NeusoftRabc neusoftRabc = new NeusoftRabc();
		if(userDetailsInfo.getUserName() == null) {
			if(userDetailsInfo.getRole().equals("ADMIN")) {
				return true;
			}else if(userDetailsInfo.getRole().equals("USER")) {
				if(null != staticUrls && staticUrls.length>=1) {
					for(String url : staticUrls) {
						if(request.getServletPath().contains(url)) {
							arg = neusoftRabc.staticHasPermission(request, userResource);
						}else {
							arg = neusoftRabc.hasPermission(request,userResource);
						}
					}
				}
			}else {
				JSONObject res = new JSONObject();
				res.put("STATUS", "ERROR 201");
				res.put("MSG", "用户未登录");
	            out = response.getWriter();
	            out.append(res.toString());
	            return false;
			}
		}else {
			if(userDetailsInfo.getRole().equals("ADMIN")) {
				return true;
			}else {
				if(null != staticUrls && staticUrls.length>=1) {
					for(String url : staticUrls) {
						if(request.getServletPath().contains(url)) {
							arg = neusoftRabc.staticHasPermission(request, userResource);
						}else {
							arg = neusoftRabc.hasPermission(request,userResource);
						}
					}
				}
			}
		}
				
		if (arg == false) {
			JSONObject res = new JSONObject();
			res.put("STATUS", "ERROR 403");
			res.put("MSG", "不具备该资源访问权限！！！");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }

		return arg;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
