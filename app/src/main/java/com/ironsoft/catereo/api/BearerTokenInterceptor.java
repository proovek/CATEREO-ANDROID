package com.ironsoft.catereo.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BearerTokenInterceptor implements Interceptor {
    private final String authToken;

    public BearerTokenInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // Dodaj nagłówek Bearer z tokenem
        Request authorizedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + authToken)
                .build();

        return chain.proceed(authorizedRequest);
    }
}

