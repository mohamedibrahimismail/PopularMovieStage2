package com.example.mieib.popularmoviestage2.Data.Remote;

import com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie.MovieModel;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Review.Review;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Trailers.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("3/movie/{sort_type}")
    Call<MovieModel>getMovies(@Path("sort_type") String SortType);

    @GET("3/movie/{movie_id}/videos")
    Call<Trailer>getMovieTrailers(@Path("movie_id") String movie_id);

    @GET("3/movie/{movie_id}/reviews")
    Call<Review>getMovieReviews(@Path("movie_id") String movie_id);

}
