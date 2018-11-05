package com.example.mieib.popularmoviestage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mieib.popularmoviestage2.Adapter.ReviewsAdapter;
import com.example.mieib.popularmoviestage2.Adapter.TrailersAdapter;
import com.example.mieib.popularmoviestage2.Communicator.Get_youtubeVideo;

import com.example.mieib.popularmoviestage2.Data.Local.Favorite;
import com.example.mieib.popularmoviestage2.Data.Local.FavoriteViewModel;
import com.example.mieib.popularmoviestage2.Data.Remote.ApiClient;
import com.example.mieib.popularmoviestage2.Data.Remote.ApiInterface;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie.MovieModel;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Review.Review;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Trailers.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailes extends AppCompatActivity implements Get_youtubeVideo {

    @BindView(R.id.movie_name)
    TextView title;
    @BindView(R.id.realse_date)
    TextView release_date;
    @BindView(R.id.rating)
    TextView rate;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.movie_poster)
    ImageView poster;

    @BindView(R.id.trailers_recyclerview)RecyclerView trailer_Recycler;
    @BindView(R.id.reviews_recyclerview)RecyclerView review_Recycler;

    @BindView(R.id.Favorite)ImageButton imageButton;

    private String baseImageUrl = "http://image.tmdb.org/t/p/w185/";

    private String Movie_id ;
    private String Movie_title;
    private String poster_path;
    private String synopsis;
    private String rating;
    private String date;


    public  ApiInterface apiInterface;

    boolean isfavorite = false;

    private FavoriteViewModel favoriteViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);


        ButterKnife.bind(this);

        Intent intent =getIntent();
        getDataFromIntent(intent);


    }

    public void getDataFromIntent(Intent intent){

        if(intent.hasExtra("id")){
            Movie_id = intent.getStringExtra("id");

        }

        if(intent.hasExtra("title")){
            Movie_title = intent.getStringExtra("title");
            title.setText(Movie_title);
        }
        if(intent.hasExtra("poster")){
            poster_path = intent.getStringExtra("poster");
            Picasso.get().load(baseImageUrl+poster_path).placeholder(R.drawable.ic_imageholder).into(poster);

        }
        if(intent.hasExtra("release_date")){
            date = intent.getStringExtra("release_date");
            release_date.setText(date);
        }
        if(intent.hasExtra("rate")){
            rating = intent.getDoubleExtra("rate",0)+"";
            rate.setText(rating+"/10");
        }
        if(intent.hasExtra("overview")){
            synopsis = intent.getStringExtra("overview");
            overview.setText(synopsis);
        }

        Load_trailers(Movie_id);
        Load_reviews(Movie_id);
        setup_room(Integer.parseInt(Movie_id));

    }

    public void Load_trailers(String id){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Trailer> call = apiInterface.getMovieTrailers(id);

        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                Trailer trailer = response.body();
                updateTrailersUI(trailer);
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {

            }
        });


    }

    private void updateTrailersUI(Trailer trailer) {

        TrailersAdapter trailersAdapter = new TrailersAdapter(this,trailer.results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        trailer_Recycler.setLayoutManager(layoutManager);
        trailer_Recycler.setAdapter(trailersAdapter);


    }

    @Override
    public void goto_video(String id) {

      watchYoutubeVideo(this,id);

    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public void Load_reviews(String id){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Review> call = apiInterface.getMovieReviews(id);

        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Review review = response.body();
                updateReviewUI(review);
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });


    }

    public void updateReviewUI(Review review){

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this,review.results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        review_Recycler.setLayoutManager(layoutManager);
        review_Recycler.setAdapter(reviewsAdapter);

    }


    @OnClick(R.id.Favorite)
    public void Favorite_Clicked(){
     /*   if(!toggle){
            imageButton.setImageResource(R.drawable.ic_favorite);
            toggle=true;
        }else {
            imageButton.setImageResource(R.drawable.ic_favorite_border);
            toggle = false;
        }*/

     if(isfavorite){
         favoriteViewModel.delete(new Favorite(Integer.parseInt(Movie_id), Movie_title,poster_path,synopsis,rating,date));
     }else {
         favoriteViewModel.insert(new Favorite(Integer.parseInt(Movie_id), Movie_title,poster_path,synopsis,rating,date));
     }

    }


    public void setup_room(int id){

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getFavoriteMovie(id).observe(this, new Observer<Favorite>() {
            @Override
            public void onChanged(@Nullable Favorite note) {
                update_UI(note);
            }
        });


    }

    private void update_UI(Favorite note) {

        if(note!=(null)){
            isfavorite=true;
            imageButton.setImageResource(R.drawable.ic_favorite);
        }else {
            isfavorite=false;
            imageButton.setImageResource(R.drawable.ic_favorite_border);
        }


    }


}
