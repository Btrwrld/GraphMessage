package com.ce.datosi.GraphMessage.Servicios;

import com.ce.datosi.GraphMessage.EstructurasDeDatos.ListaEnlazadaSimple;
import com.ce.datosi.GraphMessage.Herramientas.Codigo;
import com.ce.datosi.GraphMessage.Usuario.ID;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Created by erick on 11/28/2016.
 */

public class ServidorAndroid implements Runnable {

    private ServerSocket serverSocket;
    public static final int SERVERPORT = 4445;


    private LinkedList<Thread> clientes = new LinkedList<>();

    @Override
    public void run() {
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(SERVERPORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {

            try {

                socket = serverSocket.accept();

                ID usuarioNuevo = new ID();
                usuarioNuevo.setIP(socket.getInetAddress().toString());

                ServidorComunicacion hiloComunicador = new ServidorComunicacion(socket, usuarioNuevo, this);

                Thread hilo = new Thread(hiloComunicador);
                clientes.addLast(hilo);
                hilo.start();
                System.out.println("IP nuevo: " + usuarioNuevo.getIP());

                Thread.sleep(100);

                if(!Comunicador.getEstadoServer()){
                    this.cerrarServidor();
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cerrarServidor(){
        for (Thread hilo:
             this.clientes) {
            hilo.interrupt();
        }
        Thread.currentThread().interrupt();
    }
}


