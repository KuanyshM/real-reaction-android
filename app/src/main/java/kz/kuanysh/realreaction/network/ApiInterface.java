package kz.kuanysh.realreaction.network;

import java.util.List;

import kz.kuanysh.realreaction.models.Video;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("videos/")
    Call<List<Video>> getVideoList();
}
