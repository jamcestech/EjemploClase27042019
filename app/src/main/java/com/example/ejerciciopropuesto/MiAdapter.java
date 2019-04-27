package com.example.ejerciciopropuesto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MiAdapter extends ArrayAdapter {

    Context context;
    int itemLayout;
    ArrayList<Element> datos;



    public MiAdapter(ListaDinamicaActivity context, int custom, ArrayList<Element> cosas) {
        super(context, custom, cosas);
        this.context = context;
        itemLayout = custom;
        datos = cosas ;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater lyi = LayoutInflater.from(context);
           convertView = lyi.inflate(itemLayout, parent,false);
        }

       TextView txtv = convertView.findViewById(R.id.txtV);
       ImageView imgv = convertView.findViewById(R.id.imgV);
        //Handle buttons and add onClickListeners
        Button callbtn= (Button)convertView.findViewById(R.id.btnBorrar);

        callbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ((ListaDinamicaActivity)context).onItemClick(null, v, position, '0');
                Toast.makeText(context, "Borrado", Toast.LENGTH_SHORT).show();
            }
        });
        txtv.setText(datos.get(position).txt);
        imgv.setImageResource(datos.get(position).img);

        return convertView;
    }
}
