package com.prtify.android.response;

import java.util.List;

/**
 * Created by leshan on 11/4/17.
 */

public class CurrentlyPlayingResponse {

    final String id;
    final boolean is_playing;
    final CurrentItem item;
    final int progress_ms;

    public CurrentlyPlayingResponse(String id, boolean is_playing, CurrentItem item, int progress_ms) {
        this.id = id;
        this.is_playing = is_playing;
        this.item = item;
        this.progress_ms = progress_ms;
    }

    public int getTimeLeft(){
        return item.getDuration_ms() - progress_ms;
    }

    public String getSongName(){
        return item.getName();
    }

    public String getAlbumName(){
        return item.getAlbum().getName();
    }

    public String getArtistName(){
        String result = "";
        for(CurrentArtist artist: item.getArtists()){
            result +=  artist.getName() + ", ";
        }
        return result;
    }

    public String getAlbumPicture(){
        return item.getAlbum().getImages().get(0).getUrl();
    }

    private class CurrentItem {
        final CurrentAlbum album;
        final List<CurrentArtist> artists;
        final String name;
        final int duration_ms;

        public CurrentItem(CurrentAlbum album, List<CurrentArtist> artists, String name, int duration_ms) {
            this.album = album;
            this.artists = artists;
            this.name = name;
            this.duration_ms = duration_ms;
        }

        public int getDuration_ms() {
            return duration_ms;
        }

        public CurrentAlbum getAlbum() {
            return album;
        }

        public List<CurrentArtist> getArtists() {
            return artists;
        }

        public String getName() {
            return name;
        }
    }

    private class CurrentAlbum {
        final String name;
        final List<CurrentImage> images;

        public CurrentAlbum(String name, List<CurrentImage> images) {
            this.name = name;
            this.images = images;
        }

        public String getName() {
            return name;
        }

        public List<CurrentImage> getImages() {
            return images;
        }
    }

    private class CurrentArtist {
        final String name;

        public CurrentArtist(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private class CurrentImage {
        final int height;
        final int width;
        final String url;

        public CurrentImage(int height, int width, String url) {
            this.height = height;
            this.width = width;
            this.url = url;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public String getUrl() {
            return url;
        }
    }
}
/**
{
  "context": {
    "external_urls" : {
      "spotify" : "http://open.spotify.com/user/spotify/playlist/49znshcYJROspEqBoHg3Sv"
    },
    "href" : "https://api.spotify.com/v1/users/spotify/playlists/49znshcYJROspEqBoHg3Sv",
    "type" : "playlist",
    "uri" : "spotify:user:spotify:playlist:49znshcYJROspEqBoHg3Sv"
  },
  "timestamp": 1490252122574,
  "progress_ms": 44272,
  "is_playing": true,
  "item": {
    "album": {
      "album_type": "album",
      "external_urls": {
        "spotify": "https://open.spotify.com/album/6TJmQnO44YE5BtTxH8pop1"
      },
      "href": "https://api.spotify.com/v1/albums/6TJmQnO44YE5BtTxH8pop1",
      "id": "6TJmQnO44YE5BtTxH8pop1",
      "images": [
        {
          "height": 640,
          "url": "https://i.scdn.co/image/8e13218039f81b000553e25522a7f0d7a0600f2e",
          "width": 629
        },
        {
          "height": 300,
          "url": "https://i.scdn.co/image/8c1e066b5d1045038437d92815d49987f519e44f",
          "width": 295
        },
        {
          "height": 64,
          "url": "https://i.scdn.co/image/d49268a8fc0768084f4750cf1647709e89a27172",
          "width": 63
        }
      ],
      "name": "Hot Fuss",
      "type": "album",
      "uri": "spotify:album:6TJmQnO44YE5BtTxH8pop1"
    },
    "artists": [
      {
        "external_urls": {
          "spotify": "https://open.spotify.com/artist/0C0XlULifJtAgn6ZNCW2eu"
        },
        "href": "https://api.spotify.com/v1/artists/0C0XlULifJtAgn6ZNCW2eu",
        "id": "0C0XlULifJtAgn6ZNCW2eu",
        "name": "The Killers",
        "type": "artist",
        "uri": "spotify:artist:0C0XlULifJtAgn6ZNCW2eu"
      }
    ],
    "available_markets": [
      "AD",
      "AR",
  ...
      "TW",
      "UY"
    ],
    "disc_number": 1,
    "duration_ms": 222075,
    "explicit": false,
    "external_ids": {
      "isrc": "USIR20400274"
    },
    "external_urls": {
      "spotify": "https://open.spotify.com/track/0eGsygTp906u18L0Oimnem"
    },
    "href": "https://api.spotify.com/v1/tracks/0eGsygTp906u18L0Oimnem",
    "id": "0eGsygTp906u18L0Oimnem",
    "name": "Mr. Brightside",
    "popularity": 0,
    "preview_url": "http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6",
    "track_number": 2,
    "type": "track",
    "uri": "spotify:track:0eGsygTp906u18L0Oimnem"
  }
}
 */