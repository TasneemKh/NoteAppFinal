package com.example.noteappfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoteBookadapter  extends RecyclerView.Adapter<NoteBookadapter.NoteBookVh> {
    Context context ;
    ArrayList<noteBook> noteBooks ;
    public NoteBookadapter( Context context,ArrayList<noteBook> noteBooks)
    {
        this.context = context;
        this.noteBooks = noteBooks;
    }
    @NonNull
    @Override
    public NoteBookVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_book_layout ,parent , false );
        return new NoteBookadapter.NoteBookVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteBookadapter.NoteBookVh holder, int position) {
       // holder.image.setText(noteBook.get(position).getNoteBookPic());
        holder.ImageTxt.setText(noteBooks.get(position).getImageTxt());
       // Picasso.get().load(noteBooks.get(position).getImage()).into(holder.image);
       // image.setImageResource(R.drawable.monkey);


    }

    @Override
    public int getItemCount() {
        return noteBooks.size();
    }



    class NoteBookVh extends RecyclerView.ViewHolder {
        TextView ImageTxt;
        ImageView image;

        public NoteBookVh(@NonNull View itemView) {
            super(itemView);
            ImageTxt=itemView.findViewById(R.id.ImageTxt);
            image=itemView.findViewById(R.id.image);
        }
        public void setData(final noteBook note) {
           // image.setImageResource(note.getNoteBookPic().toString());
            ImageTxt.setText(note.getImageTxt().toString());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, note.getImageTxt(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext() ,noteBookView.class);
                    intent.putExtra("image",note.getImage());
                    intent.putExtra("ImageTxt",note.getImageTxt());
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
