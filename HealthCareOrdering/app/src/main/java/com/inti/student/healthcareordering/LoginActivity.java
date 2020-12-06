package com.inti.student.healthcareordering;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Database.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private SessionManager session;
    private DBHelper db;
    private EditText etUsername;
    private EditText etPassword;
    private String username = "";
    private String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instantiate database handler
        db=new DBHelper(this);

        session = new SessionManager(this);

        etUsername = (EditText)findViewById(R.id.et_login_username);
        etPassword = (EditText)findViewById(R.id.et_login_password);

    }

    // function to get values from the Edittext
    private void getValues(){
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
    }

    //Function Insert data to the database
    private void verifyUser(){
        getValues();
        boolean validate;

        validate = db.authenticateUser(username, password);

        if (validate) {

            Cursor cursor = db.fetchData(username,password);
            cursor.moveToFirst();

            String contact = cursor.getString(3);

            session.createLoginSession(username, password, contact);

            Toast.makeText(this, "[SYSTEM]: Login Successful! ", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "FAIL!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnLogin_clicked(View view) {
        verifyUser();
    }

    public void btn_linkedSignup(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
