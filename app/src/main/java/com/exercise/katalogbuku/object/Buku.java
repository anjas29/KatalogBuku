package com.exercise.katalogbuku.object;


import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created by anjas on 07/04/17.
 */

public class Buku implements SortedListAdapter.ViewModel{
    private int id;
    private String judul_buku;
    private String pengarang;
    private String penerbit;
    private String deskripsi;

    public Buku(int id, String judul_buku, String pengarang, String penerbit, String deskripsi) {
        this.id = id;
        this.judul_buku = judul_buku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
