package com.ironsoft.catereo.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.Rating;
import android.os.Bundle;
import android.util.Base64;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ironsoft.catereo.R;
import com.ironsoft.catereo.api.Helpers.Ingredient;
import com.ironsoft.catereo.realmdb.helpers.FoodInCartItemModelRealm;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    private LayoutInflater inflater;
    @Nullable
    private ViewGroup container;
    @Nullable
    private Bundle savedInstanceState;

    private CircleImageView detailsImage;
    private TextView textItemName;
    private TextView textItemDescription;
    private RatingBar ratingBar;
    private ImageView isVegePill;
    private ImageView isVegetarianPill;
    private LinearLayout pillContainerIngredients;
    private LinearLayout pillContainerAllergens;
    private TextView itemPrice;
    private Button buttonAddToCart;


    // Tutaj możesz dodać konstruktor i metody do przekazywania danych do fragmentu,
    // oraz metody typu `newInstance` jeśli są potrzebne.

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        Realm realm = Realm.getDefaultInstance();

        detailsImage = view.findViewById(R.id.detailsImage);
        textItemName = view.findViewById(R.id.textItemName);
        textItemDescription = view.findViewById(R.id.textItemDescription);
        ratingBar = view.findViewById(R.id.ratingBar);
        isVegePill = view.findViewById(R.id.isVegan);
        isVegetarianPill = view.findViewById(R.id.isVege);
        pillContainerIngredients = view.findViewById(R.id.pillContainerIngredients);
        pillContainerAllergens = view.findViewById(R.id.pillContainerAllergens);
        itemPrice = view.findViewById(R.id.itemPrice);
        buttonAddToCart = view.findViewById(R.id.buttonAddToCart);


        Bundle arguments = getArguments();

        if (arguments != null) {

            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            FoodInCartItemModelRealm realmItem = realm.createObject(FoodInCartItemModelRealm.class);
                            realmItem.setMenuItemId(arguments.getInt("menuItemId"));
                            realmItem.setImageBitmap(arguments.getParcelable("bitmapImage") != null ? BitMapToString(arguments.getParcelable("bitmapImage")) : BitMapToString(generateAvatarFromName(arguments.getString("name").toUpperCase())));
                            realmItem.setTitle(arguments.getString("name"));
                            realmItem.setRating(arguments.getFloat("rating"));
                            realmItem.setPrice(arguments.getDouble("price"));
                            realmItem.setQuantity(1);
                        }
                    });
                    realm.close();
                }
            });

            Integer menuItemId = arguments.getInt("menuItemId");
            String name = arguments.getString("name");
            textItemName.setText(name);
            String description = arguments.getString("description");
            textItemDescription.setText(description);
            Double price = arguments.getDouble("price");
            itemPrice.setText(price.toString() + " ZŁ");
            Float rating = arguments.getFloat("rating");
            ratingBar.setRating(rating);
            String sku = arguments.getString("sku");
            Boolean availability = arguments.getBoolean("availability");
            Boolean isVegan = arguments.getBoolean("isVegan");
            if (!isVegan.booleanValue()) {
                isVegePill.setVisibility(View.GONE);
            }
            Boolean isVegetarian = arguments.getBoolean("isVegetarian");
            if (!isVegetarian.booleanValue()) {
                isVegetarianPill.setVisibility(View.GONE);
            }
            ArrayList<String> allergenInformation = arguments.getStringArrayList("allergeninformation");
            if (allergenInformation != null) {
                for (String allergen : allergenInformation) {

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(15, 0, 15, 0); // LTR: lewo, góra, prawo, dół
                    TextView textView = new TextView(getContext());
                    textView.setLayoutParams(layoutParams);
                    textView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.pill_drawable)); // Ustaw tło
                    textView.setPadding(30, 15, 30, 15);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Ustaw rozmiar tekstu
                    textView.setText(allergen); // Ustaw tekst
                    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.selective_yellow)); // Ustaw kolor tekstu
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER); // Ustaw wyrównanie tekstu
                    // Dodaj TextView do LinearLayout
                    pillContainerAllergens.addView(textView);
                }
            }
            @SuppressWarnings("unchecked")
            List<Ingredient> ingredients = (List<Ingredient>) arguments.getSerializable("ingredients");
                if (ingredients != null) {
                    for (Ingredient ingredient : ingredients) {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams.setMargins(15, 0, 15, 0); // LTR: lewo, góra, prawo, dół
                        TextView textView = new TextView(getContext());
                        textView.setLayoutParams(layoutParams);
                        textView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.pill_drawable)); // Ustaw tł
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Ustaw rozmiar tekstu
                        textView.setPadding(30, 15, 30, 15);
                        textView.setText(ingredient.getProductName()); // Ustaw tekst na nazwę składnika
                        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.selective_yellow)); // Ustaw kolor tekstu
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER); // Ustaw wyrównanie tekstu

                        // Dodaj TextView do LinearLayout
                        pillContainerIngredients.addView(textView);
                    }
                }
            Bitmap bitmapImage = arguments.getParcelable("bitmapImage");
            detailsImage.setImageBitmap(bitmapImage != null ? bitmapImage : generateAvatarFromName(name.toUpperCase()));


        }
        return view;
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

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
