package com.webingate.GameWinR.utils;

import com.google.gson.JsonObject;
import com.webingate.GameWinR.Deo.GameResponce;
import com.webingate.GameWinR.Deo.UserValidateResp;
import com.webingate.GameWinR.Deo.UsersResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {
    @POST("/api/login/ValidateAdminLogin")
    public Observable<List<UserValidateResp>> ValidateCred(@Body JsonObject jsonObject);//

    @GET("/api/Users/Getusers")
    public Observable<List<UsersResponse>> GetUserslist();//

    @POST("/api/Users/RegistrationAdminUser")
    public Observable<List<UsersResponse>> CreateUser(@Body JsonObject jsonObject);//

    @POST("/api/Games/SaveGames")
    public Observable<List<GameResponce>> CreatNewGame(@Body JsonObject jsonObject);//

    @GET("/api/Games/getGames")
    public Observable<List<GameResponce>> GetGamelist();//

    @GET("/api/Games/GetGamesById")
    public Observable<List<GameResponce>> GetGamesById(@Query("Id") int Id);

    @POST("/api/GamePricing/saveGamePricing")
    public Observable<List<GameResponce>> savenewprice(@Body JsonObject jsonObject);//

    @POST("/api/GameRoom/SaveGameRoom")
    public Observable<List<GameResponce>> saveroom(@Body JsonObject jsonObject);//


    @GET("/api/GameRoom/getGameRoom")
    public Observable<List<GameResponce>> GeRoomList();//

    @GET("/api/GamePricing/getGamePricing")
    public Observable<List<GameResponce>> GetPricinglist();//

    @GET("/api/Types/GetTypeGroupId")
    public Observable<List<GameResponce>> GetTypeGroupId(@Query("groupid") int groupid);//


}
