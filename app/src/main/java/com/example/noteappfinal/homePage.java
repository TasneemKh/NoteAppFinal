package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity implements View.OnClickListener {
    List<NoteInfo> noteList = new ArrayList<NoteInfo>();
    RecyclerView note_rv;
    NoteAdapter NoteAdapter ;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViewById(R.id.addNotebookFix).setOnClickListener(this);
        findViewById(R.id.addNotebook).setOnClickListener(this);
      //  initData();
        note_rv = findViewById(R.id.note_rv);
        note_rv.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter = new NoteAdapter(this ,noteList );
        note_rv.setAdapter(NoteAdapter);

    }

    private void initData() {
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
              //  Log.d(TAG, "Value is: " + value);
                noteList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                    NoteInfo note = snapshot.getValue(NoteInfo.class);
                    noteList.add(note);

                }
                NoteAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(homePage.this, "Failed to read value.",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNotebookFix:
                //startActivity(new Intent(this, mainSlide.class));
                break;
            case R.id.addNotebook:
                //startActivity(new Intent(this, mainSlide.class));
                break;
            case R.id.logIn:
                break;
            default:
                break;
        }
    }
}
