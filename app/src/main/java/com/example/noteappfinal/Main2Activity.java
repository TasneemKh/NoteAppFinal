package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class Main2Activity extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<NoteInfo> list;
    private FirebaseAuth mAuth;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        String noteUid= uid+"frghj1542";
        findViewById(R.id.showAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, homePage.class));
            }
        });
        reference = FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid).child(noteUid);
        //.child("notes")
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<NoteInfo>();
                if  (dataSnapshot.getChildrenCount()!=0){
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.noteBackground);
                    linearLayout.setVisibility(View.INVISIBLE);
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        NoteInfo p = dataSnapshot1.getValue(NoteInfo.class);
                        list.add(p);
                    }
                    adapter = new NoteAdapter(Main2Activity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
