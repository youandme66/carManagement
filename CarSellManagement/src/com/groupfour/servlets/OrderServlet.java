package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupfour.daos.CarDao;
import com.groupfour.daos.OrderDao;
import com.groupfour.models.CarModel;
import com.groupfour.models.OrderModel;

public class OrderServlet extends HttpServlet {

	public OrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("addorder")){
			addOrder(request,response);
		}else if(action.equals("getorder")){
			getOrder(request,response);
		}else if(action.equals("getall")){
			getAll(request,response);
		}else if(action.equals("deleteorder")){
			deleteOrder(request,response);
		}else if(action.equals("delete")){
			delete(request,response);
		}
			
}
public void addOrder(HttpServletRequest request, HttpServletResponse response)throws IOException{
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
     CarModel carmodel = new CarModel();
     OrderModel ordermodel = new OrderModel();
     carmodel.setCar_number(request.getParameter("car_number")); 
     carmodel.setCar_name(request.getParameter("car_name")); 
     carmodel.setCar_to(request.getParameter("car_to")); 
     carmodel.setCar_engine_num(request.getParameter("car_engine_num")); 
     CarDao cardao = new CarDao();
     cardao.getCar_id(carmodel);
     ordermodel.setCar_id(carmodel.getCar_id());
     ordermodel.setOrder_to(carmodel.getCar_to());
     ordermodel.setOrder_id(Integer.parseInt(request.getParameter("order_id")));
     ordermodel.setCustom_id(Integer.parseInt(request.getParameter("custom_id")));
     ordermodel.setOrder_status("已发货");
     try {
		ordermodel.setOrder_date(f.parse(request.getParameter("order_date")));
	} catch (ParseException e) {
		e.printStackTrace();
	}
     ordermodel.setOrder_price(Float.parseFloat(request.getParameter("order_price")));
     ordermodel.setOrder_info(request.getParameter("order_info"));
     OrderDao orderdao = new OrderDao();
     boolean flag = orderdao.addOrder(ordermodel);
     if(!flag){
    	 request.getSession().invalidate();
			response.sendRedirect("sell/order.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('提交失败');window.location.href='sell/order.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
			pw.flush();
		}
}

public void deleteOrder(HttpServletRequest request, HttpServletResponse response)throws IOException{
	OrderDao orderdao = new OrderDao();
	String order_id = request.getParameter("checked");
	String order[] = order_id.split(",");
	if(order[0]==""){
		PrintWriter pw = response.getWriter();
		pw.write("<html><head><meta charset='utf-8'/><script>alert('请选中要删除的订单');window.location.href='sell/SellList.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
		pw.flush();
	}else{
		boolean flag=orderdao.deleteOrder(Integer.parseInt(order[0]));
		if(!flag){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='sell/SellList.html</script></head></html>");
			pw.flush();
	   	 request.getSession().invalidate();
				response.sendRedirect("sell/SellList.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='sell/SellList.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
				pw.flush();
			}
	}
}
public void delete(HttpServletRequest request, HttpServletResponse response)throws IOException{
	OrderDao orderdao = new OrderDao();
	String order_id = request.getParameter("checked");
	String order[] = order_id.split(",");
	if(order[0]==""){
		PrintWriter pw = response.getWriter();
		pw.write("<html><head><meta charset='utf-8'/><script>alert('请选中要删除的订单');window.location.href='sell/SellList.html?order_id="+request.getParameter("order_id")+"custom_id="+request.getParameter("custom_id")+"car_number="+request.getParameter("car_number")+"car_name="+request.getParameter("car_name")+"car_to="+request.getParameter("car_to")+"car_engine_num="+request.getParameter("car_engine_num")+"order_price="+request.getParameter("order_price")+"order_date="+request.getParameter("order_date")+"order_info="+request.getParameter("order_info")+"';</script></head></html>");
		pw.flush();
	}else{
		boolean flag=orderdao.deleteOrder(Integer.parseInt(order[0]));
		if(!flag){
			request.getSession().invalidate();
			response.sendRedirect("sell/listorder.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='sell/listorder.html;</script></head></html>");
				pw.flush();
			}
	}
	
	
}


public void getOrder(HttpServletRequest request, HttpServletResponse response)throws IOException{
	request.getSession().invalidate();
	String type = request.getParameter("select");
	String text = request.getParameter("text");
	String selltime = request.getParameter("selltime");
	OrderDao orderdao = new OrderDao();
		 List list=orderdao.getOrder(type,text,selltime);
		    HttpSession session = request.getSession();
		    session.setAttribute("orderlist", list);
		    response.sendRedirect("sell/SellList.html");
}

public void getAll(HttpServletRequest request, HttpServletResponse response)throws IOException{
	request.getSession().invalidate();
	String type = request.getParameter("select");
	String text = request.getParameter("text");
	String selltime = request.getParameter("selltime");
	OrderDao orderdao = new OrderDao();
		 List list=orderdao.getOrder(type,text,selltime);
		    HttpSession session = request.getSession();
		    session.setAttribute("orderlist", list);
		    response.sendRedirect("sell/listorder.html");
}
}