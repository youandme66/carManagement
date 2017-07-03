package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupfour.daos.CarDao;
import com.groupfour.daos.UserDao;
import com.groupfour.models.CarModel;
import com.groupfour.models.UsersModel;

public class CarServlet extends HttpServlet {

	public CarServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action.equals("getcar")) {
			getCar(request, response);
		} else if (action.equals("updatecar")) {
			updateCar(request, response);
		}
	}
   public void getCar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   String location=request.getParameter("select2");
	   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	   
	   Date godate=null;
	   Date prodate=null;
	   try {
		prodate=f.parse(request.getParameter("mailingTime2"));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
		godate=f.parse(request.getParameter("mailingTime"));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   CarModel carmodel = new CarModel();
	   carmodel.setCar_location(location);
	   carmodel.setCar_prodate(prodate);
	   carmodel.setCar_go_date(godate);
	   CarDao cardao = new CarDao();
	   List list=cardao.getCar(carmodel);
	    HttpSession session = request.getSession();
	    session.setAttribute("carlist", list);
	    response.sendRedirect("sell/listcar.html");
   }
   public void updateCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   response.setCharacterEncoding("utf-8");
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		String car_name = request.getParameter("car_name");
		String car_number = request.getParameter("car_number");
		String car_color = request.getParameter("car_color");
		String car_location = request.getParameter("car_location");
		CarModel carmodel = new CarModel();
		carmodel.setCar_id(car_id);
		carmodel.setCar_name(car_name);
		carmodel.setCar_number(car_number);
		carmodel.setCar_color(car_color);
		carmodel.setCar_location(car_location);
		CarDao cardao = new CarDao();
		boolean flag = cardao.updateCar(carmodel);
		if(!flag){
			request.getSession().invalidate();
			response.sendRedirect("sell/listcar.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('更新失败');window.location.href='system/updatecarr.html?car_id="+car_id+"car_name="+car_name+"car_number="+car_number+"car_color="+car_color+"car_location="+car_location+"';</script></head></html>");
			pw.flush();
		}
}
}
