package com.example.prueba.controles;

import android.app.Activity;
import android.content.Context;
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

import com.example.prueba.R;
import com.example.prueba.componentes.ButtonPro;
import com.example.prueba.componentes.RadioButtonPro;
import com.example.prueba.componentes.RadioGroupPro;
import com.example.prueba.componentes.SwitchPro;
import com.example.prueba.conexion.ConectorBluetooth;
import com.example.prueba.conexion.ConectorWifi;
import com.example.prueba.conexion.Vincular;

import java.util.ArrayList;


public class Editor extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {


    private final int SET_TEXTO = 1;
    private final int SET_ACCION_RADIOBUTTON = 2;
    private final int SET_ACCION_PRESIONAR_BUTTON = 3;
    private final int SET_ACCION_SOLTAR_BUTTON = 4;
    private final int SET_ACCION_ENCENDIDO_SWITCH = 5;
    private final int SET_ACCION_APAGADO_SWITCH = 6;
    private final int CREAR_RADIOGROUP = 7;
    private final int AGREGAR_RADIOGROUP = 8;
    private final int VINCULAR_EDITTEXT = 9;

    private final int codeVincular = 1000;
    private final int codeicono = 2000;
    private ConstraintLayout areaComponentes;
    private ImageButton agregarRadio;
    private ImageButton agregarEditText;
    private ImageButton agregarSwitch;
    private ImageButton agregarBoton;
    private ImageButton agregarTextView;

    private ArrayList<View> viewsEditor = new ArrayList<>();

    private int indexControlActual;

    private float posX, posY, oldPosX, oldPosY;

    private Intent intent;
    private boolean moviendose;

    private Handler handler;
    private View viewSeleccionado;
    private TextView compSeleccionado;
    private Context thisContext;


    Control controlActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        areaComponentes = findViewById(R.id.activityEditorAreaComponentes);
        agregarRadio = findViewById(R.id.activityEditorAgregarRadioButton);
        agregarSwitch = findViewById(R.id.activityEditorAgregarSwitch);
        agregarEditText = findViewById(R.id.activityEditorAgregarEditText);
        agregarBoton = findViewById(R.id.activityEditorAgregarBoton);
        agregarTextView = findViewById(R.id.activityEditorAgregarTextView);

        agregarEditText.setOnClickListener(this);
        agregarSwitch.setOnClickListener(this);
        agregarRadio.setOnClickListener(this);
        agregarBoton.setOnClickListener(this);
        agregarTextView.setOnClickListener(this);
        indexControlActual = this.getIntent().getIntExtra("indexControl", -1);

