package com.webingate.GameWinR.Deo;

import com.google.gson.annotations.SerializedName;

public class GameResponce {
    @SerializedName("GameImage") private String GameImage;
    @SerializedName("GameTitle") private String GameTitle;
    @SerializedName("Code") private String Code;
    @SerializedName("description") private String description;
    @SerializedName("Id") private int Id;
    @SerializedName("Name") private String Name;
    @SerializedName("RoomId") private String RoomId;
    @SerializedName("RoomPassword") private String RoomPassword;
    @SerializedName("Date") private String Date;
    @SerializedName("Time") private String Time;
    @SerializedName("TypeName") private String TypeName;
    @SerializedName("EntryFee") private String EntryFee;

    public String getGameImage() {return GameImage;}
    public String getGameTitle() {return GameTitle;}
    public String getCode() {return Code;}
    public String getDescription() {return description;}
    public int getId() {return Id;}
    public String getRoomId(){ return RoomId;}
    public String getName(){ return Name;}
    public String getDate(){ return Date;}
    public String getTime(){ return Time;}
    public String getTypeName(){ return TypeName;}
    public String getRoomPassword(){ return RoomPassword;}
    public String getEntryFee(){ return EntryFee;}

    @Override
    public String toString(){
        return
                "UsersResponse{" +
                        "GameImage = '" +GameImage  + '\'' +
                        "GameTitle = '" +GameTitle  + '\'' +
                        "Code = '" +Code  + '\'' +
                        "Id = '" +Id  + '\'' +
                        "}";
    }
}
