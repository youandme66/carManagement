package com.groupfour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.groupfour.daos.RoleDao;
import com.groupfour.models.RoleModel;

public class RoleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("addrole")){
			addRole(request,response);
		}else if(action.equals("updaterole")){
			updateRole(request,response);
		}else if(action.equals("deleterole")){
			deleteRole(request,response);
		}else{
			
		}
	}
	public void addRole(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("utf-8");
		String roleName = request.getParameter("roleName");
		String roleDepict = request.getParameter("roleDepict");
		String authority_id = request.getParameter("authority_id");
		String authority[] = authority_id.split(",");
		RoleDao roledao = new RoleDao();
		RoleModel isTheRole = roledao.isTheRole(roleName);
		if(isTheRole==null){
			boolean isAddRole = roledao.addRole(roleName, roleDepict);
			if(isAddRole){
				RoleModel rolemodel = roledao.isTheRole(roleName);
				boolean isAddAuthority = roledao.addRoleAuthority(rolemodel.getRole_id(), authority);
				if(isAddAuthority){
					response.sendRedirect("system/listrole.html");
				}else{
					PrintWriter pw = response.getWriter();
					pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='system/addrole.html';</script></head></html>");
					pw.flush();
				}
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('添加失败');window.location.href='system/addrole.html';</script></head></html>");
				pw.flush();
			}
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('已经存在的角色名');window.location.href='system/addrole.html';</script></head></html>");
			pw.flush();
		}
	}
	public void updateRole(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("utf-8");
		String role_id = request.getParameter("role_id");
		String role_name = request.getParameter("roleName");
		String authority_id = request.getParameter("authority_id");
		String description = request.getParameter("roleDepict");
		String authority[] = authority_id.split(",");
		if(authority_id.equals("")){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('请添加权限');window.location.href='system/updaterole.html?role_id="+role_id+"&role_name="+role_name+"&role_description="+description+"';</script></head></html>");
			pw.flush();
			return;
		}
		RoleDao roledao = new RoleDao();
		int flag = roledao.updateRole(Integer.parseInt(role_id), role_name, description, authority);
		if(flag==1){
			response.sendRedirect("system/listrole.html");
		}else{
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('更新失败');window.location.href='system/updaterole.html?role_id="+role_id+"&role_name="+role_name+"&role_description="+description+"';</script></head></html>");
			pw.flush();
		}
	}
	public void deleteRole(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("utf-8");
		String role_id = request.getParameter("role_id");
		String role[] = role_id.split(",");
		RoleDao roledao = new RoleDao();
		boolean isUserRole = roledao.isUserRole(role);
		if(isUserRole){
			PrintWriter pw = response.getWriter();
			pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败,此角色正在被使用');window.location.href='system/listrole.html';</script></head></html>");
			pw.flush();
		}else{
			int flag = roledao.deleteRole(role);
			if(flag==1){
				response.sendRedirect("system/listrole.html");
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<html><head><meta charset='utf-8'/><script>alert('删除失败');window.location.href='system/listrole.html';</script></head></html>");
				pw.flush();
			}
		}	
	}
}
