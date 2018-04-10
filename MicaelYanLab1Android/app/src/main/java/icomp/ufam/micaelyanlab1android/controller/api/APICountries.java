package icomp.ufam.micaelyanlab1android.controller.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import icomp.ufam.micaelyanlab1android.model.bean.Country;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class APICountries {

    private static RestCountriesInterface restCountriesService;
    private static final String BASE_ENDPOINT = "http://restcountries.eu";
    private static final String ENDPOINT_VERSION = "/rest/v1";

    public interface RestCountriesInterface {
        @GET(ENDPOINT_VERSION + "/all")
        Call<List<Country>> getCountries();
    }

    public static RestCountriesInterface getRestCountriesClient() {
        if (restCountriesService == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_ENDPOINT)
                    .addConverterFactory( GsonConverterFactory.create(gson) )
                    .build();

            restCountriesService = restAdapter.create(RestCountriesInterface.class);
        }

        return restCountriesService;
    }

}
