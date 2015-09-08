package com.semkagtn.lastfm.vkapi;

import com.semkagtn.lastfm.httpclient.HttpClient;
import com.semkagtn.lastfm.vkapi.response.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by semkagtn on 30.08.15.
 */
public class VkApi {

    private static final String API_URL = "https://api.vk.com/method/";
    private static final String API_VERSION = "5.37";
    private static final String FIELDS = "sex,bdate";

    private static final String AUDIO_GET = "audio.get";
    private static final String USERS_GET = "users.get";
    private static final String FRIENDS_GET = "friends.get";
    private static final String WALL_GET = "wall.get";

    private HttpClient client;
    private String token;
    private ObjectMapper objectMapper;

    public VkApi(HttpClient client, String token) {
        this.client = client;
        this.token = token;
        this.objectMapper = new ObjectMapper();
    }

    private <T extends BaseVkResponse> T call(String method, List<NameValuePair> parameters, Class<T> resultClass) {
        parameters.add(new BasicNameValuePair("access_token", token));
        parameters.add(new BasicNameValuePair("v", API_VERSION));

        T result = null;
        boolean resultReceived = false;
        while (!resultReceived) {
            String response = client.request(API_URL + method, parameters);
            try {
                result = objectMapper.readValue(response, resultClass);
            } catch (IOException e) {
                throw new VkResponseParseError(e);
            }
            if (result.getError() != null) {
                int errorCode = result.getError().getErrorCode();
                if (errorCode == 6 || errorCode == 9 || errorCode == 10) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // WTF??
                    }
                } else {
                    resultReceived = true;
                }
            } else {
                resultReceived = true;
            }
        }
        return result;
    }

    public static class VkResponseParseError extends Error {

        public VkResponseParseError(Throwable cause) {
            super(cause);
        }
    }

    public UsersGetResponse usersGet(List<String> userIds) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("user_ids", String.join(",", userIds)));
        parameters.add(new BasicNameValuePair("fields", FIELDS));
        return call(USERS_GET, parameters, UsersGetResponse.class);
    }

    public AudioGetResponse audioGet(int ownerId, Integer offset, Integer count) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("owner_id", String.valueOf(ownerId)));
        if (offset != null) {
            parameters.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        }
        if (count != null) {
            parameters.add(new BasicNameValuePair("count", String.valueOf(count)));
        }
        parameters.add(new BasicNameValuePair("need_user", "0"));
        return call(AUDIO_GET, parameters, AudioGetResponse.class);
    }

    public FriendsGetResponse friendsGet(int userId, Integer offset, Integer count) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("user_id", String.valueOf(userId)));
        parameters.add(new BasicNameValuePair("fields", FIELDS));
        if (offset != null) {
            parameters.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        }
        if (count != null) {
            parameters.add(new BasicNameValuePair("count", String.valueOf(count)));
        }
        return call(FRIENDS_GET, parameters, FriendsGetResponse.class);
    }

    public WallGetResponse wallGet(int userId, Integer offset, Integer count, WallGetFilter filter) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("owner_id", String.valueOf(userId)));
        if (offset != null) {
            parameters.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        }
        if (count != null) {
            parameters.add(new BasicNameValuePair("count", String.valueOf(count)));
        }
        parameters.add(new BasicNameValuePair("filter", filter.getValue()));
        return call(WALL_GET, parameters, WallGetResponse.class);
    }
}
