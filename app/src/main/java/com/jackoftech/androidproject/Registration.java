package com.jackoftech.androidproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {


    private EditText rEmail, rPasswd, rName;
    private Button nRegister;
    private RadioGroup rRadioGroup;


    private FirebaseAuth rAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rAuth = FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

                if (fUser != null){
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        nRegister = findViewById(R.id.btnRegister);

        rEmail = findViewById(R.id.email);
        rPasswd = findViewById(R.id.passwd);
        rName = findViewById(R.id.name);
        rRadioGroup = findViewById(R.id.radioGroup);

        nRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int selected = rRadioGroup.getCheckedRadioButtonId();

                final RadioButton radioButton = findViewById(selected);

                if (radioButton.getText() == null){
                    Toast.makeText(getApplicationContext(),"Please Select Male or Female",Toast.LENGTH_LONG);
                }else{


                final String email = rEmail.getText().toString();
                final String pass = rPasswd.getText().toString();
                final String name = rName.getText().toString();

                rAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                          Toast.makeText(Registration.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                        }else
                            {
                            String userId = rAuth.getCurrentUser().getUid();
                                DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                Map userInfo = new HashMap<>();
                                userInfo.put("name", name);
                                userInfo.put("sex", radioButton.getText().toString());
                                userInfo.put("profileImageUrl", "default");
                                currentUserDb.updateChildren(userInfo);

                            }
                    }
                });

            }}
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        rAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        rAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}