package com.groupfour.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.groupfour.daos.UserDao;

public class SystemFilter implements Filter{
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Cookie cookies[];
		cookies = req.getCookies();
		int authority[] = new int[5];
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("management")){
				UserDao userdao = new UserDao();
				authority = userdao.getUserAuthority(Integer.parseInt(cookies[i].getValue()));
				break;
			}
		}
		for(int i=0;i<authority.length;i++){
			if(authority[i]==1){
				chain.doFilter(request, response);
				return;
			}else if(i==authority.length-1){
				response.setCharacterEncoding("utf-8");
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('你没有此界面的权限');window.top.location.href='index.html';</script></head></html>");
				pw.flush();
			}
		}
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
