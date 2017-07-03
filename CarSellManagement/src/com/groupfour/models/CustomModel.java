package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;

public class CustomModel implements Serializable{
	private int custom_id;
	private String custom_name;
	private String custom_sex;
	private String custom_phone;
	private String custom_idcard;
	private String custom_location;
	public int getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(int custom_id) {
		this.custom_id = custom_id;
	}
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}
	public String getCustom_sex() {
		return custom_sex;
	}
	public void setCustom_sex(String custom_sex) {
		this.custom_sex = custom_sex;
	}
	public String getCustom_phone() {
		return custom_phone;
	}
	public void setCustom_phone(String custom_phone) {
		this.custom_phone = custom_phone;
	}
	public String getCustom_idcard() {
		return custom_idcard;
	}
	public void setCustom_idcard(String custom_idcard) {
		this.custom_idcard = custom_idcard;
	}
	public String getCustom_location() {
		return custom_location;
	}
	public void setCustom_location(String custom_location) {
		this.custom_location = custom_location;
	}

}
