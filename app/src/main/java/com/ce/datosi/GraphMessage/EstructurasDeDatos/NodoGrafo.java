package com.ce.datosi.GraphMessage.EstructurasDeDatos;

import com.fernandocejas.arrow.optional.Optional;

import java.util.ArrayList;
import java.util.List;



public class NodoGrafo<T> {

    private T vertice;

    private List<Arista<T>> aristas;

    private NodoGrafo<T> nodoPadre;

    private boolean visitado;

    public NodoGrafo(T vertice) {
        this.vertice = vertice;
        this.aristas = new ArrayList<>();
    }

    public T vertice() {
        return vertice;
    }

    public boolean agregarArista(NodoGrafo<T> nodo, Double peso) {
        if (poseeArista(nodo)) {
            return false;
        }
        Arista<T> nuevaArista = new Arista<>(this, nodo, peso);
        return aristas.add(nuevaArista);
    }

    public boolean removerArista(NodoGrafo<T> nodo) {
        if(encontrarArista(nodo) == null){
            return false;
        }
        Arista<T> optional = encontrarArista(nodo);
        return aristas.remove(optional);
    }

    public boolean poseeArista(NodoGrafo<T> nodo) {
        if(encontrarArista(nodo) == null){
            return false;
        }
        return true;
    }

    public Arista<T> encontrarArista(NodoGrafo<T> nodo) {
        Arista<T> arista = null;
        for (Arista<T> a:
             this.aristas) {
            if (a.nodoObjetivo() == nodo) {
                arista = a;
            }
        }
        return arista;
    }

    public List<Arista<T>> aristas() {
        return aristas;
    }

    public int getCantidadAristas() {
        return aristas.size();
    }

    public NodoGrafo<T> nodoPadre() {
        return nodoPadre;
    }

    public boolean esVisitado() {
        return visitado;
    }

    public void setVisitado(boolean esVisitado) {
        this.visitado = esVisitado;
    }

    public void setPadre(NodoGrafo<T> nodoPadre) {
        this.nodoPadre = nodoPadre;
    }
}