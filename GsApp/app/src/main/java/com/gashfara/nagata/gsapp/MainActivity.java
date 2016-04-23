package com.gashfara.nagata.gsapp;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener {

    private MessageRecordsAdapter mAdapter;
    private GoogleMap mMap;
    private static final int LOCATION_REQUEST_CODE = 1;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create Adapter
        mAdapter = new MessageRecordsAdapter(this);
        // Set Adapter
        ListView listView = (ListView) findViewById(R.id.location_list);
        listView.setAdapter(mAdapter);
//      Get Window Size
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point windowSize = new Point();
        display.getSize(windowSize);
//      Set Map Size
        FrameLayout mapLayout = (FrameLayout) findViewById(R.id.map);
        LinearLayout.LayoutParams mapLayoutParams = (LinearLayout.LayoutParams) mapLayout.getLayoutParams();
        mapLayoutParams.height = windowSize.x * 9 / 16;
//      Set Google Map
        GoogleMapOptions mapOptions = new GoogleMapOptions();
        LatLng latLng = new LatLng(0, 0);
        CameraPosition cameraPosition = new CameraPosition(latLng, 0, 0, 0);
        mapOptions.mapType(GoogleMap.MAP_TYPE_NORMAL)
                .camera(cameraPosition)
                .zoomControlsEnabled(false)
                .compassEnabled(false)
                .zoomGesturesEnabled(true)
                .scrollGesturesEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(false);
        MapFragment mMapFragment = MapFragment.newInstance(mapOptions);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnCameraChangeListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, LOCATION_REQUEST_CODE);
            }
        } else {
            getLocation();
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        double latitude = cameraPosition.target.latitude;
        double longitude = cameraPosition.target.longitude;
        fetchAllLocations(latitude, longitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Log.e("gsapp", "permission denied");
                }
            }
        }
    }

    private void getLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        myLocation = locationManager.getLastKnownLocation(provider);
        if (myLocation != null) {
            onLocationChanged(myLocation);
            // Show Locations
            double latitude = myLocation.getLatitude();
            double longitude = myLocation.getLongitude();
            fetchAllLocations(latitude, longitude);
        }
    }

    private void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        mMap.animateCamera(cameraUpdate);
    }

    private void fetchAllLocations(double latitude, double longitude) {
        Response.Listener success = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    List<MessageRecord> messageRecords = parse(jsonObject);
                    mAdapter.setMessageRecords(messageRecords);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        // Fetch JSON
        double radius = 32.0 * Math.pow(2, Math.min(0.0, 10 - mMap.getCameraPosition().zoom));
        Log.e("gsapp_debug", String.valueOf(radius));
        JsonObjectRequest request = new JsonObjectRequest(
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=6c540996a0ebd478547b645f04c0f3f6&sort=interestingness-desc&lat="+latitude+"&lon="+longitude+"&radius="+radius+"&per_page=100&format=json&nojsoncallback=1",
                null,
                success,
                error
        );
        // Add Request to Queue
        VolleyApplication.getsInstance().getRequestQueue().add(request);
    }

    private List<MessageRecord> parse(JSONObject json) throws JSONException {
        ArrayList<MessageRecord> records = new ArrayList<MessageRecord>();

        JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
        for (int i = 0; i < photos.length(); i++) {
            JSONObject photo = (JSONObject) photos.get(i);
            String title = photo.getString("title");
            String imgUrl = "https://farm"+photo.getString("farm")+".staticflickr.com/"+photo.getString("server")+"/"+photo.getString("id")+"_"+photo.getString("secret")+"_q.jpg";
            MessageRecord record = new MessageRecord(imgUrl, title);
            records.add(record);
        }
        return records;
    }

}
