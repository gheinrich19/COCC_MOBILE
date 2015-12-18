package com.gheinrich.cocc_mobile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class google_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap coccmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
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
       coccmap = googleMap;

        coccmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //setting the location of the opening screen view
        LatLng cocc = new LatLng(44.071991, -121.348357);


        // this moves your camera view to the location and the second perameter in .newLatLngzoom is the zoom!
        coccmap.moveCamera(CameraUpdateFactory.newLatLngZoom(cocc, 15));

        // this creates amarker on the map and has a few options i only used two.
        coccmap.addMarker(new MarkerOptions()
                .title("Central Oregon Community College")
                .position(cocc));




        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(44.072791,-121.3496053),
                        new LatLng(44.0726638,-121.3496107),
                        new LatLng(44.0726754,-121.3500988),
                        new LatLng(44.0724711,-121.3501096),
                        new LatLng(44.0724518,-121.3491976),
                        new LatLng(44.0726561,-121.349203),
                        new LatLng(44.0726522,-121.3489884),
                        new LatLng(44.0727717,-121.348983),
                        new LatLng(44.072791,-121.3496053)).strokeColor(Color.BLUE).fillColor(Color.BLUE).strokeWidth(2);




// Get back the mutable Polygon

         coccmap.addPolygon(rectOptions);

    }
}
