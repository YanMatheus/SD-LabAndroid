package icomp.ufam.micaelyanlab1android.view;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.activeandroid.ActiveAndroid;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Random;

import icomp.ufam.micaelyanlab1android.R;
import icomp.ufam.micaelyanlab1android.controller.api.APICountries;
import icomp.ufam.micaelyanlab1android.model.bean.Country;
import icomp.ufam.micaelyanlab1android.model.dao.DAOCountry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap ;
    private Button voltar, mudarpais;
    private List<Country> countryList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*
        try {
            if ( DAOCountry.getCountries().isEmpty() ) {
                getData();
            }
        } catch (NullPointerException ex) {
            System.err.println("erro ao recuperar countries do BD");
        }
        */
        getData();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ActionBar actionBar = getActionBar();
        voltar = findViewById(R.id.voltarButton);
        mudarpais = findViewById(R.id.outropaisButton);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mudarpais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countryList != null) {
                    System.out.println("asdasdasd");
                    Random random = new Random();
                    int randomInt = random.nextInt(countryList.size() - 1);

                    Country randomCountry = countryList.get(randomInt);
                    LatLng countryLatlng = new LatLng(
                            randomCountry.getLatlng().get(0),
                            randomCountry.getLatlng().get(1)
                    );

                    Marker randomCountryMarker = mMap.addMarker(new MarkerOptions()
                            .position(countryLatlng)
                            .title(randomCountry.getName())
                            .snippet("population: " + randomCountry.getPopulation()));

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(countryLatlng, 20));
                }
            }
        });

        LatLng ufam = new LatLng(-3.0890975,-59.9657044);
        mMap.addMarker(new MarkerOptions().position(ufam).title("IComp UFã"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ufam));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }



    public void getData() {
        APICountries
            .getRestCountriesClient()
            .getCountries().enqueue(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                        if ( response.isSuccessful() ) {
                            countryList = response.body();

                            ActiveAndroid.beginTransaction();
                            try {
                                for (Country country : countryList) {
                                DAOCountry daoCountry = new DAOCountry(country);
                                daoCountry.save();
                            }
                                ActiveAndroid.setTransactionSuccessful();
                            } finally {
                                ActiveAndroid.endTransaction(); // commit
                            }

                        } else System.err.println( response.errorBody() );
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                            t.printStackTrace();
                    }
            });

    }




}
