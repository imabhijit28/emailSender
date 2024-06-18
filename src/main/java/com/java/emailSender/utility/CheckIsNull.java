package com.java.emailSender.utility;

public class CheckIsNull {

	public static	String forString(Object obj) {
		
		return obj 	!= null 	? 	obj.toString().trim() : "";
	}
    public static	Character forCharacter(Object obj) {
		
		return obj 	!= null && !obj.toString().trim().isEmpty()	 ? obj.toString().toUpperCase().charAt(0) : ' ';
	}
	public static	Integer forInteger(Object obj) {
		
		return obj	!= null && !obj.toString().trim().isEmpty()		? 	Integer.parseInt(obj.toString())  : 0;
	}
    public static	Float	forFloat(Object obj){
 		
		return	obj	!=	null && !obj.toString().trim().isEmpty()	?	Float.parseFloat(obj.toString())	:	0.0F;
	}
	public static	Double	forDouble(Object obj){
		
		return	obj	!=	null && !obj.toString().trim().isEmpty()	?	Double.parseDouble(obj.toString())	:	0.0;
	}
	public static	Long	forLong(Object obj){
		
		return	obj	!=	null && !obj.toString().trim().isEmpty()	?	Long.parseLong(obj.toString())	:	0L;
	}
	
	public static	Object	forObject(Object obj){
		
		return	obj	!=	null	?	obj	:	new String("");
	}
}