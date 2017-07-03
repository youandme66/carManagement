package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;
import java.util.Date;


public class OrderModel implements Serializable{
	private int order_id;
	private int custom_id;
	private int car_id;
	private float order_price;
	private String order_tyoe;
	private String order_to;
	private String order_status;
	private Date order_date;
	private String order_info;
	private CarModel carmodel;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(int custom_id) {
		this.custom_id = custom_id;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public float getOrder_price() {
		return order_price;
	}
	public void setOrder_price(float order_price) {
		this.order_price = order_price;
	}
	public String getOrder_tyoe() {
		return order_tyoe;
	}
	public void setOrder_tyoe(String order_tyoe) {
		this.order_tyoe = order_tyoe;
	}
	public String getOrder_to() {
		return order_to;
	}
	public void setOrder_to(String order_to) {
		this.order_to = order_to;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_info() {
		return order_info;
	}
	public void setOrder_info(String order_info) {
		this.order_info = order_info;
	}
	public CarModel getCarmodel() {
		return carmodel;
	}
	public void setCarmodel(CarModel carmodel) {
		this.carmodel = carmodel;
	}
	
}
