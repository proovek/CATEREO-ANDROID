package com.ironsoft.catereo.helpers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {
    private List<OrderItemModel> orderList;
    private Context mContext;

    // Konstruktor
    public OrderListAdapter(Context context, List<OrderItemModel> orderList) {
        this.orderList = orderList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_view, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItemModel currentItem = orderList.get(position);
        holder.orderId.setText(currentItem.getOrderId());
        holder.orderDate.setText(currentItem.getOrderDate());
        holder.orderDateTime.setText(currentItem.getOrderDateTime());
        holder.orderSummaryPrice.setText(currentItem.getOrderSummaryPrice());
        holder.orderStatus.setText(currentItem.getOrderStatus());

        // Przenieś logikę zmiany kolorów tutaj
        if (currentItem.getOrderStatus().equals("W toku")) {
            holder.orderStatus.setBackgroundColor(Color.parseColor("#FFB534"));
            holder.orderStatus.setTextColor(Color.parseColor("#966714")); // Zmieniono dla lepszej czytelności
        } else if (currentItem.getOrderStatus().equals("Zakończone")) {
            holder.orderStatus.setBackgroundColor(Color.parseColor("#C1F2B0"));
            holder.orderStatus.setTextColor(Color.parseColor("#65B741"));
        } else if (currentItem.getOrderStatus().equals("Anulowane")) {
            holder.orderStatus.setBackgroundColor(Color.parseColor("#B80000"));
            holder.orderStatus.setTextColor(Color.parseColor("#590c0c")); // Zmieniono dla lepszej czytelności
        }
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId, orderDate, orderDateTime, orderSummaryPrice, orderStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderDateTime = itemView.findViewById(R.id.orderDateTime);
            orderSummaryPrice = itemView.findViewById(R.id.orderSummaryPrice);
            orderStatus = itemView.findViewById(R.id.orderStatus);
        }
    }
}
