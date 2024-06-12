package com.ironsoft.catereo;

import static java.security.AccessController.getContext;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.GET.GetCurrentUserDetails;
import com.ironsoft.catereo.api.GET.GetMenuItemsDayByDay;
import com.ironsoft.catereo.api.GET.GetOrdersForCurrentUser;
import com.ironsoft.catereo.api.GET.GetProductCategories;
import com.ironsoft.catereo.api.Helpers.Category;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.CustomerCompanyDTO;
import com.ironsoft.catereo.api.Helpers.Ingredient;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.Order;
import com.ironsoft.catereo.api.Helpers.Orderstatus;
import com.ironsoft.catereo.databinding.ActivityMainBinding;
import com.ironsoft.catereo.helpers.CategoriesListViewAdapter;
import com.ironsoft.catereo.helpers.FoodItemModel;
import com.ironsoft.catereo.helpers.OrderItemModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = ApiClient.getClient(this);
        ApiService apiService = retrofit.create(ApiService.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    //Pobieranie danych z serwera i trzymanie w MutableData
        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        GetMenuItemsDayByDay getMenuItemsDayByDay = new GetMenuItemsDayByDay(apiService);
        getMenuItemsDayByDay.execute(new GetMenuItemsDayByDay.GetMenuItemsCallback() {
            @Override
            public void onSuccess(Call<List<MenuItemObject>> call, Response<List<MenuItemObject>> response) {
                List<MenuItemObject> menuItems = response.body();
                if (menuItems != null && !menuItems.isEmpty()) {
                    viewModel.setMenuItemsData(menuItems);
                }
            }

            @Override
            public void onError(Call<List<MenuItemObject>> call, Throwable t) {
                Log.d("error: ", String.valueOf(t));
            }
        });

        GetProductCategories getProductCategories = new GetProductCategories(apiService);
        getProductCategories.execute(new GetProductCategories.GetProductCategoriesCallback() {
            @Override
            public void onSuccess(Call<List<CategoryObject>> call, Response<List<CategoryObject>> response) {
                List<CategoryObject> categoryObjects = response.body();
                if(categoryObjects != null && !categoryObjects.isEmpty())
                {
                    viewModel.setCategoryItemsData(categoryObjects);
                }
            }

            @Override
            public void onError(Call<List<CategoryObject>> call, Throwable t) {

            }
        });

        GetCurrentUserDetails getCurrentUserDetails = new GetCurrentUserDetails(apiService);
        getCurrentUserDetails.execute(new GetCurrentUserDetails.GetProductCategoriesCallback() {
            @Override
            public void onSuccess(Call<CurrentUserDetails> call, Response<CurrentUserDetails> response) {
                CurrentUserDetails currentUserDetails = response.body();
                if(currentUserDetails != null){
                    viewModel.setCurrentUserItemsData(currentUserDetails);
                }
            }

            @Override
            public void onError(Call<CurrentUserDetails> call, Throwable t) {

            }
        });

        GetOrdersForCurrentUser getOrdersForCurrentUser = new GetOrdersForCurrentUser(apiService);
        getOrdersForCurrentUser.execute(new GetOrdersForCurrentUser.GetProductCategoriesCallback() {
            @Override
            public void onSuccess(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orders = response.body();
                if (orders != null && !orders.isEmpty()) {
                    viewModel.setOrderItemsData(orders);
                }
            }

            @Override
            public void onError(Call<List<Order>> call, Throwable t) {

            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_menu, R.id.navigation_profile, R.id.navigation_cart, R.id.navigation_orders)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}