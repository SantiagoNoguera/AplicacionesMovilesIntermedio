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

        //Crear archivo para almacenar los consejos que se mostrarán.
        File tips = new File(getFilesDir(), "tips.txt");
        try {
            FileWriter writer = new FileWriter(tips);

            //Consejo diario, Acciones de mejora.
            writer.append("Usa decoraciones reutilizables y duraderas.;Planifica el evento con la sostenibilidad en mente, minimizando el uso de recursos y maximizando la eficiencia.\n");
            writer.append("Elige vajilla y cubiertos compostables.;Implementa una política de cero residuos, incentivando a los participantes a generar la menor cantidad de desechos posible.\n");
            writer.append("Proporciona contenedores de reciclaje visibles.;Elige un lugar que apoye tus esfuerzos de reciclaje y tenga facilidades para la separación y recolección de residuos.\n");
            writer.append("Evita los productos de un solo uso.;Contrata proveedores que compartan tus valores de sostenibilidad y puedan proporcionar productos y servicios ecológicos.\n");
            writer.append("Digitaliza las invitaciones y programas.;Proporciona estaciones de reciclaje claramente marcadas y accesibles, asegurándote de que los asistentes sepan dónde depositar sus residuos.\n");
            writer.append("Donar sobras de comida a bancos de alimentos.;Incentiva a los asistentes a traer sus propios utensilios reutilizables, como botellas de agua y bolsas de tela.\n");
            writer.append("Recicla las tarjetas de identificación.;Utiliza tecnología para reducir la necesidad de materiales físicos, como aplicaciones para compartir información del evento.\n");
            writer.append("Promueve el transporte compartido o público.;Realiza un seguimiento de los residuos generados durante el evento para identificar oportunidades de mejora en futuros eventos.\n");
            writer.append("Contrata servicios de catering sostenibles.;Comunica tus esfuerzos de sostenibilidad a los asistentes, creando conciencia y fomentando su participación activa.\n");
            writer.append("Educa a los asistentes sobre reciclaje.;Considera la contratación de un profesional en gestión de residuos para asegurar el manejo adecuado de los desechos generados.\n");
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