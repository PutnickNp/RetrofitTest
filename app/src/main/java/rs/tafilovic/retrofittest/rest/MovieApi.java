package rs.tafilovic.retrofittest.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rs.tafilovic.retrofittest.model.Movies;

public interface MovieApi {

    @GET("movie/now_playing/")
    Call<Movies> getMovies(@Query("number") String number);
}
