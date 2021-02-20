package com.vhddev.task1;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    JsonArray profileList = null;
    JsonArray locationList = null;
    public static List<Profile> profiles = null;
    public static List<Location> locations = null;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatas();
        Fragment fragment = new ProfileFragment();

        profiles = new ArrayList<>();
        locations = new ArrayList<>();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.navigation_location:
                        fragment = new LocationFragment();
                        break;

                }

                if (fragment == null)
                {
                    Toast.makeText(MainActivity.this,"No fragment", Toast.LENGTH_SHORT).show();
                }

                return loadFragment(fragment);
            }
        });
        
/*        if (savedInstanceState == null)
        {
            setDefaultFragment();
            loadFragment(fragment);
        }*/

    }

    private void setDefaultFragment()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new ProfileFragment());
        transaction.commit();
    }

    private void getDatas() {

        Call<JsonObject> call = RetrofitClient.getInstance().getMyApi().getData();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                profileList = response.body().getAsJsonArray("success");
                locationList = response.body().getAsJsonArray("location");
                if (profileList.size() != 0)
                {
                    for (int i=0; i < profileList.size(); i++)
                    {
                        JsonObject jsonObject = (JsonObject) profileList.get(i);
                        profiles.add(new Profile(jsonObject.get("id").toString(),
                                jsonObject.get("name").toString(),
                                jsonObject.get("contact").toString(),
                                jsonObject.get("categoryid").toString(),
                                jsonObject.get("description").toString(),
                                jsonObject.get("address").toString(),
                                jsonObject.get("empcode").toString()));
                    }
                }

                if (locationList.size() != 0)
                {
                    for (int j=0; j < locationList.size(); j++)
                    {
                        JsonObject jsonObject = (JsonObject) locationList.get(j);
                        locations.add(new Location(jsonObject.get("id").toString(),
                                jsonObject.get("code").toString(),
                                jsonObject.get("lat").toString(),
                                jsonObject.get("longg").toString(),
                                jsonObject.get("created_at").toString()));
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean loadFragment(Fragment fragment){

        if (fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}