package com.demon.polynote.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demon.polynote.NoteActivity;
import com.demon.polynote.R;
import com.demon.polynote.database.DatabaseHelper;
import com.demon.polynote.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Note> notesList;
    private List<Note> notesListFiltered;
    private NotesAdapterListener listener;

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                FilterResults results = new FilterResults();
                if (charString.isEmpty()) {
                    notesListFiltered = notesList;
                } else {
                    List<Note> filteredList = new ArrayList<>();
                    for (Note row : notesList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    notesListFiltered = filteredList;
                }


                results.values = notesListFiltered;
                results.count = notesListFiltered.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notesListFiltered = (ArrayList<Note>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView note;
        public ImageView image;
        public TextView dot;
        public TextView timestamp;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            note = view.findViewById(R.id.note);
            image = view.findViewById(R.id.img);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onNoteSelected(notesListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public NotesAdapter(Context context, List<Note> notesList, NotesAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.notesList = notesList;
        this.notesListFiltered = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.note.setText(note.getNote());
//        Glide.with(context).load(note.getImage()).into(holder.image);
//        Glide.with(context).load(note.getImage()).into(holder.image);

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));
        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

    public void changeDataset(List<Note> notes) {
        this.notesList = notes;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        DatabaseHelper db = new DatabaseHelper(context);
        // notify item added by position
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
        db.deleteNote(notesList.get(position));
        // removing the note from the list
        notesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Note note, int position) {
        notesList.add(position, note);
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertNote(notesList.get(position).getTitle(), notesList.get(position).getNote(), notesList.get(position).getImage());
        // notify item added by position
        Log.d("abc", db.toString());
        notifyItemInserted(position);
    }

//    public void updateNote(String title, String note, byte[] image) {
//
//        DatabaseHelper db = new DatabaseHelper(context);
//        // updating note text
//        n.setTitle(title);
//        n.setNote(note);
//        n.setImage(image);
//
//        // updating note in db
//        db.updateNote(n);
//
//        // refreshing the list
//        notesList.set(position, n);
//        notifyItemChanged(position);
//
//
//    }

    public void createNote(String title, String note, byte[] image) {
        // inserting note in db and getting
        // newly inserted note id
        DatabaseHelper db = new DatabaseHelper(context);
        long id = db.insertNote(title, note, image);

        // get the newly inserted note from db
        Note n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            notifyDataSetChanged();


        }
    }

    public interface NotesAdapterListener {
        void onNoteSelected(Note note);

    }

}
