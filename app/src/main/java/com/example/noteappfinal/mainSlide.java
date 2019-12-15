package com.example.noteappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainSlide extends AppCompatActivity {
    Button signIn,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slide);
        //sign in button
        signIn=findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), signIn.class);
                startActivity(i);
            }
        });
        //sign up button
        signUp=findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), signUp.class);
                startActivity(i2);
            }
        });

    }
}
