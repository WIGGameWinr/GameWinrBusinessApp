package com.webingate.GameWinR.Deo;
import com.google.gson.annotations.SerializedName;

public class UsersResponse {

    @SerializedName("Id") private int Id;
    @SerializedName("FirstName") private String FirstName;
    @SerializedName("LastName") private String LastName;
    @SerializedName("MiddleName") private String MiddleName;
    @SerializedName("EmailId") private String EmailId;
    @SerializedName("photo") private String photo;
    @SerializedName("UserPhoto") private String UserPhoto;
    @SerializedName("CompanyId") private int CompanyId;
    @SerializedName("GenderId") private String GenderId;
    @SerializedName("dateofbirth") private String dateofbirth;
    @SerializedName("password") private String password;
    @SerializedName("Mobileno") private String Mobileno;
    @SerializedName("Code") private String Code;
    @SerializedName("description") private String description;

    public String getFirstName() {return FirstName;}
    public String getEmailId() {return EmailId;}
    public String getPhoto() {return photo;}
    public String getGenderId() {return GenderId;}
    public String getLastName() {return LastName;}
    public int getCompanyId() {return CompanyId;}
    public int getId() {return Id;}
    public String getMiddleName() {return MiddleName;}
    public String getCode() {return Code;}
    public String getDescription() {return description;}
    @Override
    public String toString(){
        return
                "UsersResponse{" +
                        "Id = '" + Id + '\'' +
                        "FirstName = '" + FirstName + '\'' +
                        "LastName = '" + LastName + '\'' +
                        "MiddleName = '" + MiddleName + '\'' +
                        "EmailId = '" + EmailId + '\'' +
                        "CompanyId = '" + CompanyId + '\'' +
                        "GenderId = '" + GenderId + '\'' +
                        "}";
    }
}
