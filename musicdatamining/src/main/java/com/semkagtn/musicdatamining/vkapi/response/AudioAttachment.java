package com.semkagtn.musicdatamining.vkapi.response;

import com.semkagtn.musicdatamining.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 31.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioAttachment {

    @JsonProperty("audio")
    private AudioItem audio;

    public AudioItem getAudio() {
        return audio;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
