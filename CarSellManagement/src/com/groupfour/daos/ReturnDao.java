package com.groupfour.daos;

import java.awt.Cursor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.CustomModel;
import com.groupfour.models.ReturnModel;

public class ReturnDao extends BaseDao{
	public List getAllReturn(){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_return a inner join tb_custom b on a.custom_id=b.custom_id");
			rs = ps.executeQuery();
			while(rs.next()){
				ReturnModel returnmodel = new ReturnModel();
				returnmodel.setReturn_id(rs.getInt("return_id"));
				returnmodel.setReturn_time(rs.getDate("return_time"));
				returnmodel.setReturn_event(rs.getString("return_event"));
				returnmodel.setReturn_node(rs.getString("return_node"));
				returnmodel.setCustom_name(rs.getString("custom_name"));
				list.add(returnmodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public int deleteReturn(String return_id[]){
		int j=0;
		String sql = "delete from tb_return where return_id in (";
		for(int i=0;i<return_id.length;i++){
			if(i==return_id.length-1){
				sql+=return_id[i]+")";
			}else{
				sql+=return_id[i]+",";
			}
		}
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			j = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public List searchReturn(String customer_name,String time){
		int j=0;
		List list = new ArrayList();
		String sql = "select * from tb_return a inner join tb_custom b on a.custom_id=b.custom_id";
		if(!customer_name.equals("")){
			sql+=" where custom_name='"+customer_name+"'";
		}
		if(!time.equals("")){
			if(customer_name.equals("")){
				sql+=" where return_time='"+time+"'";
			}else{
				sql+=" and return_time='"+time+"'";
			}
		}
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ReturnModel returnmodel = new ReturnModel();
				returnmodel.setReturn_id(rs.getInt("return_id"));
				returnmodel.setCustom_name(rs.getString("custom_name"));
				returnmodel.setReturn_event(rs.getString("return_event"));
				returnmodel.setReturn_node(rs.getString("return_node"));
				returnmodel.setReturn_time(rs.getDate("return_time"));
				list.add(returnmodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
