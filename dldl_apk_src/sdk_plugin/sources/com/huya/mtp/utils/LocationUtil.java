package com.huya.mtp.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.huya.mtp.api.MTPApi;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LocationUtil {
    private static final String TAG = "LocationUtil";
    private static Location sLastLocation;

    private static class SimpleLocationListener implements LocationListener {
        private LocationManager mLocationManager;
        private String mProviderName;

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public SimpleLocationListener(LocationManager locationManager, String str) {
            this.mLocationManager = locationManager;
            this.mProviderName = str;
        }

        public String getProviderName() {
            return this.mProviderName;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            this.mLocationManager.removeUpdates(this);
        }
    }

    public static void requestLocation(Context context, LocationListener locationListener) {
        try {
            requestLocation((LocationManager) context.getSystemService("location"), locationListener);
        } catch (SecurityException e) {
            MTPApi.LOGGER.info(TAG, "[requestLocation] exception occurred, should be user denied location permission");
            MTPApi.LOGGER.error(TAG, e);
        } catch (Exception e2) {
            MTPApi.LOGGER.error(TAG, e2);
        }
    }

    public static Location getLocation() {
        return sLastLocation;
    }

    private static void requestLocation(LocationManager locationManager, LocationListener locationListener) {
        String bestProvider = getBestProvider(locationManager);
        List<String> providers = locationManager.getProviders(true);
        if (FP.empty(providers)) {
            return;
        }
        Location lastKnownLocation = getLastKnownLocation(locationManager, bestProvider, providers);
        if (lastKnownLocation != null) {
            sLastLocation = lastKnownLocation;
        }
        requestUpdates(locationManager, providers, locationListener);
    }

    private static void requestUpdates(final LocationManager locationManager, List<String> list, final LocationListener locationListener) {
        final ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            if (str != null) {
                SimpleLocationListener simpleLocationListener = new SimpleLocationListener(locationManager, str) { // from class: com.huya.mtp.utils.LocationUtil.1
                    @Override // com.huya.mtp.utils.LocationUtil.SimpleLocationListener, android.location.LocationListener
                    public void onLocationChanged(Location location) {
                        LocationListener locationListener2;
                        super.onLocationChanged(location);
                        arrayList.remove(this);
                        boolean zEquals = getProviderName().equals(LocationUtil.getBestProvider(locationManager));
                        if (location != null) {
                            Location unused = LocationUtil.sLastLocation = location;
                            if (zEquals) {
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    locationManager.removeUpdates((LocationListener) it.next());
                                }
                                arrayList.clear();
                            }
                        }
                        if (!FP.empty(arrayList) || LocationUtil.sLastLocation == null || (locationListener2 = locationListener) == null) {
                            return;
                        }
                        locationListener2.onLocationChanged(LocationUtil.sLastLocation);
                    }
                };
                locationManager.requestLocationUpdates(str, 1000L, 1.0f, simpleLocationListener);
                arrayList.add(simpleLocationListener);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() { // from class: com.huya.mtp.utils.LocationUtil.2
                    @Override // java.lang.Runnable
                    public void run() {
                        List list2 = arrayList;
                        if (list2 != null) {
                            Iterator it = list2.iterator();
                            while (it.hasNext()) {
                                locationManager.removeUpdates((LocationListener) it.next());
                            }
                            arrayList.clear();
                        }
                        handler.removeCallbacks(this);
                    }
                }, OAIDHelper.TIMEOUT);
            }
        }
    }

    private static Location getLastKnownLocation(LocationManager locationManager, String str, List<String> list) {
        Location location = null;
        for (String str2 : list) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(str2);
            if (lastKnownLocation != null) {
                if (str2.equals(str)) {
                    return lastKnownLocation;
                }
                location = lastKnownLocation;
            }
        }
        return location;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBestProvider(LocationManager locationManager) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(1);
        try {
            return locationManager.getBestProvider(criteria, true);
        } catch (Exception unused) {
            return "";
        }
    }
}
