package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear archivo para datos de usuarios y escribir los datos.
        File users = new File(getFilesDir(), "users.txt");
        try {
            System.out.println(getFilesDir());
            FileWriter writer = new FileWriter(users);
            writer.append("root,root,root@email.com,pass\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Iniciar vista de login.
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}