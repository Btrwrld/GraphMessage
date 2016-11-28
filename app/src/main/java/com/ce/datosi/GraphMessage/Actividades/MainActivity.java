package com.ce.datosi.GraphMessage.Actividades;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ce.datosi.GraphMessage.Herramientas.Codigo;
import com.ce.datosi.GraphMessage.R;
import com.ce.datosi.GraphMessage.Servicios.EnvioDatos;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    //Geolocalizacion
    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleApiClient apiClient;


    //Facebook
    CallbackManager callbackManager;
    LoginButton login;
    ProfilePictureView profile;
    TextView vistaNombre;
    ImageButton continuar;
    private String nombre, profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        login = (LoginButton)findViewById(R.id.login_button);
        login.setReadPermissions("public_profile email");
        profile = (ProfilePictureView)findViewById(R.id.picture);
        vistaNombre = (TextView)findViewById(R.id.lblnombre) ;
        continuar = (ImageButton)findViewById(R.id.ibtnContinuar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccessToken.getCurrentAccessToken() != null) {
                    profile.setProfileId(null);
                }
            }
        });

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        nombre = profile_image = "";

                        if (AccessToken.getCurrentAccessToken() != null) {
                            RequestData();
                            Profile profile = Profile.getCurrentProfile();
                            if (profile != null) {
                                vistaNombre.setText(profile.getName());
                                Log.e("full_name", nombre);
                                profile_image = profile.getProfilePictureUri(400, 400).toString();
                                Log.e("profile_image", profile_image);
                            }
                        }
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contactos.class);
                startActivity(intent);
                onPause();

                }
            });

            //Servicios de localizacion
       apiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addConnectionCallbacks(this).addApi(LocationServices.API).build();


    }

    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :"+json);
                try {
                    if(json != null){
                        profile.setProfileId(json.getString("id"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }






    private void updateUI(Location loc) {
        Toast.makeText(this.getApplicationContext(), "Latitud: " + String.valueOf(loc.getLatitude()) +
                "    Longitud: " + String.valueOf(loc.getLongitude()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {

            Location lastLocation =
                    LocationServices.FusedLocationApi.getLastLocation(apiClient);


            Codigo info = new Codigo();
            info.setX(lastLocation.getLatitude());
            info.setY(lastLocation.getLongitude());
            info.setNombre("Erick");
            EnvioDatos envio = new EnvioDatos(info);
            envio.execute();
            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);


                Codigo info = new Codigo();
                info.setX(lastLocation.getLatitude());
                info.setY(lastLocation.getLongitude());
                info.setNombre("Erick");
                EnvioDatos envio = new EnvioDatos(info);
                envio.execute();
               updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }

}
