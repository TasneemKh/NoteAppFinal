package com.example.noteappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class confirmationMessage extends AppCompatActivity {
Button goToEmail;
ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_message);
        goToEmail=findViewById(R.id.goToEmail);
        goToEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent mailClient = new Intent(Intent.ACTION_VIEW);
                    mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail");
                    startActivity(mailClient); }
                catch (Exception e) {
                    Toast.makeText(confirmationMessage.this,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(confirmationMessage.this, signIn.class));
                 }catch (Exception e) {
                    Toast.makeText(confirmationMessage.this,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
