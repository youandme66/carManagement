package com.groupfour.daos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.CarModel;
import com.groupfour.models.FactoryModel;
import com.groupfour.models.UsersModel;

public class FactoryDao extends BaseDao {
	public List getFactory(String factory_id) {
		List list = new ArrayList();
		getConn();
		try {
			ps = conn.prepareStatement("select * from tb_factory where factory_id=?");
			ps.setString(1, factory_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				FactoryModel fmodel = new FactoryModel();
				fmodel.setFactory_number(rs.getString("factory_number"));
				fmodel.setFactory_name(rs.getString("factory_name"));
				fmodel.setFactory_location(rs.getString("factory_location"));
				fmodel.setFactory_phone(rs.getString("factory_phone"));
				fmodel.setFactory_zipcode(rs.getString("factory_zipcode"));
				fmodel.setFactory_rname(rs.getString("factory_rname"));
				list.add(fmodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean addFactory(FactoryModel fmodel) {
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_factory(factory_number,factory_name,factory_location,factory_phone,factory_zipcode,factory_rname) value(?,?,?,?,?,?)");
			ps.setString(1, fmodel.getFactory_number());
			ps.setString(2, fmodel.getFactory_name());
			ps.setString(3, fmodel.getFactory_location());
			ps.setString(4, fmodel.getFactory_phone());
			ps.setString(5, fmodel.getFactory_zipcode());
			ps.setString(6, fmodel.getFactory_rname());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteFactory(int factory_id) {
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("delete from tb_factory where factory_id=?");
			ps.setInt(1, factory_id);
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateFactory(FactoryModel fmodel) {
		boolean flag = false;
		getConn();
		try {
			ps = conn.prepareStatement("update tb_factory set factory_name=?,factory_location=?,factory_phone=?,factory_zipcode=?,factory_rname=? where factory_number=?");
			ps.setString(1, fmodel.getFactory_name());
			ps.setString(2, fmodel.getFactory_location());
			ps.setString(3, fmodel.getFactory_phone());
			ps.setString(4, fmodel.getFactory_zipcode());
			ps.setString(5, fmodel.getFactory_rname());
			ps.setString(6, fmodel.getFactory_number());
			flag = ps.execute();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
