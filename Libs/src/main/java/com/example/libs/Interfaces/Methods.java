package com.example.libs.Interfaces;

import com.example.libs.Model.ResponseBase;
import com.example.libs.Model.Room;
import com.example.libs.Model.RoomListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @GET("api/Room/get-rooms")
    Call<RoomListResponse> getRoomList();
    @POST("api/Room/insert-rooms")
    Call<ResponseBase> insertRoom(@Body Room r);
}