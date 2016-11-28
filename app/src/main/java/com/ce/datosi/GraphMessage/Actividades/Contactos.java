package com.ce.datosi.GraphMessage.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ce.datosi.GraphMessage.R;
import com.facebook.AccessToken;

public class Contactos extends AppCompatActivity {

    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        continuar = (Button)findViewById(R.id.btnContinuar2);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Contactos.this, EnvioMultimedia.class);
                startActivity(intent);
                onPause();

            }
        });
    }
}
