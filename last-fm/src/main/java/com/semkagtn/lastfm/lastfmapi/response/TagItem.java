package com.semkagtn.lastfm.lastfmapi.response;

import com.semkagtn.lastfm.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 02.09.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagItem {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
