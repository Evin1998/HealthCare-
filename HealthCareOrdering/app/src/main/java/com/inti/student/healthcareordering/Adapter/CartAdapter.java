package com.inti.student.healthcareordering.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inti.student.healthcareordering.Model.Cart;
import com.inti.student.healthcareordering.Model.Product;
import com.inti.student.healthcareordering.R;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private List<Product> mCart;
    private CartAdapter.OnItemClickListener mListener;

    // Pass in the contact array into the constructor
    public CartAdapter(List<Product> cart) {
        mCart = cart;
    }

    public interface OnItemClickListener{
        void onItemClick(int p);
    }

    public void setOnClickListener(CartAdapter.OnItemClickListener listener){mListener = listener;}

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_cart, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Product product = mCart.get(position);

        // set precision
        DecimalFormat precision = new DecimalFormat("0.00");

        // Set item views based on your views and data model
        TextView name = viewHolder.tvProductName;
        TextView price = viewHolder.tvProductPrice;

        name.setText(position+1 + ". " + product.getName());
        price.setText("RM" + precision.format(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName, tvProductPrice;
        public Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tv_cart_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_cart_product_price);
            btnDelete = itemView.findViewById(R.id.btn_cart_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
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
