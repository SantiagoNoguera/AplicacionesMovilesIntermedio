package com.atenea.ecoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

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
                        view = new Intent(CategoriesActivity.this, CategoriesActivity.class);
                        break;
                    case "Estadísticas":
                        view = new Intent(CategoriesActivity.this, StatisticsActivity.class);
                        break;
                    case "Consejos":
                        view = new Intent(CategoriesActivity.this, TipsActivity.class);
                        break;
                    default:
                        view = new Intent(CategoriesActivity.this, HomeActivity.class);
                        break;
                }

                startActivity(view);
                return false;
            }
        });

    }
}