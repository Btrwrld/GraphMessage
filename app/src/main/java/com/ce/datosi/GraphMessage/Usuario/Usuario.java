package com.ce.datosi.GraphMessage.Usuario;


import com.ce.datosi.GraphMessage.Mensajes.Mensaje;

public class Usuario {
	private ID id;
	private int x;
	private int y;
	private Mensaje mensaje = new Mensaje();
	
	public Usuario(){
		id = new ID();
	}

	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		id.setX(x);
		this.x = x;
	}
	public void setY(int y) {
		id.setY(y);
		this.y = y;
	}
	

}
