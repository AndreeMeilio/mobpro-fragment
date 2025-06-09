package com.andreemeilio.tugasfragmentmobpro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public static final String JUDUL = "judul";
    public static final String ISI = "isi";
    public static final String TANGGAL = "tanggal";

    private String judul;
    private String isi;
    private String tanggal;

    public static DetailFragment newInstance(String judul, String isi, String tanggal) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(JUDUL, judul);
        args.putString(ISI, isi);
        args.putString(TANGGAL, tanggal);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            System.out.println(getArguments().getString(JUDUL));
            this.judul = getArguments().getString(JUDUL);
            this.isi = getArguments().getString(ISI);
            this.tanggal = getArguments().getString(TANGGAL);
        }
    }

    TextView detailJudul, detailIsi, detailTanggal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        detailJudul = view.findViewById(R.id.detail_judul);
        detailIsi = view.findViewById(R.id.detail_isi);
        detailTanggal = view.findViewById(R.id.detail_created_at);

        detailJudul.setText(this.judul);
        detailIsi.setText(this.isi);
        detailTanggal.setText(this.tanggal);

        return view;
    }
}