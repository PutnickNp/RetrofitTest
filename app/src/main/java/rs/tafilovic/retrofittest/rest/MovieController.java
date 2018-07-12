package rs.tafilovic.retrofittest.rest;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import rs.tafilovic.retrofittest.model.Movies;

public class MovieController extends BaseController implements Callback<Movies> {

    private WeakReference<RestCallback<Movies, Throwable>> weakCallback;

    public MovieController(RestCallback<Movies, Throwable> callback) {
        super();
        this.weakCallback = new WeakReference<>(callback);
    }

    public void getMovies(String page) {
        retrofit.create(MovieApi.class).getMovies(page).enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Movies> call, @NonNull retrofit2.Response<Movies> response) {
        if (response.isSuccessful() && weakCallback.get() != null) {
            weakCallback.get().onResult(response.body());
        }
    }

    @Override
    public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
        if (weakCallback.get() != null) {
            weakCallback.get().onError(t);
        }
    }
}
