package com.exercise.katalogbuku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    //komponen2 pada layout detail
    TextView judulBukuView, pengarangView, penerbitView, deskripsiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //mereferensikan komponen pada layout
        judulBukuView = (TextView)findViewById(R.id.listBukuView);
        pengarangView = (TextView)findViewById(R.id.pengarangView);
        penerbitView = (TextView)findViewById(R.id.penerbitView);
        deskripsiView = (TextView)findViewById(R.id.deskripsiView);

        //Mengambil Data dari Intent sebelumnya
        Bundle extras = getIntent().getExtras();
        String judul = extras.getString("judul_buku");
        String pengarang = extras.getString("pengarang");
        String penerbit = extras.getString("penerbit");
        String deskripsi = extras.getString("deskripsi");

        //Menampilkan data yang diambil melalui TextView
        judulBukuView.setText(judul);
        pengarangView.setText(pengarang);
        penerbitView.setText(penerbit);
        deskripsiView.setText(deskripsi);
    }
}
