package com.prtify.android.objects;

/**
 * Created by leshan on 11/5/17.
 */

public class RequestItem {

    String album, artist, name;

    public RequestItem(String album, String artist, String name) {
        this.album = album;
        this.artist = artist;
        this.name = name;
    }

    public RequestItem() {
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }
}