        if (indexControlActual < 0) {
            controlActual = new Control();
        } else {
            controlActual = Controles.getControl(indexControlActual);
            for (View v : controlActual.getWidgets()) {
                v = controlActual.getCopiaWidget(v, this);
                addViewAreaComponentes(v);
            }
        }
        this.setTitle(controlActual.getNombre());
        thisContext = this;
        moviendose = false;


        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SET_TEXTO:
                        compSeleccionado.setText((String) msg.obj);
                        break;
                    case SET_ACCION_RADIOBUTTON:
                        ((RadioButtonPro) compSeleccionado).setAccion((String) msg.obj);
                        break;
                    case CREAR_RADIOGROUP:
                        RadioGroupPro radioGroupPro = new RadioGroupPro(thisContext);
                        radioGroupPro.setNombre((String) msg.obj);
                        addNewViewAreaComponentes(radioGroupPro);
                        radioGroupPro.setOnLongClickListener(null);
                        radioGroupPro.setOnTouchListener(null);
                        break;
                    case SET_ACCION_PRESIONAR_BUTTON:
                        ((ButtonPro) compSeleccionado).setAccionPresionar((String) msg.obj);
                        break;
                    case SET_ACCION_SOLTAR_BUTTON:
                        ((ButtonPro) compSeleccionado).setAccionSoltar((String) msg.obj);
                        break;
                    case SET_ACCION_ENCENDIDO_SWITCH:
                        ((SwitchPro) compSeleccionado).setAccionEncendido((String) msg.obj);
                        break;
                    case SET_ACCION_APAGADO_SWITCH:
                        ((SwitchPro) compSeleccionado).setAccionApagado((String) msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int movimiento = 100;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldPosX = event.getRawX() - (v.getWidth() / 2);
                oldPosY = event.getRawY() - (v.getHeight() * 2);
                break;
            case MotionEvent.ACTION_MOVE:

                posX = event.getRawX() - (v.getWidth() / 2);
                posY = event.getRawY() - (v.getHeight() * 2);

                if (posY > oldPosY + movimiento/2) {
                    moviendose = true;
                } else if (posY < oldPosY - movimiento/2) {
                    moviendose = true;
                }
                if (posX > oldPosX + movimiento/2) {
                    moviendose = true;
                } else if (posX < oldPosX - movimiento/2) {
                    moviendose = true;
                }

                if (posY > oldPosY + movimiento) {
                    v.setY(v.getY() + movimiento);
                    oldPosY = posY;
                } else if (posY < oldPosY - movimiento) {
                    v.setY(v.getY() - movimiento);
                    oldPosY = posY;
                }
                if (posX > oldPosX + movimiento) {
                    v.setX(v.getX() + movimiento);
                    oldPosX = posX;
                } else if (posX < oldPosX - movimiento) {
                    v.setX(v.getX() - movimiento);
                    oldPosX = posX;
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
            case R.id.editorActionEditarNombre:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final EditText nombre = new EditText(this);
                builder.setTitle(getText(R.string.dialog_title_nombre));
                builder.setView(nombre);
                builder.setPositiveButton(getText(R.string.dialog_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        controlActual.setNombre(nombre.getText().toString());
                        setTitle(nombre.getText().toString());
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
            case R.id.editorActionGuardar:
                if (indexControlActual < 0) {
                    for (View v : viewsEditor) {
                        controlActual.addWidget(v);
                    }
                    Controles.addControl(controlActual);
                    finish();
                } else {
                    Controles.getControl(indexControlActual).removeWidgets();

                    for (View v : viewsEditor) {
                        Controles.getControl(indexControlActual).addWidget(v);
                    }
                    finish();
                }

                return true;
            case R.id.editorActionVincular:
                intent = new Intent(this, Vincular.class);
                startActivityForResult(intent, codeVincular);
                return true;
            case R.id.editorActionCambiarIcono:
                intent = new Intent(this, SeleccionarIcono.class);
                startActivityForResult(intent, codeicono);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (!moviendose) {
            viewSeleccionado = v;
            int layoutMenu;
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(this);
            switch (v.getClass().toString()) {
                case "class com.example.prueba.componentes.RadioButtonPro":
                    layoutMenu = R.menu.pop_menu_radio_button;
                    compSeleccionado = (RadioButtonPro) v;
                    break;
                case "class com.example.prueba.componentes.ButtonPro":
                    layoutMenu = R.menu.pop_menu_button;
                    compSeleccionado = (ButtonPro) v;
                    popup.getMenuInflater().inflate(layoutMenu, popup.getMenu());
                    popup.show();
                    MenuItem itemAccion;
                    Menu popmenu = popup.getMenu();
                    itemAccion = popmenu.findItem(R.id.popMenuButtonAccionPresionar);
                    if (((ButtonPro) compSeleccionado).isVinculado()) {
                        itemAccion.setEnabled(false);
                        itemAccion.setTitle(R.string.pop_menu_radio_botton_item_vinculado);
                    } else {
                        itemAccion.setEnabled(true);
                        itemAccion.setTitle(R.string.pop_menu_button_item_accion_presionar);
                    }
                    return false;
                case "class com.example.prueba.componentes.SwitchPro":
                    layoutMenu = R.menu.pop_menu_switch_basico;
                    compSeleccionado = (SwitchPro) v;
                    break;
                case "class android.widget.TextView":
                    layoutMenu = R.menu.pop_menu_basico;
                    compSeleccionado = (TextView) v;
                    break;
                case "class android.widget.EditText":
                    layoutMenu = R.menu.pop_menu_edit_text;
                    compSeleccionado = (EditText) v;
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        compSeleccionado = (TextView) viewSeleccionado;
        String dialogAccion, dialogTexto;
        dialogAccion = getText(R.string.dialog_title_accion).toString();
        dialogTexto = getText(R.string.dialog_title_texto).toString();

        switch (item.getItemId()) {

            case R.id.popMenuRadioButtonItemAccion:
                dialogText(SET_ACCION_RADIOBUTTON, ((RadioButtonPro) compSeleccionado).getAccion(), dialogAccion);
                break;
            case R.id.popMenuButtonAccionPresionar:
                dialogText(SET_ACCION_PRESIONAR_BUTTON, ((ButtonPro) compSeleccionado).getAccionPresionar(), dialogAccion);
                break;
            case R.id.popMenuButtonAccionSoltar:
                dialogText(SET_ACCION_SOLTAR_BUTTON, ((ButtonPro) compSeleccionado).getAccionSoltar(), dialogAccion);
                break;
            case R.id.popMenuSwitchAccionEncendido:
                dialogText(SET_ACCION_ENCENDIDO_SWITCH, ((SwitchPro) compSeleccionado).getAccionEncendido(), dialogAccion);
                break;
            case R.id.popMenuSwitchAccionApagado:
                dialogText(SET_ACCION_APAGADO_SWITCH, ((SwitchPro) compSeleccionado).getAccionApagado(), dialogAccion);
                break;
            case R.id.popMenuButtonTexto:
            case R.id.popMenuRadioButtonItemTexto:
            case R.id.popMenuSwitchTexto:
            case R.id.popMenuBasicoTexto:
                dialogText(SET_TEXTO, compSeleccionado.getText().toString(), dialogTexto);
                break;

            case R.id.popMenuRadioButtonItemCrear:
                dialogText(CREAR_RADIOGROUP, null, getText(R.string.dialog_title_crear_radio_group).toString());
                break;
            case R.id.popMenuRadioButtonItemAgregar:
                dialogText(AGREGAR_RADIOGROUP, null, getText(R.string.dialog_title_agregar_radio_group).toString());
                break;

            case R.id.popMenuRadioButtonItemEliminar:
            case R.id.popMenuButtonEliminar:
            case R.id.popMenuEditTextEliminar:
            case R.id.popMenuSwitchEliminar:
            case R.id.popMenuBasicoEliminar:
                areaComponentes.removeView(compSeleccionado);
                viewsEditor.remove(compSeleccionado);
                break;

            case R.id.popMenuEditTextVincular:
                dialogText(VINCULAR_EDITTEXT, null, getText(R.string.dialog_title_edit_text_vincular).toString());
                break;

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityEditorAgregarRadioButton:
                RadioButtonPro radBtn = new RadioButtonPro(this);
                radBtn.setText(R.string.text_default_RadioButton);
                addNewViewAreaComponentes(radBtn);
                break;
            case R.id.activityEditorAgregarSwitch:
                SwitchPro sw = new SwitchPro(this);
                sw.setText(R.string.text_default_Switch);
                addNewViewAreaComponentes(sw);
                break;
            case R.id.activityEditorAgregarEditText:
                EditText editTxt = new EditText(this);
                editTxt.setText(R.string.text_default_EditText);
                addNewViewAreaComponentes(editTxt);
                break;
            case R.id.activityEditorAgregarBoton:
                ButtonPro button = new ButtonPro(this);
                button.setText(R.string.text_default_Button);
                addNewViewAreaComponentes(button);
                break;
            case R.id.activityEditorAgregarTextView:
                TextView textView = new TextView(this);
                textView.setText(R.string.text_default_TextView);
                textView.setTextColor(getColor(R.color.negro));
                addNewViewAreaComponentes(textView);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String direccion;
        switch (resultCode) {
            case Activity.RESULT_OK:
                controlActual.setIcono(data.getIntExtra("idRes",R.drawable.control2));
                break;
            case Vincular.RESULT_BLUETOOTH:
                direccion = data.getStringExtra(Vincular.EXTRA_DIRECCION_MAC);
                controlActual.setConector(new ConectorBluetooth(direccion));
                break;
            case Vincular.RESULT_WIFI:
                 direccion = data.getStringExtra(Vincular.EXTRA_DIRECCION_IP);
                controlActual.setConector(new ConectorWifi(direccion));
                break;
        }

    }

    private void addViewAreaComponentes(View v) {
        v.setMinimumWidth(200);
        v.setOnTouchListener(this);
        v.setOnLongClickListener(this);
        v.setScaleX(1.2f);
        v.setScaleY(1.2f);
        if(v.getClass().toString().equals("class android.widget.TextView")){
            ((TextView)v).setTextSize(18f);
        }
        areaComponentes.addView(v);
        viewsEditor.add(v);
    }

    private void addNewViewAreaComponentes(View v) {
        v.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        v.setX((areaComponentes.getWidth() / 2) - 100);
        v.setY((areaComponentes.getHeight() / 2));
        addViewAreaComponentes(v);
    }

    private void dialogText(final int idAccion, String textoActual, String titulo) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        if (idAccion == AGREGAR_RADIOGROUP) {
            ArrayList<String> nombreRadioGroup = new ArrayList<>();
            final ArrayList<RadioGroupPro> listRadioGroup = new ArrayList<>();

            for (View v : viewsEditor) {
                if (v.getClass().toString().equals("class com.example.prueba.componentes.RadioGroupPro")) {
                    listRadioGroup.add((RadioGroupPro) v);
                    nombreRadioGroup.add(((RadioGroupPro) v).getNombre());
                }
            }
            String nombres[] = new String[nombreRadioGroup.size()];
            nombres = nombreRadioGroup.toArray(nombres);
            builder.setItems(nombres, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (((RadioButtonPro) compSeleccionado).getGroup() != null) {
                        ((RadioButtonPro) compSeleccionado).removeGroup();
                    }
                    ((RadioButtonPro) compSeleccionado).setGroup(listRadioGroup.get(which));
                }
            });

        } else if (idAccion == VINCULAR_EDITTEXT) {
            ArrayList<String> nombreButtonPro = new ArrayList<>();
            final ArrayList<ButtonPro> listButtons = new ArrayList<>();

            for (View v : viewsEditor) {
                if (v.getClass().toString().equals("class com.example.prueba.componentes.ButtonPro")) {
                    listButtons.add((ButtonPro) v);
                    nombreButtonPro.add(((ButtonPro) v).getText().toString());
                }
            }
            String nombres[] = new String[nombreButtonPro.size()];
            nombres = nombreButtonPro.toArray(nombres);
            builder.setItems(nombres, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listButtons.get(which).setVinculo((EditText) compSeleccionado);
                }
            });
        } else {
            final EditText texto = new EditText(this);
            texto.setText(textoActual);
            builder.setView(texto);
            builder.setPositiveButton(getText(R.string.dialog_aceptar), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    int what = idAccion;
                    Message message = handler.obtainMessage(what, texto.getText().toString());
                    handler.sendMessage(message);
                }
            });
            builder.setNegativeButton(getText(R.string.dialog_cancelar), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
