package com.atenea.ecoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.atenea.ecoevent.models.Category;
import com.atenea.ecoevent.models.Event;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CategoriesActivity extends AppCompatActivity {

    String[] items = {"Alimentos", "Bebidas", "Decoraciones", "Reutilizados", "Reciclados"};

    //Información de la vista para rellenar la lista con la información de las categorías.
    ListView categoriesInformationLV;

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

        //Rellenar lista con información de las categorías.
        showCategoriesInformation();

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

    private void showCategoriesInformation() {
        //Leer los datos del archivo de eventos.
        File dataEvents = new File(getFilesDir(), "events.txt");
        ArrayList<Event> events = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataEvents));
            String lineContent;

            while ((lineContent = reader.readLine()) != null) {
                //Obtener los datos del evento al dividir la cadena de texto de cada linea del archivo.
                String[] eventData = lineContent.split(",");
                String name = eventData[0];

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(eventData[1]);

                Integer food = Integer.parseInt(eventData[2]);
                Integer drink = Integer.parseInt(eventData[3]);
                Integer decoration = Integer.parseInt(eventData[4]);
                Integer reused = Integer.parseInt(eventData[5]);
                Integer recycled = Integer.parseInt(eventData[6]);

                //Se crea un nuevo objeto Event y se añade a la lista de eventos.
                Event newEvent = new Event(name, date, food, drink, decoration, reused, recycled);
                events.add(newEvent);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Referencía a lista de la vista.
        categoriesInformationLV = findViewById(R.id.lv_categories_information);

        //Arreglo con la información de cada una de las categorías.
        ArrayList<Category> categories = new ArrayList<>();

        Integer totalFood = 0;
        Integer totalDrink = 0;
        Integer totalDecoration = 0;
        Integer totalReused = 0;
        Integer totalRecycled = 0;

        for (Event event: events) {
            totalFood += event.getFood();
            totalDrink += event.getDrink();
            totalDecoration += event.getDecoration();
            totalReused += event.getReused();
            totalRecycled += event.getRecycled();
        }

        Integer meta = 300;

        categories.add(new Category(items[0], totalFood, Float.valueOf(totalFood) / events.size(), meta, meta - totalFood));
        categories.add(new Category(items[1], totalDrink, Float.valueOf(totalDrink) / events.size(), 10, 10 - totalDrink));
        categories.add(new Category(items[2], totalDecoration, Float.valueOf(totalDecoration) / events.size(), 50, 50 - totalDecoration));
        categories.add(new Category(items[3], totalReused, Float.valueOf(totalReused) / events.size(), meta, meta - totalReused));
        categories.add(new Category(items[4], totalRecycled, Float.valueOf(totalRecycled) / events.size(), 150, 150 - totalRecycled));

        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        categoriesInformationLV.setAdapter(adapter);
    }
}