package com.example.mieib.popularmoviestage2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mieib.popularmoviestage2.Communicator.Get_youtubeVideo;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Review.Result;
import com.example.mieib.popularmoviestage2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private Context context;
    private List<Result> results;

    private Get_youtubeVideo getyoutubeVideo;



    public ReviewsAdapter(Context context, List<Result> results){

        this.context = context;
        this.results = results;
        getyoutubeVideo  = (Get_youtubeVideo)context;

    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.review_item,viewGroup,false);

        return new ReviewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder moviewViewHolder, int i) {


        moviewViewHolder.auther_name.setText(results.get(i).author);
        moviewViewHolder.review_content.setText(results.get(i).content);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.auther_name)TextView auther_name;
        @BindView(R.id.auther_reviewContent)TextView review_content;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }

    }


}
