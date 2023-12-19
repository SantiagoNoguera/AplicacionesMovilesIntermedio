package com.atenea.ecoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.atenea.ecoevent.models.Event;
import com.atenea.ecoevent.models.User;
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

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Referencias a elementos de la vista.
        TextView eventsTV = findViewById(R.id.tv_events);
        TextView foodTV = findViewById(R.id.tv_food);
        TextView drinkTV = findViewById(R.id.tv_drink);
        TextView decorationTV = findViewById(R.id.tv_decoration);
        TextView reusedTV = findViewById(R.id.tv_reused);
        TextView recycledTV = findViewById(R.id.tv_recycled);

        //Leer los datos del archivo de eventos.
        File dataEvents = new File(getFilesDir(), "events.txt");
        ArrayList<Event> events = new ArrayList<>();

        Integer lines = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataEvents));
            String lineContent;
            lines = 0;

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

                //Contar la cantidad de registros en el archivo.
                lines++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Establecer número total de eventos en el registro.
        eventsTV.setText(String.valueOf(lines));

        //Establecer total de alimentos en la vista (cantidad del ultimo elemento menos la cantidad del penultimo evento).
        showDataInformation(foodTV, events.get(events.size()-1).getFood() - events.get(events.size()-2).getFood());

        //Establecer total de bebidas en la vista (cantidad del ultimo elemento menos la cantidad del penultimo evento).
        showDataInformation(drinkTV, events.get(events.size()-1).getDrink() - events.get(events.size()-2).getDrink());

        //Establecer total de decoraciones en la vista (cantidad del ultimo elemento menos la cantidad del penultimo evento).
        showDataInformation(decorationTV, events.get(events.size()-1).getDecoration() - events.get(events.size()-2).getDecoration());

        //Establecer total de elementos reutilizados en la vista (cantidad del ultimo elemento menos la cantidad del penultimo evento).
        showDataInformation(reusedTV, events.get(events.size()-1).getReused() - events.get(events.size()-2).getReused());

        //Establecer total de elementos reciclados en la vista (cantidad del ultimo elemento menos la cantidad del penultimo evento).
        showDataInformation(recycledTV, events.get(events.size()-1).getRecycled() - events.get(events.size()-2).getRecycled());

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
                        view = new Intent(HomeActivity.this, CategoriesActivity.class);
                        break;
                    case "Estadísticas":
                        view = new Intent(HomeActivity.this, StatisticsActivity.class);
                        break;
                    case "Consejos":
                        view = new Intent(HomeActivity.this, TipsActivity.class);
                        break;
                    default:
                        view = new Intent(HomeActivity.this, HomeActivity.class);
                        break;
                }

                startActivity(view);
                return false;
            }
        });

    }

    //Método que muestra en pantalla la cantidad de cada elemento con su color correspondiente.
    private void showDataInformation(TextView textView, Integer number) {
        String text = String.valueOf(number);
        textView.setText(text);

        //Determinar el color que tomará la información a mostrar.
            if (number == 0) {
                textView.setTextColor(getResources().getColor(R.color.black, null));
            } else if (number < 0) {
                textView.setTextColor(getResources().getColor(R.color.error, null));
            } else {
            textView.setTextColor(getResources().getColor(R.color.success, null));
            textView.setText("+" + text);
        }
    }
}