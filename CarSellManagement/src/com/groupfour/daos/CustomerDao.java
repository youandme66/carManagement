package com.groupfour.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.CustomModel;

public class CustomerDao extends BaseDao{
	public List getAllCustomer(){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_custom");
			rs = ps.executeQuery();
			while(rs.next()){
				CustomModel custommodel = new CustomModel();
				custommodel.setCustom_id(rs.getInt("custom_id"));
				custommodel.setCustom_name(rs.getString("custom_name"));
				custommodel.setCustom_location(rs.getString("custom_location"));
				custommodel.setCustom_phone(rs.getString("custom_phone"));
				custommodel.setCustom_sex(rs.getString("custom_sex"));
				custommodel.setCustom_idcard(rs.getString("custom_idcard"));
				list.add(custommodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int deleteCustomer(String custom_id[]){
		int j=0;
		getConn();
		String sql = "delete from tb_custom where custom_id in (";
		for(int i=0;i<custom_id.length;i++){
			if(i==custom_id.length-1){
				sql+=custom_id[i]+")";
			}else{
				sql+=custom_id[i]+",";
			}
		}
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
	public List searchCustom(String name){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_custom where custom_name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				CustomModel custommodel = new CustomModel();
				custommodel.setCustom_id(rs.getInt("custom_id"));
				custommodel.setCustom_name(rs.getString("custom_name"));
				custommodel.setCustom_phone(rs.getString("custom_phone"));
				custommodel.setCustom_sex(rs.getString("custom_sex"));
				custommodel.setCustom_location(rs.getString("custom_location"));
				custommodel.setCustom_idcard(rs.getString("custom_idcard"));
				list.add(custommodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int addCustom(CustomModel custommodel){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_custom(custom_name,custom_phone,custom_sex,custom_location,custom_idcard) value(?,?,?,?,?)");
			ps.setString(1, custommodel.getCustom_name());
			ps.setString(2, custommodel.getCustom_phone());
			ps.setString(3, custommodel.getCustom_sex());
			ps.setString(5, custommodel.getCustom_idcard());
			ps.setString(4, custommodel.getCustom_location());
			j = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public int updateCustom(CustomModel custommodel){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("update tb_custom set custom_name=?,custom_phone=?,custom_sex=?,custom_location=?,custom_idcard=? where custom_id=?");
			ps.setString(1, custommodel.getCustom_name());
			ps.setString(2, custommodel.getCustom_phone());
			ps.setString(3, custommodel.getCustom_sex());
			ps.setString(4, custommodel.getCustom_location());
			ps.setString(5, custommodel.getCustom_idcard());
			ps.setInt(6, custommodel.getCustom_id());
			j = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
}
