package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;
import java.util.List;

public class ResponsityModel implements Serializable{
	private int responsity_id;
	private String responsity_name;
    private String responsity_location;
    private int responsity_max_number;
    private int responsity_now_number;
    private List<CarModel> listCar;
	public int getResponsity_id() {
		return responsity_id;
	}
	public void setResponsity_id(int responsity_id) {
		this.responsity_id = responsity_id;
	}
	public String getResponsity_name() {
		return responsity_name;
	}
	public void setResponsity_name(String responsity_name) {
		this.responsity_name = responsity_name;
	}
	public String getResponsity_location() {
		return responsity_location;
	}
	public void setResponsity_location(String responsity_location) {
		this.responsity_location = responsity_location;
	}
	public int getResponsity_max_number() {
		return responsity_max_number;
	}
	public void setResponsity_max_number(int responsity_max_number) {
		this.responsity_max_number = responsity_max_number;
	}
	public int getResponsity_now_number() {
		return responsity_now_number;
	}
	public void setResponsity_now_number(int responsity_now_number) {
		this.responsity_now_number = responsity_now_number;
	}
	public List<CarModel> getListCar() {
		return listCar;
	}
	public void setListCar(List<CarModel> listCar) {
		this.listCar = listCar;
	}
    
}
