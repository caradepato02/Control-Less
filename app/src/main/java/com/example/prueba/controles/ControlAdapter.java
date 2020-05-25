package com.example.prueba.controles;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.prueba.R;

import java.util.ArrayList;

public class ControlAdapter  extends ArrayAdapter<Control> {
    private Context context;
    private int resourse;
    private ArrayList<Control> objects;

    public ControlAdapter(@NonNull Context context, int resource, ArrayList<Control> objects) {
        super(context, resource,objects);
        this.context = context;
        this.resourse = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)context).getLayoutInflater().inflate(resourse, parent, false);
        }
        TextView txtNombre;
        txtNombre = convertView.findViewById(R.id.txtNombre);
        txtNombre.setText(objects.get(position).getNombre());
        return convertView;
    }
}
