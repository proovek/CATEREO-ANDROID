package com.ironsoft.catereo.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.ironsoft.catereo.R;
import com.ironsoft.catereo.SharedViewModel;
import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetCurrentUserDetails;
import com.ironsoft.catereo.api.GET.GetMenuItemsDayByDay;
import com.ironsoft.catereo.api.GET.GetProductCategories;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CustomerCompanyDTO;
import com.ironsoft.catereo.api.Helpers.Ingredient;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.UserManager;
import com.ironsoft.catereo.databinding.FragmentHomeBinding;
import com.ironsoft.catereo.helpers.BannerListViewAdapter;
import com.ironsoft.catereo.helpers.CategoriesListViewAdapter;
import com.ironsoft.catereo.helpers.FoodItemModel;
import com.ironsoft.catereo.helpers.FoodListAdapter;
import com.ironsoft.catereo.api.Helpers.Category;
import com.ironsoft.catereo.ui.menu.MenuFragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView bannerView;

    private RecyclerView foodView;
    private CategoriesListViewAdapter adapter;
    private BannerListViewAdapter bannerAdapter;
    private FoodListAdapter foodListAdapter;
    private FragmentHomeBinding binding;
    private ImageView avatarImageView;
    private TextView greetingTextView;
    private TextView deliveryAddress;
    private Button categoriesButton;
    private Button foodButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Ustawienie Retrofit Klucza Api
        Retrofit retrofit = ApiClient.getClient(getContext());
        ApiService apiService = retrofit.create(ApiService.class);

        greetingTextView = view.findViewById(R.id.greetingTextView);
        String FullDisplayName = UserManager.getUserDisplayname(getContext());
        String[] partsDisplayName = FullDisplayName.split(" ");
        if(partsDisplayName.length > 0 ){
            String firstName = partsDisplayName[0];
            greetingTextView.setText("Cześć, " + firstName);
        }

        deliveryAddress = view.findViewById(R.id.deliveryAddress);

        categoriesButton = view.findViewById(R.id.categoriesButton);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_currentFragment_to_menuFragment, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_home, true)
                        .build());
            }
        });

        foodButton = view.findViewById(R.id.foodButton);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_currentFragment_to_menuFragment, null, new NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_home, true)
                        .build());
            }
        });


        avatarImageView = view.findViewById(R.id.avatarImageView);
        avatarImageView.setImageBitmap(generateAvatarFromName(UserManager.getUserName(getContext()).toUpperCase()));

        recyclerView = view.findViewById(R.id.categories_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        bannerView = view.findViewById(R.id.banner_list_view);
        bannerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        foodView = view.findViewById(R.id.food_list_view);
        foodView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        Bitmap budowlankaBitmapa = BitmapFactory.decodeResource(getResources(), R.drawable.budowlanka_billboard);
        Bitmap imprezyBitmapa = BitmapFactory.decodeResource(getResources(), R.drawable.imprezy_billboard);
        Bitmap kwateryBitmapa = BitmapFactory.decodeResource(getResources(), R.drawable.kwatery_billboard);

        Bitmap przeskalowanaBudowlanka = przeskalujBitmapę(budowlankaBitmapa, 400, 225);
        Bitmap przeskalowanaImprezy = przeskalujBitmapę(imprezyBitmapa, 400, 225);
        Bitmap przeskalowanaKwatery = przeskalujBitmapę(kwateryBitmapa, 400, 225);

        List<BannerListViewAdapter.BannerData> yourBannerDataList = new ArrayList<>();
        yourBannerDataList.add(new BannerListViewAdapter.BannerData(przeskalowanaBudowlanka));
        yourBannerDataList.add(new BannerListViewAdapter.BannerData(przeskalowanaImprezy));
        yourBannerDataList.add(new BannerListViewAdapter.BannerData(przeskalowanaKwatery));

        List<CategoriesListViewAdapter.ItemData> yourDataList = new ArrayList<>();
        adapter = new CategoriesListViewAdapter(getContext(), yourDataList);
        recyclerView.setAdapter(adapter);

        bannerAdapter = new BannerListViewAdapter(getContext(), yourBannerDataList);
        bannerView.setAdapter(bannerAdapter);

        List<FoodItemModel> yourFoodListData = new ArrayList<>();
        foodListAdapter = new FoodListAdapter(getContext(), yourFoodListData, getChildFragmentManager());
        foodView.setAdapter(foodListAdapter);

        //Dodawanie danych do tabel:
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
            List<FoodItemModel> limitedList = new ArrayList<>();
            if (yourFoodListData.size() > 5) {
                // Użyj subList, aby uzyskać nową listę zawierającą tylko pierwsze 5 elementów oryginalnej listy
                limitedList = yourFoodListData.subList(0, 5);
            } else {
                // Jeśli lista ma 5 lub mniej elementów, użyj jej bez zmian
                limitedList = new ArrayList<>(yourFoodListData);
            }
            foodListAdapter.updateData(limitedList);
            foodListAdapter.notifyDataSetChanged();
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

                yourDataList.add(new CategoriesListViewAdapter.ItemData(categoryObject.getCategoryName(), bitmapImage));
            }

            adapter.notifyDataSetChanged();
        });

        GetCurrentUserDetails getCurrentUserDetails = new GetCurrentUserDetails(apiService);
        getCurrentUserDetails.execute(new GetCurrentUserDetails.GetProductCategoriesCallback() {
            @Override
            public void onSuccess(Call<CurrentUserDetails> call, Response<CurrentUserDetails> response) {
                CurrentUserDetails currentUserDetails = response.body();
                List<CustomerCompanyDTO> companyList = currentUserDetails.getUser().getCustomerCompanyDTO();

                    for (CustomerCompanyDTO company : companyList) {
                        if(company != null) {
                            deliveryAddress.setText(company.getAddress() != null ? company.getAddress() : "Brak adresu");
                        }else {
                            deliveryAddress.setText("Brak adresu");
                        }
                    }

            }

            @Override
            public void onError(Call<CurrentUserDetails> call, Throwable t) {

            }
        });

        return view;
    }

    private Bitmap przeskalujBitmapę(Bitmap oryginalnaBitmapa, int nowaSzerokosc, int nowaWysokosc) {
        return Bitmap.createScaledBitmap(oryginalnaBitmapa, nowaSzerokosc, nowaWysokosc, true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Bitmap generateAvatarFromName(String name) {
        // Tworzenie bitmapy z tłem jasnoniebieskim
        Bitmap backgroundBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
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
