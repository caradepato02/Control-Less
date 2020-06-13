package com.example.prueba.controles;


import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.prueba.R;
import com.example.prueba.componentes.ButtonPro;
import com.example.prueba.componentes.RadioButtonPro;
import com.example.prueba.componentes.RadioGroupPro;
import com.example.prueba.componentes.SwitchPro;
import com.example.prueba.conexion.Conector;

import java.util.ArrayList;

public class Control {
    private ArrayList<View> widgets = new ArrayList<>();
    private Conector conector;
    private String nombre;
    private int icono;


    public Control() {
        icono = R.drawable.control2;
        this.nombre = "Control " + (Controles.getSize() + 1);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addWidget(View v) {
        widgets.add(v);
    }

    public ArrayList<View> getWidgets() {
        return widgets;
    }

    public void removeWidgets() {
        widgets.clear();
    }

    public View getCopiaWidget(View original, Context context) {
        View copia;
        switch (original.getClass().toString()) {
            case "class com.example.prueba.componentes.ButtonPro":
                copia = new ButtonPro(context);
                ((ButtonPro) copia).setAccionPresionar(((ButtonPro) original).getAccionPresionar());
                ((ButtonPro) copia).setAccionSoltar(((ButtonPro) original).getAccionSoltar());
                ((ButtonPro) copia).setVinculo(((ButtonPro) original).getVinculo());
                ((ButtonPro) copia).setText(((ButtonPro) original).getText().toString());
                copia.setX(original.getX());
                copia.setY(original.getY());
                break;
            case "class com.example.prueba.componentes.SwitchPro":
                copia = new SwitchPro(context);
                ((SwitchPro) copia).setAccionEncendido(((SwitchPro) original).getAccionEncendido());
                ((SwitchPro) copia).setAccionApagado(((SwitchPro) original).getAccionApagado());
                ((SwitchPro) copia).setText(((SwitchPro) original).getText().toString());
                copia.setScaleX(original.getScaleX());
                copia.setScaleY(original.getScaleY());
                copia.setX(original.getX());
                copia.setY(original.getY());
                break;
            case "class com.example.prueba.componentes.RadioButtonPro":
                copia = new RadioButtonPro(context);
                ((RadioButtonPro) copia).setAccion(((RadioButtonPro) original).getAccion());
                ((RadioButtonPro) copia).setText(((RadioButtonPro) original).getText().toString());
                ((RadioButtonPro) copia).setGroup(((RadioButtonPro) original).getGroup());
                copia.setScaleX(original.getScaleX());
                copia.setScaleY(original.getScaleY());
                copia.setX(original.getX());
                copia.setY(original.getY());
                break;
            case "class android.widget.EditText":
                copia = new EditText(context);
                ((EditText) copia).setText(((EditText) original).getText());
                copia.setX(original.getX());
                copia.setY(original.getY());
                break;
            case "class android.widget.TextView":
                copia = new TextView(context);
                ((TextView) copia).setText(((TextView) original).getText());
                copia.setX(original.getX());
                copia.setY(original.getY());
                break;
            case "class com.example.prueba.componentes.RadioGroupPro":
                copia = new RadioGroupPro(context);
                ((RadioGroupPro) copia).setNombre(((RadioGroupPro) original).getNombre());
                ((RadioGroupPro) copia).setElementosGrupo(((RadioGroupPro) original).getElementosGrupo());
                break;
            default:
                copia = original;
                break;
        }

        copia.setX(original.getX());
        copia.setY(original.getY());
        return copia;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public void dejarHuerfanos() {
        if (!widgets.isEmpty()) {
            try {
                ((ConstraintLayout) widgets.get(0).getParent()).removeAllViews();
            } catch (Exception e) {

            }

        }
    }

    public Conector getConector() {
        return conector;
    }

    public void setConector(Conector conector) {
        this.conector = conector;
    }
}
