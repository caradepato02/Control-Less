package com.example.prueba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;
import com.example.prueba.controles.ControlAdapter;
import com.example.prueba.controles.Controles;
import com.example.prueba.controles.UsoControl;

public class HomeFragment extends Fragment implements ListView.OnItemClickListener {

    private Intent intent;
    private ListView lvwControles;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        lvwControles = getView().findViewById(R.id.lvwControles);
        lvwControles.setAdapter(new ControlAdapter(getContext(), R.layout.cuadrito_control, Controles.getLListaControles()));
        lvwControles.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        intent = new Intent(parent.getContext(), UsoControl.class);
        intent.putExtra("numControl", position);
        startActivity(intent);
    }
}
