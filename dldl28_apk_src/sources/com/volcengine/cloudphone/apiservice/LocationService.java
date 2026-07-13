package com.volcengine.cloudphone.apiservice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface LocationService {
    public static final int MODE_AUTO = 0;
    public static final int MODE_MANUAL = 1;

    public interface LocationEventListener {
        void onReceivedRemoteLocationRequest(RequestOptions requestOptions);

        void onRemoteLocationRequestEnded();

        void onRemoteLocationUpdated(LocationInfo locationInfo);

        void onSentLocalLocation(LocationInfo locationInfo);
    }

    public static class LocationInfo {
        public final Double latitude;
        public final Double longitude;

        public LocationInfo(Double d, Double d2) {
            this.latitude = d;
            this.longitude = d2;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                Double d = this.latitude;
                if (d != null) {
                    jSONObject.put("latitude", d);
                }
                Double d2 = this.longitude;
                if (d2 != null) {
                    jSONObject.put("longitude", d2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }

        public String toString() {
            return "Location{latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
        }
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface LocationServiceMode {
    }

    public static class RequestOptions {
        public final float minDistance;
        public final long minTime;
        public final int numUpdates;
        public final String provider;

        public RequestOptions(String str, long j, float f, int i) {
            this.provider = str;
            this.minTime = j;
            this.minDistance = f;
            this.numUpdates = i;
        }

        public String toString() {
            return "RequestOptions{minTime=" + this.minTime + ", minDistance=" + this.minDistance + ", provider='" + this.provider + "', numUpdates=" + this.numUpdates + '}';
        }
    }

    void enableLocationService(boolean z);

    int getLocationServiceMode();

    boolean isLocationServiceEnabled();

    void setLocationEventListener(LocationEventListener locationEventListener);

    void setLocationServiceMode(int i);

    int setRemoteLocationMock(LocationInfo locationInfo);
}
