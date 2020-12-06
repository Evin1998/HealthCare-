package com.inti.student.healthcareordering;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inti.student.healthcareordering.Adapter.CartAdapter;
import com.inti.student.healthcareordering.Adapter.HistoryAdapter;
import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Database.SessionManager;
import com.inti.student.healthcareordering.Model.Cart;
import com.inti.student.healthcareordering.Model.Product;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsername,tvUsername1, tvContact;
    private RecyclerView rvHistory;
    private SessionManager session;
    private DBHelper db;
    private ArrayList<Product> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DBHelper(this);
        session = new SessionManager(this);

        tvUsername = findViewById(R.id.tv_username);
        tvUsername1 = findViewById(R.id.tv_username1);
        tvContact = findViewById(R.id.tv_contact);
        rvHistory = findViewById(R.id.rv_history);

        tvUsername.setText(session.getUsername());
        tvUsername1.setText(session.getUsername());
        tvContact.setText(session.getContact());

        historyList = new ArrayList<>();
        historyList = db.retrieveHistory(session.getUsername());

        HistoryAdapter adapter = new HistoryAdapter(historyList);
        rvHistory.setAdapter(adapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));

    }

    public void btn_editProfile(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null, false);

        // Set up the input
        final EditText etUsername = (EditText) viewInflated.findViewById(R.id.et_new_username);
        final EditText etPassword = (EditText) viewInflated.findViewById(R.id.et_new_password);
        final EditText etContact = (EditText) viewInflated.findViewById(R.id.et_new_contact);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the textu
        builder.setView(viewInflated);

        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                String contact = etContact.getText().toString();

                db.updateUser(session.getUsername(),username, pass, contact);
                session.logoutUser();

                Toast.makeText(getApplicationContext(), "[SYSTEM]: Please login again with your new password.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
