package dk.easj.anbo.geocoderexample;

import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MINE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application application = getApplication();
        Geocoder geocoder = new Geocoder(application);
        int maxResults = 4;

        // Geocoding: location name -> (latitude, longitude)
        String locationName =  "Maglegårdsvej 2, 4000 Roskilde, Denmark";
        TextView viewResult = findViewById(R.id.main_addresses_textview);
        viewResult.setText(locationName + "\n");
        try {
            List<Address> addressList = geocoder.getFromLocationName(locationName, maxResults);
            for (Address addr : addressList) {
                viewResult.append("Lat: " + addr.getLatitude() + " Long: " + addr.getLongitude());
            }
        } catch (IOException e) {
            viewResult.setText(e.getMessage());
        }

        // Reverse geocoding: (latitude, longitude) -> address(es)
        double latitude = 55.63;
        double longitude = 12.08;
        TextView viewReverseResult = findViewById(R.id.main_position_textview);
        viewReverseResult.setText("Lat: " + latitude + " Long:" + longitude + "\n");
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, maxResults);
            Log.d(LOG_TAG, addressList.toString());
            for (Address addr : addressList) {
                // Log.d(LOG_TAG, addr.toString());
                String addressLine = addr.getAddressLine(0);
                Log.d(LOG_TAG, addressLine);
                viewReverseResult.append(addressLine  + "\n");
                //viewReverseResult.setText(addr.getPremises() + " " + addr.getFeatureName() + " " + addr.getLocality() + ", " + addr.getCountryName());
            }
        } catch (IOException e) {
            viewReverseResult.setText(e.getMessage());
        }
    }
}
