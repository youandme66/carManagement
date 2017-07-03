package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupfour.daos.CustomerDao;
import com.groupfour.models.CustomModel;

public class CustomerServlet extends HttpServlet{
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
			searchCustom(request, response);
		}else if(action.equals("add")){
			addCustom(request,response);
		}else if(action.equals("delete")){
			deleteCustom(request,response);
		}else if(action.equals("update")){
			updateCustom(request,response);
		}else{
			
		}
	}
	private void deleteCustom(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		String custom_id = request.getParameter("checked");
		String custom[] = custom_id.split(",");
		if(custom[0]==""){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('请选择删除的客户');window.location.href='customer/listcustomer.html';</script></head></html>");
			pw.flush();
			return;
		}
		CustomerDao customerdao = new CustomerDao();
		int flag = customerdao.deleteCustomer(custom);
		if(flag>0){
			request.getSession().invalidate();
			response.sendRedirect("customer/listcustomer.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='customer/listcustomer.html';</script></head></html>");
			pw.flush();
		}
	}
	private void searchCustom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String custom_name = request.getParameter("custom_name");
		CustomerDao customerdao = new CustomerDao();
		List list = customerdao.searchCustom(custom_name);
		request.getSession().setAttribute("custom", list);
		response.sendRedirect("customer/listcustomer.html");
	}
	/**
	 * @param request
	 * @param respon
	 * @throws IOException 
	 */
	private void addCustom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String custom_name = request.getParameter("custom_name");
		String custom_sex = request.getParameter("custom_sex");
		String custom_phone = request.getParameter("custom_phone");
		String custom_idcard = request.getParameter("custom_idcard");
		String custom_location = request.getParameter("custom_location");
		CustomModel custommodel = new CustomModel();
		custommodel.setCustom_name(custom_name);
		custommodel.setCustom_sex(custom_sex);
		custommodel.setCustom_phone(custom_phone);
		custommodel.setCustom_location(custom_location);
		custommodel.setCustom_idcard(custom_idcard);
		CustomerDao customerdao = new CustomerDao();
		int flag = customerdao.addCustom(custommodel);
		if(flag>0){
			request.getSession().invalidate();
			response.sendRedirect("customer/listcustomer.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='customer/addcustomer.html';</script></head></html>");
			pw.flush();
		}
	}
	private void updateCustom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CustomModel custommodel = new CustomModel();
		custommodel.setCustom_id(Integer.parseInt(request.getParameter("custom_id")));
		custommodel.setCustom_idcard(request.getParameter("custom_idcard"));
		custommodel.setCustom_location(request.getParameter("custom_location"));
		custommodel.setCustom_phone(request.getParameter("custom_phone"));
		custommodel.setCustom_sex(request.getParameter("custom_sex"));
		custommodel.setCustom_name(request.getParameter("custom_name"));
		CustomerDao customerdao = new CustomerDao();
		int flag = customerdao.updateCustom(custommodel);
		if(flag>0){
			request.getSession().invalidate();
			response.sendRedirect("customer/listcustomer.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('更新失败');window.location.href='customer/addcustomer.html';</script></head></html>");
			pw.flush();
		}
	}
}
