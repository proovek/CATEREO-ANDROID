package com.ironsoft.catereo.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> implements Filterable {

    private List<FoodItemModel> mItems;
    private List<FoodItemModel> mItemsFull;
    private Context mContext;
    private FragmentManager fragmentManager;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView categoryTextView;
        public RatingBar ratingBar;
        public Button addButton;
        public TextView priceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            categoryTextView = itemView.findViewById(R.id.category);
            addButton = itemView.findViewById(R.id.add_to_cart);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            priceTextView = itemView.findViewById(R.id.foodPrice);
        }
    }

    public FoodListAdapter(Context context, List<FoodItemModel> items, FragmentManager fragmentManager) {
        mContext = context;
        mItems = new ArrayList<>(items);
        mItemsFull = new ArrayList<>(items); // Stwórz głęboką kopię listy
        this.fragmentManager = fragmentManager;
    }

    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodListAdapter.ViewHolder holder, int position) {
        FoodItemModel item = mItems.get(position);
        Bitmap bitmap = item.getImageUrl() != null ? item.getImageUrl() : generateAvatarFromName(item.getTitle().toUpperCase());
        holder.imageView.setImageBitmap(bitmap);
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
        holder.categoryTextView.setText(item.getCategory());
        holder.ratingBar.setRating(item.getRating());
        holder.priceTextView.setText(String.format("%.2f zł", item.getPrice()));

        // Ustawianie logiki przycisku i klikalności tutaj, jeśli to konieczne
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<FoodItemModel> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(mItemsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (FoodItemModel item : mItemsFull) {
                        if (item.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItems.clear();
                mItems.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }
    public void updateData(List<FoodItemModel> newItems) {
        mItems.clear();
        mItems.addAll(newItems);
        mItemsFull.clear();
        mItemsFull.addAll(newItems);
        filterByCategory("Wszystkie");
    }
    public void filterByCategory(String category) {
        List<FoodItemModel> filteredList = new ArrayList<>();
        if (category == null || category.isEmpty() || category.equals("Wszystkie")) {
            filteredList.addAll(mItemsFull);
        } else {
            for (FoodItemModel item : mItemsFull) {
                if (item.getCategory() != null && item.getCategory().equals(category)) {
                    filteredList.add(item);
                }
            }
        }
        mItems.clear();
        mItems.addAll(filteredList);
        notifyDataSetChanged();
    }

    private Bitmap generateAvatarFromName(String name) {
        Bitmap backgroundBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        backgroundBitmap.eraseColor(Color.parseColor("#006C67"));

        Canvas canvas = new Canvas(backgroundBitmap);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        Rect textBounds = new Rect();
        textPaint.getTextBounds(name.substring(0, 1), 0, 1, textBounds);
        int x = (backgroundBitmap.getWidth() - textBounds.width()) / 2;
        int y = (backgroundBitmap.getHeight() + textBounds.height()) / 2;

        canvas.drawText(name.substring(0, 1), x, y, textPaint);

        return backgroundBitmap;
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
