package com.example.prueba.controles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.prueba.Componentes.ButtonPro;
import com.example.prueba.Componentes.Pad;
import com.example.prueba.Componentes.RadioButtonPro;
import com.example.prueba.Componentes.SwitchPro;
import com.example.prueba.R;
import com.example.prueba.conexion.Conexion;


public class Editor extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {
    private ConstraintLayout areaComponentes;
    private ImageButton agregarRadio;
    private ImageButton agregarTextArea;
    private ImageButton agregarSwitch;
    private ImageButton agregarPad;
    private ImageButton agregarBoton;
    private float posX;
    private float posY;
    private Intent intent;
    private boolean moviendose;
    private Handler handler;
    private View viewSeleccionado;
    private TextView compSeleccionado;

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

        moviendose = false;
        //botonPrueba.setOnTouchListener(this);


        this.setTitle(actualControl.getNombre());
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 11:
                        ((RadioButtonPro) compSeleccionado).setAccion((String) msg.obj);
                        break;
                    case 12:
                        compSeleccionado.setText((String) msg.obj);
                        break;
                    case 13:
                        
                        break;
                }
            }
        };


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (View v : actualControl.getWidgets()) {
            areaComponentes.removeView(v);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int movimiento = 100;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moviendose = true;
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
            case MotionEvent.ACTION_UP:
                moviendose = false;
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
                RadioButtonPro radBtn = new RadioButtonPro(this);
                agregarViewActualControl(radBtn, 300, 200, 1.5f, 1.5f);
                break;
            case R.id.agregarSwitch:
                SwitchPro sw = new SwitchPro(this);
                agregarViewActualControl(sw, 150, 200, 1.5f, 1.5f);
                break;
            case R.id.agregarTextArea:
                EditText editTxt = new EditText(this);
                agregarViewActualControl(editTxt, 400, 150);
                break;
            case R.id.agregarPad:
                Pad pad = new Pad(this);
                agregarViewActualControl(pad, 400, 300);
                break;
            case R.id.agregarBoton:
                ButtonPro button = new ButtonPro(this);
                agregarViewActualControl(button, 100, 200);
                break;
        }
    }

    private void agregarViewActualControl(View v, int width, int height) {
        v.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, height));
        v.setMinimumWidth(100);
        areaComponentes.addView(v);
        v.setX(areaComponentes.getWidth() / 2);
        v.setY(areaComponentes.getHeight() / 2);
        v.setOnTouchListener(this);
        v.setOnLongClickListener(this);
        actualControl.addWidget(v);
    }

    private void agregarViewActualControl(View v, int width, int height, Float scaleX, float scaleY) {
        v.setScaleX(scaleX);
        v.setScaleY(scaleY);
        agregarViewActualControl(v, width, height);
    }

    @Override
    public boolean onLongClick(View v) {
        if (!moviendose) {
            viewSeleccionado = v;
            int layoutMenu;
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(this);
            switch (v.toString()) {
                case "RadioButtonPro":
                    layoutMenu = R.menu.pop_menu_radio_button;
                    compSeleccionado = (RadioButtonPro) v;
                    break;
                case "BotonPro":
                    layoutMenu = R.menu.pop_menu_boton;
                    compSeleccionado = (ButtonPro) v;
                    break;
                case "SwitchPro":
                    layoutMenu = R.menu.pop_menu_switch;
                    compSeleccionado = (SwitchPro) v;
                    break;
                default:
                    layoutMenu = 0;
                    break;
            }
            popup.getMenuInflater().inflate(layoutMenu, popup.getMenu());
            popup.show();
        }
        return false;
    }

    private void dialogText(final int accion) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText texto = new EditText(this);
        builder.setView(texto);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int what = accion;
                Message message = handler.obtainMessage(what, texto.getText().toString());
                handler.sendMessage(message);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        compSeleccionado = (TextView) viewSeleccionado;
        switch (item.getItemId()) {
            case R.id.popMenuRadioButtonItemAccion:
                dialogText(11);
                break;
            case R.id.popMenuRadioButtonItemTexto:
                dialogText(12);
                break;
            case R.id.popMenuRadioButtonItemCrear:
                dialogText(13);
                break;
        }
        return false;
    }
}
