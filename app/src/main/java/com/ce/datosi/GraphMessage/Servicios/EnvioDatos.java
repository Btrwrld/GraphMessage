package com.ce.datosi.GraphMessage.Servicios;


import android.os.AsyncTask;

import com.ce.datosi.GraphMessage.Herramientas.Codigo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by erick on 11/23/2016.
 */

public class EnvioDatos extends AsyncTask<Void, Void, Void> {

    private static final int SERVERPORT = 4445;
    private static final String SERVER_IP = "192.168.100.9";

    private Codigo info;

    public EnvioDatos(Codigo info){
        this.info = info;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if(Comunicador.Serversocket == null){
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                Comunicador.Serversocket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String datosAEnviar = gson.toJson(this.info);


        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Comunicador.Serversocket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(datosAEnviar);

        BufferedReader input = null;
        String respuesta = null;
        try {
            input = new BufferedReader(new InputStreamReader(Comunicador.Serversocket.getInputStream()));
            respuesta = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Codigo info = gson.fromJson(respuesta, Codigo.class);

        if(info.getGrafo() != null){
            Comunicador.setGrafoUsuarios(info.getGrafo());
        }
        if(info.getIds() != null){
            try {
                Comunicador.setClientes(info.getIds());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




        return null;
    }

}