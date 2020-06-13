package com.example.prueba.controles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.prueba.R;
import com.example.prueba.componentes.ButtonPro;
import com.example.prueba.componentes.RadioButtonPro;
import com.example.prueba.componentes.SwitchPro;
import com.example.prueba.conexion.Conector;

public class UsoControl extends AppCompatActivity implements View.OnTouchListener {
    private Control controlEnUso;
    private int numControl;
    private ConstraintLayout consLayArea;
    private Conector conector;
    private static Handler handler;
    private Context context;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_control);
        context = this;
        numControl = getIntent().getIntExtra("numControl", 0);
        controlEnUso = Controles.getControl(numControl);
        consLayArea = findViewById(R.id.constLayArea);
        conector = controlEnUso.getConector();
        setTitle(controlEnUso.getNombre());
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case Conector.ESTADO_CONECTADO:
                        Toast.makeText(context, R.string.uso_control_toast_conectado, Toast.LENGTH_SHORT).show();
                        break;
                    case Conector.ESTADO_DESCONECTADO:
                        Toast.makeText(context,R.string.uso_control_toast_desconectado, Toast.LENGTH_SHORT).show();
                        break;
                    case Conector.ESTADO_ENVIADO:
                        Toast.makeText(context, R.string.uso_control_toast_enviado, Toast.LENGTH_SHORT).show();
                        break;
                    case Conector.ESTADO_ERROR_CONECTAR:
                        Toast.makeText(context, R.string.uso_control_toast_error_conectar, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        if (conector != null) {
            conector.setHandler(handler);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uso_control, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        removeWidgets();
        addWidgets();

        if (conector != null) {
            switch (conector.getClass().toString()) {
                case "class com.example.prueba.conexion.ConectorBluetooth":
                    if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1000);
                    }
                    break;
                case "class com.example.prueba.conexion.ConectorWifi":
                    WifiManager wifiManager = (WifiManager)this.context.getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                    break;

            }
            conector.conectar();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            conector.conectar();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (conector != null) {
            conector.desconectar();
        }

    }

    private void addWidgets() {
        controlEnUso.dejarHuerfanos();
        for (View v : controlEnUso.getWidgets()) {
            v.setOnTouchListener(this);
            v.setOnLongClickListener(null);
            consLayArea.addView(v);
        }
    }

    private void removeWidgets() {
        consLayArea.removeAllViews();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuUsoControlItemEditar:
                Intent intent = new Intent(this, Editor.class);
                intent.putExtra("indexControl", Controles.getIndexControl(controlEnUso));
                startActivity(intent);
                return true;
            case R.id.menuUsoControlItemEliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.uso_control_dialog_eliminar));
                builder.setPositiveButton(getText(R.string.dialog_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Controles.removeControl(controlEnUso);
                        finish();
                    }
                });
                builder.setNegativeButton(getText(R.string.dialog_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (conector != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    switch (v.getClass().toString()) {
                        case "class com.example.prueba.componentes.RadioButtonPro":
                            if (!((RadioButtonPro) v).isChecked()) {
                                conector.enviarAccion(((RadioButtonPro) v).getAccion());
                            }
                            break;
                        case "class com.example.prueba.componentes.SwitchPro":
                            if (((SwitchPro) v).isChecked()) {
                                conector.enviarAccion(((SwitchPro) v).getAccionApagado());
                            } else {
                                conector.enviarAccion(((SwitchPro) v).getAccionEncendido());
                            }
                            break;
                        case "class com.example.prueba.componentes.ButtonPro":
                            conector.enviarAccion(((ButtonPro) v).getAccionPresionar());
                            break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (v.getClass().toString().equals("class com.example.prueba.componentes.ButtonPro")) {
                        conector.enviarAccion(((ButtonPro) v).getAccionSoltar());
                    }
                    break;
            }
        }
        return false;
    }
}
