package com.groupfour.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//连接url
	protected final String url = "jdbc:mysql://localhost:3306/management?characterEncoding=utf-8";
	//数据库用户名
	protected final String username = "root";
	//数据库密码
	protected final String password = "";
	protected Connection conn;//数据库连接对象
	protected PreparedStatement ps;//数据库语句操作柄
	protected ResultSet rs;//数据库结果集
	/**
	 * 加载数据库驱动
	 */
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库系统加载错误");
		}
	}
	/**
	 * 链接数据库
	 */
	public void getConn(){
		try {
			if(conn==null||conn.isClosed()){
				conn=DriverManager.getConnection(url,username,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接错误");
		}
	}
	/**
	 * 关闭数据库
	 */
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库关闭错误");
		}
		
	}
}
