package com.example.prueba.controles;

import java.util.ArrayList;

public class Controles {
    public static ArrayList<Control> controls = new ArrayList<>();


    public Controles() {

    }

    public static void addControl(Control c){
        controls.add(c);
    }

    public static int getSize(){
        return controls.size();
    }

    public static Control getControl(int posicion){
        return controls.get(posicion);
    }


}
