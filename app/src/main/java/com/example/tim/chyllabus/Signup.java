package com.example.tim.chyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends Activity {

    private EditText emailEditText, passwordEditText, retypeEditText, firstNameEditText, lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        emailEditText = (EditText) findViewById(R.id.editEmailSignup);
        passwordEditText = (EditText) findViewById(R.id.editPasswordSignup);
        retypeEditText = (EditText) findViewById(R.id.editRetypeSignup);
        firstNameEditText = (EditText) findViewById(R.id.editFirstNameSignup);
        lastNameEditText = (EditText) findViewById(R.id.editLastNameSignup);
        Button alreadyHaveAccountButton = (Button) findViewById(R.id.buttonAlreadyHaveAccount);
        Button signupButton = (Button) findViewById(R.id.buttonSignup);

        final String warningMandatory = getString(R.string.warning_mandatory_field);
        final String warningMismatch = getString(R.string.warning_mismatched_passwords);

        final Intent intent = getIntent();
        if (intent!=null) {
            final String emailFromLogin = intent.getStringExtra("email");
            emailEditText.setText(emailFromLogin);
        }

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    emailEditText.setError(warningMandatory);
                }
            }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    passwordEditText.setError(warningMandatory);
                }
            }
        });
        retypeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(passwordEditText.getText().toString())) {
                    retypeEditText.setError(warningMismatch);
                }
            }
        });
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    firstNameEditText.setError(warningMandatory);
                }
            }
        });
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    lastNameEditText.setError(warningMandatory);
                }
            }
        });
        alreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setEmail(emailEditText.getText().toString().trim());
                user.setUsername(emailEditText.getText().toString().trim());
                user.setPassword(passwordEditText.getText().toString().trim());
                user.put("firstName",firstNameEditText.getText().toString().trim());
                user.put("lastName",lastNameEditText.getText().toString().trim());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            toLogin(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
                        } else {
                            Toast.makeText(getApplicationContext(),"Failure" + "\n" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void toLogin(String email, String password) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.putExtra("tag",1);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}
