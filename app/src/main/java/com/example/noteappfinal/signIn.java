package com.example.noteappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signIn extends AppCompatActivity  implements View.OnClickListener {
     FirebaseAuth mAuth;
     FirebaseAuth.AuthStateListener mAuthStateListener;
     EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Toast.makeText(getApplicationContext(),"you need to sign up",Toast.LENGTH_SHORT).show();
                }
            }
        };
        //add action for buttons
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.forgetpass).setOnClickListener(this);
        findViewById(R.id.movSignUp).setOnClickListener(this);
        findViewById(R.id.logIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(this, signIn.class));
                break;
            case R.id.forgetpass:
                startActivity(new Intent(this, forgetpass.class));
                break;
            case R.id.movSignUp:
                startActivity(new Intent(this, signUp.class));
                break;
            case R.id.logIn:
                userLogIn();
                break;
            default:
                break;
        }

    }
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);

    }
    private void userLogIn(){
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        String email1=email.getText().toString();
        String pass1=pass.getText().toString();
        if(TextUtils.isEmpty(email1)|| TextUtils.isEmpty(pass1)){
            Toast.makeText(getApplicationContext(),"please fill txt fields",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"sign in successful",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"there is a problem",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}
