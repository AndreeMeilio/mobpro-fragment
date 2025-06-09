package com.andreemeilio.tugasfragmentmobpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.ListNotesViewHolder> {

    private ArrayList<NotesModel> listNotes;
    public ArrayList<NotesModel> getListNotes(){
        return this.listNotes;
    }

    private OnClickListener setOnClick;
    public void setSetOnClick(OnClickListener setOnClick) {
        this.setOnClick = setOnClick;
    }

    interface OnClickListener {
        public View.OnClickListener setOnClickListener(int position);
    }

    ListNotesAdapter(ArrayList<NotesModel> data){
        this.listNotes = data;
    }

    @NonNull
    @Override
    public ListNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_notes, parent, false);
        return new ListNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNotesViewHolder holder, int position) {
        NotesModel data = (NotesModel) this.listNotes.get(position);
        holder.item_notes_judul.setText(data.getJudul());
        holder.item_notes_tanggal.setText(data.getTanggal());

        if (this.setOnClick != null){
            holder.itemView.setOnClickListener(setOnClick.setOnClickListener(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.listNotes.size();
    }

    public static class ListNotesViewHolder extends RecyclerView.ViewHolder{
        TextView item_notes_judul, item_notes_tanggal;

        ListNotesViewHolder(View view){
            super(view);

            item_notes_judul = view.findViewById(R.id.item_notes_judul);
            item_notes_tanggal = view.findViewById(R.id.item_notes_tanggal);
        }
    }
}
