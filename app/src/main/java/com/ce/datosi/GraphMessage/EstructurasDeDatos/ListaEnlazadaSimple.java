package com.ce.datosi.GraphMessage.EstructurasDeDatos;

public class ListaEnlazadaSimple<Dato> {
	
	public NodoListaSimple<Dato> cabeza,cola;
	private int largo = 0;
		
	public void agregarInicio(Dato dato){
		
		NodoListaSimple<Dato> nodo = new NodoListaSimple<>(dato);
		
		if (cabeza == null){
			this.cola = nodo;
		}
		else{
			nodo.setSiguiente(cabeza);
		}
		this.cabeza = nodo;
		
		++this.largo;
	}
	
	public void agregarFinal(Dato dato){
		
		NodoListaSimple<Dato> nodo = new NodoListaSimple<>(dato);
		
		if(cola == null){
			this.cabeza = nodo;
		}
		else{
			this.cola.setSiguiente(nodo);
		}
		this.cola = nodo;
		
		++this.largo;	
	}
	
	public NodoListaSimple<Dato> buscarDato(Dato objetivo){
		
		NodoListaSimple<Dato> temp = this.cabeza;
		
		for(int i = 0; i < this.largo; i++){
			if (temp.getDato() == objetivo){
				break;
			}
			temp = temp.getSiguiente();
		}
		//si no se encuentra regresa nulo
		return temp;
	}
	
public NodoListaSimple<Dato> get(int indice){
		
		NodoListaSimple<Dato> temp = this.cabeza;
		
		for(int i = 0; i < indice; i++){
			temp = temp.getSiguiente();
		}
		return temp;
	}
	
	public ListaEnlazadaSimple<Dato> eliminarDato(Dato objetivo){
		
		NodoListaSimple<Dato> temp = buscarDato(objetivo);
		
		if (temp != null){
			this.eliminarNodo(temp);
		}
		
		return this;
		
	}
	
	public boolean eliminarNodo(NodoListaSimple<Dato> nodo){
		if(nodo == this.cabeza){
			this.cabeza = this.cabeza.getSiguiente();
			--this.largo;
			return true;
		}
		else{
			NodoListaSimple<Dato> temp = this.cabeza;
			for(int i = 0; i < this.largo; i++){
				if (temp.getSiguiente() == nodo){
					temp.setSiguiente(temp.getSiguiente().getSiguiente());
					--this.largo;
					return true;
				}
				temp = temp.getSiguiente();
			}
			return false;
		}
	}
	
	public boolean estaPresente(Dato objetivo){
		
		NodoListaSimple<Dato> temp = this.cabeza;
		
		for(int i = 0; i < this.largo; i++){
			if(temp.getDato() == objetivo){return true;}
			
			temp = temp.getSiguiente();
		}
		return false;
	}
	
	public int largo(){
		return this.largo;
	}
	public boolean estaVacia() { 
		return largo == 0; 
	}
	
	public ListaEnlazadaSimple<Dato> clonar(){
		
		ListaEnlazadaSimple<Dato> clon = new ListaEnlazadaSimple<Dato>();
		
		if (this.largo > 0){
			for(int i = 0; i < this.largo; i++){
				clon.agregarFinal(this.get(i).getDato());
			}
		}
		
		return clon;
	}
}




