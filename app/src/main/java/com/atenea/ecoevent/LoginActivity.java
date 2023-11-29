package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencias a elementos de la vista.
        EditText emailET = findViewById(R.id.et_email);
        EditText passwordET = findViewById(R.id.et_password);

        Button submitBTN = findViewById(R.id.btn_submit);

        TextView registerTV = findViewById(R.id.tv_register);

        //Referencias a las demás actividades.
        Intent registerView = new Intent(this, RegisterActivity.class);

        //Leer los datos del archivo de usuarios.
        File dataUsers = new File(getFilesDir(), "users.txt");
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(dataUsers));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Evento de click para enlace a vista de registro.
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(registerView);
            }
        });
    }
}