package com.ironsoft.catereo.api.GET;
import com.ironsoft.catereo.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import com.google.gson.JsonObject;
import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;

import java.util.List;

public class GetProductCategories {

    private ApiService apiService;

    public GetProductCategories(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface GetProductCategoriesCallback {
        void onSuccess(Call<List<CategoryObject>> call, Response<List<CategoryObject>> response);

        void onError(Call<List<CategoryObject>> call, Throwable t);
    }

    public void execute(final GetProductCategoriesCallback callback) {
        Call<List<CategoryObject>> call = apiService.getCategoryItems(); // Używamy zdefiniowanego authToken z ApiClient

        call.enqueue(new Callback<List<CategoryObject>>() {
            @Override
            public void onResponse(Call<List<CategoryObject>> call, Response<List<CategoryObject>> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<List<CategoryObject>> call, Throwable t) {
                callback.onError(call, t);
            }
        });
    }
}

