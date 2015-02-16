package org.selimsinai.sinai2.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.selimsinai.sinai2.R;

/**
 * Created by ssalman on 2/10/2015.
 */
public class MapsFragment extends MapFragment implements OnMapReadyCallback {

    private static MapFragment mapFragment;

    public static MapFragment getInstance() {
        if(mapFragment ==null){
            mapFragment = new MapsFragment();
        }
        return mapFragment;
    }

    public MapsFragment() {
        super();
    }



    /*private static ;
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        if(contentFragment == null){
            contentFragment = new ContentFragment();
            Bundle args = new Bundle();
          //  args.putString(ARG_PARAM1, param1);
          //  args.putString(ARG_PARAM2, param2);
            contentFragment.setArguments(args);
        }

        return contentFragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(29.5000, 33.8333));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(7);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

        addMarker(googleMap, 29.5000, 33.8333, R.string.marker_title, R.string.marker_description);

    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           int title, int snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(getString(title))
                .snippet(getString(snippet)));
    }
}
