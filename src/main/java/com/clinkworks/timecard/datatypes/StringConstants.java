package com.clinkworks.timecard.datatypes;

public enum StringConstants {
	
	TIMECARD_DEFAULT_PERSIST_UNIT("TimecardSystem");

	private final String value;
	
	private StringConstants(String value){
		this.value = value;
	}
	
	public String getConstantValue(){
		return value;
	}
}
