package com.ce.datosi.GraphMessage.Mensajes;


import com.ce.datosi.GraphMessage.Usuario.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InterpretadorDeMensajes {
	private Usuario mensaje;
	
	public InterpretadorDeMensajes(String mensaje){
		this.mensaje = new Usuario();
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        this.mensaje = gson.fromJson(mensaje, Usuario.class);
	}

	public Usuario getMensaje() {
		return mensaje;
	}

	public void setMensaje(Usuario mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
