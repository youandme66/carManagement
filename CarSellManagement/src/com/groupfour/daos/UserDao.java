package com.groupfour.daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.UsersModel;
/**
 * 
 * @author LZH
 * 继承数据库基类
 */
public class UserDao extends BaseDao{
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public boolean addUser(UsersModel user){
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_user(user_name,user_password,user_rename,role_id) value(?,?,?,?)");
			ps.setString(1, user.getUser_name());
			ps.setString(2,user.getUser_password());
			ps.setString(3, user.getUser_rename());
			ps.setInt(4, user.getRole_id());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 删除用户信息
	 * @param user_id
	 * @return
	 */
	public boolean deletUser(int user_id){
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("delete from tb_user where user_id=?");
			ps.setInt(1, user_id);
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(UsersModel user){
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("update tb_user set user_name=?,user_password=?,user_rename=?,role_id=? where user_id=?");
			ps.setString(1,user.getUser_name());
			ps.setString(2, user.getUser_password());
			ps.setString(3, user.getUser_rename());
			ps.setInt(4, user.getRole_id());
			ps.setInt(5, user.getUser_id());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List queryUser(){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_user");
			rs = ps.executeQuery();
			while(rs.next()){
				UsersModel usermodel = new UsersModel();
				usermodel.setUser_id(rs.getInt("user_id"));
				usermodel.setRole_id(rs.getInt("role_id"));
				usermodel.setUser_name(rs.getString("user_name"));
				usermodel.setUser_rename(rs.getString("user_rename"));
				list.add(usermodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public UsersModel getUser(String username,String password){
		getConn();
		UsersModel usermodel = new UsersModel();
		try {
			ps = conn.prepareStatement("select * from tb_user where user_name=? and user_password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				usermodel.setUser_id(rs.getInt("user_id"));
				return usermodel;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int[] getUserAuthority(int user_id){
		int authority_id[] = new int[5];
		int i=0;
		getConn();
		try {
			ps = conn.prepareStatement("select authority_id from tb_role_authority where role_id = (select role_id from tb_user where user_id=?)");
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while(rs.next()){
				authority_id[i]=rs.getInt("authority_id");
				i++;
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authority_id;
	}
}
