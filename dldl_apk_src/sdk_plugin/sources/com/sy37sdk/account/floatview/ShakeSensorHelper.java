package com.sy37sdk.account.floatview;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.msdk.config.ConfigManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ShakeSensorHelper {
    private static final int SHAKE_CONTINUE_TIME = 1000;
    private static final int SHAKE_INTERVAL_TIME = 500;
    private static final float SHAKE_THRESHOLD = 12.0f;
    private static final int SPEED_SHRESHOLD = 5000;
    private static final int UPTATE_INTERVAL_TIME = 70;
    private static Context mContext;
    private static ShakeSensorHelper ourInstance;
    private boolean hasRegister;
    private boolean isShake;
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;
    private SensorManager sensorManager;
    private ShakeSensorListener shakeListener;
    private Task task;
    private String tag = getClass().getSimpleName();
    private List<SensorChangedCallback> sensorChangedCallbacks = new ArrayList();
    private long mShakeStartTime = 0;
    private long mLastShakeTime = 0;

    public interface SensorChangedCallback {
        void onSensorChanged(Context context, SensorEvent sensorEvent);
    }

    public static ShakeSensorHelper getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (ConfigManager.class) {
                if (ourInstance == null) {
                    ourInstance = new ShakeSensorHelper(context);
                }
            }
        }
        return ourInstance;
    }

    public boolean isShake() {
        return this.isShake;
    }

    public void setShake(boolean z) {
        this.isShake = z;
    }

    public void addSensorChangedCallback(SensorChangedCallback sensorChangedCallback) {
        this.sensorChangedCallbacks.add(sensorChangedCallback);
    }

    public void removeSensorChangedCallback(SensorChangedCallback sensorChangedCallback) {
        this.sensorChangedCallbacks.remove(sensorChangedCallback);
    }

    private ShakeSensorHelper(Context context) {
        if (mContext == null) {
            Context applicationContext = context.getApplicationContext();
            mContext = applicationContext;
            this.sensorManager = (SensorManager) applicationContext.getSystemService("sensor");
            this.task = Task.create();
            this.shakeListener = new ShakeSensorListener();
        }
    }

    class ShakeSensorListener implements SensorEventListener {
        ShakeSensorListener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(final SensorEvent sensorEvent) {
            if (ShakeSensorHelper.this.isShake) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - ShakeSensorHelper.this.lastUpdateTime < 70) {
                return;
            }
            ShakeSensorHelper.this.lastUpdateTime = jCurrentTimeMillis;
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            float f4 = f - ShakeSensorHelper.this.lastX;
            float f5 = f2 - ShakeSensorHelper.this.lastY;
            float f6 = f3 - ShakeSensorHelper.this.lastZ;
            ShakeSensorHelper.this.lastX = f;
            ShakeSensorHelper.this.lastY = f2;
            ShakeSensorHelper.this.lastZ = f3;
            double dSqrt = Math.sqrt(((f4 * f4) + (f5 * f5)) + (f6 * f6)) - 9.806650161743164d;
            LogUtil.i(ShakeSensorHelper.this.tag, "acceleration = " + dSqrt);
            if (dSqrt >= 12.0d) {
                if (ShakeSensorHelper.this.mShakeStartTime == 0) {
                    ShakeSensorHelper.this.mShakeStartTime = jCurrentTimeMillis;
                }
                ShakeSensorHelper.this.mLastShakeTime = jCurrentTimeMillis;
            } else if (ShakeSensorHelper.this.mLastShakeTime != 0 && jCurrentTimeMillis - ShakeSensorHelper.this.mLastShakeTime > 500) {
                ShakeSensorHelper.this.resetShakeStatics();
            }
            long j = jCurrentTimeMillis - ShakeSensorHelper.this.mShakeStartTime;
            LogUtil.d(ShakeSensorHelper.this.tag, "shakeContinueTime = " + j);
            if (ShakeSensorHelper.this.mShakeStartTime <= 0 || j <= 1000) {
                return;
            }
            LogUtil.i(ShakeSensorHelper.this.tag, "shake");
            ShakeSensorHelper.this.isShake = true;
            ShakeSensorHelper.this.resetShakeStatics();
            ShakeSensorHelper.this.task.oneShot(500L, new Task.TaskFunc() { // from class: com.sy37sdk.account.floatview.ShakeSensorHelper.ShakeSensorListener.1
                @Override // com.sqwan.common.util.task.Task.TaskFunc
                public Task.Result exec() {
                    for (SensorChangedCallback sensorChangedCallback : ShakeSensorHelper.this.sensorChangedCallbacks) {
                        if (sensorChangedCallback != null) {
                            sensorChangedCallback.onSensorChanged(ShakeSensorHelper.mContext, sensorEvent);
                        }
                    }
                    return null;
                }
            });
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            LogUtil.d(ShakeSensorHelper.this.tag, "onAccuracyChanged accuracy " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetShakeStatics() {
        this.mShakeStartTime = 0L;
        this.mLastShakeTime = 0L;
    }

    public void onResume() {
        LogUtil.d(this.tag, "onResume");
        SensorManager sensorManager = this.sensorManager;
        if (sensorManager == null || this.hasRegister) {
            return;
        }
        sensorManager.registerListener(this.shakeListener, sensorManager.getDefaultSensor(1), 0);
        this.hasRegister = true;
        this.isShake = false;
        LogUtil.d(this.tag, "hasRegister true");
    }

    public void onPause() {
        LogUtil.d(this.tag, "onPause");
        SensorManager sensorManager = this.sensorManager;
        if (sensorManager != null && this.hasRegister) {
            sensorManager.unregisterListener(this.shakeListener);
            this.hasRegister = false;
            LogUtil.d(this.tag, "hasRegister false");
        }
        Task task = this.task;
        if (task != null) {
            task.stop();
        }
        resetShakeStatics();
    }

    public void release() {
        onPause();
    }
}
