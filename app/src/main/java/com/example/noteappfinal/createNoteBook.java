package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class createNoteBook extends AppCompatActivity {
    ImageButton button,imageButton7;
    Button savebutton ;
    ArrayList<noteBook> list;
    ConstraintLayout ConstraintLayout;
    int DefaultColor ;
    DatabaseReference reference;
    TextView cancel,TitNotebook;
    FirebaseUser user;
    String uid,note_Tit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_book);
        ConstraintLayout = (ConstraintLayout) findViewById(R.id.Con);
        mAuth = FirebaseAuth.getInstance();

        cancel =findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(new Intent(createNoteBook.this, homePage.class)); }
        });
        TitNotebook=findViewById(R.id.TitNotebook);

        button = (ImageButton) findViewById(R.id.imageButton2);
        DefaultColor = ContextCompat.getColor(createNoteBook.this, R.color.colorPrimary);
        //color picker button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenColorPickerDialog(false);
            }
        });



        savebutton =findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNoteBook();
            }
        });
    }

    private void saveNoteBook() {
        note_Tit=TitNotebook.getText().toString();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        Map<String,Object> data = new HashMap<>();
        data.put("image","@drawable/rounded_button");
        data.put("ImageTxt",note_Tit);
       // String x=new Date().getTime();
        data.put("createdAt",new Date().getTime());
        String id= UUID.randomUUID().toString();
        //FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid).dataSnapshot.getChildrenCount();
        if(note_Tit != null) {
            FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid).child(id).setValue(data)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "on Failuer", Toast.LENGTH_SHORT).show();
                            Log.d("error", e.getLocalizedMessage());
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(createNoteBook.this, Main2Activity.class);
                            startActivity(intent);
                        }
                    });

        }else{
            Toast.makeText(createNoteBook.this, "please , enter your notebookTitle", Toast.LENGTH_SHORT).show();

        }
    }

    private void OpenColorPickerDialog(boolean AlphaSupport) {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(createNoteBook.this, DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {

                DefaultColor = color;
                ConstraintLayout.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {

                Toast.makeText(createNoteBook.this, "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();
    }
}





