package com.inti.student.healthcareordering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Model.User;

public class SignupActivity extends AppCompatActivity {

    private DBHelper db;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etContact;
    private String username = "";
    private String password = "";
    private String contact = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Instantiate database handler
        db=new DBHelper(this);

        etUsername = (EditText)findViewById(R.id.et_signup_username);
        etPassword = (EditText)findViewById(R.id.et_signup_password);
        etContact = (EditText)findViewById(R.id.et_signup_contact);
    }

    // function to get values from the Edittext
    private void getValues(){
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        contact = etContact.getText().toString();
    }

    //Function Insert data to the database
    private void registerUser(){
        getValues();

        if(!db.isUsernameExists(username)) {
            db.insertUser(new User(username, password,contact));
            Toast.makeText(getApplicationContext(), "[SYSTEM]: Register Successfully! Please Log in Again.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "[SYSTEM]: Username Exists, Please Change Another Username!", Toast.LENGTH_LONG).show();
        }

    }

    public void btnSignup_clicked(View view) {
        registerUser();
    }

    public void btn_linkedLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
