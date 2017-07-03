package com.groupfour.daos;

import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Request;

import com.groupfour.models.CarModel;
import com.groupfour.models.UsersModel;

public class CarDao extends BaseDao {
	public List getCar(CarModel carmodel) {
		List list = new ArrayList();
		getConn();
		try {
			ps = conn
					.prepareStatement("select * from tb_car where car_location=? and car_prodate=? and car_go_date=?");
			ps.setString(1, carmodel.getCar_location());
			ps.setDate(2, new Date(carmodel.getCar_prodate().getTime()));
			ps.setDate(3, new Date(carmodel.getCar_go_date().getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {
				CarModel carmodel1 = new CarModel();
				carmodel1.setCar_id(rs.getInt("car_id"));
				carmodel1.setCar_name(rs.getString("car_name"));
				carmodel1.setCar_number(rs.getString("car_number"));
				carmodel1.setCar_color(rs.getString("car_color"));
				carmodel1.setCar_engine_num(rs.getString("car_engine_num"));
				carmodel1.setCar_location(rs.getString("car_location"));
				carmodel1.setCar_to(rs.getString("car_to"));
				carmodel1.setCar_description(rs.getString("car_description"));
				carmodel1.setCar_from(rs.getString("car_from"));
				carmodel1.setCar_prodate(rs.getDate("car_prodate"));
				carmodel1.setCar_go_date(rs.getDate("car_go_date"));
				list.add(carmodel1);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateCar(CarModel car) {
		boolean flag = false;
		getConn();
		try {
			ps = conn
					.prepareStatement("update tb_car set car_name=?,car_number=?,car_color=?,car_location=? where car_id=?");
			ps.setString(1, car.getCar_name());
			ps.setString(2, car.getCar_number());
			ps.setString(3, car.getCar_color());
			ps.setString(4, car.getCar_location());
			ps.setInt(5, car.getCar_id());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public void getCar_id(CarModel carmodel) {
		String sql = "select car_id from tb_car where car_number=? and car_name=? and car_engine_num=? ";
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, carmodel.getCar_number());
			ps.setString(2, carmodel.getCar_name());
			ps.setString(3, carmodel.getCar_engine_num());
			rs = ps.executeQuery();
			if(rs.next()){
				carmodel.setCar_id(rs.getInt("car_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
