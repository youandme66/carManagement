package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupfour.daos.UserDao;
import com.groupfour.models.UsersModel;

public class UserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("login")){
			login(request,response);
		}else if(action.equals("adduser")){
			addUser(request,response);
		}else if(action.equals("updateuser")){
			updateUser(request,response);
		}else if(action.equals("loginout")){
			loginout(request, response);
		}else if(action.equals("deleteuser")){
			deleteUser(request,response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	public void login(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userdao = new UserDao();
		UsersModel usermodel = userdao.getUser(username, password);
		
			try {
				if(usermodel!=null){
				Cookie cookie = new Cookie("management",usermodel.getUser_id()+"");
				cookie.setMaxAge(3000);
				cookie.setPath("/");
				response.addCookie(cookie);
				response.sendRedirect("index.html");
				}else{
					PrintWriter pw = response.getWriter();
					pw.write("<html><head><meta charset='utf-8'/><script>alert('用户名或密码错误');window.location.href='login.html';</script></head></html>");
					pw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String ensurePassword = request.getParameter("ensurePassword");
		String name = request.getParameter("name");
		int role_id = Integer.parseInt(request.getParameter("role"));
		UsersModel usermodel = new UsersModel();
		usermodel.setUser_name(username);
		usermodel.setUser_password(password);
		usermodel.setUser_rename(name);
		usermodel.setRole_id(role_id);
		UserDao userdao = new UserDao();
		boolean flag = userdao.addUser(usermodel);
		if(!flag){
			response.sendRedirect("system/listuser.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='system/adduser.html';</script></head></html>");
			pw.flush();
		}
	}
	public void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setCharacterEncoding("utf-8");
			int user_id = Integer.parseInt(request.getParameter("uid"));
			String user_name = request.getParameter("username");
			String user_password = request.getParameter("password");
			String user_rename = request.getParameter("name");
			int role_id = Integer.parseInt(request.getParameter("role"));
			UsersModel usermodel = new UsersModel();
			usermodel.setRole_id(role_id);
			usermodel.setUser_id(user_id);
			usermodel.setUser_name(user_name);
			usermodel.setUser_rename(user_rename);
			usermodel.setUser_password(user_password);
			UserDao userdao = new UserDao();
			boolean flag = userdao.updateUser(usermodel);
			if(!flag){
				response.sendRedirect("system/listuser.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('更新失败');window.location.href='system/adduser.html?uid="+user_id+"uname="+user_name+"urname="+user_rename+"';</script></head></html>");
				pw.flush();
			}
	}
	public void loginout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Cookie cookie[] = req.getCookies();
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("management")){
				cookie[i].setMaxAge(0);
				cookie[i].setPath("/");
				resp.addCookie(cookie[i]);
			}
		}
		PrintWriter pw = resp.getWriter();
		pw.write("<script>window.top.location.href='login.html';</script>");
		pw.flush();
	}
	public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String va = request.getParameter("checked");
		UserDao userdao = new UserDao();
		String user_id[] = va.split(",");
		for(int i=0;i<user_id.length;i++){
			boolean flag = userdao.deletUser(Integer.parseInt(user_id[i]));
		}
		response.sendRedirect("system/listuser.html");
	}
}
