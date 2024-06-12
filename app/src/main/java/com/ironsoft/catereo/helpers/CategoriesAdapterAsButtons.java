package com.ironsoft.catereo.helpers;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ironsoft.catereo.R;

import java.util.List;


public class CategoriesAdapterAsButtons extends RecyclerView.Adapter<CategoriesAdapterAsButtons.CategoryViewHolder> {

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryName);
    }

    private OnCategoryClickListener listener;

    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }


    private List<String> categories; // Lista nazw kategorii

    public CategoriesAdapterAsButtons(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialButton button = new MaterialButton(parent.getContext(), null, com.google.android.material.R.style.Widget_MaterialComponents_Button_OutlinedButton);
        // Ustawienie LayoutParams dla buttona, w tym margin
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int marginEnd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, parent.getResources().getDisplayMetrics());
        layoutParams.setMarginEnd(marginEnd); // Ustawienie marginEnd
        button.setLayoutParams(layoutParams);

        // Ustawienie padding dla buttona
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, parent.getResources().getDisplayMetrics());
        button.setPadding(padding, padding, padding, padding); // Ustawienie padding dla wszystkich stron

        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setAllCaps(false);
        button.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.selective_yellow)); // Ustaw odpowiedni kolor
        button.setCornerRadius(50);
        button.setStrokeWidth(4);
        button.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(parent.getContext(), R.color.selective_yellow))); // Ustaw odpowiedni kolor
        button.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));

        return new CategoryViewHolder(button);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String categoryName = categories.get(position); // Pobierz nazwę kategorii na tej pozycji
        holder.button.setText(categoryName);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCategoryClick(categoryName); // Wywołanie interfejsu słuchacza
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        MaterialButton button;

        public CategoryViewHolder(@NonNull MaterialButton button) {
            super(button);
            this.button = button;
        }
    }
}
