package com.sqwan.msdk.api;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DiagnosticSensorManager implements SensorEventListener {
    private static final DiagnosticSensorManager INSTANCE = new DiagnosticSensorManager();
    private static final int SPEED_THRESHOLD = 25000;
    private static final String TAG = "DiagnosticSensorManager";
    private static final int UPDATE_INTERVAL_TIME = 70;
    private SensorChangedCallback mCallback;
    private long mLastUpdateTime;
    private float mLastX;
    private float mLastY;
    private float mLastZ;
    private boolean mRegisterFlag;
    private long mRegisterSensorTime;
    private SensorManager mSensorManager;
    private long mTriggerCallbackTime;

    public interface SensorChangedCallback {
        void onSensorChanged(SensorEvent sensorEvent);
    }

    public static DiagnosticSensorManager getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        Context applicationContext;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            return;
        }
        this.mSensorManager = (SensorManager) applicationContext.getSystemService("sensor");
    }

    public void registerSensorChangedCallback(SensorChangedCallback sensorChangedCallback) {
        this.mCallback = sensorChangedCallback;
        registerSensorListener();
    }

    public void unregisterSensorChangedCallback() {
        this.mCallback = null;
        unregisterSensorListener();
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.mLastUpdateTime;
        if (j < 70) {
            return;
        }
        this.mLastUpdateTime = jCurrentTimeMillis;
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        float f4 = f - this.mLastX;
        float f5 = f2 - this.mLastY;
        float f6 = f3 - this.mLastZ;
        this.mLastX = f;
        this.mLastY = f2;
        this.mLastZ = f3;
        double dSqrt = (Math.sqrt(((f4 * f4) + (f5 * f5)) + (f6 * f6)) / j) * 10000.0d;
        if (dSqrt < 25000.0d) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mRegisterSensorTime < OAIDHelper.TIMEOUT) {
            SQLogUtils.i(TAG, "The system triggered a shake, interval time = " + (jUptimeMillis - this.mRegisterSensorTime));
            this.mTriggerCallbackTime = jUptimeMillis;
            return;
        }
        if (jUptimeMillis - this.mTriggerCallbackTime < 10000) {
            SQLogUtils.i(TAG, "It could be that the system triggers a lot of shaking,  interval time = " + (this.mTriggerCallbackTime - jUptimeMillis));
            this.mTriggerCallbackTime = jUptimeMillis;
            return;
        }
        this.mTriggerCallbackTime = jUptimeMillis;
        SQLogUtils.i(TAG, "Listen to shake shake, speed = " + dSqrt);
        SensorChangedCallback sensorChangedCallback = this.mCallback;
        if (sensorChangedCallback != null) {
            sensorChangedCallback.onSensorChanged(sensorEvent);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        SQLogUtils.d(TAG, "onAccuracyChanged accuracy " + i);
    }

    public void onResume() {
        if (this.mSensorManager == null || this.mCallback == null || this.mRegisterFlag) {
            return;
        }
        registerSensorListener();
    }

    public void onPause() {
        if (this.mSensorManager == null || this.mCallback == null || !this.mRegisterFlag) {
            return;
        }
        unregisterSensorListener();
    }

    private void registerSensorListener() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager == null) {
            return;
        }
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(1), 0);
        this.mRegisterFlag = true;
        this.mRegisterSensorTime = SystemClock.uptimeMillis();
    }

    private void unregisterSensorListener() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager == null) {
            return;
        }
        sensorManager.unregisterListener(this);
        this.mRegisterFlag = false;
    }
}
