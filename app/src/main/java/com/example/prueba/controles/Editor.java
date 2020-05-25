package com.example.prueba.controles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.prueba.Componentes.BotonPro;
import com.example.prueba.Componentes.Pad;
import com.example.prueba.R;
import com.example.prueba.conexion.Conexion;



public class Editor extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
    private ConstraintLayout areaComponentes;
    private ImageButton agregarRadio;
    private ImageButton agregarTextArea;
    private ImageButton agregarSwitch;
    private ImageButton agregarPad;
    private ImageButton agregarBoton;
    private float posX;
    private float posY;
    private float oldX;
    private float oldY;
    private Intent intent;

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
        agregarBoton = findViewById(R.id.agregarBoton);

        agregarTextArea.setOnClickListener(this);
        agregarSwitch.setOnClickListener(this);
        agregarRadio.setOnClickListener(this);
        agregarPad.setOnClickListener(this);
        agregarBoton.setOnClickListener(this);
        actualControl = new Control();


        //botonPrueba.setOnTouchListener(this);

        this.setTitle(actualControl.getNombre());


    }

    @Override
    protected void onPause() {
        super.onPause();
        for (View v: actualControl.getWidgets()) {
            areaComponentes.removeView(v);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int movimiento = 100;

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                posX = event.getRawX() - (v.getWidth() / 2);
                //posY = event.getRawY()-(v.getHeight()*(float)1.5);
                posY = event.getRawY() - (v.getHeight() * 2);


                if (posY > v.getY() + movimiento) {
                    v.setY(v.getY() + movimiento);
                } else if (posY < v.getY() - movimiento) {
                    v.setY(v.getY() - movimiento);
                }

                if (posX > v.getX() + movimiento) {
                    v.setX(v.getX() + movimiento);
                } else if (posX < v.getX() - movimiento) {
                    v.setX(v.getX() - movimiento);
                }

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
        switch (item.getItemId()) {
            case R.id.action_editar_nombre:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final EditText nombre = new EditText(this);
                builder.setView(nombre);
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        actualControl.setNombre(nombre.getText().toString());
                        setTitle(nombre.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            case R.id.action_guardar:
                Controles.addControl(actualControl);
                return true;
            case R.id.action_vincular:
                intent = new Intent(this, Conexion.class);
                startActivityForResult(intent, 1000);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agregarRadioButton:
                RadioButton radBtn = new RadioButton(this);
                radBtn.setLayoutParams(new ConstraintLayout.LayoutParams(100, 200));
                radBtn.setScaleY(1.5f);
                radBtn.setScaleX(1.5f);
                areaComponentes.addView(radBtn);
                radBtn.setX(areaComponentes.getWidth() / 2);
                radBtn.setY(areaComponentes.getHeight() / 2);
                radBtn.setOnTouchListener(this);
                radBtn.setOnLongClickListener(this);
                actualControl.addWidget(radBtn);
                break;
            case R.id.agregarSwitch:
                Switch sw = new Switch(this);
                sw.setLayoutParams(new ConstraintLayout.LayoutParams(150, 200));
                sw.setScaleY(1.5f);
                sw.setScaleX(1.5f);
                areaComponentes.addView(sw);
                sw.setX(areaComponentes.getWidth() / 2);
                sw.setY(areaComponentes.getHeight() / 2);
                sw.setOnTouchListener(this);
                actualControl.addWidget(sw);
                break;
            case R.id.agregarTextArea:
                EditText editTxt = new EditText(this);
                editTxt.setLayoutParams(new ConstraintLayout.LayoutParams(400, 150));
                areaComponentes.addView(editTxt);
                editTxt.setX(areaComponentes.getWidth() / 2);
                editTxt.setY(areaComponentes.getHeight() / 2);
                editTxt.setOnTouchListener(this);
                actualControl.addWidget(editTxt);
                break;
            case R.id.agregarPad:
                Pad pad = new Pad(this);
                pad.setLayoutParams(new ConstraintLayout.LayoutParams(400,300));
                areaComponentes.addView(pad);
                pad.setX(areaComponentes.getWidth() / 2);
                pad.setY(areaComponentes.getHeight() / 2);
                pad.setOnTouchListener(this);
                actualControl.addWidget(pad);
                break;
            case R.id.agregarBoton:
                BotonPro button = new BotonPro(this);
                button.setLayoutParams(new ConstraintLayout.LayoutParams(300, 150));
                areaComponentes.addView(button);
                button.setX(areaComponentes.getWidth() / 2);
                button.setY(areaComponentes.getHeight() / 2);
                button.setOnTouchListener(this);
                actualControl.addWidget(button);
                break;
        }
    }


    @Override
    public boolean onLongClick(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.pop_menu_boton, popup.getMenu());
        popup.show();
        return false;
    }
}
