package com.alipay.face.photinus;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.alipay.zoloz.toyger.blob.BlobManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class LightSensorListener implements SensorEventListener {
    private float _currentLightSensorValue = -1.0f;
    private SensorManager _sensorManager;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public LightSensorListener(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(BlobManager.BLOB_ELEM_TYPE_SENSOR);
        this._sensorManager = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(5);
        if (defaultSensor != null) {
            this._sensorManager.registerListener(this, defaultSensor, 0);
        }
    }

    public void discard() {
        SensorManager sensorManager = this._sensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this._sensorManager = null;
        }
    }

    public float getReading() {
        return this._currentLightSensorValue;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        this._currentLightSensorValue = sensorEvent.values[0];
    }
}
