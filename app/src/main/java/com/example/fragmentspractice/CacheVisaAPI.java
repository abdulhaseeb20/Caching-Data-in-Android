package com.example.fragmentspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CacheVisaAPI extends AppCompatActivity
{
    private static final String PREF_NAME = "MySharedPref";

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private MyModel destinationList;
    private ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    private ArrayList<MyModel> myModelsArrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_visa_api);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.visaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        myModelsArrList = getVisaList();
        if (myModelsArrList == null)
        {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "arraylist is not saved", Toast.LENGTH_SHORT).show();
            myModelsArrList = new ArrayList<>();
            apiCall(myModelsArrList);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "arraylist is saved", Toast.LENGTH_SHORT).show();
            for (MyModel model : myModelsArrList)
            {
                Log.e("checkLocalData: ", model.toString());
            }
            myAdapter = new MyAdapter(myModelsArrList);
            recyclerView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
    }

    void apiCall(ArrayList<MyModel> myModelsArrList)
    {
        AndroidNetworking.get("https://dashboard.mosafir.pk/api/visa/countries")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // Hide the loader when response is received
                        progressBar.setVisibility(View.GONE);
                        // Show the RecyclerView and TextView
                        recyclerView.setVisibility(View.VISIBLE);
                        Log.d("CHECK", "Response OK"+ response.toString());
                        try
                        {
                            JSONArray citiesArray = response.getJSONArray("country_list");
                            for (int i = 0; i < citiesArray.length(); i++)
                            {
                                JSONObject cityObject = citiesArray.getJSONObject(i);
                                String id = cityObject.getString("country_id");
                                String name = cityObject.getString("country_name");
                                String tag = cityObject.getString("tag_name");

                                destinationList = new MyModel(id, name, tag);
                                myModelsArrList.add(destinationList);
                                Log.e("CHECK", "Fetching Data!!!");
                            }
                            Log.e("CHECK_Size", String.valueOf(myModelsArrList.size()));
                            myAdapter = new MyAdapter( myModelsArrList);
                            recyclerView.setAdapter(myAdapter);
                            myAdapter.notifyDataSetChanged();

                            saveData(myModelsArrList);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        Log.d("CHECK", "Response OK"+ response.toString());
                    }

                    @Override
                    public void onError(ANError error)
                    {
                        // handle error
                        Log.d("CHECK", "Error!!!", error);
                    }
                });
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void saveData(ArrayList<MyModel> dataModel)
    {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(dataModel);
        editor.putString("countryList", json);
        editor.apply(); // Don't forget to apply changes
    }
    // Retrieve ArrayList from SharedPreferences
    public ArrayList<MyModel> getVisaList()
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("countryList", null);
        Type type = new TypeToken<ArrayList<MyModel>>() {}.getType();
        return gson.fromJson(json, type);
    }
}