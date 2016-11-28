package com.ce.datosi.GraphMessage.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ce.datosi.GraphMessage.Herramientas.MediaResultsAdapter;
import com.ce.datosi.GraphMessage.R;
import com.kbeanie.multipicker.api.AudioPicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.AudioPickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenAudio;

import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by erick on 11/25/2016.
 */

public class SeleccionAudio extends android.support.v4.app.Fragment implements AudioPickerCallback {


    private final static String TAG = SeleccionAudio.class.getSimpleName();

    private AudioPicker audioPicker;

    private ListView lvResults;

    Button buscar,enviar;

    public static SeleccionAudio newInstance() {
        SeleccionAudio fragment = new SeleccionAudio();
        return fragment;
    }

    public SeleccionAudio(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaRaiz = inflater.inflate(R.layout.fragment_envio_audios, container, false);


        lvResults = (ListView) vistaRaiz.findViewById(R.id.lvResults);
        buscar = (Button)vistaRaiz.findViewById(R.id.btnBuscarAudio);
        enviar = (Button)vistaRaiz.findViewById(R.id.btnEnviarAudio);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFilesSingle();

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return vistaRaiz;
    }


    private void pickFilesSingle() {
        audioPicker = getAudioPicker();
        audioPicker.pickAudio();
    }


    private AudioPicker getAudioPicker() {
        audioPicker = new AudioPicker(this);
        audioPicker.setAudioPickerCallback(this);
        return audioPicker;
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Picker.PICK_AUDIO && resultCode == RESULT_OK) {
            if(audioPicker == null) {
                audioPicker = new AudioPicker(SeleccionAudio.this);
                audioPicker.setAudioPickerCallback(this);
            }
            audioPicker.submit(data);
        }
    }

    @Override
    public void onAudiosChosen(List<ChosenAudio> audios) {
        for (ChosenAudio audio : audios) {
            Log.d(TAG, "onFilesChosen: " + audio);
        }

        MediaResultsAdapter adapter = new MediaResultsAdapter(audios, this.getContext());
        lvResults.setAdapter(adapter);

    }
}
