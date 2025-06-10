package com.andreemeilio.tugasfragmentmobpro;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class InputFragment extends Fragment {
    Button inputSubmitButton;

    EditText judulEditText, isiEditText;

    public static final String INPUT_JUDUL = "input_judul";
    public static final String INPUT_ISI = "input_isi";
    public static final String INPUT_TANGGAL = "input_tanggal";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);

        judulEditText = view.findViewById(R.id.judulEditText);
        isiEditText = view.findViewById(R.id.isiEditText);

        inputSubmitButton = ((MainActivity) getActivity()).mainButton;
        inputSubmitButton.setText(R.string.submit_button);
        inputSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultJudul = judulEditText.getText().toString();
                String resultIsi = isiEditText.getText().toString();

                Locale locale = new Locale("id", "ID");
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);

                String resultTanggal = dateFormat.format(new Date());

                FragmentManager fragmentManager = getParentFragmentManager();
                Bundle result = new Bundle();
                result.putString(INPUT_JUDUL, resultJudul);
                result.putString(INPUT_ISI, resultIsi);
                result.putString(INPUT_TANGGAL, resultTanggal);

                getParentFragmentManager().setFragmentResult(ListFragment.REQUEST_NEW_DATA, result);
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}