package com.prtify.android.response;

import com.prtify.android.objects.QueueItem;

import java.util.List;

/**
 * Created by leshan on 11/5/17.
 */

public class SearchResponse {

    final Tracks tracks;

    public SearchResponse(Tracks tracks) {
        this.tracks = tracks;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public QueueItem getFirst(){
        if(getTracks().getTotal() < 1)return null;
        CurrentItem item = tracks.getItems().get(0);
        return new QueueItem(item.getName(), item.getAlbum().getName(), item.getArtists().get(0).getName(), item.getAlbum().getImages().get(0).getUrl(), item.getId());
    }

    private class Tracks {
        final List<CurrentItem> items;
        final int total;

        public Tracks(List<CurrentItem> items, int total) {
            this.items = items;
            this.total = total;
        }

        public List<CurrentItem> getItems() {
            return items;
        }

        public int getTotal() {
            return total;
        }
    }

    private class CurrentItem {
        final CurrentAlbum album;
        final List<CurrentArtist> artists;
        final String name, id;

        public CurrentItem(CurrentAlbum album, List<CurrentArtist> artists, String name, String id) {
            this.album = album;
            this.artists = artists;
            this.name = name;
            this.id = id;
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

        public String getId() {
            return id;
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
  "tracks" : {
    "href" : "https://api.spotify.com/v1/search?query=Ride+Paradise+Lana+Del+Rey&type=track&market=US&offset=0&limit=20",
    "items" : [ {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/00FQb4jTyendYWaN8pK0wa"
          },
          "href" : "https://api.spotify.com/v1/artists/00FQb4jTyendYWaN8pK0wa",
          "id" : "00FQb4jTyendYWaN8pK0wa",
          "name" : "Lana Del Rey",
          "type" : "artist",
          "uri" : "spotify:artist:00FQb4jTyendYWaN8pK0wa"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TH", "TR", "TW", "US", "UY", "VN" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/5PW8nAtvf2HV8RYZFd4IrX"
        },
        "href" : "https://api.spotify.com/v1/albums/5PW8nAtvf2HV8RYZFd4IrX",
        "id" : "5PW8nAtvf2HV8RYZFd4IrX",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/8d94671b235deefae167c13dd658e8f0ea1d2402",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/34d220e676923f06dcb162cf568a50310fa0dcd5",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/b67a06f05e6db28386b3e793e7f9ccabc6c64db9",
          "width" : 64
        } ],
        "name" : "Born To Die - The Paradise Edition",
        "type" : "album",
        "uri" : "spotify:album:5PW8nAtvf2HV8RYZFd4IrX"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/00FQb4jTyendYWaN8pK0wa"
        },
        "href" : "https://api.spotify.com/v1/artists/00FQb4jTyendYWaN8pK0wa",
        "id" : "00FQb4jTyendYWaN8pK0wa",
        "name" : "Lana Del Rey",
        "type" : "artist",
        "uri" : "spotify:artist:00FQb4jTyendYWaN8pK0wa"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TH", "TR", "TW", "US", "UY", "VN" ],
      "disc_number" : 2,
      "duration_ms" : 289080,
      "explicit" : true,
      "external_ids" : {
        "isrc" : "GBUM71205878"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/3l2S2RkGG4lbh7WqmCV3NB"
      },
      "href" : "https://api.spotify.com/v1/tracks/3l2S2RkGG4lbh7WqmCV3NB",
      "id" : "3l2S2RkGG4lbh7WqmCV3NB",
      "name" : "Ride",
      "popularity" : 58,
      "preview_url" : null,
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:3l2S2RkGG4lbh7WqmCV3NB"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/00FQb4jTyendYWaN8pK0wa"
          },
          "href" : "https://api.spotify.com/v1/artists/00FQb4jTyendYWaN8pK0wa",
          "id" : "00FQb4jTyendYWaN8pK0wa",
          "name" : "Lana Del Rey",
          "type" : "artist",
          "uri" : "spotify:artist:00FQb4jTyendYWaN8pK0wa"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TH", "TR", "TW", "US", "UY", "VN" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/1JnjcAIKQ9TSJFVFierTB8"
        },
        "href" : "https://api.spotify.com/v1/albums/1JnjcAIKQ9TSJFVFierTB8",
        "id" : "1JnjcAIKQ9TSJFVFierTB8",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/fe97d902eef9bc38d3c86c7a7a93ffd98d1e933f",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/9f6891ee0501ecd1b95386dce21c20ea5983384d",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/9a7c11b822c46f51d958e5750c96ad26d550994f",
          "width" : 64
        } ],
        "name" : "Paradise",
        "type" : "album",
        "uri" : "spotify:album:1JnjcAIKQ9TSJFVFierTB8"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/00FQb4jTyendYWaN8pK0wa"
        },
        "href" : "https://api.spotify.com/v1/artists/00FQb4jTyendYWaN8pK0wa",
        "id" : "00FQb4jTyendYWaN8pK0wa",
        "name" : "Lana Del Rey",
        "type" : "artist",
        "uri" : "spotify:artist:00FQb4jTyendYWaN8pK0wa"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TH", "TR", "TW", "US", "UY", "VN" ],
      "disc_number" : 1,
      "duration_ms" : 289080,
      "explicit" : true,
      "external_ids" : {
        "isrc" : "GBUM71205878"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/7EWgsXKCJPlsJSbd5eCQOi"
      },
      "href" : "https://api.spotify.com/v1/tracks/7EWgsXKCJPlsJSbd5eCQOi",
      "id" : "7EWgsXKCJPlsJSbd5eCQOi",
      "name" : "Ride",
      "popularity" : 55,
      "preview_url" : null,
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:7EWgsXKCJPlsJSbd5eCQOi"
    } ],
    "limit" : 20,
    "next" : null,
    "offset" : 0,
    "previous" : null,
    "total" : 2
  }
}
 */
