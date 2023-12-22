package com.atenea.ecoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CategoriesActivity extends AppCompatActivity {

    String[] items = {"Alimentos", "Bebidas", "Decoraciones", "Reutilizados", "Reciclados"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //Referencias a elementos d ela vista.
        AutoCompleteTextView listACTV = findViewById(R.id.ac_tv_list);

        TextView categoryTV = findViewById(R.id.tv_category);

        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);

        listACTV.setAdapter(adapterItems);
        listACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

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