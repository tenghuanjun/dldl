package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class RelativeLocationInfo {

    @SerializedName("EndDistance")
    public int endDistance;

    @SerializedName("NeighbourList")
    public ArrayList<RelativeLocation> relativeLocations;

    @SerializedName("StartDistance")
    public int startDistance;
}
