package com.semkagtn.lastfm.vkapi.response;

import com.semkagtn.lastfm.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by semkagtn on 31.08.15.
 */
public class FriendsGetResponse extends BaseVkResponse {

    @JsonProperty("response")
    private FriendsList response;

    public FriendsList getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
