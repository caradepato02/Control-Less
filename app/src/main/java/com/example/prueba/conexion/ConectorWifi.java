package com.example.prueba.conexion;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConectorWifi extends Conector {
    private String ip;

    private HttpURLConnection connection;
    private OutputStreamWriter out;
    private OutputStream os;
    private String datos;

    public ConectorWifi(String ip) {
        this.ip = "http://" + ip + "/";
    }

    @Override
    public void conectar() {

    }

    @Override
    public void desconectar() {

    }

    @Override
    public void enviarAccion(String accion) {
        this.datos = accion;
        if (accion != null) {
            Thread hilo = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        connection = (HttpURLConnection) new URL(ip).openConnection();
                        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        connection.setDoOutput(true);
                        connection.setRequestMethod("POST");
                        connection.setFixedLengthStreamingMode(datos.length());
                        os = connection.getOutputStream();
                        out = new OutputStreamWriter(os, "UTF-8");
                        out.write(datos);
                        msg = handler.obtainMessage(Conector.ESTADO_ENVIADO);
                        handler.sendMessage(msg);
                        out.flush();
                        out.close();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (connection != null) {
                            connection.disconnect();
                        }
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            hilo.start();
        }
    }
}

