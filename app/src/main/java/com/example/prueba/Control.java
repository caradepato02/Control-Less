package com.example.prueba;


import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Control {
    private ArrayList<View> widgets = new ArrayList<>();
    private String nombre;

    public Control() {
        this.nombre = "Control";
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
}
