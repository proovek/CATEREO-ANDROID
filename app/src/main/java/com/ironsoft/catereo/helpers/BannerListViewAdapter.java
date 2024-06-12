package com.ironsoft.catereo.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;

import java.util.List;

public class BannerListViewAdapter extends RecyclerView.Adapter<BannerListViewAdapter.ViewHolder> {

    private List<BannerData> bannerDataList;
    private LayoutInflater inflater;


    public BannerListViewAdapter(Context context, List<BannerData> bannerDataList) {
        this.inflater = LayoutInflater.from(context);
        this.bannerDataList = bannerDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.banners_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BannerData item = bannerDataList.get(position);
        holder.imageView.setImageBitmap(item.getImageResId()); // Załaduj obraz z zasobów
        // Możesz tutaj również załadować obraz z internetu za pomocą biblioteki, np. Glide lub Picasso
    }

    @Override
    public int getItemCount() {
        return bannerDataList.size();
    }

    // Klasa pomocnicza dla danych elementu
    public static class BannerData {
        private Bitmap imageResId; // ID zasobu obrazu

        public BannerData(Bitmap imageResId) {
            this.imageResId = imageResId;
        }

        public Bitmap getImageResId() {
            return imageResId;
        }
    }
}
