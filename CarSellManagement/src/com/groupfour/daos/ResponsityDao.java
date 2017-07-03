package com.groupfour.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.CarModel;
import com.groupfour.models.ResponsityModel;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Date;

public class ResponsityDao extends BaseDao{
	public List getAllResponsity(){
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_responsity");
			rs = ps.executeQuery();
			while(rs.next()){
				ResponsityModel responsity = new ResponsityModel();
				responsity.setResponsity_id(rs.getInt("responsity_id"));
				responsity.setResponsity_name(rs.getString("responsity_name"));
				responsity.setResponsity_location(rs.getString("responsity_location"));
				responsity.setResponsity_max_number(rs.getInt("responsity_max_number"));
				responsity.setResponsity_now_number(rs.getInt("responsity_now_number"));
				list.add(responsity);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int addResponsity(ResponsityModel responsitymodel){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_responsity(responsity_id,responsity_name,responsity_location,responsity_max_number) value(?,?,?,?)");
			ps.setInt(1, responsitymodel.getResponsity_id());
			ps.setString(2, responsitymodel.getResponsity_name());
			ps.setString(3, responsitymodel.getResponsity_location());
			ps.setInt(4, responsitymodel.getResponsity_max_number());
			j=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public int deleteResponsity(String responsity[]){
		int j=1;
		String sql = "delete from tb_responsity where responsity_id in (";
		String sql2 ="update tb_car set car_location='未知仓库' where car_location in (select responsity_name from tb_responsity where responsity_id in (";
		for(int i=0;i<responsity.length;i++){
			if(i==responsity.length-1){
				sql+=responsity[i]+")";
				sql2+=responsity[i]+"))";
			}else{
				sql+=responsity[i]+",";
				sql2+=responsity[i]+",";
			}
		}
		getConn();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
			ps2.executeUpdate();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			j=0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return j;
	}
	public int updateResponsity(ResponsityModel responsitymodel){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("update tb_responsity set responsity_name=?,responsity_location=?,responsity_max_number=? where responsity_id=?");
			ps.setString(1, responsitymodel.getResponsity_name());
			ps.setString(2, responsitymodel.getResponsity_location());
			ps.setInt(3, responsitymodel.getResponsity_max_number());
			ps.setInt(4, responsitymodel.getResponsity_id());
			j = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public int addCar(CarModel carmodel){
		int j=1;
		getConn();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into tb_car(car_number,car_name,car_type,car_color,car_engine_num,car_from,car_to,car_location,car_prodate,car_go_date,car_description) value(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, carmodel.getCar_number());
			ps.setString(2, carmodel.getCar_name());
			ps.setString(3, carmodel.getCar_type());
			ps.setString(4, carmodel.getCar_color());
			ps.setString(5, carmodel.getCar_engine_num());
			ps.setString(6,carmodel.getCar_from());
			ps.setString(7, carmodel.getCar_to());
			ps.setString(8, carmodel.getCar_location());
			ps.setDate(9,new Date(carmodel.getCar_prodate().getTime()));
			ps.setDate(10,new Date(carmodel.getCar_go_date().getTime()));
			ps.setString(11, carmodel.getCar_description());
			ps.executeUpdate();
			PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("update tb_responsity set responsity_now_number=(select count(*) from tb_car where car_location=?) where responsity_name=?");
			ps1.setString(1, carmodel.getCar_location());
			ps1.setString(2, carmodel.getCar_location());
			ps1.executeUpdate();
			conn.commit();
			close();
		} catch (SQLException e) {
			j=0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public List getCarName(){
		List list = new ArrayList<String>();
		getConn();
		try {
			ps = conn.prepareStatement("select car_name from tb_car group by car_name");
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("car_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List getResponsityCar(){
		List listResponsity;
		listResponsity = getAllResponsity();
		getConn();
		for(int i=0;i<listResponsity.size();i++){
			List<CarModel> listCar = new ArrayList<CarModel>();
			try {
				ps = conn.prepareStatement("select car_name,car_type,count(*) amount from tb_car where car_location=? group by car_name,car_type");
				ps.setString(1, ((ResponsityModel)listResponsity.get(i)).getResponsity_name());
				rs = ps.executeQuery();
				while(rs.next()){
					CarModel carmodel = new CarModel();
					carmodel.setCar_name(rs.getString("car_name"));
					carmodel.setCar_type(rs.getString("car_type"));
					carmodel.setCar_count(rs.getInt("amount"));
					listCar.add(carmodel);
				}
				((ResponsityModel)listResponsity.get(i)).setListCar(listCar);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return listResponsity;
	}
	public ResponsityModel searchResponsityCar(String location,String car_name,String car_type){
		ResponsityModel responsitymodel = getResponsityByName(location);
		List<CarModel> listcarmodel = new ArrayList<CarModel>();
		getConn();
		String sql = "select car_name,car_type,count(*) amount from tb_car where car_location=? group by car_name,car_type having car_name=?";
		if(!car_type.equals("")){
			sql+=" and car_type=?";
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, location);
			ps.setString(2, car_name);
			if(!car_type.equals("")){
				ps.setString(3, car_type);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				CarModel carmodel = new CarModel();
				carmodel.setCar_name(rs.getString("car_name"));
				carmodel.setCar_type(rs.getString("car_type"));
				carmodel.setCar_count(rs.getInt("amount"));
				listcarmodel.add(carmodel);
			}
			responsitymodel.setListCar(listcarmodel);
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responsitymodel;
	}
	public ResponsityModel getResponsityByName(String location){
		ResponsityModel responsitymodel=new ResponsityModel();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_responsity where responsity_name=?");
			ps.setString(1, location);
			rs = ps.executeQuery();
			if(rs.next()){
				responsitymodel.setResponsity_id(rs.getInt("responsity_id"));
				responsitymodel.setResponsity_name(rs.getString("responsity_name"));
				responsitymodel.setResponsity_location(rs.getString("responsity_location"));
				responsitymodel.setResponsity_max_number(rs.getInt("responsity_max_number"));
				responsitymodel.setResponsity_now_number(rs.getInt("responsity_now_number"));
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responsitymodel;
	}
}
