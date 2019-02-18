package com.webingate.GameWinR.object;

import com.google.gson.annotations.SerializedName;

public class ProductsVO {

    @SerializedName("id")
    private String id;

    @SerializedName("icon")
    private String icon;

    @SerializedName("name")
    private String name;

    public ProductsVO(String id, String icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
