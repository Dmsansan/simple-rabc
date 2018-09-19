package com.neusoft.security.rabc.authority;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.neusoft.security.rabc.entity.UserDetailsInfo;
import com.neusoft.security.rabc.neusoftrabc.NeusoftRabc;

/**
 * 账号权限判断
 * @author siss
 *
 */
public class AuthorityDicede {
	public boolean userAuthorityDicede(HttpServletRequest request,HttpServletResponse response,List<HashMap<String, String>> userResource,UserDetailsInfo userDetailsInfo,String[] staticUrls) throws IOException {
		NeusoftRabc neusoftRabc = new NeusoftRabc();
		boolean arg = true;
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		
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
				}else {
					arg = neusoftRabc.hasPermission(request, userResource);
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
				}else {
					arg = neusoftRabc.hasPermission(request, userResource);
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
}
