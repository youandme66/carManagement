package com.groupfour.daos;

import java.awt.geom.QuadCurve2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.groupfour.models.AuthorityModel;
import com.mysql.jdbc.PreparedStatement;

public class AuthorityDao extends BaseDao {
	public List getAuthority(int i){
		getConn();
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement("select * from tb_authority where authority_id in (select authority_id from tb_role_authority where role_id=?)");
			ps.setInt(1, i);
			rs = ps.executeQuery();
			while(rs.next()){
				AuthorityModel authoritymodel = new AuthorityModel();
				authoritymodel.setAuthority_id(rs.getInt("authority_id"));
				authoritymodel.setAuthority_name(rs.getString("authority_name"));
				list.add(authoritymodel);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List getAllAuthority(){
		getConn();
		List list = new ArrayList();
		try {
			ps = conn.prepareStatement("select * from tb_authority");
			rs = ps.executeQuery();
			while(rs.next()){
				AuthorityModel authority = new AuthorityModel();
				authority.setAuthority_id(rs.getInt("authority_id"));
				authority.setAuthority_name(rs.getString("authority_name"));
				list.add(authority);
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int deleteAuthority(String authority_id[]){
		int j=1;
		getConn();
		String sql = "delete from tb_role_authority where authority_id in(";
		String sql1 = "delete from tb_authority where authority_id in(";
		for(int i=0;i<authority_id.length;i++){
			if(i==authority_id.length-1){
				sql+=authority_id[i]+")";
				sql1+=authority_id[i]+")";
			}else{
				sql+=authority_id[i]+",";
				sql1+=authority_id[i]+",";
			}
		}
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
			ps1.executeUpdate();
			conn.commit();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			j=0;
		}
		
		return j;
	}
	public int updateAuthority(String authority_name,int authority_id){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("update tb_authority set authority_name=? where authority_id=?");
			ps.setString(1,authority_name);
			ps.setInt(2, authority_id);
			j=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	public int addAuthority(String authority_name){
		int j=0;
		getConn();
		try {
			ps = conn.prepareStatement("insert into tb_authority(authority_name) value(?)");
			ps.setString(1, authority_name);
			j=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
}
