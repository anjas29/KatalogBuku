package com.exercise.katalogbuku;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.exercise.katalogbuku.adapter.ListBukuAdapter;
import com.exercise.katalogbuku.object.Buku;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView listBuku;
    ArrayList<Buku> data;
    ListBukuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mengambil tampilan RecyclerView
        listBuku = (RecyclerView)findViewById(R.id.listBukuView);

        //Mengambil data dari database internet
        getData();
    }

    public void getData(){
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                "http://tokobuku-stembayo.esy.es/list_buku.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("DEBUGS", response.toString());
                try {
                    data = new ArrayList<Buku>();
                    for (int i=0; i<response.length();i++){
                        JSONObject object = response.getJSONObject(i);
                        int id = object.getInt("id");
                        String judul_buku = object.getString("judul_buku");
                        String pengarang =object.getString("pengarang");
                        String penerbit = object.getString("penerbit");
                        String deskripsi = object.getString("deskripsi");

                        data.add(new Buku(id, judul_buku, pengarang, penerbit, deskripsi));
                    }
                    //Menambahkan data kedalam RecyclerView
                    LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    listBuku.setHasFixedSize(true);

                    listBuku.setLayoutManager(layoutManager);

                    adapter = new ListBukuAdapter(MainActivity.this, data);

                    listBuku.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("DEBUGS", "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        return true;
    }
}
