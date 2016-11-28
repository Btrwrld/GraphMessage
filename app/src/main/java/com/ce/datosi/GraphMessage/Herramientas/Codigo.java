package com.ce.datosi.GraphMessage.Herramientas;

public class Codigo {
	
	private String nombre;
	private String IP;
	private String MAC;
	private double x = 0.0;
	private double y = 0.0;
	private String multimedia;
	
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
		this.nombre = iD;
	}
	public void setIP(String iP) {
		this.IP = iP;
	}
	public void setMAC(String MAC) {
		this.MAC = MAC;
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
	public String getMultimedia() {
		
		if(this.multimedia != null){
		
			String[] contenido = this.multimedia.split(" ");
			
			if(contenido[0] == "IMAGEN"){
				this.multimedia = "IMAGEN";
			}
			else if(contenido[0] == "AUDIO"){
				this.multimedia = "AUDIO";
			}
			else if(contenido[0] == "TEXTO"){
				for(int i = 1; i < contenido.length; i++){
					this.multimedia = this.multimedia + " " + contenido[i];
				}
			}
		}
	
		return multimedia;
	}
	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}


}
