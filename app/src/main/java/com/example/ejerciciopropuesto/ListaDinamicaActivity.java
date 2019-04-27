package com.example.ejerciciopropuesto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaDinamicaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Element> cosas;
    ListView listaDinamica;
    EditText et;
    SharedPreferences sharedPreferences;
    ElementList elementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dinamica);

        et = findViewById(R.id.txtE);
        listaDinamica = findViewById(R.id.listaDinamica);
        cosas = new ArrayList<>();

        Element example = new Element("Hola", R.drawable.uno);
       // cosas.add(example);
        ListAdapter adapter =  new MiAdapter(this, R.layout.custom, cosas);

      sharedPreferences = getSharedPreferences("cosas", MODE_PRIVATE);

        String json = sharedPreferences.getString("elements", "");

        listaDinamica.setAdapter(adapter);

        listaDinamica.setOnItemClickListener(this);

        elementList = new ElementList();
        if (json != null && !json.isEmpty()) {
            elementList = elementList.fromJson(json);

            for (Element e: elementList.elements) {

                cosas.add(new Element(e.txt, e.img));
                adapter =  new MiAdapter(this, R.layout.custom, cosas);
                listaDinamica.setAdapter(adapter);
            }
        }
        Log.d("Tamaño", elementList.elements.size() + "");
        if(elementList.elements.size() == 0) {
            elementList.addElement(example);
            guardadoPreferencias();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       // elementList.editElement(position, cosas.get(position).img);
        elementList.removeElement(position);
        cosas.remove(position);

        refresAdapter();
    }

    public void addTarea(View view) {
        Element eleNuevo = new Element(et.getText().toString(), R.drawable.uno);
        cosas.add(eleNuevo);

        et.setText("");
        refresAdapter();

        elementList.addElement(eleNuevo);
        guardadoPreferencias();
    }

    public void refresAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) listaDinamica.getAdapter();
        adapter.notifyDataSetChanged();

        guardadoPreferencias();
    }

    public void guardadoPreferencias() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("elements", elementList.toJson());
        editor.apply();
    }

    public void atrasIr(View view) {
        Intent i = new Intent(ListaDinamicaActivity.this, MainActivity.class);
        startActivity(i);
    }
}
