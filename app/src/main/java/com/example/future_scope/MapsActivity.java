package com.example.future_scope;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng ubicacionPedido, ubicacionLocal;
    private Button confirmar;
    private TextView ubicacion;
    private Location ubicacionActual;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Polyline polyline = null;
    List<LatLng> latLngList = new ArrayList<>();
    private  MarkerOptions markerLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        confirmar=findViewById(R.id.confirmar);
        ubicacion=findViewById(R.id.ubicacion_actual);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

    }

    private void fetchLastLocation(){

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    ubicacionActual = location;
                    Toast.makeText(getApplicationContext(),ubicacionActual.getLatitude()+" "+ ubicacionActual.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);

                }
            }
        });
    }

    @SuppressLint("ServiceCast")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Instancia de mapa
        mMap = googleMap;

        LatLng latLng = new LatLng(ubicacionActual.getLatitude(),ubicacionActual.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Ubicación actual");

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        mMap.addMarker(markerOptions);

        ubicacionPedido=latLng;
        ubicacion.setText(ubicacionPedido.toString());

        //Pedir permiso para saber ubicacion actual
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        mMap.setMyLocationEnabled(true);

        //Habilito que se pueda ver el trafico
        mMap.setTrafficEnabled(true);

        //Habilito que se pueda hacer zoom
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //Instancia de marcadores
        final Map<Integer, MarkerOptions> marcadores = new HashMap<>();

        //Configuraciones de la rotaciones y gestos
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);

        //ubicacion del local

        Random r = new Random();

        // Una direccion aleatoria de 0 a 359 grados
        int direccionRandomEnGrados = r.nextInt(360);

        // Una distancia aleatoria de 100 a 1000 metros
        int distanciaMinima = 100;
        int distanciaMaxima = 1000;
        int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

        LocationManager locMa = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locMa.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        ubicacionLocal = new LatLng(location.getLatitude(), location.getLongitude());

        LatLng nuevaPosicion = SphericalUtil.computeOffset(
                ubicacionLocal,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );
        markerLocal = new MarkerOptions()
                .position(nuevaPosicion)
                .title("Ubicación del local")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerLocal);

        //Marcador de clic largo
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions m = new MarkerOptions()
                        .position(latLng)
                        .title("Destino del pedido")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.clear();
                latLngList.clear();

                mMap.addMarker(markerLocal);
                mMap.addMarker(m);

                ubicacionPedido=latLng;
                ubicacion.setText(ubicacionPedido.toString());

                latLngList.add(markerLocal.getPosition());
                latLngList.add(latLng);

                dibujarPolyline();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iresult = new Intent();
                System.out.println(ubicacionPedido.latitude+"   "+ubicacionPedido.longitude);

                iresult.putExtra("latitud", ubicacionPedido.latitude);
                iresult.putExtra("longitud", ubicacionPedido.longitude);
                setResult(RESULT_OK,iresult);
                finish();
            }
        });

    }
    public void dibujarPolyline(){
        if (polyline != null){
            polyline.remove();
        }
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(latLngList).clickable(true);
        polyline = mMap.addPolyline(polylineOptions);
    }
    //Habilitar ir a la posicion actual
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
            case 9999:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }
}