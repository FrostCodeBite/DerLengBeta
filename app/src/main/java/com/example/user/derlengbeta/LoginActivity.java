package com.example.user.derlengbeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.derlengbeta.Common.Common;
import com.example.user.derlengbeta.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 24-Nov-18.
 */

public class LoginActivity extends AppCompatActivity {
    EditText editUsername, editPass;
    Button btnSignIn;
    TextView txtSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button)findViewById(R.id.signin_btn);
        txtSignUp = (TextView)findViewById(R.id.sign_up);

        editUsername = (EditText)findViewById(R.id.username_edit);
        editPass = (EditText)findViewById(R.id.password_edit);

//        Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                        Check if users doesnt have in db
                        if(dataSnapshot.child(editUsername.getText().toString()).exists()){
                            mDialog.dismiss();
//                        Get User Info
                            Users users = dataSnapshot.child(editUsername.getText().toString()).getValue(Users.class);

                            if(users.getPassword().equals(editPass.getText().toString())){
                                Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
                                Common.currentUser = users;
                                startActivity(homeIntent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Sign In Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User not exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}
