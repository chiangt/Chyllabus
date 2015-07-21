package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseUser;

public class Launch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser!=null) {
            startActivity(new Intent(getApplicationContext(), Userpage.class));;
        }

        final Button toLoginButton = (Button) findViewById(R.id.buttonToLogin);
        final Button toSignupButton = (Button) findViewById(R.id.buttonToSignup);

        toLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        toSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
    }
}