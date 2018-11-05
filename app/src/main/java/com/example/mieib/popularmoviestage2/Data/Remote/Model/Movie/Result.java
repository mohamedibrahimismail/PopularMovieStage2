
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("vote_count")
    @Expose
    public Long voteCount;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    public List<Long> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    public Result withVoteCount(Long voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Result withId(Long id) {
        this.id = id;
        return this;
    }

    public Result withVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Result withVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public Result withTitle(String title) {
        this.title = title;
        return this;
    }

    public Result withPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public Result withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public Result withOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public Result withOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public Result withGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public Result withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Result withAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public Result withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public Result withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

}
