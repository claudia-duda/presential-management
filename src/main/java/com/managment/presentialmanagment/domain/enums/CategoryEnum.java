package com.managment.presentialmanagment.domain.enums;

public enum CategoryEnum {
	
	SIMCARD(1, "SIM Card"),
	AKACARD(2, "AKA Card"),
	HEADSET(3, "Headset"),
	CELLPHONE(4, "DUT"),
	OTHER(5, "Other");
	
	private int code;
	private String description;
	
	private CategoryEnum(int code, String description) {
	this.code = code;
	this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	public static CategoryEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (CategoryEnum type : CategoryEnum.values()) {
			if(code.equals(type.getCode())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}

