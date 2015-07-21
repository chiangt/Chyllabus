package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final EditText emailEditText = (EditText) findViewById(R.id.editEmailLogin);
        final EditText passwordEditText = (EditText) findViewById(R.id.editPasswordLogin);
        final Button forgotPasswordButton = (Button) findViewById(R.id.buttonForgotPassword);
        final Button noAccountButton = (Button) findViewById(R.id.buttonNoAccount);
        final Button loginButton = (Button) findViewById(R.id.buttonLogin);

        final Intent intent = getIntent();
        final int tag = intent.getIntExtra("tag", 0);
        if (tag == 1) {
            emailEditText.setText(intent.getStringExtra("email"));
            passwordEditText.setText(intent.getStringExtra("password"));
        } else if (tag == 2) {
            emailEditText.setText(intent.getStringExtra("email"));
        }

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forgot Password button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        noAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                toSignup(email);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(emailEditText.getText().toString().trim(), passwordEditText.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Userpage.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "The credentials you entered are not valid. Please recheck." + "\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void toSignup(String email) {
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
