
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Review;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("page")
    @Expose
    public Long page;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("total_pages")
    @Expose
    public Long totalPages;
    @SerializedName("total_results")
    @Expose
    public Long totalResults;

    public Review withId(Long id) {
        this.id = id;
        return this;
    }

    public Review withPage(Long page) {
        this.page = page;
        return this;
    }

    public Review withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public Review withTotalPages(Long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Review withTotalResults(Long totalResults) {
        this.totalResults = totalResults;
        return this;
    }

}
