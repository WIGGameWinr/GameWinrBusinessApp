package com.webingate.GameWinR.Deo;

import com.google.gson.annotations.SerializedName;

public class PriceResponce {
    @SerializedName("GameImage") private String GameImage;
    @SerializedName("GameTitle") private String GameTitle;
    @SerializedName("Code") private String Code;
    @SerializedName("description") private String description;
    @SerializedName("Id") private int Id;
    @SerializedName("Name") private String Name;

    public String getGameImage() {return GameImage;}
    public String getGameTitle() {return GameTitle;}
    public String getCode() {return Code;}
    public String getDescription() {return description;}
    public int getId() {return Id;}
    public String getName(){ return Name;}
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
