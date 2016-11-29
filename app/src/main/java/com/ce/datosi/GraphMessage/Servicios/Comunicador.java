package com.ce.datosi.GraphMessage.Servicios;

import com.ce.datosi.GraphMessage.EstructurasDeDatos.Grafo;
import com.ce.datosi.GraphMessage.Usuario.ID;

import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by erick on 11/28/2016.
 */

public class Comunicador {

    public static Socket Serversocket;
    private static Thread servidorAndroid;
    private static boolean estadoServer = false;
    private static LinkedList<ID> clientes = new LinkedList<>();
    private static LinkedList<Thread> conexiones = new LinkedList<>();
    private static Grafo<ID> grafoUsuarios = new Grafo<>();
    private static LinkedList<String> porEnviar = new LinkedList<>();


    public static void agregarACola(String ip, String contenido){
        porEnviar.addLast(ip);
        porEnviar.addLast(contenido);
    }
    public static void sacarDeCola(){
        porEnviar.removeFirst();
        porEnviar.removeFirst();
    }
    public static String destinatarioCola(){
        return porEnviar.getFirst();
    }
    public static String mensajeCola(){
        return porEnviar.get(1);
    }

    public Comunicador(){
        this.servidorAndroid = new Thread(new ServidorAndroid());
        this.servidorAndroid.start();
        this.estadoServer = true;
    }

    public static LinkedList<Thread> getConexiones() {
        return conexiones;
    }

    public static void setConexiones(LinkedList<Thread> conexiones) {

        Comunicador.conexiones = conexiones;
    }

    public static Grafo<ID> getGrafoUsuarios() {
        return grafoUsuarios;
    }

    public static void setGrafoUsuarios(Grafo<ID> grafoUsuarios) {
        Comunicador.grafoUsuarios = grafoUsuarios;
    }

    public static LinkedList<ID> getClientes() {
        return clientes;
    }

    public static void setClientes(LinkedList<ID> clientes) throws InterruptedException {
        if(estadoServer){
            estadoServer = false;
            Thread.sleep(200);
        }

        servidorAndroid = new Thread(new ServidorAndroid());
        servidorAndroid.start();
        estadoServer = true;

        Comunicador.clientes = clientes;
    }

    public static boolean getEstadoServer(){
        return estadoServer;
    }


}
