package com.example.prueba.controles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

import com.example.prueba.R;

public class UsoControl extends AppCompatActivity {
        private Control controlEnUso;
        private int numControl;
        private ConstraintLayout consLayArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_control);
        numControl = getIntent().getIntExtra("numControl",0);
        controlEnUso = Controles.getControl(numControl);

        consLayArea = findViewById(R.id.constLayArea);

        for (View v: controlEnUso.getWidgets()) {
            v.setOnTouchListener(null);
            consLayArea.addView(v);
        }

    }
}
