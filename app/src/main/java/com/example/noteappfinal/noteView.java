package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class noteView extends AppCompatActivity {
TextView Title,textArea_information,date;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    ArrayList<NoteInfo> list;
    NoteAdapter adapter;
    androidx.constraintlayout.widget.ConstraintLayout ConstraintLayout;
    Button Save;
    int DefaultColor ;
    ImageButton back,button;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        ConstraintLayout = (ConstraintLayout) findViewById(R.id.Con);
        mAuth = FirebaseAuth.getInstance();
        date=findViewById(R.id.date);
        textArea_information=findViewById(R.id.textArea_information);
        Title=findViewById(R.id.Title);
        Save=findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noteView.this, homePage.class);
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButton5);
        DefaultColor = ContextCompat.getColor(noteView.this, R.color.colorPrimary);
        //color picker button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenColorPickerDialog(false);
            }
        });


       /* String Txt = getIntent().getStringExtra("Txt");
        String Title = getIntent().getStringExtra("Title");
        String date1 = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");
        textView9=findViewById(R.id.textView9);
        textView9.setText(Title);
        textArea_information=findViewById(R.id.textArea_information);
        textArea_information.setText(Txt);
        date=findViewById(R.id.date);
        date.setText(date1);*/
    }
        private void saveNote() {
            String tit=Title.getText().toString();
            String txt=textArea_information.getText().toString();
            String id= UUID.randomUUID().toString();
            user = mAuth.getCurrentUser();
            String uid = user.getUid();
            Map<String,Object> data = new HashMap<>();
            data.put("noteTit",tit);
            data.put("noteTxt",txt);
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            data.put("noteDate",strDate);
            data.put("id",id);
            data.put("color","#ccc");
            //FirebaseDatabase.getInstance().getReference().child("notebooks").child(uid).dataSnapshot.getChildrenCount();
            if(tit != null && txt != null ) {
                FirebaseDatabase.getInstance().getReference().child("notes").child(uid).child(id).setValue(data)
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
                                Intent intent = new Intent(noteView.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });

            }else{
                Toast.makeText(noteView.this, "please , enter your notebookTitle", Toast.LENGTH_SHORT).show();

            }
        }

    private void OpenColorPickerDialog(boolean AlphaSupport) {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(noteView.this, DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {

                DefaultColor = color;
                ConstraintLayout.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {

                Toast.makeText(noteView.this, "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();
    }
}
