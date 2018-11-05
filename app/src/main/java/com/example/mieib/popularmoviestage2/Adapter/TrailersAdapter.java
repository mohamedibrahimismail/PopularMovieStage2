package com.example.mieib.popularmoviestage2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mieib.popularmoviestage2.Communicator.Get_youtubeVideo;
import com.example.mieib.popularmoviestage2.Communicator.Switcher;
import com.example.mieib.popularmoviestage2.Data.Remote.Model.Trailers.Result;
import com.example.mieib.popularmoviestage2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private Context context;
    private List<Result> results;

    private Get_youtubeVideo getyoutubeVideo;



    public TrailersAdapter(Context context, List<Result> results){

        this.context = context;
        this.results = results;
        getyoutubeVideo  = (Get_youtubeVideo)context;

    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.trailer_item,viewGroup,false);

        return new TrailerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder moviewViewHolder, int i) {


        moviewViewHolder.trailer_text.setText("Trailer "+(i+1));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class TrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_text)TextView trailer_text;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getyoutubeVideo.goto_video(results.get(getAdapterPosition()).key);
                }
            });

        }

    }


}
