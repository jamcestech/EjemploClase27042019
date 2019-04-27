package com.example.ejerciciopropuesto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Element> cosas;
    ListView listaDinamica;
    EditText et;
    SharedPreferences sharedPreferences;
    ElementList elementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        et = findViewById(R.id.txtE);
        listaDinamica = findViewById(R.id.listaDinamica);
        cosas = new ArrayList<>();

        Element example = new Element("Hola", R.drawable.uno);
        // cosas.add(example);


        sharedPreferences = getSharedPreferences("cosas", MODE_PRIVATE);

        String json = sharedPreferences.getString("elements", "");


        elementList = new ElementList();
        if (json != null && !json.isEmpty()) {
            elementList = elementList.fromJson(json);

            for (Element e: elementList.elements) {

                cosas.add(new Element(e.txt, e.img ));


            }
        }
        Log.d("Tamaño", elementList.elements.size() + "");
        if(elementList.elements.size() == 0) {
            elementList.addElement(example);
            guardadoPreferencias();
        }

    }

    public void addTarea(View view) {
        Element eleNuevo = new Element(et.getText().toString(), R.drawable.uno);
        cosas.add(eleNuevo);
        et.setText("");

        elementList.addElement(eleNuevo);
        guardadoPreferencias();
    }

    public void verTarea(View view) {
        Intent i = new Intent(MainActivity.this, ListaDinamicaActivity.class);
        startActivity(i);
    }

    public void guardadoPreferencias() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("elements", elementList.toJson());
        editor.apply();
    }
}
