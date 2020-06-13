package com.example.prueba.componentes;

import android.content.Context;
import android.widget.Switch;

public class SwitchPro extends Switch {
    //Texto que se envia cuando el switch se enciende
    String accionEncendido;

    //Texto que se envia cuando el switch se apaga
    String accionApagado;

    public SwitchPro(Context context) {
        super(context);
    }

    public String getAccionApagado() {
        return accionApagado;
    }

    public void setAccionApagado(String accionApagado) {
        this.accionApagado = accionApagado;
    }

    public String getAccionEncendido() {
        return accionEncendido;
    }

    public void setAccionEncendido(String accionEncendido) {
        this.accionEncendido = accionEncendido;
    }


}
