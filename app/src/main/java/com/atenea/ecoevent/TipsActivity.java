package com.atenea.ecoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        //Referencias a elementos de la vista.
        TextView dailyTV = findViewById(R.id.tv_daily);
        TextView improveTV = findViewById(R.id.tv_improve);

        //Leer el archivo que contiene los consejos a mostrar.
        File dataTips = new File(getFilesDir(), "tips.txt");
        ArrayList<String> tips = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataTips));
            String lineContent;

            while ((lineContent = reader.readLine()) != null) {
                //Agregar a la lista el contenido del archivo.
                tips.add(lineContent);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Mostrar los consejos en la vista de forma aleatoria.
        Random random = new Random();
        Integer randomIndex = random.nextInt(tips.size());

        String dailyTip = tips.get(randomIndex).split(";")[0];
        String improveTip = tips.get(randomIndex).split(";")[1];

        dailyTV.setText(dailyTip);
        improveTV.setText(improveTip);

        //Configuración de la barra inferior.
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);

        //Configuración de la navegación de la barra inferior.
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent view;

                switch (item.getTitle().toString()) {
                    case "Categorias":
                        view = new Intent(TipsActivity.this, CategoriesActivity.class);
                        break;
                    case "Estadísticas":
                        view = new Intent(TipsActivity.this, StatisticsActivity.class);
                        break;
                    case "Consejos":
                        view = new Intent(TipsActivity.this, TipsActivity.class);
                        break;
                    default:
                        view = new Intent(TipsActivity.this, HomeActivity.class);
                        break;
                }

                startActivity(view);
                return false;
            }
        });

    }
}