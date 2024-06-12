package com.ironsoft.catereo.api.GET;
import com.ironsoft.catereo.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import com.google.gson.JsonObject;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;

import java.util.List;

public class GetMenuItemsDayByDay {

    private ApiService apiService;

    public GetMenuItemsDayByDay(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface GetMenuItemsCallback {
        void onSuccess(Call<List<MenuItemObject>> call, Response<List<MenuItemObject>> response);

        void onError(Call<List<MenuItemObject>> call, Throwable t);
    }

    public void execute(final GetMenuItemsCallback callback) {
        Call<List<MenuItemObject>> call = apiService.getMenuItems(); // Używamy zdefiniowanego authToken z ApiClient

        call.enqueue(new Callback<List<MenuItemObject>>() {
            @Override
            public void onResponse(Call<List<MenuItemObject>> call, Response<List<MenuItemObject>> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<List<MenuItemObject>> call, Throwable t) {
                callback.onError(call, t);
            }
        });
    }
}

