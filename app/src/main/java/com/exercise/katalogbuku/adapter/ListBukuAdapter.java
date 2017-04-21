package com.exercise.katalogbuku.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.katalogbuku.MainActivity;
import com.exercise.katalogbuku.R;
import com.exercise.katalogbuku.object.Buku;

import java.util.ArrayList;

/**
 * Created by anjas on 08/04/17.
 */

public class ListBukuAdapter extends RecyclerView.Adapter<ListBukuViewHolder> {

    private Context context;
    public static ArrayList<Buku> data;
    public ArrayList<Buku> resultData;

    public ListBukuAdapter(Context context, ArrayList<Buku> data) {
        super();
        this.context = context;
        this.data = data;
        this.resultData = new ArrayList<>();
        this.resultData.addAll(data);
    }

    @Override
    public ListBukuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku, null);
        ListBukuViewHolder view = new ListBukuViewHolder(layoutView);
        return view;
    }

    @Override
    public void onBindViewHolder(ListBukuViewHolder holder, int position) {
        holder.judulBukuView.setText(resultData.get(position).getJudul_buku());
        holder.pengarangView.setText(resultData.get(position).getPengarang());
        holder.penerbitView.setText(resultData.get(position).getPenerbit());
    }

    @Override
    public int getItemCount() {
        return (null != resultData ? resultData.size() : 0);
    }

    public void filter(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                resultData.clear();
                if (TextUtils.isEmpty(text)) {
                    resultData.addAll(data);
                } else {
                    for (Buku item : data) {
                        if (item.getJudul_buku().toLowerCase().contains(text.toLowerCase())) {
                            resultData.add(item);
                        }
                    }
                }
                ((MainActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }
}
