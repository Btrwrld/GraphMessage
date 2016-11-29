package com.ce.datosi.GraphMessage.Usuario;

public class ID {
	private String nombre;
	private String IP;
	private String MAC;
	private double x;
	private double y;
	
	public ID(){
		
	}
	public String getNombre() {
		return nombre;
	}
	public String getIP() {
		return IP;
	}
	public String getMAC() {
		return MAC;
	}
	public void setNombre(String iD) {
		nombre = iD;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public void setMAC(String mAC) {
		MAC = mAC;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
	

}
