package com.ce.datosi.GraphMessage.Actividades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ce.datosi.GraphMessage.R;

/**
 * Created by erick on 11/26/2016.
 */

public class EscrituraTexto extends Fragment {

    public static EscrituraTexto newInstance() {
        EscrituraTexto fragment = new EscrituraTexto();
        return fragment;
    }

    public EscrituraTexto(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaRaiz = inflater.inflate(R.layout.fragment_envio_texto, container, false);


        return vistaRaiz;
    }
}
