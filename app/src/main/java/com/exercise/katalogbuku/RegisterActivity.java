package com.exercise.katalogbuku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText namaMemberView, emailView, passwordView, alamatView;
    RadioButton lakiRadioView, perempuanRadioView;
    Button registerButton;

    TextView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Menginisialisasi editText, radioButton dan Button
        namaMemberView = (EditText)findViewById(R.id.namaMemberView);
        emailView = (EditText)findViewById(R.id.emailView);
        passwordView = (EditText)findViewById(R.id.passwordView);
        alamatView = (EditText)findViewById(R.id.alamatView);
        lakiRadioView = (RadioButton)findViewById(R.id.lakiRadioView);
        perempuanRadioView = (RadioButton)findViewById(R.id.perempuanRadioView);
        registerButton = (Button)findViewById(R.id.registerButton);

        //Menambahkan action jika button register diklik
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lihat method public void postRegister()
                postRegister();
            }
        });

        loginView = (TextView)findViewById(R.id.loginView);
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void postRegister(){
        //Nama variabel yang dibutuhkan
        final String namaMember, email, password, alamat, jenisKelamin;
        
        namaMember = namaMemberView.getText().toString();
        email = emailView.getText().toString();
        password = passwordView.getText().toString();
        alamat = alamatView.getText().toString();

        //mendapatkan input dari radio button
        if(lakiRadioView.isChecked()){
            jenisKelamin = "Laki-laki";
        }else{
            jenisKelamin = "Perempuan";
        }

        //perhatikan Method.POST dan alamat webservice
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://tokobuku-stembayo.esy.es/register_member.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DEBUGS", response);
                        try{
                            //mengubah string menjadi jsonObject
                            JSONObject object = new JSONObject(response);

                            //Mendapatkan string dari status
                            String status = object.getString("status");

                            //Jika registrasi berhasil
                            if(status.equalsIgnoreCase("success")){
                                //menampilkan pesan berhasil
                                Toast.makeText(RegisterActivity.this, object.getString("message"),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                //menampilkan pesan gagal
                                Toast.makeText(RegisterActivity.this, object.getString("message"),Toast.LENGTH_LONG).show();
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
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //menambahkan parameter post, nama put sama dengan nama variabel di webservice PHP
                params.put("nama_member",namaMember);
                params.put("email", email);
                params.put("password",password);
                params.put("jenis_kelamin",jenisKelamin);
                params.put("alamat",alamat);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}


