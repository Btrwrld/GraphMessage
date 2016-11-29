package com.ce.datosi.GraphMessage.EstructurasDeDatos;

public class Arista<T> {

    private NodoGrafo<T> nodo1;

    private NodoGrafo<T> nodo2;

    private double peso;

    public Arista(NodoGrafo<T> nodo1, NodoGrafo<T> nodo2, double peso) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.peso = peso;
    }

    public NodoGrafo<T> nodoInicial() {
        return nodo1;
    }

    public NodoGrafo<T> nodoObjetivo() {
        return nodo2;
    }

    public boolean estaEnMedio(NodoGrafo<T> nodo1, NodoGrafo<T> nodo2) {
        return (this.nodo1 == nodo1 && this.nodo2 == nodo2);
    }

	public double getPeso() {
		return peso;
	}
}
