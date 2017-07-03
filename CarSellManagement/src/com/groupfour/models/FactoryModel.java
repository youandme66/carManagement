package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;

public class FactoryModel implements Serializable{
	private int factory_id;
	private String factory_number;
	private String factory_name;
	private String factory_rname;
	private String factory_phone;
	private String factory_zipcode;
	private String factory_location;
	public int getFactory_id() {
		return factory_id;
	}
	public void setFactory_id(int factory_id) {
		this.factory_id = factory_id;
	}
	public String getFactory_number() {
		return factory_number;
	}
	public void setFactory_number(String factory_number) {
		this.factory_number = factory_number;
	}
	public String getFactory_name() {
		return factory_name;
	}
	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}
	public String getFactory_rname() {
		return factory_rname;
	}
	public void setFactory_rname(String factory_rname) {
		this.factory_rname = factory_rname;
	}
	public String getFactory_phone() {
		return factory_phone;
	}
	public void setFactory_phone(String factory_phone) {
		this.factory_phone = factory_phone;
	}
	public String getFactory_zipcode() {
		return factory_zipcode;
	}
	public void setFactory_zipcode(String factory_zipcode) {
		this.factory_zipcode = factory_zipcode;
	}
	public String getFactory_location() {
		return factory_location;
	}
	public void setFactory_location(String factory_location) {
		this.factory_location = factory_location;
	}

}
