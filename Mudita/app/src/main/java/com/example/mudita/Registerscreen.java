package com.example.mudita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Registerscreen extends AppCompatActivity {
    private EditText Name,Email,Password,Age;
    private Button Register,Sign_in;
    private ImageView Male,Female;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerscreen);
        Name=findViewById(R.id.name);
        Email=findViewById(R.id.emailenter);
        Password=findViewById(R.id.passwordenter);
        Age=findViewById(R.id.age);
        Register=findViewById(R.id.registerbutton);
        Sign_in=findViewById(R.id.signintransparent);
        Male=findViewById(R.id.maleicon);
        Female=findViewById(R.id.femaleicon);
        auth=FirebaseAuth.getInstance();
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name=Name.getText().toString();
                String txt_email=Email.getText().toString();
                String txt_password=Password.getText().toString();
                String txt_Email=Email.getText().toString();
            }
        };
    }
}
