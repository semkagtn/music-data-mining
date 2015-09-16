package com.semkagtn.musicdatamining.lastfmapi.response;

import com.semkagtn.musicdatamining.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 02.09.15.
 */
public class ArtistGetInfoResponse extends BaseLastFmResponse {

    @JsonProperty("artist")
    private ArtistItem artist;

    public ArtistItem getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}