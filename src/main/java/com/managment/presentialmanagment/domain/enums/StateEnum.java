package com.managment.presentialmanagment.domain.enums;

public enum StateEnum{
	
	PENDING(1, "Pending"),
	CONCLUDED(2, "Concluded"),
	CANCELED(3, "Canceled");
	
	private int code;
	private String description;
	
	private StateEnum(int code, String description) {
	this.code = code;
	this.description = description;
	}	

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	public static StateEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (StateEnum type : StateEnum.values()) {
			if(code.equals(type.getCode())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}
