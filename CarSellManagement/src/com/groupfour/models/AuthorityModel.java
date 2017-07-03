package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;
public class AuthorityModel implements Serializable{
	private int authority_id;
	private String authority_name;
	public int getAuthority_id() {
		return authority_id;
	}
	public void setAuthority_id(int authority_id) {
		this.authority_id = authority_id;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

}
