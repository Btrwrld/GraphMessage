package com.ce.datosi.GraphMessage.EstructurasDeDatos;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Grafo<T> {

    private final Map<T, NodoGrafo<T>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    /**
     * Adds a vertice to the graph.
     * @param vertice        vertice to add
     */
    public boolean agregarVertice(T vertice) {
        if (listaAdyacencia.containsKey(vertice)) {
            return false;
        }
        listaAdyacencia.put(vertice, new NodoGrafo<>(vertice));
        return true;
    }

    /**
     * Agrega una arista dirigida entre dos vertices del grafo.
     * @param vertice1       vertice donde la arista inicia.
     * @param vertice2       vertice donde la arista termina.
     */
    public boolean agregarArista(T vertice1, T vertice2) {
        return agregarArista(vertice1, vertice2, 255.0);
    }

    /**
     * Agrega una arista pesada y dirigida entre dos vertices del grafo.
     * @param vertice1       vertice donde la arista inicia.
     * @param vertice2       vertice donde la arista termina.
     * @param peso           peso de la arista.
     */
    public boolean agregarArista(T vertice1, T vertice2, Double peso) {
        if (!contieneVertice(vertice1) || !contieneVertice(vertice2)) {
            throw new RuntimeException("El vertice no existe");
        }

        // add the edge
        NodoGrafo<T> node1 = getNodo(vertice1);
        NodoGrafo<T> node2 = getNodo(vertice2);
        return node1.agregarArista(node2, peso) && node2.agregarArista(node1, peso);
    }

    /**
     * Remueve un vertice del grafo.
     * @param vertice        vertice por remover.
     * @return true si el vertice fue removido, false si el vertice no fue encontrado.
     */

    /**
     * Metodo para remover una arista dirigida entre dos nodos.
     * @param vertice1       vertice donde la arista inicia.
     * @param vertice2       vertice donde la arista termina.
     * @return true si la arista fue removida, false sino se encontro la arista.
     */
    public boolean removerArista(T vertice1, T vertice2) {
        if (!contieneVertice(vertice1) || !contieneVertice(vertice2)) {
            return false;
        }
        return getNodo(vertice1).removerArista(getNodo(vertice2)) && getNodo(vertice2).removerArista(getNodo(vertice1));
    }

    /**
     * Metodo para encontrar la cantidad de vertices en el grafo.
     * @return cantidad de vertices.
     */
    public int cantidadVertices() {
        return listaAdyacencia.keySet().size();
    }


    /**
     * Metodo que revisa si un vertice esta contenido en el grafo.
     * @param vertice    vertice por revisar.
     * @return true si se encuentra el vertice, false de lo contrario.
     */
    public boolean contieneVertice(T vertice) {
        return listaAdyacencia.containsKey(vertice);
    }

    /**
     * Metodo que revisa si una arista esta contenida en el grafo.
     * @param vertice1       vertice donde la arista inicia.
     * @param vertice2       vertice donde la arista termina.
     * @return returns true si se encuentra la arista, false de lo contrario.
     */
    public boolean contieneArista(T vertice1, T vertice2) {
        if (!contieneVertice(vertice1) || !contieneVertice(vertice2)) {
            return false;
        }
        return getNodo(vertice1).poseeArista(getNodo(vertice2));
    }

    /**
     * Metodo para encontrar el camino mas corto de un nodo a otro.(Algoritmo Djkstra)
     * @param verticeInicial   vertice donde inicia el camino.
     * @param verticeFinal     vertice donde termina el camino.
     * @return  lista de vertices en el camino mas corto y null si no existe.
     */
    public ListaEnlazadaSimple<T> Dijkstra(T verticeInicial, T verticeFinal) {

        ListaEnlazadaSimple<T> camino = new ListaEnlazadaSimple<T>();
        camino.agregarInicio(verticeInicial);

        return Dijkstra(verticeInicial, verticeFinal, camino);
    }

    private ListaEnlazadaSimple<T> Dijkstra(T verticeInicial, T verticeFinal, ListaEnlazadaSimple<T> caminoRecorrido) {

        Map<Double, ListaEnlazadaSimple<T>> caminos = new TreeMap<Double, ListaEnlazadaSimple<T>>();

        ListaEnlazadaSimple<T> caminoActual = caminoRecorrido.clonar();

        for(Arista<T> arista : getNodo(verticeInicial).aristas()){

            caminoActual.agregarFinal(arista.nodoObjetivo().vertice());

            if (arista.nodoObjetivo().vertice() != verticeFinal){
                caminoActual = Dijkstra(arista.nodoObjetivo().vertice(), verticeFinal, caminoActual);
            }

            caminos.put(calcularPeso(caminoActual), caminoActual);
            caminoActual = caminoRecorrido.clonar();
        }


        return caminos.get(caminos.keySet().iterator().next());

    }

    private Double calcularPeso(ListaEnlazadaSimple<T> camino){
        Double peso = 0.0;
        for(int i = 0; i < camino.largo() - 1; i++){
            peso += getNodo(camino.get(i).getDato())
                    .encontrarArista( getNodo( camino.get(i + 1).getDato()))
                    .getPeso();


        }
        return peso;
    }

    private NodoGrafo<T> getNodo(T value) {
        return listaAdyacencia.get(value);
    }



}
