package com.job.JobAssist;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    DatabaseReference myRef;
    EditText emailid,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailid=(EditText)findViewById(R.id.et_signin_id);
        pwd=(EditText)findViewById(R.id.et_signin_pwd);
        mAuth= FirebaseAuth.getInstance();
        findViewById(R.id.tv_signin).setOnClickListener(this);
        findViewById(R.id.btn_signin).setOnClickListener(this);




    }


    private void userLogin(){


        String email = emailid.getText().toString().trim();
        String password = pwd.getText().toString().trim();


        if (email.isEmpty()){
            emailid.setError("Email is required");
            emailid.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailid.setError("Please enter valid email");
            emailid.requestFocus();
            return;
        }

        if (password.isEmpty()){
            pwd.setError("Password is required");
            pwd.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Loggin In.........", Toast.LENGTH_SHORT).show();
                    emailid.setText("");
                    pwd.setText("");
                    Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_signin:
                startActivity(new Intent(this,SignUpActivity.class));
                break;

            case R.id.btn_signin:
                userLogin();
                break;



        }

    }
}
