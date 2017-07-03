package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;
import java.util.Date;


public class CarModel implements Serializable{
	private int car_id;
	private String car_name;
	private String car_number;
	private String car_color;
	private String car_engine_num;
	private String car_location;
	private String car_to;
	private String car_image_url;
	private String car_description;
	private String car_from;
	private Date car_prodate;
	private Date car_go_date;
	private String car_type;
	private int car_count;
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public String getCar_color() {
		return car_color;
	}
	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}
	public String getCar_engine_num() {
		return car_engine_num;
	}
	public void setCar_engine_num(String car_engine_num) {
		this.car_engine_num = car_engine_num;
	}
	public String getCar_location() {
		return car_location;
	}
	public void setCar_location(String car_location) {
		this.car_location = car_location;
	}
	public String getCar_to() {
		return car_to;
	}
	public void setCar_to(String car_to) {
		this.car_to = car_to;
	}
	public String getCar_image_url() {
		return car_image_url;
	}
	public void setCar_image_url(String car_image_url) {
		this.car_image_url = car_image_url;
	}
	public String getCar_description() {
		return car_description;
	}
	public void setCar_description(String car_description) {
		this.car_description = car_description;
	}
	public String getCar_from() {
		return car_from;
	}
	public void setCar_from(String car_from) {
		this.car_from = car_from;
	}
	public Date getCar_prodate() {
		return car_prodate;
	}
	public void setCar_prodate(Date car_prodate) {
		this.car_prodate = car_prodate;
	}
	public Date getCar_go_date() {
		return car_go_date;
	}
	public void setCar_go_date(Date car_go_date) {
		this.car_go_date = car_go_date;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public int getCar_count() {
		return car_count;
	}
	public void setCar_count(int car_count) {
		this.car_count = car_count;
	}
	
}
