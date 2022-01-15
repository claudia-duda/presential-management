package com.managment.presentialmanagment.domain.enums;

public enum Profile {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLENT"),
	SUPER(3, "ROLE_SUPER");
	
	
	private int code;
	private String description;
	
	private Profile(int code, String description) {
	this.code = code;
	this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	public static Profile toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (Profile type : Profile.values()) {
			if(code.equals(type.getCode())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}

