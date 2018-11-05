package com.example.mieib.popularmoviestage2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mieib.popularmoviestage2.Communicator.Switcher;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie.Result;
import com.example.mieib.popularmoviestage2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {

    private Context context;
    private List<Result> results;
    private String baseImageUrl = "http://image.tmdb.org/t/p/w185/";

    private Switcher switcher;

    public MovieAdapter(Context context,List<Result> results){

        this.context = context;
        this.results = results;
        this.switcher = (Switcher)context;
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.movie_item,viewGroup,false);

        return new MoviewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder moviewViewHolder, int i) {

        Picasso.get().load(baseImageUrl+results.get(i).posterPath).into(moviewViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class MoviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_image)ImageView imageView;

        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switcher.getPosition(getAdapterPosition());
                }
            });

        }

    }


}
