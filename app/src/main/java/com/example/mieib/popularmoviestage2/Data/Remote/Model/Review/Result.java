
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;

    public Result withAuthor(String author) {
        this.author = author;
        return this;
    }

    public Result withContent(String content) {
        this.content = content;
        return this;
    }

    public Result withId(String id) {
        this.id = id;
        return this;
    }

    public Result withUrl(String url) {
        this.url = url;
        return this;
    }

}
