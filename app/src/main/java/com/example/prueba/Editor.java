package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;


public class Editor extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    ConstraintLayout areaComponentes;
    private ImageButton agregarRadio;
    private ImageButton agregarTextArea;
    private ImageButton agregarSwitch;
    private ImageButton agregarPad;
    private float posX;
    private float posY;

    Control actualControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        areaComponentes = findViewById(R.id.areaComponentes);
        agregarPad = findViewById(R.id.agregarPad);
        agregarRadio = findViewById(R.id.agregarRadioButton);
        agregarSwitch = findViewById(R.id.agregarSwitch);
        agregarTextArea = findViewById(R.id.agregarTextArea);

        agregarTextArea.setOnClickListener(this);
        agregarSwitch.setOnClickListener(this);
        agregarRadio.setOnClickListener(this);
        agregarPad.setOnClickListener(this);
        actualControl = new Control();
        Controles.addControl(actualControl);

        //botonPrueba.setOnTouchListener(this);

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                //ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams((int) event.getX(), (int) event.getY());
                //v.setLayoutParams(params);
                posX = event.getRawX()-(v.getWidth()/2);
                //posY = event.getRawY()-(v.getHeight()*(float)1.5);
                posY = event.getRawY()-(500);

                Log.wtf("X: ",String.valueOf(posX));
                Log.wtf("Y: ",String.valueOf(posY));

                v.setX(posX);
                v.setY(posY);
                v.invalidate();


                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editor, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_guardar:

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.agregarRadioButton:
                RadioButton radBtn = new RadioButton(this);
                radBtn.setLayoutParams(new ConstraintLayout.LayoutParams(100, 200));
                areaComponentes.addView(radBtn);
                radBtn.setX(areaComponentes.getWidth()/2);
                radBtn.setY(areaComponentes.getHeight()/2);
                radBtn.setOnTouchListener(this);
                actualControl.addWidget(radBtn);
                break;
            case R.id.agregarSwitch:
                Switch sw = new Switch(this);
                sw.setLayoutParams(new ConstraintLayout.LayoutParams(150, 200));
                areaComponentes.addView(sw);
                sw.setX(areaComponentes.getWidth()/2);
                sw.setY(areaComponentes.getHeight()/2);
                sw.setOnTouchListener(this);
                actualControl.addWidget(sw);
                break;
            case R.id.agregarTextArea:
                EditText editTxt = new EditText(this);
                editTxt.setLayoutParams(new ConstraintLayout.LayoutParams(400, 150));
                areaComponentes.addView(editTxt);
                editTxt.setX(areaComponentes.getWidth()/2);
                editTxt.setY(areaComponentes.getHeight()/2);
                editTxt.setOnTouchListener(this);
                actualControl.addWidget(editTxt);
                break;
        }
    }
}
