package com.inti.student.healthcareordering;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inti.student.healthcareordering.Adapter.ProductAdapter;
import com.inti.student.healthcareordering.Adapter.TabAdapter;
import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Database.SessionManager;
import com.inti.student.healthcareordering.Model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;
    private SessionManager session;

    private RecyclerView rvProducts;
    private ArrayList<Product> products;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        session = new SessionManager(this);
        db = new DBHelper(this);

        invalidateOptionsMenu();

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Baby Care"));
        tabLayout.addTab(tabLayout.newTab().setText("Medicine"));
        tabLayout.addTab(tabLayout.newTab().setText("Supplement"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter adapter = new TabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                if(session.isLoggedIn()) {
                    Intent i = new Intent(this, ProfileActivity.class);
                    startActivity(i);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alert")
                            .setMessage("Please login first!")
                            .setPositiveButton("Login now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                }
                return true;
            case R.id.cart:
                if(session.isLoggedIn()) {
                    Intent i = new Intent(this, CartActivity.class);
                    //i.putExtra(CART_PNAME, cartProductName);
//                    i.putExtra(CART_PPRICE, cartProductPrice);
                    startActivity(i);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alert")
                            .setMessage("Please Login First!")
                            .setPositiveButton("Login Now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                }
                return true;
            case R.id.logout:
                if(session.isLoggedIn()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alert")
                            .setMessage("Are You Sure to Logout?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    session.logoutUser();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                }
                else{
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logout);
        if (session.isLoggedIn()) {
            item.setTitle("Logout");
        } else {
            item.setTitle("Login");
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
