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
import com.google.firebase.auth.FirebaseUser;

public class signIn extends AppCompatActivity  implements View.OnClickListener {
    private FirebaseAuth mAuth;
     FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText emailTxt,passTxt;
   // private ProgressBar progressBar;
    Button logIn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
       /* if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }*/
        if (mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(signIn.this , homePage.class);
            startActivity(intent);
        }
        initializeUI();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.forgetpass).setOnClickListener(this);
        findViewById(R.id.movSignUp).setOnClickListener(this);
       // findViewById(R.id.logIn).setOnClickListener(this);

    }
    private void loginUserAccount() {
        //progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTxt.getText().toString();
        password = passTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                          //  progressBar.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(signIn.this, homePage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                           // progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void initializeUI() {
        emailTxt = findViewById(R.id.email);
        passTxt = findViewById(R.id.pass);

        logIn = findViewById(R.id.logIn);
        //progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(this, mainSlide.class));
                break;
            case R.id.forgetpass:
                startActivity(new Intent(this, forgetpass.class));
                break;
            case R.id.movSignUp:
                startActivity(new Intent(this, signUp.class));
                break;
            case R.id.logIn:
                loginUserAccount();
                break;
            default:
                break;
        }

    }/*
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
        emailTxt=findViewById(R.id.email);
        passTxt=findViewById(R.id.pass);
        String email1=emailTxt.getText().toString();
        String pass1=passTxt.getText().toString();
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
                        Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }*/
}
