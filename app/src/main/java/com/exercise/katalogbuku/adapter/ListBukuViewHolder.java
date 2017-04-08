package com.exercise.katalogbuku.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.exercise.katalogbuku.DetailActivity;
import com.exercise.katalogbuku.R;

/**
 * Created by anjas on 08/04/17.
 */

public class ListBukuViewHolder extends RecyclerView.ViewHolder {

    TextView judulBukuView, pengarangView, penerbitView;

    public ListBukuViewHolder(View itemView) {
        super(itemView);

        judulBukuView = (TextView)itemView.findViewById(R.id.judulBukuView);
        pengarangView = (TextView)itemView.findViewById(R.id.pengarangView);
        penerbitView = (TextView)itemView.findViewById(R.id.penerbitView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mengambil posisi item
                int position = getAdapterPosition();

                //Membuat Intent untuk menjalankan activity baru
                Intent intent = new Intent(view.getContext(), DetailActivity.class);

                //Mengirim data antar intent
                intent.putExtra("judul_buku", ListBukuAdapter.data.get(position).getJudul_buku());
                intent.putExtra("pengarang", ListBukuAdapter.data.get(position).getPengarang());
                intent.putExtra("penerbit", ListBukuAdapter.data.get(position).getPenerbit());
                intent.putExtra("deskripsi", ListBukuAdapter.data.get(position).getDeskripsi());

                //Menjalankan intent
                view.getContext().startActivity(intent);
            }
        });
    }


}

