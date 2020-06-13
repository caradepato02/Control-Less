package com.example.prueba.conexion;

import android.os.Handler;
import android.os.Message;

public class Conector {
    public static final int ESTADO_CONECTADO = 1;
    public static final int ESTADO_DESCONECTADO = 2;
    public static final int ESTADO_ENVIADO = 3;
    public static final int ESTADO_ERROR_CONECTAR = 4;


    Handler handler;
    Message msg;
    public void conectar() {
    }

    public void desconectar() {
    }

    public void enviarAccion(String accion) {
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
