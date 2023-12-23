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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    String[] items = {"Alimentos", "Bebidas", "Decoraciones", "Reutilizados", "Reciclados"};

    //Referencias a elementos de la vista.
    TextView mediaTV;
    TextView maxTV;
    TextView minTV;
    TextView reductionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadistics);

        //Referencias de elementos en la vista.
        mediaTV = findViewById(R.id.tv_media);
        maxTV = findViewById(R.id.tv_max);
        minTV = findViewById(R.id.tv_min);
        reductionTV = findViewById(R.id.tv_reduction);

        AutoCompleteTextView listACTV = findViewById(R.id.ac_tv_list);

        TextView categoryTV = findViewById(R.id.tv_category);

        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);

        //Leer los datos del archivo de eventos.
        File dataEvents = new File(getFilesDir(), "events.txt");
        ArrayList<String> dataCategories = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataEvents));
            String lineContent;

            while ((lineContent = reader.readLine()) != null) {
                //Obtener los datos del evento al dividir la cadena de texto de cada linea del archivo.
                String[] eventData = lineContent.split(",");

                String food = eventData[2];
                String drink = eventData[3];
                String decoration = eventData[4];
                String reused = eventData[5];
                String recycled = eventData[6];

                dataCategories.add(food + "," + drink + "," + decoration + "," + reused + "," + recycled);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listACTV.setAdapter(adapterItems);
        listACTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                categoryTV.setText(item);

                showData(dataCategories, i);
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
                        view = new Intent(StatisticsActivity.this, CategoriesActivity.class);
                        break;
                    case "Estadísticas":
                        view = new Intent(StatisticsActivity.this, StatisticsActivity.class);
                        break;
                    case "Consejos":
                        view = new Intent(StatisticsActivity.this, TipsActivity.class);
                        break;
                    default:
                        view = new Intent(StatisticsActivity.this, HomeActivity.class);
                        break;
                }

                startActivity(view);
                return false;
            }
        });

    }

    //Método para calcular la información que se mostrará en la vista.
    private void showData(ArrayList<String> dataCategories, Integer position) {
        Double max = Double.NEGATIVE_INFINITY;
        Double min = Double.POSITIVE_INFINITY;

        Float reduction = Float.valueOf(0);

        for (String dataCategory: dataCategories) {
            //Obtener el dato de la categoria indicada.
            String data = dataCategory.split(",")[position];

            if (Integer.parseInt(data) >= max) {
                max = Double.valueOf(data);
            }

            if (Integer.parseInt(data) <= min) {
                min = Double.valueOf(data);
            }

            reduction += Integer.parseInt(data);
        }

        //Promedio de los datos
        Float media = reduction / dataCategories.size();

        DecimalFormat format = new DecimalFormat("0.0");

        //Mostrar datos en la vista.
        mediaTV.setText(String.valueOf(format.format(media)));
        maxTV.setText(String.valueOf(format.format(max)));
        minTV.setText(String.valueOf(format.format(min)));
        reductionTV.setText(String.valueOf(format.format(reduction)));
    }
}