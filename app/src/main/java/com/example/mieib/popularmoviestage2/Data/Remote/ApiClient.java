package com.example.mieib.popularmoviestage2.Data.Remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String api_key = "api_key";
    public static Retrofit retrofit = null;


    public static Retrofit getApiClient(){
        if(retrofit==null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl httpUrl = request.url();

                            HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter(api_key,API_KEY.Api_Key).build();

                            Request.Builder builder = request.newBuilder().url(newHttpUrl);
                            Request request1 = builder.build();

                            return chain.proceed(request1);
                        }
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
