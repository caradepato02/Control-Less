package com.example.prueba.conexion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.prueba.R;
import com.google.android.material.tabs.TabLayout;

public class Vincular extends AppCompatActivity {
    public static final String EXTRA_DIRECCION_MAC = "macDispositivo";
    public static final String EXTRA_DIRECCION_IP = "ipDispositivo";

    public static final int RESULT_BLUETOOTH = 1;
    public static final int RESULT_WIFI = 2;


    private TabLayout tabLayout;
    private ViewPager vwPager;
    private TabAdapter tAdapter;
    private TabLayout.Tab tabWifi;
    private TabLayout.Tab tabBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_vinculo);
        setContentView(R.layout.activity_vincular);
        tabLayout = findViewById(R.id.tabLayout);
        tabWifi = tabLayout.newTab();
        tabBluetooth = tabLayout.newTab();

        tabWifi.setText(R.string.activity_conexion_tabItemWifi);
        tabBluetooth.setText(R.string.activity_conexion_tabItemBluetoth);

        tabWifi.setIcon(R.drawable.ic_wifi_24px);
        tabBluetooth.setIcon(R.drawable.ic_bluetooth_24px);

        tabLayout.addTab(tabWifi);
        tabLayout.addTab(tabBluetooth);
        vwPager = findViewById(R.id.vwPager);
        tAdapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());

        vwPager.setAdapter(tAdapter);
        vwPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vwPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
