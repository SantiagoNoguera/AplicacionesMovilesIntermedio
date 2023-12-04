package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atenea.ecoevent.models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Referencias a elementos de la vista.
        final EditText nameET = findViewById(R.id.et_name);
        final EditText emailET = findViewById(R.id.et_email);
        final EditText passwordET = findViewById(R.id.et_password);
        final EditText passwordConfirmET = findViewById(R.id.et_password_confirm);

        CheckBox termsConditionsCB = findViewById(R.id.cb_terms);
        CheckBox dataProcessingCB = findViewById(R.id.cb_data_processing);

        Button registerBTN = findViewById(R.id.btn_register);

        TextView loginTV = findViewById(R.id.tv_login);

        //Referencias a las demás actividades.
        Intent loginView = new Intent(this, LoginActivity.class);

        //Acción de botón para registrar a los usuarios.
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Corroborar que todos los campos esten llenos.
                if (nameET.getText().toString().trim().isEmpty() || emailET.getText().toString().trim().isEmpty() || passwordET.getText().toString().isEmpty() || passwordConfirmET.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacios.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Corroborar que las contraseñas coincidan.
                if (!passwordET.getText().toString().equals(passwordConfirmET.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Corroborar que los terminos y condiciones y las politicas de tratamiento de datos se accepten.
                if (!termsConditionsCB.isChecked() && !dataProcessingCB.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Debe aceptar los terminos, condiciones y tratamiento de datos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Corroborar que las politicas de tratamiento de datos se accepten.
                if (!termsConditionsCB.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Debe aceptar los terminos y condiciones.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Corroborar que las politicas de tratamiento de datos se accepten.
                if (!dataProcessingCB.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Debe aceptar el tratamiento de datos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Corroborar si el usuario ya se encuentra registrado.
                if(userExist(emailET.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "El usuario ya se encuentra registrado.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Registrar usuario.
                String username = emailET.getText().toString().split("@")[0];
                User newUser = new User(nameET.getText().toString(), username, emailET.getText().toString(), passwordET.getText().toString());

                //Guardar los datos en el archivo.
                saveUser(newUser);

                //Redirección a la vista de Inicio de Sesión.
                startActivity(loginView);
            }
        });

        //Evento de click para enlace a la vista de inicio de sesión.
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginView);
            }
        });
    }

    //Método que comprueba si un usuario se encuentra registrado.

    private Boolean userExist(String email) {
        //Leer archivo con los datos de los usuarios.
        File dataUsers = new File(getFilesDir(), "users.txt");

        try {
            FileReader reader = new FileReader(dataUsers);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            //Leer los datos de cada usurio, linea por linea del archivo.
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                String emailUserData = userData[2];

                //Comparar si el correo del usuario se encuentra registrado.
                if (email.equals(emailUserData)) {
                    bufferedReader.close();
                    return true;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Método para guardar en el archivo los datos del nuevo usuario.
    private void saveUser(User user) {
        File users = new File(getFilesDir(), "users.txt");

        try {
            //El nuevo registro se agregará al final del archivo sin alterar los registros ya creados.
            FileWriter writer = new FileWriter(users, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String newUser = user.getName() + "," + user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "\n";

            bufferedWriter.write(newUser);
            bufferedWriter.close();

            Toast.makeText(getApplicationContext(), "Usuario exitosamente registrado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}