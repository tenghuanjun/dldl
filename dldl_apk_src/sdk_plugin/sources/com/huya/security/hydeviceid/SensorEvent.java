package com.huya.security.hydeviceid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SensorEvent implements SensorEventListener {
    static SensorEvent sSensorEvent = new SensorEvent();
    Context mContext = null;
    public boolean finished = false;
    public Rotation rotation = new Rotation();

    public static class Rotation {
        public float[] r = new float[9];
        public float[] gravity = null;
        public float[] geomagnetic = null;
        public float[] I = new float[9];
        public List<Integer> sensorList = new ArrayList();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public static SensorEvent getInstance() {
        return sSensorEvent;
    }

    public void init(Context context) {
        this.mContext = context;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(1), 2);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(2), 2);
        List<Sensor> sensorList = sensorManager.getSensorList(-1);
        for (int i = 0; i < sensorList.size(); i++) {
            Sensor sensor = sensorList.get(i);
            if (sensor != null) {
                this.rotation.sensorList.add(Integer.valueOf(sensor.getType()));
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(android.hardware.SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            this.rotation.gravity = sensorEvent.values;
        } else if (type == 2) {
            this.rotation.geomagnetic = sensorEvent.values;
        }
        if (this.rotation.gravity == null || this.rotation.geomagnetic == null || !SensorManager.getRotationMatrix(this.rotation.r, this.rotation.I, this.rotation.gravity, this.rotation.geomagnetic)) {
            return;
        }
        ((SensorManager) this.mContext.getSystemService("sensor")).unregisterListener(this);
        this.finished = true;
    }

    public String getRotation() {
        int i = 5;
        while (!this.finished) {
            try {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                Thread.sleep(200L);
                i = i2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("r", (Object) this.rotation.r);
        jSONObject.put("geomagnetic", (Object) this.rotation.geomagnetic);
        jSONObject.put("gravity", (Object) this.rotation.gravity);
        jSONObject.put("I", (Object) this.rotation.I);
        jSONObject.put("sensorList", (Object) this.rotation.sensorList);
        return jSONObject.toJSONString();
    }
}
