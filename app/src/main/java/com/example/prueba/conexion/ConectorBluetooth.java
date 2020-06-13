package com.example.prueba.conexion;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class ConectorBluetooth extends Conector {
    private BluetoothDevice dispositivoBt;
    private BluetoothSocket socketBt;
    private String btMAC;
    private UUID uuid;
    private OutputStream salidaDatos;
    private BluetoothAdapter btAdapter;


    public ConectorBluetooth(String btMAC) {
        this.btMAC = btMAC;
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            dispositivoBt = btAdapter.getRemoteDevice(btMAC);
            uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            socketBt = dispositivoBt.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Log.e("excepcion", "Socket's create() method failed", e);
        }

    }

    @Override
    public void conectar() {
        if (btAdapter.isEnabled()) {
            Thread iniciar = new Thread() {
                @Override
                public void run() {
                    super.run();
                    if (!socketBt.isConnected()) {
                        try {
                            socketBt = dispositivoBt.createRfcommSocketToServiceRecord(uuid);
                            socketBt.connect();
                            salidaDatos = socketBt.getOutputStream();
                            msg = handler.obtainMessage(Conector.ESTADO_CONECTADO);
                            handler.sendMessage(msg);
                        } catch (IOException connectException) {
                            try {
                                socketBt.close();
                                msg = handler.obtainMessage(Conector.ESTADO_ERROR_CONECTAR);
                                handler.sendMessage(msg);
                            } catch (IOException closeException) {
                                Log.e("hola", "Could not close the client socket", closeException);
                            }
                        }

                    }
                }
            };
            iniciar.start();
        }
    }

    @Override
    public void desconectar() {
        if (socketBt.isConnected()) {
            try {
                socketBt.close();
                msg = handler.obtainMessage(Conector.ESTADO_DESCONECTADO);
                handler.sendMessage(msg);
                finalize();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }

    @Override
    public void enviarAccion(final String accion) {
        if (accion != null) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    byte datos[] = accion.getBytes();
                    try {
                        salidaDatos.write(datos);
                        msg = handler.obtainMessage(Conector.ESTADO_ENVIADO);
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }


}
