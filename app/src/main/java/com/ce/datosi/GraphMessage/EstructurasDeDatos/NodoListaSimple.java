package com.ce.datosi.GraphMessage.EstructurasDeDatos;

public class NodoListaSimple<Dato> {

	private Dato dato;
	private NodoListaSimple<Dato> siguiente;
	
	public NodoListaSimple(Dato info) {
		this(info,null);
	}
	
	public NodoListaSimple(Dato dato, NodoListaSimple<Dato> siguiente) {
		this.dato = dato; 
		this.siguiente = siguiente;
	}
	
	public Dato getDato(){
		return dato;
	}
	
	public void setInfo(Dato dato){
		this.dato = dato;
	}
	
	public void setSiguiente(NodoListaSimple<Dato> siguiente){
		this.siguiente = siguiente;
	}
	
	public NodoListaSimple<Dato> getSiguiente(){
		return this.siguiente;
	}
}
