package com.youme.lbs;

import android.content.Context;
import com.youme.im.NativeEngine;
import com.youme.lbs.IGeographyLocationCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class GeographyLocationManager implements IGeographyLocationCallback {
    private static GeographyLocation geographyLocation;
    private static GeographyLocationManager s_instance;

    public static GeographyLocationManager Instance() {
        if (s_instance == null) {
            s_instance = new GeographyLocationManager();
        }
        return s_instance;
    }

    public void Init(Context context) {
        GeographyLocation geographyLocation2 = new GeographyLocation();
        geographyLocation = geographyLocation2;
        geographyLocation2.Init(context);
        geographyLocation.SetCallback(this);
    }

    public static int GetGeographyLocation() {
        GeographyLocation geographyLocation2 = geographyLocation;
        if (geographyLocation2 != null) {
            return geographyLocation2.GetLocation().ordinal();
        }
        return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_INIT_FAILED.getValue();
    }

    @Override // com.youme.lbs.IGeographyLocationCallback
    public void OnUploadGeoLocation(IGeographyLocationCallback.LocationErrorcode locationErrorcode, double d, double d2) {
        NativeEngine.OnUpdateLocation(locationErrorcode.ordinal(), d, d2);
    }
}
