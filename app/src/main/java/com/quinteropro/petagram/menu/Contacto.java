package com.quinteropro.petagram.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import com.quinteropro.petagram.R;
import com.quinteropro.petagram.email.JavaMailAPI;

import static com.quinteropro.petagram.email.Utils.EMAIL;

public class Contacto extends AppCompatActivity {

    private TextInputLayout txtInputNombre;
    private TextInputLayout txtInputFrom;
    private TextInputLayout txtInputMesaje;

    private Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        androidx.appcompat.widget.Toolbar miActionBarmascotaFavorita = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBarmascotaFavorita);
        setSupportActionBar (miActionBarmascotaFavorita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtInputNombre = (TextInputLayout) findViewById(R.id.txtNombre);
        txtInputFrom = (TextInputLayout) findViewById(R.id.txtFrom);
        txtInputMesaje = (TextInputLayout) findViewById(R.id.txtMensaje);

        btSend = (Button) findViewById(R.id.btnSend);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMail();
            }
        });
   }

    private void SendMail() {

        //Prepare email Details
        String mEmail = txtInputFrom.getEditText().getText().toString();
        String mEmailMessage = txtInputMesaje.getEditText().getText().toString();
        String mEmailSubject = "Nome Contacto: " + txtInputNombre.getEditText().getText().toString() + " Email Contacto: " + mEmail;

        //Send Mail
        JavaMailAPI mJavaMailAPI = new JavaMailAPI(this, EMAIL, mEmailSubject, mEmailMessage);

        mJavaMailAPI.execute();

    }

}