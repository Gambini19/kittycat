package ru.artemdivin.kittycat;

import android.content.Intent;
import android.database.Cursor;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Cat.isFirstLaunchApp(this);



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

        Cursor cursor = App.dbHelper.getReadableDatabase()
                            .rawQuery("SELECT * FROM mytable", null);

        if (cursor.moveToFirst()){
            do{
                Cat cat = Cat.fromCursor(cursor);
                LatLng catCoordinate =new LatLng(cat.coordinatesLng,cat.coordinatesLat);
                mMap.addMarker(new MarkerOptions().position(catCoordinate));

            }
            while(cursor.moveToNext());
        }

        cursor.close();


        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerWashington));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String titleID = marker.getTitle();
                Intent intent = new Intent(MapsActivity.this, CatActivity.class);
                intent.putExtra("titleId", titleID  );
                startActivity(intent);
                return true;
            }
        });
    }
}

