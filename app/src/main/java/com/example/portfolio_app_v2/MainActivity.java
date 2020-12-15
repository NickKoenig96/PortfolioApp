package com.example.portfolio_app_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    RecyclerView PortfolioList;
    PortfolioAdapter adapter;
    List<Portfolio> allPortfolioItems ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allPortfolioItems = new ArrayList<>();

        PortfolioList = findViewById(R.id.PortfolioList);
        PortfolioList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PortfolioAdapter(this,allPortfolioItems);
        PortfolioList.setAdapter(adapter);
        getJsonData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.top_menuitem) {
            PortfolioList.smoothScrollToPosition(0);
            return true;
        }
        if(id == R.id.bottom_menuitem) {
            PortfolioList.smoothScrollToPosition(allPortfolioItems.size());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getJsonData() {
        String URL = "https://nickkoenig.site/api/portfolio";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest ayr = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i< response.length();i++){
                        JSONObject Portfolio = response.getJSONObject(i);

                        Portfolio v = new Portfolio();

                        v.setLink(Portfolio.getString("field_portfolio_link"));
                        v.setTitle(Portfolio.getString("title"));
                        v.setDescription(Portfolio.getString("body"));
                        v.setImage(Portfolio.getString("field_app_image"));

                        allPortfolioItems.add(v);
                        adapter.notifyDataSetChanged();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        requestQueue.add(ayr);
    }
}

