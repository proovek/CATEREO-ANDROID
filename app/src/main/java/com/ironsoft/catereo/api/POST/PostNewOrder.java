package com.ironsoft.catereo.api.POST;

import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.POST.Helpers.NewOrder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostNewOrder {

    private ApiService apiService;

    public PostNewOrder(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface PostNewOrderCallback {
        void onSuccess(Call<Void> call, Response<Void> response);

        void onError(Call<Void> call, Throwable t);
    }

    public void execute(NewOrder orderData, final PostNewOrderCallback callback) {
        Call<Void> call = apiService.sendOrder(orderData); // Przekazujemy obiekt Order zawierający dane zamówienia

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(call, t);
            }
        });
    }
}
