package com.prtify.android.services;

import com.prtify.android.response.DeviceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * Created by leshan on 11/4/17.
 */

/*

       BRAIN FUNGUS!

 */
public interface SpotifyApi {

    @GET("https://api.spotify.com/v1/me/player/devices")
    Call<DeviceResponse> getDevices(@Header("Authorization") String accessToken);

    @PUT("https://api.spotify.com/v1/me/player/play")
    Call<Void> playTrack(@Header("Authorization") String accessToken);

    @PUT("https://api.spotify.com/v1/me/player/pause")
    Call<Void> pauseTrack(@Header("Authorization") String accessToken);


}
