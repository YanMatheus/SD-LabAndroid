package rest.example.com.rest.api;

/**
 * Created by thiag_000 on 29/03/2017.
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import rest.example.com.rest.model.Team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiClient {
    private static WorldCupInterface worldCupService;

    public static WorldCupInterface getWorldCupClient() {
        if (worldCupService == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("http://worldcup.sfg.io")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            worldCupService = restAdapter.create(WorldCupInterface.class);
        }

        return worldCupService;
    }

    public interface WorldCupInterface {
        @GET("/teams/")
        Call<List<Team>> getTeams();
    }
}