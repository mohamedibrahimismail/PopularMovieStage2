
package com.example.mieib.popularmoviestage2.Data.Remote.Model.Trailers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("iso_639_1")
    @Expose
    public String iso6391;
    @SerializedName("iso_3166_1")
    @Expose
    public String iso31661;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("site")
    @Expose
    public String site;
    @SerializedName("size")
    @Expose
    public Long size;
    @SerializedName("type")
    @Expose
    public String type;

    public Result withId(String id) {
        this.id = id;
        return this;
    }

    public Result withIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public Result withIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public Result withKey(String key) {
        this.key = key;
        return this;
    }

    public Result withName(String name) {
        this.name = name;
        return this;
    }

    public Result withSite(String site) {
        this.site = site;
        return this;
    }

    public Result withSize(Long size) {
        this.size = size;
        return this;
    }

    public Result withType(String type) {
        this.type = type;
        return this;
    }

}
