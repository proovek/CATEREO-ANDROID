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

import java.util.List;

public class GetCurrentUserDetails {

    private ApiService apiService;

    public GetCurrentUserDetails(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface GetProductCategoriesCallback {
        void onSuccess(Call<CurrentUserDetails> call, Response<CurrentUserDetails> response);

        void onError(Call<CurrentUserDetails> call, Throwable t);
    }

    public void execute(final GetProductCategoriesCallback callback) {
        Call<CurrentUserDetails> call = apiService.getCurrentUserDetails(); // Używamy zdefiniowanego authToken z ApiClient

        call.enqueue(new Callback<CurrentUserDetails>() {
            @Override
            public void onResponse(Call<CurrentUserDetails> call, Response<CurrentUserDetails> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<CurrentUserDetails> call, Throwable t) {
                callback.onError(call, t);
            }
        });
    }
}

