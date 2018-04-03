package rest.example.com.rest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import rest.example.com.rest.api.ApiClient;
import rest.example.com.rest.model.Team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ListActivity {
    private ArrayAdapter<String> adapter;
    private List<String> wordList;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                wordList);

        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public void getData(View view) {
        ApiClient.getWorldCupClient().getTeams().enqueue(new Callback<List<Team>>() {
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response ){
                if (response.isSuccessful() ) {
                    List<Team> teams = response.body();
                    wordList.clear();
                    for (Team team : teams) {
                        wordList.add(team.getCountry());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }
}
