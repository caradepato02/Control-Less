package com.example.prueba.controles;


import android.view.View;

import com.example.prueba.controles.Controles;

import java.util.ArrayList;

public class Control {
    private ArrayList<View> widgets = new ArrayList<>();
    private String nombre;


    public Control() {
        this.nombre = "Control " + (Controles.getSize() + 1) ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addWidget(View v){
        widgets.add(v);
    }

    public ArrayList<View> getWidgets() {
        return widgets;
    }
}
