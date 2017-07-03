package com.groupfour.daos;


import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.groupfour.models.CarModel;
import com.groupfour.models.OrderModel;

public class OrderDao extends BaseDao {

	public boolean addOrder(OrderModel order){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_order(order_id,custom_id,car_id,order_price,order_date,order_info,order_status,order_to) value(?,?,?,?,?,?,?,?)");
			ps.setInt(1, order.getOrder_id());
			ps.setInt(2, order.getCustom_id());
			ps.setInt(3, order.getCar_id());
			ps.setFloat(4, order.getOrder_price());
			ps.setDate(5, new Date(order.getOrder_date().getTime()));
			ps.setString(6, order.getOrder_info());
			ps.setString(7, order.getOrder_status());
			ps.setString(8, order.getOrder_to());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public List getOrder(String type,String text,String selltime){
		List list = new ArrayList();
		CarModel carmodel = new CarModel();
		OrderModel order = new OrderModel();
		getConn();
		try {
		String sql = "select * from tb_order a inner join tb_car b on a.car_id=b.car_id where ";
		if(type.equals("订单号")){
			sql+="order_id=? and order_date=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(text));
			ps.setString(2,selltime);
			rs = ps.executeQuery();
		}else if(type.equals("客户号")){
			sql+="custom_id=? and order_date=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(text));
			ps.setString(2, selltime);
			rs = ps.executeQuery();
		}else if(type.equals("车名称")){
			sql+="car_name=? and order_date=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, text);
			ps.setString(2, selltime);
			rs = ps.executeQuery();
		}else if(type.equals("车引擎编号")){
			sql+="car_engine_num=? and order_date=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, text);
			ps.setString(2, selltime);
			rs = ps.executeQuery();
		}
			while (rs.next()) {
				order.setOrder_id(rs.getInt("order_id"));
				order.setCustom_id(rs.getInt("custom_id"));
			    carmodel.setCar_number(rs.getString("car_number"));
				carmodel.setCar_name(rs.getString("car_name"));
				order.setOrder_price(rs.getFloat("order_price"));
				carmodel.setCar_engine_num(rs.getString("car_engine_num"));
				order.setOrder_to(rs.getString("order_to"));
				order.setOrder_date(rs.getDate("order_date"));
				order.setOrder_status(rs.getString("order_status"));
				order.setCarmodel(carmodel);
				list.add(order);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteOrder(int order_id){
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("delete from tb_order where order_id=?");
			ps.setInt(1, order_id);
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
