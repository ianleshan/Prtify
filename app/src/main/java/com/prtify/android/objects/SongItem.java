package com.prtify.android.objects;

/**
 * Created by leshan on 11/6/17.
 */

public class SongItem {

    String name, artist, id;

    public SongItem(String name, String artist, String id) {
        this.name = name;
        this.artist = artist;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        SongItem o = (SongItem) obj;
        return getId().equals(o.getId());
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getId() {
        return id;
    }
}
