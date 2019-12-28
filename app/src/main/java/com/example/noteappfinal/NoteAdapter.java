package com.example.noteappfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.noteVh> {
    Context context ;
    ArrayList<NoteInfo> Notes ;
    public NoteAdapter(Context context,ArrayList<NoteInfo> Notes ){
        this.context = context;
        this.Notes=Notes;
    }
    @NonNull
    @Override
    public noteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_layout ,parent , false );
        return new noteVh(view);
    }
    @Override
    public void onBindViewHolder(@NonNull noteVh holder, int position) {
        holder.setData(Notes.get(position));
       /* holder.noteId.setText(Notes.get(position).getId());
        holder.noteTitle.setText(Notes.get(position).getNoteTit());
        holder.noteTxt.setText(Notes.get(position).getNoteTxt());
        holder.noteDate.setText(Notes.get(position).getNoteDate());*/
  // Notes.get(position).getColor();
       // holder.noteCol.setColorFilter(Color.argb(255, 61, 16, 84));

    }
    @Override
    public int getItemCount() {return Notes.size();
    }

    class noteVh extends RecyclerView.ViewHolder {
        TextView noteDate,noteTitle,noteTxt,noteId;
        //ImageButton noteCol;
        public noteVh(@NonNull View itemView) {
            super(itemView);
            noteId = itemView.findViewById(R.id.noteId);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteTxt = itemView.findViewById(R.id.noteTxt);
          //  noteCol = itemView.findViewById(R.id.noteCol);
        }

        public void setData(final NoteInfo note) {
          //  std_id.setText(note.getId());
            noteTitle.setText(note.getNoteTit());
            noteDate.setText(note.getNoteDate().toString());
            noteTxt.setText(note.getNoteTxt());
           // noteCol.intint(note.getColor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, note.getNoteTit(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext() ,noteView2.class);
                    intent.putExtra("id",note.getId());
                    intent.putExtra("Title",note.getNoteTit());
                    intent.putExtra("Txt",note.getNoteTxt());
                    intent.putExtra("date",note.getNoteDate());
                    intent.putExtra("color",note.getColor());
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
