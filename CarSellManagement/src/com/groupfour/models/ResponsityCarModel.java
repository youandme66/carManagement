package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;

public class ResponsityCarModel implements Serializable{
	private int car_id;
	private int respoinsity_id;
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getRespoinsity_id() {
		return respoinsity_id;
	}
	public void setRespoinsity_id(int respoinsity_id) {
		this.respoinsity_id = respoinsity_id;
	}

}
