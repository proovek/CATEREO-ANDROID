package com.ironsoft.catereo.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;

import java.util.List;

public class CategoriesListViewAdapter extends RecyclerView.Adapter<CategoriesListViewAdapter.ViewHolder> {

    private List<ItemData> dataList;
    private LayoutInflater inflater;


    public CategoriesListViewAdapter(Context context, List<ItemData> dataList) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.categories_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData item = dataList.get(position);
        holder.textView.setText(item.getText());
        holder.imageView.setImageBitmap(item.getImageResId() != null ? item.getImageResId() : generateAvatarFromName(item.getText()));// Ustaw obrazek // Załaduj obraz z zasobów
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                Bundle bundle = new Bundle();
                String categoryName = item.getText().toString(); // Przypuszczam, że item.getText() daje Ci nazwę kategorii
                bundle.putString("categoryName", categoryName);

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_home, true)
                        .build();

                navController.navigate(R.id.action_currentFragment_to_menuFragment, bundle, navOptions);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Klasa pomocnicza dla danych elementu
    public static class ItemData {
        private String text;
        private Bitmap imageResId; // ID zasobu obrazu

        public ItemData(String text, Bitmap imageResId) {
            this.text = text;
            this.imageResId = imageResId;
        }

        public String getText() {
            return text;
        }

        public Bitmap getImageResId() {
            return imageResId;
        }
    }

    private Bitmap generateAvatarFromName(String name) {
        // Tworzenie bitmapy z tłem jasnoniebieskim
        Bitmap backgroundBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        backgroundBitmap.eraseColor(0xFF006C67); // Kolor tła (jasnoniebieski)

        // Tworzenie canvas do rysowania
        Canvas canvas = new Canvas(backgroundBitmap);

        // Ustawienie koloru tekstu na biały
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);

        // Ustawienie rozmiaru tekstu
        textPaint.setTextSize(40);

        // Ustalenie pozycji tekstu na środku
        Rect textBounds = new Rect();
        textPaint.getTextBounds(name.substring(0, 1), 0, 1, textBounds);
        int x = (backgroundBitmap.getWidth() - textBounds.width()) / 2;
        int y = (backgroundBitmap.getHeight() + textBounds.height()) / 2;

        // Rysowanie pierwszej litery "name" na tle
        canvas.drawText(name.substring(0, 1), x, y, textPaint);

        return backgroundBitmap;
    }
}

