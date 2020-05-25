package com.example.prueba.Componentes;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.prueba.R;



public class Pad extends LinearLayout {
    private Button btnArriba;
    private Button btnAbajo;
    private Button btnDerecha;
    private Button btnIzquierda;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private TypedArray tyArray;
    private int arriba;
    private int abajo;
    private int derecha;
    private int izquierda;



    public Pad(Context context) {
        super(context);
        tyArray = context.obtainStyledAttributes(R.styleable.view_pad);
        arriba =tyArray.getInt(R.styleable.view_pad_iconoArriba,R.drawable.ic_arrow_drop_up_24px);
        abajo = tyArray.getInt(R.styleable.view_pad_iconoAbajo,R.drawable.ic_arrow_drop_down_24px);
        derecha = tyArray.getInt(R.styleable.view_pad_iconoDerecha,R.drawable.ic_arrow_right_24px);
        izquierda = tyArray.getInt(R.styleable.view_pad_iconoIzquierda,R.drawable.ic_arrow_left_24px);
        tyArray.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pad, this, true);
        Log.wtf("hijos",String.valueOf(getChildCount()));
        linearLayout1 = (LinearLayout) getChildAt(0);
        linearLayout2 = (LinearLayout)linearLayout1.getChildAt(1);
        btnArriba = (Button) linearLayout1.getChildAt(0);
        btnIzquierda = (Button) linearLayout2.getChildAt(0);
        btnDerecha = (Button) linearLayout2.getChildAt(1);
        btnAbajo = (Button) linearLayout1.getChildAt(2);

        btnArriba.setBackgroundResource(arriba);
        btnAbajo.setBackgroundResource(abajo);
        btnDerecha.setBackgroundResource(derecha);
        btnIzquierda.setBackgroundResource(izquierda);

    }
    




}
