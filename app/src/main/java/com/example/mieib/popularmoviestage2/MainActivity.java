package com.example.mieib.popularmoviestage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mieib.popularmoviestage2.Adapter.FavoritesAdapter;
import com.example.mieib.popularmoviestage2.Adapter.MovieAdapter;
import com.example.mieib.popularmoviestage2.Communicator.FavoriteSwitcher;
import com.example.mieib.popularmoviestage2.Communicator.Switcher;
import com.example.mieib.popularmoviestage2.Data.Local.Favorite;
import com.example.mieib.popularmoviestage2.Data.Local.FavoriteViewModel;
import com.example.mieib.popularmoviestage2.Data.Remote.ApiClient;
import com.example.mieib.popularmoviestage2.Data.Remote.ApiInterface;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie.MovieModel;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements Switcher,FavoriteSwitcher{

    public  ApiInterface apiInterface;
    public  MovieModel movieModel;
    public  List<Result> results;

    String popular_movies = "popular";
    String top_rated_movies = "top_rated";
    String default_RatedMovies = popular_movies;


    @BindView(R.id.recyclerview)RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        results = new ArrayList<>();
        getMovies(default_RatedMovies);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if(item_id==R.id.topRated){
            getMovies(top_rated_movies);
            return true;
        }else if(item_id==R.id.Popular){
            getMovies(popular_movies);
            return true;
        }else if(item_id==R.id.Favorite){
            hadleFavoriteList();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void hadleFavoriteList() {

        FavoriteViewModel favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAllNotes().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(@Nullable List<Favorite> movies) {
                update_UI(movies);
            }
        });

    }

    private void update_UI(List<Favorite> movies) {


        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this,movies);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(favoritesAdapter);

    }

    private void getMovies(String sort_type) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<MovieModel> call = apiInterface.getMovies(sort_type);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                movieModel = response.body();
                results = movieModel.results;
                updateUI();
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

    }


    private void updateUI() {

        MovieAdapter movieAdapter = new MovieAdapter(this,results);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(movieAdapter);

    }


    @Override
    public void getPosition(int position) {


        Intent intent = new Intent(MainActivity.this,Detailes.class);
        intent.putExtra("id",results.get(position).id+"");
        intent.putExtra("title",results.get(position).originalTitle);
        intent.putExtra("poster",results.get(position).posterPath);
        intent.putExtra("release_date",results.get(position).releaseDate);
        intent.putExtra("rate",results.get(position).voteAverage);
        intent.putExtra("overview",results.get(position).overview);

        startActivity(intent);
    }

    @Override
    public void getFavoritePosition(Favorite favorite) {

        Intent intent = new Intent(MainActivity.this,Detailes.class);
        intent.putExtra("id",favorite.getId()+"");
        intent.putExtra("title", favorite.getTitle());
        intent.putExtra("poster",favorite.getPoster());
        intent.putExtra("release_date",favorite.getRelease_date());
        intent.putExtra("rate",favorite.getRating());
        intent.putExtra("overview",favorite.getSynopsis());

        startActivity(intent);

    }
}
