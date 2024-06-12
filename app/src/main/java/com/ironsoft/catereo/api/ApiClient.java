package com.ironsoft.catereo.api;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.Log;

import com.ironsoft.catereo.BuildConfig;
import com.ironsoft.catereo.api.Helpers.User;
import com.ironsoft.catereo.api.Helpers.UserManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://gosciniec-api.catereo.pl:44349/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS); // Wybierz poziom logowania, np. BODY

            String authToken = UserManager.getUserToken(context);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BearerTokenInterceptor(authToken))
                    .addInterceptor(interceptor)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getAuthService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

