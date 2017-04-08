package com.exercise.katalogbuku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.katalogbuku.R;
import com.exercise.katalogbuku.object.Buku;

import java.util.ArrayList;

/**
 * Created by anjas on 08/04/17.
 */

public class ListBukuAdapter extends RecyclerView.Adapter<ListBukuViewHolder> {

    private Context context;
    public static ArrayList<Buku> data;

    public ListBukuAdapter(Context context, ArrayList<Buku> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public ListBukuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku, null);
        ListBukuViewHolder view = new ListBukuViewHolder(layoutView);
        return view;
    }

    @Override
    public void onBindViewHolder(ListBukuViewHolder holder, int position) {
        holder.judulBukuView.setText(data.get(position).getJudul_buku());
        holder.pengarangView.setText(data.get(position).getPengarang());
        holder.penerbitView.setText(data.get(position).getPenerbit());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
