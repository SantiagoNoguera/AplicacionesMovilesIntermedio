package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Referencias a elementos de la vista.
        TextView loginTV = findViewById(R.id.tv_login);

        //Referencias a las demás actividades.
        Intent loginView = new Intent(this, LoginActivity.class);

        //Evento de click para enlace a la vista de inicio de sesión.
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginView);
            }
        });
    }
}