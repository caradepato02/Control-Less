package com.example.prueba.controles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba.R;
import com.example.prueba.componentes.ImageViewPro;

public class SeleccionarIcono extends AppCompatActivity implements View.OnClickListener {

    ImageViewPro seleccionarIconoImgVw1,
            seleccionarIconoImgVw2,
            seleccionarIconoImgVw3,
            seleccionarIconoImgVw4,
            seleccionarIconoImgVw5,
            seleccionarIconoImgVw6,
            seleccionarIconoImgVw7,
            seleccionarIconoImgVw8,
            seleccionarIconoImgVw9,
            seleccionarIconoImgVw10,
            seleccionarIconoImgVw11,
            seleccionarIconoImgVw12,
            seleccionarIconoImgVw13,
            seleccionarIconoImgVw14,
            seleccionarIconoImgVw15,
            seleccionarIconoImgVw16,
            seleccionarIconoImgVw17,
            seleccionarIconoImgVw18,
            seleccionarIconoImgVw19,
            seleccionarIconoImgVw20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_icono);
        setContentView(R.layout.activity_seleccionar_icono);
        seleccionarIconoImgVw1 = findViewById(R.id.seleccionarIconoImgVw1);
        seleccionarIconoImgVw2 = findViewById(R.id.seleccionarIconoImgVw2);
        seleccionarIconoImgVw3 = findViewById(R.id.seleccionarIconoImgVw3);
        seleccionarIconoImgVw4 = findViewById(R.id.seleccionarIconoImgVw4);
        seleccionarIconoImgVw5 = findViewById(R.id.seleccionarIconoImgVw5);
        seleccionarIconoImgVw6 = findViewById(R.id.seleccionarIconoImgVw6);
        seleccionarIconoImgVw7 = findViewById(R.id.seleccionarIconoImgVw7);
        seleccionarIconoImgVw8 = findViewById(R.id.seleccionarIconoImgVw8);
        seleccionarIconoImgVw9 = findViewById(R.id.seleccionarIconoImgVw9);
        seleccionarIconoImgVw10 = findViewById(R.id.seleccionarIconoImgVw10);
        seleccionarIconoImgVw11 = findViewById(R.id.seleccionarIconoImgVw11);
        seleccionarIconoImgVw12 = findViewById(R.id.seleccionarIconoImgVw12);
        seleccionarIconoImgVw13 = findViewById(R.id.seleccionarIconoImgVw13);
        seleccionarIconoImgVw14 = findViewById(R.id.seleccionarIconoImgVw14);
        seleccionarIconoImgVw15 = findViewById(R.id.seleccionarIconoImgVw15);
        seleccionarIconoImgVw16 = findViewById(R.id.seleccionarIconoImgVw16);
        seleccionarIconoImgVw17 = findViewById(R.id.seleccionarIconoImgVw17);
        seleccionarIconoImgVw18 = findViewById(R.id.seleccionarIconoImgVw18);
        seleccionarIconoImgVw19 = findViewById(R.id.seleccionarIconoImgVw19);
        seleccionarIconoImgVw20 = findViewById(R.id.seleccionarIconoImgVw20);
        seleccionarIconoImgVw1.setOnClickListener(this);
        seleccionarIconoImgVw2.setOnClickListener(this);
        seleccionarIconoImgVw3.setOnClickListener(this);
        seleccionarIconoImgVw4.setOnClickListener(this);
        seleccionarIconoImgVw5.setOnClickListener(this);
        seleccionarIconoImgVw6.setOnClickListener(this);
        seleccionarIconoImgVw7.setOnClickListener(this);
        seleccionarIconoImgVw8.setOnClickListener(this);
        seleccionarIconoImgVw9.setOnClickListener(this);
        seleccionarIconoImgVw10.setOnClickListener(this);
        seleccionarIconoImgVw11.setOnClickListener(this);
        seleccionarIconoImgVw12.setOnClickListener(this);
        seleccionarIconoImgVw13.setOnClickListener(this);
        seleccionarIconoImgVw14.setOnClickListener(this);
        seleccionarIconoImgVw15.setOnClickListener(this);
        seleccionarIconoImgVw16.setOnClickListener(this);
        seleccionarIconoImgVw17.setOnClickListener(this);
        seleccionarIconoImgVw18.setOnClickListener(this);
        seleccionarIconoImgVw19.setOnClickListener(this);
        seleccionarIconoImgVw20.setOnClickListener(this);

        seleccionarIconoImgVw1.setImageResource(R.drawable.control2);
        seleccionarIconoImgVw2.setImageResource(R.drawable.ic_esports_24px);
        seleccionarIconoImgVw3.setImageResource(R.drawable.ic_games_24px);
        seleccionarIconoImgVw4.setImageResource(R.drawable.ic_keyboard_24px);
        seleccionarIconoImgVw5.setImageResource(R.drawable.ic_car_24px);
        seleccionarIconoImgVw6.setImageResource(R.drawable.ic_airplane_24px);
        seleccionarIconoImgVw7.setImageResource(R.drawable.ic_house_24px);
        seleccionarIconoImgVw8.setImageResource(R.drawable.ic_kitchen_24px);
        seleccionarIconoImgVw9.setImageResource(R.drawable.ic_tv_24px);
        seleccionarIconoImgVw10.setImageResource(R.drawable.ic_radio_24px);
        seleccionarIconoImgVw11.setImageResource(R.drawable.ic_wifi_24px);
        seleccionarIconoImgVw12.setImageResource(R.drawable.ic_bluetooth_24px);
        seleccionarIconoImgVw13.setImageResource(R.drawable.ic_toys_24px);
        seleccionarIconoImgVw14.setImageResource(R.drawable.ic_incandescent_24px);
        seleccionarIconoImgVw15.setImageResource(R.drawable.ic_power_24px);
        seleccionarIconoImgVw16.setImageResource(R.drawable.ic_photo_camera_24px);
        seleccionarIconoImgVw17.setImageResource(R.drawable.ic_audiotrack_24px);
        seleccionarIconoImgVw18.setImageResource(R.drawable.ic_favorite_24px);
        seleccionarIconoImgVw19.setImageResource(R.drawable.ic_headset_24px);
        seleccionarIconoImgVw20.setImageResource(R.drawable.ic_time_24px);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("idRes",((ImageViewPro)v).getImageResource());
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
