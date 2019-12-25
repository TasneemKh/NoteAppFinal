package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgetpass extends AppCompatActivity {
    ImageButton back;
    Button recover;
    TextView email_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        //return back to sign in
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), signIn.class);
                startActivity(i);
            }
        });

        //recover pass using email

        recover=findViewById(R.id.recover);
        recover.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                email_rec=findViewById(R.id.email_rec);
                String emailAddress = email_rec.getText().toString();
                auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                               // Log.d(TAG, "Email sent.");
                                Toast.makeText(getApplicationContext(), "Please ,review your email", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), confirmationMessage.class);
                                startActivity(i);
                            }else{
                              //  Toast.makeText(getApplicationContext(), "Please ,enter a valid email", Toast.LENGTH_LONG).show();
                                Log.d("MEDIA_PLAYER", new Exception().getMessage());
                                Toast.makeText(getApplicationContext(), "oops", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
    }
}
