package com.semkagtn.lastfm.utils;

import com.semkagtn.lastfm.Artists;
import com.semkagtn.lastfm.Tags;
import com.semkagtn.lastfm.Tracks;
import com.semkagtn.lastfm.Users;
import com.semkagtn.lastfm.lastfmapi.response.ArtistItem;
import com.semkagtn.lastfm.lastfmapi.response.TagItem;
import com.semkagtn.lastfm.lastfmapi.response.TrackItem;
import com.semkagtn.lastfm.vkapi.response.UserItem;

/**
 * Created by semkagtn on 08.09.15.
 */
public class EntityConverter {

    public static Users convertUser(UserItem userItem) {
        Users users = new Users();
        users.setId(userItem.getId());
        users.setGender(userItem.getSex() == 1 ? "f" : userItem.getSex() == 2 ? "m" : null);
        users.setBirthday(DateTimeUtils.birthdayToUnixTime(userItem.getBdate()));
        return users;
    }

    public static Artists convertArtist(ArtistItem artistItem) {
        Artists artists = new Artists();
        artists.setArtistName(artistItem.getName());
        artists.setId(HashUtils.md5(artistItem.getName()));
        return artists;
    }

    public static Tracks convertTrack(TrackItem trackItem) {
        Tracks tracks = new Tracks();
        tracks.setTrackName(trackItem.getName());
        String artistName = trackItem.getArtist() != null ? trackItem.getArtist().getName() : "";
        tracks.setId(HashUtils.md5(trackItem.getName() + artistName));
        return tracks;
    }

    public static Tags convertTag(TagItem tagItem) {
        Tags tags = new Tags();
        tags.setTagName(tagItem.getName());
        tags.setId(HashUtils.md5(tagItem.getName()));
        return tags;
    }

    private EntityConverter() {

    }
}
