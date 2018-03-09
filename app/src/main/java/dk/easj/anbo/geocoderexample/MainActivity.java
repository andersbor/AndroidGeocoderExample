package dk.easj.anbo.geocoderexample;

import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application application = getApplication();
        Geocoder geocoder = new Geocoder(application);
        int maxResults = 4;

        // Geocoding: location name -> (latitude, longitude)
        String locationName =  "Jernbanegade 3A, 4000 Roskilde, Denmark";
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
        double latitude = 55.6389;
        double longitude = 12.0880;
        TextView viewReverseResult = findViewById(R.id.main_position_textview);
        viewReverseResult.setText("Lat: " + latitude + " Long:" + longitude + "\n");
        try {

            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, maxResults);
            for (Address addr : addressList) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < addr.getMaxAddressLineIndex(); i++) {
                    sb.append(addr.getAddressLine(i));
                    sb.append(", ");
                }
                Log.d("MINE", sb.toString());
                viewReverseResult.append(sb + "\n");
                //viewReverseResult.setText(addr.getPremises() + " " + addr.getFeatureName() + " " + addr.getLocality() + ", " + addr.getCountryName());
            }
            // view.setText(addressList.toString());
        } catch (IOException e) {
            viewReverseResult.setText(e.getMessage());
        }
    }
}
