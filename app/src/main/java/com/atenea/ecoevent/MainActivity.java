package com.atenea.ecoevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.atenea.ecoevent.models.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear archivo para datos de usuarios.
        File users = new File(getFilesDir(), "users.txt");
        try {
            FileWriter writer = new FileWriter(users);

            //Name, Username, Email, Password.
            writer.append("root,root,root@email.com,pass\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Crear archivo para datos de los eventos.
        File events = new File(getFilesDir(), "events.txt");
        try {
            FileWriter writer = new FileWriter(events);

            //Name, Date, Food, Drink, Decoration, Reused, Recycled.
            writer.append("Evento 1,01/01/2023,1,2,3,4,5\n");
            writer.append("Evento 2,02/02/2023,10,2,3,5,5\n");
            writer.append("Evento 3,03/03/2023,6,2,8,4,5\n");
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