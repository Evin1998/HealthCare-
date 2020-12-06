package com.inti.student.healthcareordering.Fragment;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inti.student.healthcareordering.Adapter.ProductAdapter;
import com.inti.student.healthcareordering.Database.DBHelper;
import com.inti.student.healthcareordering.Database.SessionManager;
import com.inti.student.healthcareordering.Model.Product;
import com.inti.student.healthcareordering.R;

import java.util.ArrayList;

public class BabyCareFragment extends Fragment {

    private RecyclerView rvProduct;

    private DBHelper db;
    private SessionManager session;

    private ArrayList<Product> products;

    public BabyCareFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_baby_care, container,false);

        rvProduct = v.findViewById(R.id.rv_product_baby_care);
        session = new SessionManager(getActivity());
        db = new DBHelper(getActivity());

        products = Product.createBabyCareProductList();

        ProductAdapter adapter = new ProductAdapter(products);
        rvProduct.setAdapter(adapter);
        rvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setOnClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int p) {
                // get product
                Product currentProduct = products.get(p);

                // get username
                String username = session.getUsername();

                // insert cart into database
                db.insertCart(currentProduct, username);

                // message box
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Alert")
                        .setMessage("Added to Cart!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });

        return v;
    }


}
