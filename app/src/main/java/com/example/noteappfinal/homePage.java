package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference reference;
    RecyclerView recyclerView,recyclerView0;
    ArrayList<NoteInfo> list;
    ArrayList<noteBook> list0;
    private FirebaseAuth mAuth;
    NoteAdapter adapter;
    NoteBookadapter noteBookAdapter;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        findViewById(R.id.addNotebookFix).setOnClickListener(this);
        findViewById(R.id.addNotebook).setOnClickListener(this);
        findViewById(R.id.showAll).setOnClickListener(this);
        findViewById(R.id.showAll2).setOnClickListener(this);
        //  initData();
    /*    note_rv = findViewById(R.id.note_rv);
        note_rv.setLayoutManager(new LinearLayoutManager(this));
       // NoteAdapter = new NoteAdapter(this ,noteList );
        note_rv.setAdapter(NoteAdapter);*/
        recyclerView = (RecyclerView) findViewById(R.id.note_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView0 =findViewById(R.id.Notebookx);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(homePage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView0.setLayoutManager(horizontalLayoutManagaer);
        initData0();
        initData1();


    }

    private void initData1() {
        reference = FirebaseDatabase.getInstance().getReference().child("notes").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<NoteInfo>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    NoteInfo p = dataSnapshot1.getValue(NoteInfo.class);
                    list.add(p);
                }
                adapter = new NoteAdapter(homePage.this, list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(homePage.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initData0() {
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
                noteBookAdapter = new NoteBookadapter(homePage.this, list0);
                recyclerView0.setAdapter(noteBookAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(homePage.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNotebookFix:
                startActivity(new Intent(this, noteView.class));
                break;
            case R.id.addNotebook:
                startActivity(new Intent(this, createNoteBook.class));
                break;
            case R.id.showAll:
                startActivity(new Intent(this, notebooksPage.class));
                break;
            case R.id.showAll2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}