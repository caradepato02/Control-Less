package com.example.prueba.Componentes;

import android.content.Context;

public class RadioButtonPro extends androidx.appcompat.widget.AppCompatRadioButton{
    private String accion;

    public RadioButtonPro(Context context) {
        super(context);
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "RadioButtonPro";
    }

}
