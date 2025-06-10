package com.andreemeilio.tugasfragmentmobpro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    Button tambahNotesButton;

    RecyclerView listNotesRv;
    ListNotesAdapter adapterListNotes;

    ArrayList<NotesModel> dataNotes = new ArrayList<>();

    public static final String REQUEST_NEW_DATA = "requestNewData";

    public ListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener(REQUEST_NEW_DATA, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String inputJudul = result.getString(InputFragment.INPUT_JUDUL);
                String inputIsi = result.getString(InputFragment.INPUT_ISI);
                String inputTanggal = result.getString(InputFragment.INPUT_TANGGAL);

                NotesModel newData = new NotesModel(inputJudul, inputIsi, inputTanggal);
                dataNotes.add(newData);

                adapterListNotes.notifyItemChanged(dataNotes.size() - 1, dataNotes.size());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);



        listNotesRv = (RecyclerView) view.findViewById(R.id.listItemRv);
        listNotesRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapterListNotes = new ListNotesAdapter(dataNotes);
        adapterListNotes.setSetOnClick(new ListNotesAdapter.OnClickListener() {
            @Override
            public View.OnClickListener setOnClickListener(int position) {
                return new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        DetailFragment detailFragment = DetailFragment.newInstance(
                                dataNotes.get(position).getJudul(),
                                dataNotes.get(position).getIsi(),
                                dataNotes.get(position).getTanggal()
                        );

                        System.out.println(dataNotes.get(position).getTanggal());

                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container_view, detailFragment.getClass(), detailFragment.getArguments())
                                .setReorderingAllowed(true)
                                .addToBackStack("detailFragment")
                                .commit();
                    }
                };
            }
        });
        listNotesRv.setAdapter(adapterListNotes);

        tambahNotesButton = ((MainActivity) getActivity()).mainButton;
        tambahNotesButton.setText(R.string.tambah_notes);
        tambahNotesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, InputFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("inputFragment")
                        .commit();
            }
        });

        return view;
    }
}