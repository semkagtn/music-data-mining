package com.semkagtn.musicdatamining.vkapi.response;

import com.semkagtn.musicdatamining.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 12.09.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountersItem {

    @JsonProperty("audios")
    private Integer audios;

    @JsonProperty("friends")
    private Integer friends;

    public Integer getAudios() {
        return audios;
    }

    public Integer getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
