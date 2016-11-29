package com.ce.datosi.GraphMessage.Herramientas;

import com.ce.datosi.GraphMessage.EstructurasDeDatos.Grafo;
import com.ce.datosi.GraphMessage.Usuario.ID;

import java.util.LinkedList;

public class Codigo {
	
	private String nombre = "";
	private String IP  = "";
	private String MAC = "";
	private double x = 0.0;
	private double y = 0.0;
	private String multimedia = "";
    private String IPreceptor = "";
	private Grafo<ID> grafo = new Grafo<>();
	private LinkedList<ID> ids = new LinkedList<>();
	
	

	public Grafo<ID> getGrafo() {
		return grafo;
	}
	public void setGrafo(Grafo<ID> grafo) {
		this.grafo = grafo;
	}
	public LinkedList<ID> getIds() {
		return ids;
	}
	public void setIds(LinkedList<ID> ids) {
		this.ids = ids;
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
	public String getIPReceptor() {
        return IPreceptor;
	}
	public void setIPReceptor(String receptor) {
		this.IPreceptor = receptor;
	}
	public String getMultimedia() {
		
		if(this.multimedia != ""){
		
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
    public String[] getMultimediaFrag() {
        return this.multimedia.split(" ");
    }
	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}


}
