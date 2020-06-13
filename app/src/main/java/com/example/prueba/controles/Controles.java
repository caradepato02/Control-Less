package com.example.prueba.controles;

import java.util.ArrayList;

public class Controles {
    private static ArrayList<Control> controls = new ArrayList<>();


    public Controles() {

    }

    public static void addControl(Control c) {
        controls.add(c);
    }

    public static int getSize() {
        return controls.size();
    }

    public static Control getControl(int posicion) {
        return controls.get(posicion);
    }

    public static int getIndexControl(Control control) {
        return controls.indexOf(control);
    }

    public static ArrayList getLListaControles() {
        return controls;
    }

    public static void removeControl(Control control) {
        controls.remove(control);
    }


}
