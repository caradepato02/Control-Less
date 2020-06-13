package com.example.prueba.conexion;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    //List view donde se muestra el nombre de los dispositivos vinculados
    private ListView lstVwVinculados;

    //Lista de dispositivos bluetooth vinculados
    private Set<BluetoothDevice> dispVinculados;

    //Adaptar de bluetooth
    private BluetoothAdapter mBluetoothAdapter;

    //Lista par mostrar los nombres de los dispositivos vinculados en el list view
    private ArrayList<String> aListVinculados;

    //Lista para facilitar el uso de los dispositivos vinculados
    private ArrayList<BluetoothDevice> aListDispositivos;

    //Posicion de la lista del dispositivo vinculados
    private int dispositivoSeleccionado;

    //Boton para fijar el resultado de la acttividad y terminarla
    private Button btnVincularBt;

    public BluetoothFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstVwVinculados = getView().findViewById(R.id.fragmentBluetoothLstVwVinculados);
        lstVwVinculados.setOnItemClickListener(this);
        btnVincularBt = getView().findViewById(R.id.fragmentBluetoothBtnVincular);
        btnVincularBt.setOnClickListener(this);
        aListVinculados = new ArrayList<>();
        aListDispositivos = new ArrayList<>();
        btnVincularBt.setEnabled(false);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1000);
        }else {
            mostrarLista();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mostrarLista();
    }

    //Muestra los nombres de los dispositivos vinculados en el list view
    public void mostrarLista(){
        dispVinculados = mBluetoothAdapter.getBondedDevices();
        dispVinculados.toArray();
        if (dispVinculados.size() > 0) {
            for (BluetoothDevice device : dispVinculados) {
                aListDispositivos.add(device);
                aListVinculados.add(device.getName());
            }
        }
        lstVwVinculados.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_selectable_list_item, aListVinculados));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(Vincular.EXTRA_DIRECCION_MAC,aListDispositivos.get(dispositivoSeleccionado).getAddress());
        getActivity().setResult(Vincular.RESULT_BLUETOOTH, intent);
        getActivity().finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        btnVincularBt.setEnabled(true);
        dispositivoSeleccionado = position;
    }
}
