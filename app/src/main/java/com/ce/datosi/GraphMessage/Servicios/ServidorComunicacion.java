package com.ce.datosi.GraphMessage.Servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.ce.datosi.GraphMessage.Herramientas.Codigo;
import com.ce.datosi.GraphMessage.Usuario.ID;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ServidorComunicacion implements Runnable {

    private Socket clientSocket;
    private ID usuario;
	private BufferedReader input;
    private PrintWriter out;
	private ServidorAndroid servidor;

    public ServidorComunicacion(Socket clientSocket, ID usuario, ServidorAndroid servidor) {

        this.clientSocket = clientSocket;
        this.usuario = usuario;
        this.servidor = servidor;

        try {
            this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	    public void run() {

	       while(!Thread.currentThread().isInterrupted()){

	            try {
	            	//Se obtiene y decodifica la informacion
	                String entrada = input.readLine(); 
	                
	                if(entrada != null){
		                GsonBuilder builder = new GsonBuilder();
		                Gson gson = builder.create();
		                Codigo info = gson.fromJson(entrada, Codigo.class);

		                if(info.getMultimedia() != null){

		                	String[] mensaje = info.getMultimediaFrag();

                            if(mensaje[0] == "IMAGEN"){
                                System.out.print(mensaje[0]);
                                //Mostrar en recibidos
                            }
                            else if(mensaje[0] == "AUDIO"){
                                System.out.print(mensaje[0]);
                                //Mostrar en recibidos
                            }
                            else if(mensaje[0] == "TEXTO"){
                                String porMostrar = "";
                                System.out.print(mensaje[0]);
                                for(int i = 1; i < mensaje.length; i++){
                                    porMostrar = porMostrar + " " + mensaje[i];
                                }


                                //Mostrar en recibidos
                            }
                            else{

                                String porEnviar = "";
                                for(int i = 1; i < mensaje.length; i++){
                                    porEnviar = porEnviar + " " + mensaje[i];
                                }
                                Comunicador.agregarACola(mensaje[0], porEnviar);
                            }
		                }
		                System.out.println("Llego info");

	                }

                    if(Comunicador.destinatarioCola().equals(usuario.getIP())){
                        out.println(Comunicador.mensajeCola());
                        Comunicador.sacarDeCola();
                    }
	                
	                Thread.sleep(500);
   
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	    }

}
