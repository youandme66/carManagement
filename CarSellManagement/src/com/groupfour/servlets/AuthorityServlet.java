package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupfour.daos.AuthorityDao;

public class AuthorityServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("addauthority")){
			addAuthority(request, response);
		}else if(action.equals("updateauthority")){
			updateAuthority(request, response);
		}else if(action.equals("deleteauthority")){
			deleteAuthority(request, response);
		}
	}
	private void addAuthority(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String authority_name = request.getParameter("authority_name");
		AuthorityDao authority = new AuthorityDao();
		int flag = authority.addAuthority(authority_name);
		if(flag==0){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='system/listpur.html';</script></head></html>");
			pw.flush();
		}else{
			response.sendRedirect("system/listpur.html");
		}
	}
	private void updateAuthority(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String authority_name = request.getParameter("roleDepict");
		int authority_id = Integer.parseInt(request.getParameter("authority_id"));
		AuthorityDao authoritydao = new AuthorityDao();
		int flag = authoritydao.updateAuthority(authority_name, authority_id);
		if(flag==0){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('更新失败');window.location.href='system/updatepur.html?authority_id="+authority_id+"&authority_name="+authority_name+"';</script></head></html>");
			pw.flush();
		}else{
			response.sendRedirect("system/listpur.html");
		}
	}
	private void deleteAuthority(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String authority_id = request.getParameter("checked");
		String authority[] = authority_id.split(",");
		AuthorityDao authoritydao = new AuthorityDao();
		int flag = authoritydao.deleteAuthority(authority);
		if(flag==1){
			response.sendRedirect("system/listpur.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='system/listpur.html';</script></head></html>");
			pw.flush();
		}
	}
}
