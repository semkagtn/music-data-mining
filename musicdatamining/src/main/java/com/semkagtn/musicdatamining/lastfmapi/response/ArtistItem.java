package com.semkagtn.musicdatamining.lastfmapi.response;

import com.semkagtn.musicdatamining.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 02.09.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("tags")
    private TagsItem tags;

    public String getName() {
        return name;
    }

    public TagsItem getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}