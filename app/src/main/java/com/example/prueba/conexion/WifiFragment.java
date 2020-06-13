package com.example.prueba.conexion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;

public class WifiFragment extends Fragment implements View.OnClickListener {
    private Button btnVincularWifi;
    private EditText editTxtDireccion;

    public WifiFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wifi, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnVincularWifi = getView().findViewById(R.id.fragmentWifiBtnVincular);
        editTxtDireccion = getView().findViewById(R.id.fragmentWifiEditTxtDireccion);
        btnVincularWifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(Vincular.EXTRA_DIRECCION_IP, editTxtDireccion.getText().toString());
        getActivity().setResult(Vincular.RESULT_WIFI, intent);
        getActivity().finish();
    }
}
