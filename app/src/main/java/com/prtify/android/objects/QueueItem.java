package com.prtify.android.objects;

/**
 * Created by leshan on 11/5/17.
 */

public class QueueItem {

    final String name, album, artist, image, id;

    public QueueItem(String name, String album, String artist, String image, String id) {
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }
}
