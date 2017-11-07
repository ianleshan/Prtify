package com.prtify.android.services;

import com.prtify.android.requests.DeviceRequest;
import com.prtify.android.requests.TrackUriRequest;
import com.prtify.android.response.CurrentlyPlayingResponse;
import com.prtify.android.response.DeviceResponse;
import com.prtify.android.response.SearchResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by leshan on 11/4/17.
 */

/********

 BRAIN FUNGUS!

 *******/
public interface SpotifyApi {

    @GET("v1/me/player/currently-playing")
    Call<CurrentlyPlayingResponse> getCurrentlyPlaying(@Header("Authorization") String accessToken);

    @GET("v1/me/player/devices")
    Call<DeviceResponse> getDevices(@Header("Authorization") String accessToken);

    @PUT("v1/me/player/play")
    Call<Void> playTrack(@Header("Authorization") String accessToken);

    @PUT("v1/me/player")
    Call<Void> playTrackOnDevice(@Header("Authorization") String accessToken, @Body DeviceRequest deviceRequest);

    @PUT("v1/me/player/play")
    Call<Void> playTrack(@Header("Authorization") String accessToken, @Body TrackUriRequest uris);

    @PUT("v1/me/player/pause")
    Call<Void> pauseTrack(@Header("Authorization") String accessToken);

    @PUT("v1/me/player/volume")
    Call<Void> setVolume(@Header("Authorization") String accessToken, @Query("volume_percent") int volumeValue);

    @GET("v1/search")
    Call<SearchResponse> search(@Header("Authorization") String accessToken, @Query("q") String n, @Query("type") String t);

}
