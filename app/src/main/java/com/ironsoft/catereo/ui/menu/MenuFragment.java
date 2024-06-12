package com.ironsoft.catereo.ui.menu;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsoft.catereo.R;
import com.ironsoft.catereo.SharedViewModel;
import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetMenuItemsDayByDay;
import com.ironsoft.catereo.api.GET.GetProductCategories;
import com.ironsoft.catereo.api.Helpers.Category;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.Ingredient;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.UserManager;
import com.ironsoft.catereo.databinding.FragmentHomeBinding;
import com.ironsoft.catereo.databinding.FragmentMenuBinding;
import com.ironsoft.catereo.helpers.CategoriesAdapterAsButtons;
import com.ironsoft.catereo.helpers.CategoriesListViewAdapter;
import com.ironsoft.catereo.helpers.FoodItemModel;
import com.ironsoft.catereo.helpers.FoodListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuFragment extends Fragment {
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapterAsButtons categoriesAdapterAsButtons;
    private ImageView avatarImageView;
    private FragmentHomeBinding binding;
    private List<String> categories;

    private RecyclerView foodView;
    private SearchView searchView;
    private FoodListAdapter foodListAdapter;

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        categories = new ArrayList<>();
        categories.add("Wszystkie");

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        foodView = view.findViewById(R.id.food_list_view2);
        foodView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        avatarImageView = view.findViewById(R.id.avatarImageView);
        avatarImageView.setImageBitmap(generateAvatarFromName(UserManager.getUserName(getContext()).toUpperCase()));

        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        categoriesAdapterAsButtons = new CategoriesAdapterAsButtons(categories);
        categoriesAdapterAsButtons.setOnCategoryClickListener(categoryName -> {
            foodListAdapter.filterByCategory(categoryName);
             //Toast.makeText(getContext(), categoryName, Toast.LENGTH_SHORT).show(); // Wyświetlenie Toast z nazwą kategorii
        });
        categoriesRecyclerView.setAdapter(categoriesAdapterAsButtons);

        List<FoodItemModel> yourFoodListData = new ArrayList<>();

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getMenuItemsData().observe(getViewLifecycleOwner(), data -> {
            for (MenuItemObject menuItem : data) {
                Integer menuItemId = menuItem.getMenuItemId() != null ? menuItem.getMenuItemId() : 0;
                String name = menuItem.getName() != null ? menuItem.getName() : "";
                String description = menuItem.getDescription() != null ? menuItem.getDescription() : "";
                Double price = menuItem.getPrice() != null ? menuItem.getPrice() : 0.0;
                Float rating = menuItem.getPopularityRating() != null ? menuItem.getPopularityRating() : 0.0f;
                String sku = menuItem.getSku() != null ? menuItem.getSku() : "";
                Boolean availability = menuItem.getAvailability() != null ? menuItem.getAvailability() : false;
                Boolean isVegan = menuItem.getIsVegan() != null ? menuItem.getIsVegan() : false;
                Boolean isVegetarian = menuItem.getIsVegetarian() != null ? menuItem.getIsVegetarian() : false;
                List<String> allergeninformation = menuItem.getAllergeninformation() != null ? menuItem.getAllergeninformation() : new ArrayList<>();
                List<Ingredient> ingredients = menuItem.getIngredients() != null ? menuItem.getIngredients() : new ArrayList<>();


                String base64Image = menuItem.getImage() != null ? menuItem.getImage() : "";
                Bitmap bitmapImage = null;
                if (base64Image != null && !base64Image.isEmpty()) {
                    try {
                        // Rozdziel ciąg Base64 na części, jeśli zawiera nagłówek
                        String[] imageParts = base64Image.split(",");
                        String base64Data = imageParts.length == 2 ? imageParts[1] : base64Image; // Użyj danych bezpośrednio, jeśli nie ma nagłówka

                        // Dekoduj Base64 do tablicy bajtów
                        byte[] imageBytes = Base64.decode(base64Data, Base64.DEFAULT);

                        // Ustawienia dekodowania bitmapy
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false; // Dekoduj rzeczywistą bitmapę

                        // Ustaw stałą wartość próbkowania, np. 4, co zmniejszy rozmiar obrazu
                        options.inSampleSize = 4; // Obraz będzie 1/4 oryginalnej szerokości i wysokości

                        bitmapImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
                    } catch (IllegalArgumentException e) {
                        // Obsługa błędu dekodowania Base64
                        Log.e("BitmapDecode", "Błąd dekodowania Base64", e);
                    }
                } else {
                }

                List<Category> categories = menuItem.getCategory();
                List<String> categoryNames = new ArrayList<>();
                // Przekształć listę obiektów Category na listę nazw kategorii
                for (Category category : categories) {
                    categoryNames.add(category.getCategoryName());
                }

                // Połącz nazwy kategorii w pojedynczy string, używając przecinków jako separatorów
                StringBuilder categoryStringBuilder = new StringBuilder();
                for (String categoryName : categoryNames) {
                    if (categoryStringBuilder.length() > 0) {
                        categoryStringBuilder.append(", ");
                    }
                    categoryStringBuilder.append(categoryName);
                }
                String category = categoryStringBuilder.toString();

                // Tutaj możesz przetwarzać każdy element listy
                yourFoodListData.add(new FoodItemModel(menuItemId, bitmapImage, name, description, category, rating, price, sku, availability, isVegan, isVegetarian, allergeninformation, ingredients));
            }
            foodListAdapter = new FoodListAdapter(getContext(), yourFoodListData, getChildFragmentManager());
            foodView.setAdapter(foodListAdapter);
            foodListAdapter.updateData(yourFoodListData);
        });
        viewModel.getCategoryItemsData().observe(getViewLifecycleOwner(), data -> {
            for(CategoryObject categoryObject : data){
                String base64Image = categoryObject.getImage() != null ? categoryObject.getImage() : "";
                Bitmap bitmapImage = null;
                if (base64Image != null && !base64Image.isEmpty()) {
                    // Jeśli base64Image nie jest nullem ani pusty, wykonaj podział ciągu znaków
                    String[] imageParts = base64Image.split(",");
                    if (imageParts.length == 2) {
                        String base64Data = imageParts[1];
                        byte[] imageBytes = Base64.decode(base64Data, Base64.DEFAULT);

                        bitmapImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    } else {
                    }
                } else {
                }

                categories.add(categoryObject.getCategoryName());
            }
            categoriesAdapterAsButtons.notifyDataSetChanged();
        });

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Kod, który chcesz wykonać, gdy SearchView zostanie wyczyszczony
                    foodListAdapter.filterByCategory("Wszystkie"); // Przywróć wyświetlanie wszystkich elementów
                } else {
                    // Opcjonalnie, filtruj listę na podstawie wpisanego tekstu
                    foodListAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Ukryj klawiaturę
                    hideKeyboard(v);
                }
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                // Ukryj klawiaturę
                foodListAdapter.filterByCategory("Wszystkie");
                hideKeyboard(searchView);
                return false;
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Metoda pomocnicza do dekodowania obrazów z Base64
    private Bitmap decodeBase64ToBitmap(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            String[] imageParts = base64Image.split(",");
            if (imageParts.length == 2) {
                byte[] imageBytes = Base64.decode(imageParts[1], Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            }
        }
        return null;
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
