package com.ironsoft.catereo.api;

import com.google.gson.JsonObject;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.LoginCredentials;
import com.ironsoft.catereo.api.Helpers.LoginDetails;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.Order;
import com.ironsoft.catereo.api.POST.Helpers.NewOrder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("menuitems/menuitemsDayByDay")
    Call<List<MenuItemObject>> getMenuItems();

    @GET("productcategories")
    Call<List<CategoryObject>> getCategoryItems();
    @POST("Authenticate/login")
    Call<LoginDetails> login(@Body LoginCredentials credentials);

    @GET("Authenticate/getCurrentUser")
    Call<CurrentUserDetails> getCurrentUserDetails();

    @GET("orders/ForCurrentUser")
    Call<List<Order>> getOrderItems();

    @POST("orders")
    Call<Void> sendOrder(@Body NewOrder body);

}

