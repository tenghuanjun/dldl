package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class RelativeLocation {

    @SerializedName("Latitude")
    public double fLatitude;

    @SerializedName("Longitude")
    public double fLongitude;

    @SerializedName("Distance")
    public int iDistance;

    @SerializedName("City")
    public String strCity;

    @SerializedName("Country")
    public String strCountry;

    @SerializedName("DistrictCounty")
    public String strDistrictCounty;

    @SerializedName("Province")
    public String strProvince;

    @SerializedName("Street")
    public String strStreet;

    @SerializedName("UserID")
    public String strUserID;
}
