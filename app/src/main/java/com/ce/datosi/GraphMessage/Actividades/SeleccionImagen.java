package com.ce.datosi.GraphMessage.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ce.datosi.GraphMessage.Herramientas.MediaResultsAdapter;
import com.ce.datosi.GraphMessage.Herramientas.PickerUtils;
import com.ce.datosi.GraphMessage.R;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.List;

/**
 * Created by erick on 11/25/2016.
 */

public class SeleccionImagen extends Fragment implements ImagePickerCallback {

    private String pickerPath;

    private ListView lvResults;

    Button enviar,buscar;

    private ImagePicker imagePicker;

    public static SeleccionImagen newInstance() {
        SeleccionImagen fragment = new SeleccionImagen();
        return fragment;
    }

    public SeleccionImagen(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaRaiz = inflater.inflate(R.layout.fragment_envio_imagenes, container, false);

        lvResults = (ListView) vistaRaiz.findViewById(R.id.lvResults);
        buscar = (Button)vistaRaiz.findViewById(R.id.btnBuscarImagen);
        enviar = (Button)vistaRaiz.findViewById(R.id.btnEnviarImagen);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageSingle();

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return vistaRaiz;
    }

    public void pickImageSingle() {
        imagePicker = new ImagePicker(this);
        imagePicker.setFolderName("Random");
        imagePicker.setRequestId(1234);
        imagePicker.ensureMaxSize(500, 500);
        imagePicker.shouldGenerateMetadata(true);
        imagePicker.shouldGenerateThumbnails(true);
        imagePicker.setImagePickerCallback(this);
        Bundle bundle = new Bundle();
        bundle.putInt("android.intent.extras.CAMERA_FACING", 1);
        imagePicker.setCacheLocation(PickerUtils.getSavedCacheLocation(this.getContext()));
        imagePicker.pickImage();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(data);
            }
        }
    }

    @Override
    public void onImagesChosen(List<ChosenImage> images) {
        MediaResultsAdapter adapter = new MediaResultsAdapter(images, this.getContext());
        lvResults.setAdapter(adapter);
    }


    @Override
    public void onError(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // You have to save path in case your activity is killed.
        // In such a scenario, you will need to re-initialize the CameraImagePicker
        outState.putString("picker_path", pickerPath);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        // After Activity recreate, you need to re-intialize these
        // two values to be able to re-intialize CameraImagePicker
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("picker_path")) {
                pickerPath = savedInstanceState.getString("picker_path");
            }
        }
        super.onViewStateRestored(savedInstanceState);
    }


}
