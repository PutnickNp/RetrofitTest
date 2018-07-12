package rs.tafilovic.retrofittest.rest;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class BaseController {

    Retrofit retrofit = null;

    BaseController() {
        create();
    }

    private void create() {
        Gson gson = new GsonBuilder()
            .setLenient()
            .create();

        retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder builder = request.url().newBuilder();

                //add key parameter
                HttpUrl url = builder.addQueryParameter("api_key", "f2abe899aca398633b6ad6d2b6f12fb9").build();

                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();
    }
}
