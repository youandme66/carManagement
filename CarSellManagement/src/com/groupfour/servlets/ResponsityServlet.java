package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupfour.daos.ResponsityDao;
import com.groupfour.models.CarModel;
import com.groupfour.models.ResponsityModel;

public class ResponsityServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("add")){
			addResponsity(request, response);
		}else if(action.equals("update")){
			updateResponsity(request, response);
		}else if(action.equals("delete")){
			deleteResponsity(request, response);
		}else if(action.equals("addcar")){
			try {
				addCar(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("searchresponsity")){
			searchResponsity(request,response);
		}
		else{
			response.sendRedirect("../login.html");
		}
	}
	private void addResponsity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("uft-8");
		ResponsityModel responsitymodel = new ResponsityModel();
		try{
		responsitymodel.setResponsity_id(Integer.parseInt(request.getParameter("responsity_id")));
		responsitymodel.setResponsity_name(request.getParameter("responsity_name"));
		responsitymodel.setResponsity_location(request.getParameter("responsity_location"));
		responsitymodel.setResponsity_max_number(Integer.parseInt(request.getParameter("responsity_max_number")));
		ResponsityDao responsitydao = new ResponsityDao();
		int flag = responsitydao.addResponsity(responsitymodel);
		if(flag>0){
			response.sendRedirect("warehouse/warehouseList.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='warehouse/warehouseAdd.html';</script></head></html>");
			pw.flush();
		}}catch(Exception e){
//			PrintWriter pw = response.getWriter();
//			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='warehouse/warehouseAdd.html';</script></head></html>");
//			pw.flush();
		}
	}
	private void updateResponsity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		String responsity_id = request.getParameter("responsity_id");
		String responsity_name = request.getParameter("responsity_name");
		String responsity_location = request.getParameter("responsity_location");
		String responsity_max_number = request.getParameter("responsity_max_number");
		ResponsityModel responsitymodel = new ResponsityModel();
		try{
			responsitymodel.setResponsity_id(Integer.parseInt(responsity_id));
			responsitymodel.setResponsity_name(responsity_name);
			responsitymodel.setResponsity_location(responsity_location);
			responsitymodel.setResponsity_max_number(Integer.parseInt(responsity_max_number));
			ResponsityDao responsitydao =new ResponsityDao();
			int flag = responsitydao.updateResponsity(responsitymodel);
			if(flag>0){
				response.sendRedirect("warehouse/warehouseList.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('修改失败');window.location.href='warehouse/warehouseEdit.html?responsity_id="+responsity_id+"&responsity_name="+responsity_name+"&responsity_location="+responsity_location+"&responsity_max_number="+responsity_max_number+"';</script></head></html>");
				pw.flush();
			}
		}catch(Exception e){
//			PrintWriter pw = response.getWriter();
//			pw.write("<html><head><meta charset='utf-8'/><script>alert('修改失败');window.location.href='warehouse/warehouseEdit.html?responsity_id="+responsity_id+"&responsity_name="+responsity_name+"&responsity_location="+responsity_location+"&responsity_max_number="+responsity_max_number+"&responsity_now_number="+responsity_now_number+"';</script></head></html>");
//			pw.flush();
		}
	}
	private void deleteResponsity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reqponsity_id = request.getParameter("checked");
		String responsity[] = reqponsity_id.split(",");
		ResponsityDao responsitydao = new ResponsityDao();
		int flag = responsitydao.deleteResponsity(responsity);
		if(flag>0){
			response.sendRedirect("warehouse/warehouseList.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='warehouse/warehouseList.html';</script></head></html>");
			pw.flush();
		}
	}
	private void addCar(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException{
		response.setCharacterEncoding("utf-8");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CarModel carmodel = new CarModel();
		carmodel.setCar_number(request.getParameter("car_number"));
		carmodel.setCar_name(request.getParameter("car_name"));
		carmodel.setCar_type(request.getParameter("car_type"));
		carmodel.setCar_color(request.getParameter("car_color"));
		carmodel.setCar_engine_num(request.getParameter("car_engine_num"));
		carmodel.setCar_from(request.getParameter("car_from"));
		carmodel.setCar_to(request.getParameter("car_to"));
		carmodel.setCar_go_date(format.parse(request.getParameter("car_go_date")));
		carmodel.setCar_prodate(format.parse(request.getParameter("car_prodate")));
		carmodel.setCar_description(request.getParameter("car_description"));
		carmodel.setCar_location(request.getParameter("car_location"));
		ResponsityDao responsitydao =new ResponsityDao();
		int flag = responsitydao.addCar(carmodel);
		if(flag>0){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加成功');window.location.href='warehouse/addcar.html';</script></head></html>");
			pw.flush();
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='warehouse/addcar.html';</script></head></html>");
			pw.flush();
		}
	}
	public void searchResponsity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String car_location = request.getParameter("car_location");
		String car_name = request.getParameter("car_name");
		String car_type = request.getParameter("car_type");
		ResponsityDao responsitydao = new ResponsityDao();
		ResponsityModel responsitymodel = responsitydao.searchResponsityCar(car_location, car_name, car_type);
		request.getSession().setAttribute("responsity", responsitymodel);
		response.sendRedirect("warehouse/stocelist.html");
	}
}
