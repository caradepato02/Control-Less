package com.example.prueba.componentes;

import android.content.Context;

public class RadioButtonPro extends androidx.appcompat.widget.AppCompatRadioButton {
    //Texto que se enviará al ser seleccioado
    private String accion;

    //RadioGroup al que pertence
    private RadioGroupPro group;

    public RadioButtonPro(Context context) {
        super(context);
        group = null;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public RadioGroupPro getGroup() {
        return group;
    }

    public void removeGroup() {
        group.removeRadioButton(this);
    }

    public void setGroup(RadioGroupPro group) {
        if (group != null) {
            group.addRadioButton(this);
        }
        this.group = group;
    }
}
