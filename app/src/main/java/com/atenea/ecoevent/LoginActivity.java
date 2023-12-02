package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atenea.ecoevent.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

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
        Intent homeView = new Intent(this, HomeActivity.class);
        Intent registerView = new Intent(this, RegisterActivity.class);

        //Leer los datos del archivo de usuarios.
        File dataUsers = new File(getFilesDir(), "users.txt");
        ArrayList<User> users = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(dataUsers));
            String line;

            while ((line = reader.readLine()) != null) {
                //Obtener los datos del usuario al dividir la cadena de texto de cada linea del archivo.
                String[] userData = line.split(",");
                String name = userData[0];
                String username = userData[1];
                String email = userData[2];
                String password = userData[3];

                //Se crea un nuevo objeto User y se añade a la lista de usuarios.
                User newUser = new User(name, username, email, password);
                users.add(newUser);
            }

            reader.close();

            for (User user: users) {
                Log.d("Usuarios", "Nombre: " + user.getName() + "\nUsuario: " + user.getUsername() + "\nCorreo: " + user.getEmail() + "\nContraseña: " + user.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Evento de click para el botón de ingreso.
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Corroborar que los campos del formulario no esten vacios.
                if (emailET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                    return;
                }

                String inputEmail = emailET.getText().toString();
                String inputPassword = passwordET.getText().toString();

                for (User user: users) {
                    if (user.getEmail().toString().equals(inputEmail) && user.getPassword().toString().equals(inputPassword)) {
                        startActivity(homeView);
                        return;
                    }
                }

                Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        //Evento de click para enlace a vista de registro.
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(registerView);
            }
        });
    }
}