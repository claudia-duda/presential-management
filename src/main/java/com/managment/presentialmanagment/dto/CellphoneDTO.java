package com.managment.presentialmanagment.dto;

import java.io.Serializable;

import com.managment.presentialmanagment.domain.Cellphone;

public class CellphoneDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String model;
	private String HWVersion;
	private String code;
	
	public CellphoneDTO() {
		
	}
	public CellphoneDTO(Cellphone cellphone) {
		model = cellphone.getModel();
		HWVersion = cellphone.getHWVersion();
		code = cellphone.getCode();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHWVersion() {
		return HWVersion;
	}

	public void setHWVersion(String hWVersion) {
		HWVersion = hWVersion;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
