package com.youme.lbs;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.sqwan.bugless.core.Constant;
import com.youme.im.CommonConst;
import com.youme.im.IMEngine;
import com.youme.lbs.IGeographyLocationCallback;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class GeographyLocation {
    private static final short LOCATION_FAILED = 1;
    private static final short LOCATION_SUCCESS = 0;
    private static final short LOCATION_TIMEOUT = 2;
    private static final int LOCATION_TIMEOUT_MAX = 90000;
    private static final int LOCATION_TIMEOUT_MIN = 10000;
    private static final String LOG_TAG = "GeographyLocation";
    private static final float MIN_DISTANCE = 1000.0f;
    private static final int MIN_TIME = 60000;
    private static final int TWO_MINUTES = 120000;
    private Context m_context;
    private int m_currentTimeout;
    private LBSThread m_lbsThread;
    private Looper m_threadLooper;
    private LocationManager m_locactionManager = null;
    private IGeographyLocationCallback m_callback = null;
    private boolean mIsRunning = false;
    private boolean mIsAuthorize = true;
    private Location m_currentBestLocation = null;
    private Location m_lastLocation = null;
    private int m_updateCount = 0;
    private Timer m_timer = null;
    private TimerTask m_timerTask = null;
    private Map<String, GeoLocationListener> m_geoLocationListeners = new HashMap();

    static /* synthetic */ int access$304(GeographyLocation geographyLocation) {
        int i = geographyLocation.m_updateCount + 1;
        geographyLocation.m_updateCount = i;
        return i;
    }

    public void Init(Context context) {
        this.m_context = context;
    }

    public void SetCallback(IGeographyLocationCallback iGeographyLocationCallback) {
        this.m_callback = iGeographyLocationCallback;
    }

    private void StartTimer() {
        Timer timer;
        if (this.m_timerTask == null) {
            this.m_timerTask = new TimerTask() { // from class: com.youme.lbs.GeographyLocation.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (GeographyLocation.this.m_currentTimeout == 10000) {
                        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "timer reached 1");
                        GeographyLocation.this.m_currentTimeout = GeographyLocation.LOCATION_TIMEOUT_MAX;
                        if (GeographyLocation.this.m_callback == null || GeographyLocation.this.m_lastLocation == null || GeographyLocation.this.m_updateCount != 0) {
                            return;
                        }
                        Location location = GeographyLocation.this.m_lastLocation;
                        if (GeographyLocation.this.m_currentBestLocation != null) {
                            GeographyLocation geographyLocation = GeographyLocation.this;
                            if (geographyLocation.IsBetterLocation(geographyLocation.m_currentBestLocation, GeographyLocation.this.m_lastLocation)) {
                                location = GeographyLocation.this.m_currentBestLocation;
                                GeographyLocation geographyLocation2 = GeographyLocation.this;
                                geographyLocation2.m_lastLocation = geographyLocation2.m_currentBestLocation;
                            }
                        }
                        GeographyLocation.this.m_callback.OnUploadGeoLocation(IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_SUCCESS, location.getLongitude(), location.getLatitude());
                        return;
                    }
                    IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "timer reached 2");
                    GeographyLocation.this.StopTimer(2);
                }
            };
        }
        if (this.m_timer == null) {
            this.m_timer = new Timer();
        }
        TimerTask timerTask = this.m_timerTask;
        if (timerTask == null || (timer = this.m_timer) == null) {
            return;
        }
        if (this.m_lastLocation != null) {
            this.m_currentTimeout = 10000;
            timer.schedule(timerTask, 10000, 80000L);
        } else {
            this.m_currentTimeout = LOCATION_TIMEOUT_MAX;
            timer.schedule(timerTask, LOCATION_TIMEOUT_MAX);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void StopTimer(int i) {
        Looper looper = this.m_threadLooper;
        if (looper != null) {
            looper.quit();
            this.m_threadLooper = null;
        }
        if (this.m_lbsThread != null) {
            this.m_lbsThread = null;
        }
        StopRequestLocationUpdates();
        Timer timer = this.m_timer;
        if (timer != null) {
            timer.cancel();
        }
        TimerTask timerTask = this.m_timerTask;
        if (timerTask != null) {
            timerTask.cancel();
        }
        this.m_timer = null;
        this.m_timerTask = null;
        this.mIsRunning = false;
        NotifyLocationResult(i);
    }

    public void NotifyLocationResult(int i) {
        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "NotifyLocationResult result:" + i);
        IGeographyLocationCallback.LocationErrorcode locationErrorcode = IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_SUCCESS;
        Location location = null;
        if (i == 0) {
            if (this.m_updateCount == 0 || IsBetterLocation(this.m_currentBestLocation, this.m_lastLocation)) {
                location = this.m_currentBestLocation;
            }
        } else if (1 == i) {
            locationErrorcode = IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_FAILED;
        } else if (2 == i) {
            Location location2 = this.m_currentBestLocation;
            if (location2 == null) {
                locationErrorcode = IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_TIMEOUT;
            } else {
                Location location3 = this.m_lastLocation;
                if (location3 == null) {
                    location = location2;
                } else if (location2 == null || location3 == null || !IsBetterLocation(location2, location3)) {
                    return;
                } else {
                    location = this.m_currentBestLocation;
                }
            }
        }
        IGeographyLocationCallback.LocationErrorcode locationErrorcode2 = locationErrorcode;
        Location location4 = this.m_currentBestLocation;
        if (location4 != null) {
            this.m_lastLocation = location4;
        }
        IGeographyLocationCallback iGeographyLocationCallback = this.m_callback;
        if (iGeographyLocationCallback != null) {
            if (location != null) {
                iGeographyLocationCallback.OnUploadGeoLocation(locationErrorcode2, location.getLongitude(), location.getLatitude());
            } else {
                iGeographyLocationCallback.OnUploadGeoLocation(locationErrorcode2, -180.0d, -180.0d);
            }
        }
    }

    public IGeographyLocationCallback.LocationErrorcode GetLocation() {
        Context context;
        if (this.mIsRunning) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "location running");
            return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_RUNNING;
        }
        this.mIsRunning = true;
        if (Build.VERSION.SDK_INT >= 23 && (context = this.m_context) != null && (context instanceof Activity) && context.getApplicationInfo().targetSdkVersion > 22) {
            try {
                if (!PermissionsManager.getInstance().hasPermission(this.m_context, "android.permission.ACCESS_FINE_LOCATION")) {
                    this.mIsAuthorize = false;
                    PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult((Activity) this.m_context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, new PermissionsResultAction() { // from class: com.youme.lbs.GeographyLocation.2
                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onGranted() {
                            Log.i(GeographyLocation.LOG_TAG, " Success granted ACCESS_FINE_LOCATION permission.");
                        }

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onDenied(String str) {
                            GeographyLocation.this.mIsRunning = false;
                            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Decline granted ACCESS_FINE_LOCATION permission.");
                        }
                    });
                    IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Not granted ACCESS_FINE_LOCATION permission");
                    if (!this.mIsRunning) {
                        return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_AUTHORIZE;
                    }
                } else {
                    this.mIsAuthorize = true;
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "location permission exception");
                return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_AUTHORIZE;
            }
        }
        if (this.m_locactionManager == null) {
            LocationManager locationManager = (LocationManager) this.m_context.getSystemService("location");
            this.m_locactionManager = locationManager;
            if (locationManager == null) {
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "get location service failed");
                this.m_locactionManager = (LocationManager) this.m_context.getSystemService("location");
                this.mIsRunning = false;
                return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_INIT_FAILED;
            }
        }
        boolean zIsProviderEnabled = this.m_locactionManager.isProviderEnabled("gps");
        boolean zIsProviderEnabled2 = this.m_locactionManager.isProviderEnabled(Constant.DEV_NETWORK);
        boolean zIsProviderEnabled3 = this.m_locactionManager.isProviderEnabled("passive");
        if (zIsProviderEnabled) {
            if (!this.m_geoLocationListeners.containsKey("gps")) {
                this.m_geoLocationListeners.put("gps", new GeoLocationListener());
            }
        } else {
            this.m_geoLocationListeners.remove("gps");
        }
        if (zIsProviderEnabled2) {
            if (!this.m_geoLocationListeners.containsKey(Constant.DEV_NETWORK)) {
                this.m_geoLocationListeners.put(Constant.DEV_NETWORK, new GeoLocationListener());
            }
        } else {
            this.m_geoLocationListeners.remove(Constant.DEV_NETWORK);
        }
        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "gps:" + zIsProviderEnabled + " network:" + zIsProviderEnabled2 + " passive:" + zIsProviderEnabled3 + " total:" + this.m_geoLocationListeners.size());
        if (this.m_geoLocationListeners.size() == 0) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "location unenable");
            this.mIsRunning = false;
            return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_AUTHORIZE;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setPowerRequirement(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setSpeedRequired(false);
        this.m_currentBestLocation = null;
        String bestProvider = this.m_locactionManager.getBestProvider(criteria, true);
        if (bestProvider != null) {
            Log.d(LOG_TAG, " provider:" + bestProvider);
            Location lastKnownLocation = this.m_locactionManager.getLastKnownLocation(bestProvider);
            if (lastKnownLocation != null) {
                this.m_currentBestLocation = lastKnownLocation;
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "last location:" + this.m_currentBestLocation.getLongitude() + "x" + this.m_currentBestLocation.getLatitude() + " provider:" + bestProvider);
            }
        }
        StartTimer();
        LBSThread lBSThread = new LBSThread();
        this.m_lbsThread = lBSThread;
        lBSThread.start();
        return IGeographyLocationCallback.LocationErrorcode.LOCATIONERROR_SUCCESS;
    }

    public boolean StartRequestLocationUpdates() {
        this.m_updateCount = 0;
        try {
            if (this.m_geoLocationListeners.containsKey("gps")) {
                this.m_locactionManager.requestLocationUpdates("gps", 60000L, MIN_DISTANCE, this.m_geoLocationListeners.get("gps"));
            }
            if (!this.m_geoLocationListeners.containsKey(Constant.DEV_NETWORK)) {
                return true;
            }
            this.m_locactionManager.requestLocationUpdates(Constant.DEV_NETWORK, 60000L, MIN_DISTANCE, this.m_geoLocationListeners.get(Constant.DEV_NETWORK));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void StopRequestLocationUpdates() {
        Log.d(LOG_TAG, "stop listen");
        if (this.m_geoLocationListeners.containsKey("gps")) {
            this.m_locactionManager.removeUpdates(this.m_geoLocationListeners.get("gps"));
            this.m_geoLocationListeners.remove("gps");
        }
        if (this.m_geoLocationListeners.containsKey(Constant.DEV_NETWORK)) {
            this.m_locactionManager.removeUpdates(this.m_geoLocationListeners.get(Constant.DEV_NETWORK));
            this.m_geoLocationListeners.remove(Constant.DEV_NETWORK);
        }
    }

    protected boolean IsBetterLocation(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        boolean zIsSameProvider = IsSameProvider(location.getProvider(), location2.getProvider());
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && zIsSameProvider;
        }
        return true;
    }

    private boolean IsSameProvider(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public class LBSThread extends Thread {
        public LBSThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "thread start");
            Looper.prepare();
            GeographyLocation.this.m_threadLooper = Looper.myLooper();
            if (!GeographyLocation.this.mIsAuthorize) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    GeographyLocation.this.StopTimer(1);
                    return;
                }
            }
            if (!GeographyLocation.this.StartRequestLocationUpdates()) {
                GeographyLocation.this.StopTimer(1);
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "start location failed");
            } else {
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "thread running");
                Looper.loop();
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "thread exit");
            }
        }
    }

    private class GeoLocationListener implements LocationListener {
        private GeoLocationListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_INFO, "location changed longtitude:" + location.getLongitude() + " latitude:" + location.getLatitude() + " provider:" + location.getProvider());
            GeographyLocation geographyLocation = GeographyLocation.this;
            if (geographyLocation.IsBetterLocation(location, geographyLocation.m_currentBestLocation)) {
                GeographyLocation.this.m_currentBestLocation = location;
            }
            if (location.getProvider().equals("gps") && GeographyLocation.this.m_geoLocationListeners.containsKey("gps")) {
                GeographyLocation.this.m_locactionManager.removeUpdates((LocationListener) GeographyLocation.this.m_geoLocationListeners.get("gps"));
                GeographyLocation.this.m_geoLocationListeners.remove("gps");
            }
            if (location.getProvider().equals(Constant.DEV_NETWORK) && GeographyLocation.this.m_geoLocationListeners.containsKey(Constant.DEV_NETWORK)) {
                GeographyLocation.this.m_locactionManager.removeUpdates((LocationListener) GeographyLocation.this.m_geoLocationListeners.get(Constant.DEV_NETWORK));
                GeographyLocation.this.m_geoLocationListeners.remove(Constant.DEV_NETWORK);
            }
            if (GeographyLocation.access$304(GeographyLocation.this) >= GeographyLocation.this.m_geoLocationListeners.size()) {
                GeographyLocation.this.StopTimer(0);
            } else {
                GeographyLocation.this.NotifyLocationResult(0);
            }
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            Log.i(GeographyLocation.LOG_TAG, "onStatusChanged provider:" + str + " status:" + i);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            Log.i(GeographyLocation.LOG_TAG, "provider enabled name:" + str);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            Log.i(GeographyLocation.LOG_TAG, "provider disabled name:" + str);
        }
    }
}
