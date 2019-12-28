package com.example.noteappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class noteView2 extends AppCompatActivity {
TextView Titnote,textArea_information,date;
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
    }
}
