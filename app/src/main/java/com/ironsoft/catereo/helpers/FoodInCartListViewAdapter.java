package com.ironsoft.catereo.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;
import com.ironsoft.catereo.realmdb.helpers.FoodInCartItemModelRealm;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FoodInCartListViewAdapter extends RecyclerView.Adapter<FoodInCartListViewAdapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private RealmResults<FoodInCartItemModelRealm> mItems;
    private Context mContext;

    public FoodInCartListViewAdapter(Context context, RealmResults<FoodInCartItemModelRealm> items) {
        this.mContext = context;
        this.mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public RatingBar ratingBar;
        public TextView priceTextView;
        public TextView quantityTextView;
        public Button deleteButton;
        public Button decreaseButton;
        public Button increaseButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.title);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            priceTextView = itemView.findViewById(R.id.foodPrice);
            quantityTextView = itemView.findViewById(R.id.quantity);
            deleteButton = itemView.findViewById(R.id.delete_from_cart);
            decreaseButton = itemView.findViewById(R.id.decreaseQuantity);
            increaseButton = itemView.findViewById(R.id.increaseQuantity);
        }
    }

    @Override
    public FoodInCartListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_cart_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodInCartListViewAdapter.ViewHolder holder, int position) {
        final FoodInCartItemModelRealm item = mItems.get(position);

        holder.imageView.setImageBitmap(StringToBitMap(item.getImageBitmap()));
        holder.titleTextView.setText(item.getTitle());
        holder.ratingBar.setRating(item.getRating());
        holder.priceTextView.setText(String.format("%.2f zł", item.getPrice() * item.getQuantity()));
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

        // Obsługa przycisku increaseButton
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        // Tutaj bezpiecznie zwiększ wartość quantity o 1
                realm.beginTransaction();
                        int currentQuantity = item.getQuantity();
                        item.setQuantity(currentQuantity + 1);
                realm.commitTransaction();
                notifyDataSetChanged();

            }
        });

        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        // Tutaj bezpiecznie zmniejsz wartość quantity o 1, ale nie mniej niż 1
                realm.beginTransaction();
                        int currentQuantity = item.getQuantity();
                        if (currentQuantity > 1) {
                            item.setQuantity(currentQuantity - 1);
                        }
                realm.commitTransaction();
                notifyDataSetChanged();

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                    item.deleteFromRealm();
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });


    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}

