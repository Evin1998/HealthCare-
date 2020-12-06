package com.inti.student.healthcareordering;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.inti.student.healthcareordering.Adapter.CartAdapter;
import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Database.SessionManager;
import com.inti.student.healthcareordering.Model.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener {

    private RecyclerView rvCart;
    private ArrayList<Product> cartList;
    private DBHelper db;
    private SessionManager session;
    private Product deletedProduct;
    private Button btnCheckOut, btnClearCart;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = new DBHelper(this);
        session = new SessionManager(this);

        rvCart = (RecyclerView) findViewById(R.id.rv_cart);
        btnCheckOut = (Button) findViewById(R.id.btn_checkout);
        btnClearCart = (Button) findViewById(R.id.btn_clearcart);

        Intent i = getIntent();
        cartList = new ArrayList<>();

        cartList = db.displayCart(session.getUsername());
        adapter = new CartAdapter(cartList);
        rvCart.setAdapter(adapter);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(this);

        checkCartlist();
    }

    @Override
    public void onItemClick(int p) {
        deletedProduct = cartList.get(p);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Cart")
                .setMessage("Are You Sure to Remove This Item from Your Cart?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeCart(deletedProduct);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();


    }

    public void removeCart(Product product) {
        db.deleteCart(product.getId(), session.getUsername());
        cartList.remove(deletedProduct);
        adapter.notifyDataSetChanged();
    }

    public void btn_proceedCheckout(View view) {
        // set precision
        DecimalFormat precision = new DecimalFormat("0.00");
        double price = 0.0;

        for (int i = 0; i < cartList.size(); i++) {
            price += cartList.get(i).getPrice();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Check Out Confirmation")
                .setMessage("Total Amount: RM " + precision.format(price)
                        + "\nAre you sure to proceed/pay?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertOrderHistory();
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

    public void insertOrderHistory() {
        for (int i = 0; i < cartList.size(); i++) {
            db.insertHistory(cartList.get(i), session.getUsername());
            db.deleteCart(cartList.get(i).getId(), session.getUsername());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("Payment Successful!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .create()
                .show();
    }

    public void btn_clearCart(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("Are You Sure to DELETE All Items from Your Cart?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeAllCart();
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

    public void removeAllCart() {
        for (int i = 0; i < cartList.size(); i++) {
            db.deleteCart(cartList.get(i).getId(), session.getUsername());
        }

        cartList.clear();
        adapter.notifyDataSetChanged();
        checkCartlist();
    }

    @SuppressLint("ResourceAsColor")
    public void checkCartlist(){
        if(cartList.size()==0){

            btnCheckOut.setEnabled(false);
            btnCheckOut.setBackgroundColor(R.color.colorGrey);

            btnClearCart.setEnabled(false);
            btnClearCart.setBackgroundColor(R.color.colorGrey);
        }
    }
}
