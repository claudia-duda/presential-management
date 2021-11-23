package com.managment.presentialmanagment.domain.enums;

public enum PriorityEnum {
	
	RED(1, "Main Activity"),
	YELLOW(2, "secondary activity"),
	GREEN(3, "Optional activity");
	
	
	private int code;
	private String description;
	
	private PriorityEnum(int code, String description) {
	this.code = code;
	this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	public static PriorityEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (PriorityEnum type : PriorityEnum.values()) {
			if(code.equals(type.getCode())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + code);
	}
}

