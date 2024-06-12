package com.ironsoft.catereo.api.GET;
import com.ironsoft.catereo.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import com.google.gson.JsonObject;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.Order;

import java.util.List;

public class GetOrdersForCurrentUser {

    private ApiService apiService;

    public GetOrdersForCurrentUser(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface GetProductCategoriesCallback {
        void onSuccess(Call<List<Order>> call, Response<List<Order>> response);

        void onError(Call<List<Order>> call, Throwable t);
    }

    public void execute(final GetProductCategoriesCallback callback) {
        Call<List<Order>> call = apiService.getOrderItems(); // Używamy zdefiniowanego authToken z ApiClient

        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                callback.onError(call, t);
            }
        });
    }
}

