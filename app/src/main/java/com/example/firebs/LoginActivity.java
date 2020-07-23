package com.example.firebs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailid;
    EditText passwordTX;
    Button loginBtns;
    Button asksignupBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailid = findViewById(R.id.emailTxt);
        passwordTX = findViewById(R.id.passwordTxt);
        loginBtns = findViewById(R.id.loginBtn);
        asksignupBtn = findViewById(R.id.askSignup);

        loginBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = emailid.getText().toString();
                String Password = passwordTX.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if(Email.isEmpty()){
                    emailid.setError("Please enter your email Id");
                    emailid.requestFocus();
                }else if(Password.isEmpty()){
                    passwordTX.setError("Please enter your password");
                    passwordTX.requestFocus();
                }else if(!Email.isEmpty() && !Password.isEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Succcessfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Failed - You Entered wrong details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
