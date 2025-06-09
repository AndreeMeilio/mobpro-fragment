package com.andreemeilio.tugasfragmentmobpro;

public class NotesModel {
    private String judul;
    private String isi;
    private String tanggal;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public NotesModel(String judul, String isi, String tanggal) {
        this.judul = judul;
        this.isi = isi;
        this.tanggal = tanggal;
    }
}
