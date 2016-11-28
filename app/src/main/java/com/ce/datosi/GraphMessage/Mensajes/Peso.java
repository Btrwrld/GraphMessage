package com.ce.datosi.GraphMessage.Mensajes;

/**
 * @author chrisarrefall
 *
 */
public class Peso {
	private Integer peso;
	
	public void calculadorPeso(int x1,int y1,int x2,int y2){
		peso = (int) Math.sqrt(Math.pow(y1-y2,2)+Math.pow(x1-x2,2));
	}

	public Integer getPeso() {
		return peso;
	}
}
