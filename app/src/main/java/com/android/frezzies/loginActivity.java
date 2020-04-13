package com.android.frezzies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,12}" +               //at least 4 characters
                    "$");
    private TextInputLayout inpEmail;
    private TextInputLayout inpPassword;
    EditText etEmail, etPassword;
    Button btLogin;
    TextView notHaveAcc;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        inpEmail = findViewById(R.id.email);
        inpPassword = findViewById(R.id.password);
        //Initialize SessionManager
        sessionManager = new SessionManager(getApplicationContext());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text from EditText
                String sEmail = etEmail.getText().toString().trim();
                String sPassword = etPassword.getText().toString().trim();
                if (sPassword.equals("")) {
                    etPassword.setError("Please enter a valid Password");
                }
            }
        });

        notHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this, dashboardActivity.class));
            }
        });
    }

    private boolean validateEmail() {
        String emailInput = inpEmail.getEditText().toString().trim();
        if (emailInput.isEmpty()) {
            inpEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            inpEmail.setError("Please enter a valid email address");
            return false;
        } else {
            inpEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passInput = inpPassword.getEditText().toString().trim();
        if (passInput.isEmpty()) {
            inpPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passInput).matches()) {
            inpPassword.setError("Password too weak");
            return false;
        } else {
            inpPassword.setError(null);
            return true;
        }
    }


    //public void confirmInput(View v) {
      //  if (!validateEmail() | !validatePassword()) {
        //    return;
        //}

        //String input = "Email: " + inpEmail.getEditText().getText().toString();
        //input += "\n";
        //input += "Password: " + inpPassword.getEditText().getText().toString();
        //Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    //}
}
