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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Product> mHistory;

    // Pass in the contact array into the constructor
    public HistoryAdapter(List<Product> mHistory) {
        this.mHistory = mHistory;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_history, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Product product = mHistory.get(position);

        // set precision
        DecimalFormat precision = new DecimalFormat("0.00");

        // Set item views based on your views and data model
        TextView name = viewHolder.tvProductName;
        TextView code = viewHolder.tvProductCode;
        TextView price = viewHolder.tvProductPrice;

        name.setText(product.getId() + ". " + product.getName() );
        code.setText(product.getCode());
        price.setText("RM" + precision.format(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProductName, tvProductPrice, tvProductCode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tv_history_name);
            tvProductPrice = itemView.findViewById(R.id.tv_history_price);
            tvProductCode = itemView.findViewById(R.id.tv_history_code);
        }
    }
}
