package icomp.ufam.micaelyanlab1android.view;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import icomp.ufam.micaelyanlab1android.R;
import icomp.ufam.micaelyanlab1android.controller.api.APICountries;
import icomp.ufam.micaelyanlab1android.model.bean.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap ;
    private Button voltar, mudarpais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ActionBar actionBar = getActionBar();
        voltar= findViewById(R.id.voltarButton);
        mudarpais=findViewById(R.id.outropaisButton);



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mudarpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get random DB instance
            }
        });
    }

    public void getData(View view) {
        APICountries
            .getRestCountriesClient()
            .getCountries().enqueue(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                      if ( response.isSuccessful() ) {
                        List<Country> countryList = response.body();
                        for (Country country : countryList) System.out.println(country);
                        } else System.err.println( response.errorBody() );
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                            t.printStackTrace();
                    }
            });

    }




}
