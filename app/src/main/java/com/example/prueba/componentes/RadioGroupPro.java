package com.example.prueba.componentes;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;

public class RadioGroupPro extends View implements RadioButton.OnCheckedChangeListener {
    //Nombre del radio group pro
    private String nombre;

    //Lista de los Radio button pro que contiene
    private ArrayList<RadioButtonPro> elementosGrupo;


    public RadioGroupPro(Context context) {
        super(context);
        elementosGrupo = new ArrayList<>();
    }

    public ArrayList<RadioButtonPro> getElementosGrupo() {
        return elementosGrupo;
    }

    public void setElementosGrupo(ArrayList<RadioButtonPro> elementosGrupo) {
        this.elementosGrupo = elementosGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addRadioButton(RadioButtonPro radioButtonPro) {
        radioButtonPro.setChecked(false);
        radioButtonPro.setOnCheckedChangeListener(this);
        elementosGrupo.add(radioButtonPro);
    }

    public void removeRadioButton(RadioButtonPro radioButtonPro) {
        elementosGrupo.remove(radioButtonPro);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (RadioButtonPro radioButtonPro : elementosGrupo) {
            if (isChecked && !radioButtonPro.equals(buttonView)) {
                radioButtonPro.setChecked(false);
            }
        }
    }
}

