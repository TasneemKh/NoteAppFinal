package com.example.noteappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class noteView2 extends AppCompatActivity {
TextView Titnote,textArea_information,date;
ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view2);
        String Txt = getIntent().getStringExtra("Txt");
        String Title = getIntent().getStringExtra("Title");
        String date1 = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");
        Titnote=findViewById(R.id.Title);
        Titnote.setText(Title);
        textArea_information=findViewById(R.id.textArea_information);
        textArea_information.setText(Txt);
        date=findViewById(R.id.date);
        date.setText(date1);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noteView2.this, homePage.class);
                startActivity(intent);
            }
        });
    }
}
