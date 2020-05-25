package com.example.prueba.conexion;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post extends Thread {
    public Post() {

    }
    public static void post(String url, byte data, String contentType) throws IOException {
        HttpURLConnection connection = null;
        OutputStreamWriter out = null;
        OutputStream os = null;
        String datos = "Azul";

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setFixedLengthStreamingMode(datos.length());


            os = connection.getOutputStream();
            out = new OutputStreamWriter(os,"UTF-8");
            out.write(datos);
            out.flush();
            out.close();
            os.close();

            Log.wtf("Si","Envido");


        } finally {
            if (connection != null) connection.disconnect();
            if (out != null) out.close();

        }
    }

    @Override
    public void run() {
        super.run();
        byte data = 88;
        try {
            post("http://192.168.1.79/",data,"application/x-www-form-urlencoded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
