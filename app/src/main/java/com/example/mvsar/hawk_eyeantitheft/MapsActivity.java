package com.example.mvsar.hawk_eyeantitheft;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button b1;
    EditText e1, e2;
    Double longitude, latitude;
    Location location;
    Boolean Mapready = false;
    LatLng point;
    //private GPSactivity LocationObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i1=getIntent();
        String intentlong=i1.getStringExtra("longitude");
        String intentlat=i1.getStringExtra("latitude");
        longitude=Double.parseDouble(intentlong);
        latitude=Double.parseDouble(intentlat);
        Toast.makeText(getApplicationContext(),latitude+" "+longitude,Toast.LENGTH_LONG).show();
        point=new LatLng(longitude,latitude);



       /* b1=(Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.Lat);
        e2=(EditText)findViewById(R.id.lon);*/
              // LocationObj=new GPSactivity(getApplicationContext());
        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //latitude=e1.getText().toString();
                //longitude=e2.getText().toString();
                //location=LocationObj.getLocation();
                //if(location!=null)
                {
                    //latitude=location.getLatitude();
                    //longitude=location.getLongitude();
                    point=new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(point).title("Your location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                }
                /*else
                    location=LocationObj.getLocation();



            }
        });*/


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Mapready=true;
        mMap.addMarker(new MarkerOptions().position(point).title("Your location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));


        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
