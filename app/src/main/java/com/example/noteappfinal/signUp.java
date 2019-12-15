package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUp extends AppCompatActivity implements View.OnClickListener{
    TextView email,password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.movSignIn).setOnClickListener(this);
        findViewById(R.id.signUp1).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
            startActivity(new Intent(this,mainSlide.class));
            break;
            case R.id.movSignIn:
                startActivity(new Intent(this,signIn.class));
                break;
            case R.id.signUp1:
               // startActivity(new Intent(this,mainSlide.class));
                signUp();
                break;
            default:
                break;
        }



    }
    private void signUp(){
        email=findViewById(R.id.email2);
        password=findViewById(R.id.pass);
        String email1=email.getText().toString();
        String pass1=password.getText().toString();
        if(TextUtils.isEmpty(email1)|| TextUtils.isEmpty(pass1)) {
            Toast.makeText(getApplicationContext(),"fields are empty",Toast.LENGTH_SHORT).show();

        }else {
            mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext()," welcome our dear customer",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

}
