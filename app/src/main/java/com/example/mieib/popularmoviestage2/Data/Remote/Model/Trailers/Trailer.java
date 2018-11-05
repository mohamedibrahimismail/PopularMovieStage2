
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Trailers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    public Trailer withId(Long id) {
        this.id = id;
        return this;
    }

    public Trailer withResults(List<Result> results) {
        this.results = results;
        return this;
    }

}
