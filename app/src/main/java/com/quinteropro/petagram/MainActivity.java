package com.quinteropro.petagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar miActionBarlogin = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBarlogin);
        setSupportActionBar (miActionBarlogin);
    }

    public void irListaMascotas(View v) {
        Intent intent = new Intent(this,ListaMascotas.class);
        startActivity(intent);
    }
}