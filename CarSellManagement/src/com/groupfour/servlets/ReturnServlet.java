package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupfour.daos.ReturnDao;

public class ReturnServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("search")){
			searchReturn(request,response);
		}else if(action.equals("delete")){
			deleteReturn(request,response);
		}else{
			
		}
	}
	private void searchReturn(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String custom_name = request.getParameter("custom_name");
		String time = request.getParameter("time");
		ReturnDao returndao = new ReturnDao();
		List list = returndao.searchReturn(custom_name, time);
		request.getSession().setAttribute("returnmodel", list);
		response.sendRedirect("customer/visit.html");
	}
	private void deleteReturn(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		String return_id = request.getParameter("checked");
		String return_id2[] = return_id.split(",");
		if(return_id2[0]==""){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('请选择删除的回执单');window.location.href='customer/visit.html';</script></head></html>");
			pw.flush();
			return;
		}
		ReturnDao returndao = new ReturnDao();
		int flag = returndao.deleteReturn(return_id2);
		if(flag>0){
			request.getSession().invalidate();
			response.sendRedirect("customer/visit.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='customer/visit.html';</script></head></html>");
			pw.flush();
		}
	}
}
