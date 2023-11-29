package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencias de elementos de la vista.
        TextView registerTV = findViewById(R.id.tv_register);

        //Referencias a las demás actividades.
        Intent registerView = new Intent(this, RegisterActivity.class);

        //Evento de click para enlace a vista de registro.
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(registerView);
            }
        });
    }
}