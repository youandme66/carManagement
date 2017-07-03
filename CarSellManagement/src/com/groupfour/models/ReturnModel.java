package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;
import java.sql.Date;

public class ReturnModel implements Serializable{
	private int return_id;
	private int custon_id;
	private Date return_time;
	private String return_event;
	private String return_node;
	private String custom_name;
	public int getReturn_id() {
		return return_id;
	}
	public void setReturn_id(int return_id) {
		this.return_id = return_id;
	}
	public int getCuston_id() {
		return custon_id;
	}
	public void setCuston_id(int custon_id) {
		this.custon_id = custon_id;
	}
	public Date getReturn_time() {
		return return_time;
	}
	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}
	public String getReturn_event() {
		return return_event;
	}
	public void setReturn_event(String return_event) {
		this.return_event = return_event;
	}
	public String getReturn_node() {
		return return_node;
	}
	public void setReturn_node(String return_node) {
		this.return_node = return_node;
	}
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}

}
