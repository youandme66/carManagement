package com.groupfour.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.RoleModel;
import com.mysql.jdbc.PreparedStatement;

public class RoleDao extends BaseDao {
	//获取角色列表
	public List getRole(){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_role");
			rs = ps.executeQuery();
			while(rs.next()){
				RoleModel rolemodel = new RoleModel();
				rolemodel.setRole_id(rs.getInt("role_id"));
				rolemodel.setRole_name(rs.getString("role_name"));
				rolemodel.setRole_description(rs.getString("role_description"));
				list.add(rolemodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//获取指定角色
	public RoleModel getRole(int i){
		getConn();
		RoleModel rolemodel = new RoleModel();
		try {
			ps = conn.prepareStatement("select * from tb_role where role_id=?");
			ps.setInt(1, i);
			rs = ps.executeQuery();
			while(rs.next()){
				rolemodel.setRole_id(rs.getInt("role_id"));
				rolemodel.setRole_name(rs.getString("role_name"));
				rolemodel.setRole_description(rs.getString("role_description"));
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rolemodel;
	}
	//添加角色
	public boolean addRole(String roleName,String roleDepict){
		getConn();
		int result = 0;
		try {
			ps = conn.prepareStatement("insert into tb_role(role_name,role_description) value(?,?)");
			ps.setString(1, roleName);
			ps.setString(2, roleDepict);
			result = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean addRoleAuthority(int role_id,String authority_id[]){
		getConn();
		boolean  flag = false;
		String sql = "insert into tb_role_authority(role_id,authority_id) values";
		for(int i=0;i<authority_id.length;i++){
			if(i==authority_id.length-1){
				sql+="("+role_id+","+authority_id[i]+")";
			}else{
				sql+="("+role_id+","+authority_id[i]+"),";
			}
		}
		try {
			ps = conn.prepareStatement(sql);
			int result= ps.executeUpdate();
			if(result>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public RoleModel isTheRole(String roleName){
		getConn();
		RoleModel rolemodel = null;
		try {
			ps = conn.prepareStatement("select * from tb_role where role_name=?");
			ps.setString(1, roleName);
			rs = ps.executeQuery();
			if(rs.next()){
				rolemodel = new  RoleModel();
				rolemodel.setRole_id(rs.getInt("role_id"));
				rolemodel.setRole_name(rs.getString("role_name"));
				rolemodel.setRole_description("role_description");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rolemodel;
	}
	public int deleteRole(String role_id[]){
		getConn();
		int j = 1;
		try {
			String sql1 = "delete from tb_role where role_id in (";
			String sql2 = "delete from tb_role_authority where role_id in (";
			for(int i=0;i<role_id.length;i++){
				if(i==role_id.length-1){
					sql1+=role_id[i]+")";
					sql2+=role_id[i]+")";
				}else{
					sql1+=role_id[i]+",";
					sql2+=role_id[i]+",";
				}
			}
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql1);
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
			int delete1 = ps2.executeUpdate();
			int delete2 = ps.executeUpdate();
			conn.commit();
			close();
		} catch (SQLException e) {
			j=0;
			// TODO Auto-generated catch block
			System.out.println("删除失败");
		}
		return j;
	}
	public int updateRole(int role_id,String role_name,String role_description,String authority[]){
		getConn();
		int j=1;
		String sql = "insert into tb_role_authority(role_id,authority_id) value(";
		for(int i=0;i<authority.length;i++){
			if(i==authority.length-1){
				sql+=role_id+","+authority[i]+")";
			}else{
				sql+=role_id+","+authority[i]+"),(";
			}
		}
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update tb_role set role_name=?,role_description=? where role_id=?");
			ps.setString(1, role_name);
			ps.setString(2, role_description);
			ps.setInt(3, role_id);
			ps.executeUpdate();
			PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("delete from tb_role_authority where role_id=?");
			ps1.setInt(1, role_id);
			ps1.executeUpdate();
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql);
			ps2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			j=0;
			// TODO Auto-generated catch block
			System.out.println("更新失败");
		}
		return j;
	}
	public boolean  isUserRole(String role_id[]){
		boolean flag = false;
		String sql = "select * from tb_user where role_id in (";
		for(int i=0;i<role_id.length;i++){
			if(i==role_id.length-1){
				sql+=role_id[i]+")";
			}else{
				sql+=role_id[i]+",";
			}
		}
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
