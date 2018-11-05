
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Movie;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("page")
    @Expose
    public Long page;
    @SerializedName("total_results")
    @Expose
    public Long totalResults;
    @SerializedName("total_pages")
    @Expose
    public Long totalPages;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    public MovieModel withPage(Long page) {
        this.page = page;
        return this;
    }

    public MovieModel withTotalResults(Long totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public MovieModel withTotalPages(Long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public MovieModel withResults(List<Result> results) {
        this.results = results;
        return this;
    }

}
