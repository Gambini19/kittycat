package ru.artemdivin.kittycat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean isFirstStart = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        Cat.createCat();
        /*
        if (isFirstStart)
        {
        Cat.createCat();
        isFirstStart = false;
        }*/
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Washington and move the camera
        LatLng centerWashington = new LatLng(38.89, -77.036);

        for (int i = 0; i < Cat.catList.size() ; i++) {
            LatLng catCoordinates = new LatLng((Cat.catList.get(i).coordinatesLat)
                    ,Cat.catList.get(i).coordinatesLng);
            mMap.addMarker(new MarkerOptions().position(catCoordinates).title(Cat.catList.get(i).catName));
        }


      //  mMap.addMarker(new MarkerOptions().position(washington).title("Marker in Washington"));
       // mMap.addMarker(new MarkerOptions().position(cat1).title(cat.catList.get(0).catName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerWashington));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String titleID = marker.getTitle();
                Intent intent = new Intent(MapsActivity.this, CatActivity.class);
                intent.putExtra("titleId", titleID  );
                //Log.i("intent_catID", String.valueOf(cat.id));
                startActivity(intent);
            }
        });


    }




    }

