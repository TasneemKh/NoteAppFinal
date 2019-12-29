package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class notebooksPage extends AppCompatActivity {
    RecyclerView recyclerView0;
    ArrayList<noteBook> list0;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    NoteBookadapter noteBookAdapter;
    NoteBookadapter Adapter;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebooks_page);
       /* GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(notebooksPage.this, "Image Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });*/
        String Txt = getIntent().getStringExtra("image");
        String Title = getIntent().getStringExtra("ImageTxt");
        //String id = getIntent().getStringExtra("id");
       /* textView9=findViewById(R.id.textView9);
        textView9.setText(Title);
        textArea_information=findViewById(R.id.textArea_information);
        textArea_information.setText(Txt);
        date=findViewById(R.id.date);
        date.setText(date1);*/
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        recyclerView0 =findViewById(R.id.recycleNoteBook);
        recyclerView0.setLayoutManager(new LinearLayoutManager(this));
        String uid = user.getUid();
        /*reference = FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid).child(id).child("notes");
        //.child("notes")
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list0 = new ArrayList<NoteInfo>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    NoteInfo p = dataSnapshot1.getValue(NoteInfo.class);
                    list0.add(p);
                }
                Adapter = new NoteAdapter(notebooksPage.this, list0);
                recyclerView0.setAdapter(Adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(notebooksPage.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });*/
        reference = FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid);
        //.child("notes")
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list0 = new ArrayList<noteBook>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    noteBook p = dataSnapshot1.getValue(noteBook.class);
                    list0.add(p);
                }
                noteBookAdapter = new NoteBookadapter(notebooksPage.this, list0);
                recyclerView0.setAdapter(noteBookAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(notebooksPage.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
