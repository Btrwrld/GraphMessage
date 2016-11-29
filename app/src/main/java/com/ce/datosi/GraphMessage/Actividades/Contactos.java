package com.ce.datosi.GraphMessage.Actividades;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ce.datosi.GraphMessage.R;
import com.ce.datosi.GraphMessage.Servicios.Comunicador;

public class Contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        String lista[] = new String[Comunicador.getClientes().size()];
        for(int i = 0; i < Comunicador.getClientes().size(); i++ )
        {
            lista[i] = Comunicador.getClientes().get(i).getNombre();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        final ListView listaContactos = (ListView)findViewById(R.id.listContactos);
        listaContactos.setAdapter(adapter);
        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = listaContactos.getSelectedItemPosition();
                String ip = Comunicador.getClientes().get(i).getIP();
                Intent intent = new Intent(Contactos.this, EnvioMultimedia.class);
                Intent intent2 = new Intent(Contactos.this, EnvioMultimedia.class);
                intent2.putExtra("IP", ip);
                startActivity(intent);
                finish();

            }
        });


    }
}
