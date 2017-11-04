package com.prtify.android.requests;

/**
 * Created by satya on 11/4/17.
 */

public class TrackUriRequest {
    final String[] uris;

    public TrackUriRequest(String[] uris) {
        this.uris = uris;
    }

    public String[] getUris() {
        return uris;
    }
}
