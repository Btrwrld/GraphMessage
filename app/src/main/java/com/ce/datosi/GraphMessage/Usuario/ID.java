package com.ce.datosi.GraphMessage.Usuario;

public class ID {
	private String nombre;
	private String IP;
	private String MAC;
	private int x;
	private int y;
	
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
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
