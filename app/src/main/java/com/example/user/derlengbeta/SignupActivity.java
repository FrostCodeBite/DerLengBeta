package com.example.user.derlengbeta;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.derlengbeta.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 24-Nov-18.
 */

public class SignupActivity extends AppCompatActivity {
    EditText edit_pass, edit_username, edit_phone, edit_email;
    Button btn_signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edit_pass = (EditText)findViewById(R.id.password_edit);
        edit_username = (EditText)findViewById(R.id.username_edit);
        edit_phone = (EditText)findViewById(R.id.phone_edit);
        edit_email = (EditText)findViewById(R.id.email_edit);

        btn_signup = (Button)findViewById(R.id.signup_btn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignupActivity.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edit_phone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignupActivity.this, "Phone num already existed", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            Users users= new Users(edit_username.getText().toString(),edit_pass.getText().toString(),edit_email.getText().toString());
                            table_user.child(edit_phone.getText().toString()).setValue(users);
                            Toast.makeText(SignupActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
