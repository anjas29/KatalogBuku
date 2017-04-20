package com.exercise.katalogbuku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText emailView, passwordView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Menginisialisasi editText email, password dan button login
        emailView = (EditText)findViewById(R.id.emailView);
        passwordView = (EditText)findViewById(R.id.passwordView);
        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lihat method public void postLogin()
                postLogin();
            }
        });
    }

    public void postLogin(){
        final String email, password;
        email = emailView.getText().toString();
        password = passwordView.getText().toString();

        //Perhatikan Method.POST dan alamat webservice
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://tokobuku-stembayo.esy.es/login_member.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DEBUGS", response);
                        try{
                            //mengubah string menjadi jsonObject
                            JSONObject object = new JSONObject(response);

                            //Mendapatkan string dari status
                            String status = object.getString("status");


                            if(status.equalsIgnoreCase("success")){
                                //Mendapatkan data Nama, alamat, email, dll
                                //Mengambil Object "data : ..."
                                JSONObject data = object.getJSONObject("data");
                                String dataNama = data.getString("nama_member");
                                String dataEmail = data.getString("email");
                                String dataAlamat = data.getString("alamat");
                                String dataJenisKelamin = data.getString("jenis_kelamin");

                                //Toast pemberitahuan jika Berhasil
                                Toast.makeText(LoginActivity.this, object.getString("message"),Toast.LENGTH_LONG).show();

                                //Menyimpan data pada SharedPreferences, Perhatikan KATALOG_BUKU_DATA
                                SharedPreferences sharedPreferences = getSharedPreferences("KATALOG_BUKU_DATA", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("login", true);
                                editor.putString("nama_member", dataNama);
                                editor.putString("email", dataEmail);
                                editor.putString("alamat", dataAlamat);
                                editor.putString("jenis_kelamin", dataJenisKelamin);

                                //Menyimpan perubahan dengan cara commit
                                editor.commit();

                                //Berpindah ke Activity Main
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                //Toast pemberitahuan jika login gagal
                                Toast.makeText(LoginActivity.this, object.getString("message"),Toast.LENGTH_LONG).show();
                            }

                        }catch (Exception e){
                            Log.d("DEBUGS", e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("DEBUGS", error.toString());
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //menambahkan paramter email dan password
                params.put("email", email);
                params.put("password",password);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
