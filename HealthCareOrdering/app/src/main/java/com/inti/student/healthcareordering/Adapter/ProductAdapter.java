package com.inti.student.healthcareordering.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inti.student.healthcareordering.Model.Product;
import com.inti.student.healthcareordering.R;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Product> mProducts;
    private OnItemClickListener mListener;

    // Pass in the contact array into the constructor
    public ProductAdapter(List<Product> products) {
        mProducts = products;
    }

    public interface OnItemClickListener{
        void onItemClick(int p);
    }

    public void setOnClickListener(OnItemClickListener listener){mListener = listener;}

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Product product = mProducts.get(position);

        // set precision
        DecimalFormat precision = new DecimalFormat("0.00");

        // Set item views based on your views and data model
        TextView name = viewHolder.tvProductName;
        TextView code = viewHolder.tvProductCode;
        TextView price = viewHolder.tvProductPrice;
        TextView desc = viewHolder.tvProductDes;

        name.setText(product.getName());
        code.setText(product.getCode());
        price.setText("RM" + precision.format(product.getPrice()));
        desc.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName, tvProductCode, tvProductPrice, tvProductDes;
        public Button btnCart;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(@NonNull View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvProductName = itemView.findViewById(R.id.product_name);
            tvProductPrice = itemView.findViewById(R.id.product_price);
            tvProductCode = itemView.findViewById(R.id.product_code);
            tvProductDes = itemView.findViewById(R.id.product_desc);
            btnCart = itemView.findViewById(R.id.btn_cart);

            btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
