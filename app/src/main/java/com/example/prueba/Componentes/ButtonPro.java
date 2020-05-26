package com.example.prueba.Componentes;

import android.content.Context;


public class ButtonPro extends androidx.appcompat.widget.AppCompatButton {
    private boolean vinculado;

    public ButtonPro(Context context) {
        super(context);
        this.vinculado = false;
    }

    @Override
    public String toString() {
        return "ButtonPro";
    }
}
