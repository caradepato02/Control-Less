package com.example.prueba.componentes;

import android.content.Context;
import android.widget.EditText;


public class ButtonPro extends androidx.appcompat.widget.AppCompatButton {
    //true cuando el botonn se encuentra vinculado con un EditText
    private boolean vinculado;

    //Texto que se envia al presionar el boton
    private String accionPresionar;

    //Texto que se envia despues de presionar y soltar el boton
    private String accionSoltar;

    //EditText con el que se encuentra vinculado
    private EditText vinculo;

    public ButtonPro(Context context) {
        super(context);
        this.vinculado = false;
        vinculo = null;
    }

    public EditText getVinculo() {
        return vinculo;
    }

    public void setVinculo(EditText vinculo) {
        if (vinculo != null) {
            this.vinculado = true;
        }
        this.vinculo = vinculo;
    }

    public boolean isVinculado() {
        return vinculado;
    }

    //Si el boton se encuentra vinculado, enviará como acción el texto que tenga el editText
    public String getAccionPresionar() {
        if (vinculado) {
            return vinculo.getText().toString();
        } else {
            return accionPresionar;
        }
    }

    public void setAccionPresionar(String accionPresionar) {
        this.accionPresionar = accionPresionar;
    }

    public String getAccionSoltar() {
        return accionSoltar;
    }

    public void setAccionSoltar(String accionSoltar) {
        this.accionSoltar = accionSoltar;
    }
}
