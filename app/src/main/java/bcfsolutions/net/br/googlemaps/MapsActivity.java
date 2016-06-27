package bcfsolutions.net.br.googlemaps;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Context context;

    private SeekBar mSeekBar;

    private GoogleMap mMap;

    Circle circle;

    int radius = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        context = getBaseContext();

        // Get the widgets reference from XML layout
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Set a SeekBar change listener
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
                public abstract void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
                    Notification that the progress level has changed. Clients can use the fromUser
                    parameter to distinguish user-initiated changes from those that
                    occurred programmatically.

                Parameters
                    seekBar : The SeekBar whose progress has changed
                    progress : The current progress level. This will be in the range 0..max where
                        max was set by setMax(int). (The default value for max is 100.)
                    fromUser : True if the progress change was initiated by the user.
            */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Display the current progress of SeekBar
                final int radius = i * 5000;
                drawMapSearchRadius(radius);
            }

            /*
                public abstract void onStartTrackingTouch (SeekBar seekBar)
                    Notification that the user has started a touch gesture. Clients may want to use
                    this to disable advancing the SeekBar.

                Parameters
                    seekBar : The SeekBar in which the touch gesture began
            */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /*
                public abstract void onStopTrackingTouch (SeekBar seekBar)
                    Notification that the user has finished a touch gesture. Clients may want to
                    use this to re-enable advancing the SeekBar.

                Parameters
                    seekBar : The SeekBar in which the touch gesture began
            */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

        Toast.makeText(context, "Foi", Toast.LENGTH_SHORT).show();

        // Add a marker in Sydney and move the camera
        LatLng local = new LatLng(-23.502229, -46.867963);
        mMap.addMarker(new MarkerOptions().position(local).title("Marker in Barueri"));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(local, 11.0f);
        mMap.animateCamera(yourLocation);

        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-23.502229, -46.867963))
                .radius(radius)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(5)
                .fillColor(0x800000ff));
        }

    private void drawMapSearchRadius(int radius) {
        LatLng local = new LatLng(-23.502229, -46.867963);
        if(mMap != null) {
            if(local == null){
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.fillColor(Color.parseColor("#447755ff"));
                circleOptions.strokeColor(Color.TRANSPARENT);
                circleOptions.center(local);
                circleOptions.radius(radius);
                circle = mMap.addCircle(circleOptions);
            } else {
                circle.setCenter(local);
                circle.setRadius(radius);
            }
        }
    }
}
