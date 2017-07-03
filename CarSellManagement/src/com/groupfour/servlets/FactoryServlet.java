package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupfour.daos.CarDao;
import com.groupfour.daos.FactoryDao;
import com.groupfour.daos.OrderDao;
import com.groupfour.models.CarModel;
import com.groupfour.models.FactoryModel;


public class FactoryServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action.equals("addfactory")) {
			addFactory(request, response);
		} else if (action.equals("getfactory")) {
			getFactory(request, response);
		} else if (action.equals("deletefactory")) {
			deleteFactory(request, response);
		}else if (action.equals("updatefactory")) {
			updateFactory(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	public void addFactory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		FactoryModel fmodel = new FactoryModel();
		fmodel.setFactory_number(request.getParameter("factory_number"));
		fmodel.setFactory_name(request.getParameter("factory_name"));
		fmodel.setFactory_phone(request.getParameter("factory_phone"));
		fmodel.setFactory_rname(request.getParameter("factory_rname"));
		fmodel.setFactory_zipcode(request.getParameter("factory_zipcode"));
		fmodel.setFactory_location(request.getParameter("factory_location"));
		FactoryDao fdao = new FactoryDao();
		boolean flag = fdao.addFactory(fmodel);
		if(!flag){
			if(!flag){
				response.sendRedirect("customer/listfactory.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='customer/addfactory.html';</script></head></html>");
				pw.flush();
			}
		}
	}

	public void getFactory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		FactoryModel fmodel = new FactoryModel();
		String factory_id=request.getParameter("factory_id");
		FactoryDao fdao = new FactoryDao();
		   List list=fdao.getFactory(factory_id);
		    HttpSession session = request.getSession();
		    session.setAttribute("factorylist", list);
		    response.sendRedirect("customer/listfactory.html");
	}

	public void deleteFactory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		FactoryDao fdao = new FactoryDao();
		String factory_id = request.getParameter("checked");
		String factory[] = factory_id.split(",");
		if(factory[0]==""){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('请选中要删除的厂商');window.location.href='customer/listfactory.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
			pw.flush();
		}else{
			boolean flag=fdao.deleteFactory(Integer.parseInt(factory[0]));
			if(!flag){
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('删除成功');window.location.href='customer/listfactory.html</script></head></html>");
				pw.flush();
		   	 request.getSession().invalidate();
				}else{
					PrintWriter pw = response.getWriter();
					pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='customer/listfactory.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
					pw.flush();
				}
		}
		
		
		
	}
	
	public void updateFactory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		FactoryModel fmodel = new FactoryModel();
		fmodel.setFactory_number(request.getParameter("factory_number"));
		fmodel.setFactory_name(request.getParameter("factory_name"));
		fmodel.setFactory_phone(request.getParameter("factory_phone"));
		fmodel.setFactory_rname(request.getParameter("factory_rname"));
		fmodel.setFactory_zipcode(request.getParameter("factory_zipcode"));
		fmodel.setFactory_location(request.getParameter("factory_location"));
		FactoryDao fdao = new FactoryDao();
		boolean flag = fdao.updateFactory(fmodel);
		if(!flag){
			if(!flag){
				PrintWriter pw = response.getWriter();
				request.getSession().invalidate();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('修改成功');window.location.href='customer/listfactory.html';</script></head></html>");
				pw.flush();
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('修改失败');window.location.href='customer/updatafactory.html&factory_name="+request.getParameter("factory_number")+"&factory_number="+request.getParameter("factory_number")+"&factory_rname="+request.getParameter("factory_rname")+"&factory_phone="+request.getParameter("factory_phone")+"&factory_zipcode="+request.getParameter("factory_zipcode")+"&factory_location="+request.getParameter("factory_location")+"';</script></head></html>");
				pw.flush();
			}
		}
	}
}
