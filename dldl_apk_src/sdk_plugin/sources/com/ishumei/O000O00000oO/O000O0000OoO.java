package com.ishumei.O000O00000oO;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class O000O0000OoO {
    private static O000O0000OoO O000O0000O0oO;
    SensorManager O0000O000000oO;
    public final O000O00000OoO O000O00000OoO;
    public final O000O0000O0oO O000O00000o0O;
    public final O0000O000000oO O000O00000oO;

    public class O0000O000000oO {
        private final String O000O00000oO = "Sensor-Gravity";
        int O0000O000000oO = 0;
        List<O000O00000oO> O000O00000OoO = new ArrayList(2);
        private volatile boolean O000O0000O0oO = false;
        private float O000O0000OOoO = 0.0f;
        private float O000O0000Oo0O = 0.0f;
        private float O000O0000OoO = 0.0f;
        private SensorEventListener O00O0000OooO = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O000O0000OoO.O0000O000000oO.1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "onSensorChanged begin");
                try {
                    try {
                        if (sensorEvent.sensor.getType() == 9 && sensorEvent.values.length > 2) {
                            O0000O000000oO.this.O000O0000OOoO = sensorEvent.values[0];
                            O0000O000000oO.this.O000O0000Oo0O = sensorEvent.values[1];
                            O0000O000000oO.this.O000O0000OoO = sensorEvent.values[2];
                            O0000O000000oO.this.O000O0000O0oO = true;
                        }
                        synchronized (this) {
                            if (O0000O000000oO.this.O000O0000O0oO) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "notifyAll");
                                notifyAll();
                                O0000O000000oO.this.O0000O000000oO(new float[]{O0000O000000oO.this.O000O0000OOoO, O0000O000000oO.this.O000O0000Oo0O, O0000O000000oO.this.O000O0000OoO});
                                O0000O000000oO.this.O000O0000O0oO = false;
                            }
                        }
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gravity", "onSensorChanged failed: " + e.getMessage());
                        synchronized (this) {
                            if (O0000O000000oO.this.O000O0000O0oO) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "notifyAll");
                                notifyAll();
                                O0000O000000oO.this.O0000O000000oO(new float[]{O0000O000000oO.this.O000O0000OOoO, O0000O000000oO.this.O000O0000Oo0O, O0000O000000oO.this.O000O0000OoO});
                                O0000O000000oO.this.O000O0000O0oO = false;
                            }
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        if (O0000O000000oO.this.O000O0000O0oO) {
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "notifyAll");
                            notifyAll();
                            O0000O000000oO.this.O0000O000000oO(new float[]{O0000O000000oO.this.O000O0000OOoO, O0000O000000oO.this.O000O0000Oo0O, O0000O000000oO.this.O000O0000OoO});
                            O0000O000000oO.this.O000O0000O0oO = false;
                        }
                        throw th;
                    }
                }
            }
        };

        public O0000O000000oO(Context context) {
            try {
                if (O000O0000OoO.this.O0000O000000oO == null) {
                    O000O0000OoO.this.O0000O000000oO = (SensorManager) context.getSystemService("sensor");
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gravity", "get SENSOR_SERVICE failed: " + e.getMessage());
            }
        }

        private synchronized void O0000O000000oO() {
            O0000O000000oO((O000O00000oO) null);
        }

        private synchronized void O0000O000000oO(O000O00000oO o000O00000oO) {
            try {
                if (O000O0000OoO.this.O0000O000000oO != null) {
                    this.O0000O000000oO--;
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "registerSuccessedCount-1 = " + this.O0000O000000oO);
                    if (this.O0000O000000oO == 0) {
                        O000O0000OoO.this.O0000O000000oO.unregisterListener(this.O00O0000OooO);
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "unregisterListener");
                    }
                }
                if (o000O00000oO != null) {
                    this.O000O00000OoO.remove(o000O00000oO);
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gravity", "unregister failed: " + e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void O0000O000000oO(float[] fArr) {
            for (O000O00000oO o000O00000oO : this.O000O00000OoO) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gravity", "onUpdate begin");
                o000O00000oO.O0000O000000oO(fArr);
                O0000O000000oO();
            }
            this.O000O00000OoO.clear();
        }
    }

    public class O000O00000OoO {
        SensorManager O000O0000OOoO;
        private final String O00O0000o00O = "Sensor-Gyro";
        int O0000O000000oO = 0;
        private volatile boolean O00O0000o0O = false;
        volatile boolean O000O00000OoO = false;
        volatile boolean O000O00000o0O = false;
        private float O00O0000o0OO = 0.0f;
        private float O00O0000oO = 0.0f;
        private float O00O0000oO0O = 0.0f;
        float[] O000O00000oO = new float[3];
        float[] O000O0000O0oO = new float[3];
        ArrayList<Boolean> O000O0000Oo0O = new ArrayList<>(2);
        ArrayList<O000O00000o0O> O000O0000OoO = new ArrayList<>(2);
        private SensorEventListener O00O0000oOO = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O000O0000OoO.O000O00000OoO.1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "onSensorChanged begin");
                try {
                    try {
                        if (sensorEvent.sensor.getType() == 2) {
                            O000O00000OoO.this.O000O0000O0oO = sensorEvent.values;
                            O000O00000OoO.this.O000O00000o0O = true;
                        }
                        if (sensorEvent.sensor.getType() == 1) {
                            O000O00000OoO.this.O000O00000oO = sensorEvent.values;
                            O000O00000OoO.this.O000O00000OoO = true;
                        }
                        if (O000O00000OoO.this.O000O00000OoO && O000O00000OoO.this.O000O00000o0O) {
                            float[] fArr = new float[9];
                            SensorManager.getRotationMatrix(fArr, null, O000O00000OoO.this.O000O00000oO, O000O00000OoO.this.O000O0000O0oO);
                            SensorManager.getOrientation(fArr, new float[3]);
                            O000O00000OoO.this.O00O0000o0OO = (float) Math.toDegrees(r8[0]);
                            O000O00000OoO.this.O00O0000oO = (float) Math.toDegrees(r8[1]);
                            O000O00000OoO.this.O00O0000oO0O = (float) Math.toDegrees(r8[2]);
                            O000O00000OoO.this.O00O0000o0O = true;
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "" + O000O00000OoO.this.O00O0000o0OO + " " + O000O00000OoO.this.O00O0000oO + " " + O000O00000OoO.this.O00O0000oO0O);
                        }
                        synchronized (this) {
                            if (O000O00000OoO.this.O00O0000o0O) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "notifyAll");
                                notifyAll();
                                O000O00000OoO.this.O0000O000000oO(O000O00000OoO.this.O00O0000o0OO, O000O00000OoO.this.O00O0000oO, O000O00000OoO.this.O00O0000oO0O);
                                O000O00000OoO o000O00000OoO = O000O00000OoO.this;
                                O000O00000OoO o000O00000OoO2 = O000O00000OoO.this;
                                O000O00000OoO.this.O000O00000o0O = false;
                                o000O00000OoO2.O000O00000OoO = false;
                                o000O00000OoO.O00O0000o0O = false;
                            }
                        }
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gyro", "gyro onSensorChanged failed: " + e.getMessage());
                        synchronized (this) {
                            if (O000O00000OoO.this.O00O0000o0O) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "notifyAll");
                                notifyAll();
                                O000O00000OoO.this.O0000O000000oO(O000O00000OoO.this.O00O0000o0OO, O000O00000OoO.this.O00O0000oO, O000O00000OoO.this.O00O0000oO0O);
                                O000O00000OoO o000O00000OoO3 = O000O00000OoO.this;
                                O000O00000OoO o000O00000OoO4 = O000O00000OoO.this;
                                O000O00000OoO.this.O000O00000o0O = false;
                                o000O00000OoO4.O000O00000OoO = false;
                                o000O00000OoO3.O00O0000o0O = false;
                            }
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        if (O000O00000OoO.this.O00O0000o0O) {
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "notifyAll");
                            notifyAll();
                            O000O00000OoO.this.O0000O000000oO(O000O00000OoO.this.O00O0000o0OO, O000O00000OoO.this.O00O0000oO, O000O00000OoO.this.O00O0000oO0O);
                            O000O00000OoO o000O00000OoO5 = O000O00000OoO.this;
                            O000O00000OoO o000O00000OoO6 = O000O00000OoO.this;
                            O000O00000OoO.this.O000O00000o0O = false;
                            o000O00000OoO6.O000O00000OoO = false;
                            o000O00000OoO5.O00O0000o0O = false;
                        }
                        throw th;
                    }
                }
            }
        };

        public O000O00000OoO(Context context) {
            this.O000O0000OOoO = null;
            if (context != null) {
                try {
                    this.O000O0000OOoO = (SensorManager) context.getSystemService("sensor");
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gyro", "get SENSOR_SERVICE failed: " + e.getMessage());
                }
            }
        }

        private synchronized void O0000O000000oO() {
            O0000O000000oO((O000O00000o0O) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void O0000O000000oO(float f, float f2, float f3) {
            Iterator<O000O00000o0O> it = this.O000O0000OoO.iterator();
            while (it.hasNext()) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "onUpdate begin");
                it.next().O0000O000000oO(f, f2, f3);
                O0000O000000oO();
            }
            this.O000O0000OoO.clear();
        }

        private synchronized void O0000O000000oO(O000O00000o0O o000O00000o0O) {
            try {
                try {
                    if (this.O000O0000OOoO != null) {
                        this.O0000O000000oO--;
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "registerSuccessedCount-1 = " + this.O0000O000000oO);
                        if (this.O0000O000000oO == 0) {
                            this.O000O0000OOoO.unregisterListener(this.O00O0000oOO);
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Gyro", "unregisterListener");
                        }
                    }
                    if (o000O00000o0O != null) {
                        this.O000O0000OoO.remove(o000O00000o0O);
                    }
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Gyro", "gyro unregister failed: " + e.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public interface O000O00000o0O {
        void O0000O000000oO(float f, float f2, float f3);
    }

    public interface O000O00000oO {
        void O0000O000000oO(float[] fArr);
    }

    public class O000O0000O0oO {
        private final String O000O00000oO = "Sensor-Light";
        int O0000O000000oO = 0;
        List<O000O00000oO> O000O00000OoO = new ArrayList(2);
        private volatile boolean O000O0000O0oO = false;
        private float O000O0000OOoO = 0.0f;
        private SensorEventListener O000O0000Oo0O = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O000O0000OoO.O000O0000O0oO.1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "onSensorChanged begin");
                try {
                    try {
                        if (sensorEvent.sensor.getType() == 5 && sensorEvent.values.length > 0) {
                            O000O0000O0oO.this.O000O0000OOoO = sensorEvent.values[0];
                            O000O0000O0oO.this.O000O0000O0oO = true;
                        }
                        synchronized (this) {
                            if (O000O0000O0oO.this.O000O0000O0oO) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "notifyAll");
                                notifyAll();
                                O000O0000O0oO.this.O0000O000000oO(new float[]{O000O0000O0oO.this.O000O0000OOoO});
                                O000O0000O0oO.this.O000O0000O0oO = false;
                            }
                        }
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Light", "onSensorChanged failed: " + e.getMessage());
                        synchronized (this) {
                            if (O000O0000O0oO.this.O000O0000O0oO) {
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "notifyAll");
                                notifyAll();
                                O000O0000O0oO.this.O0000O000000oO(new float[]{O000O0000O0oO.this.O000O0000OOoO});
                                O000O0000O0oO.this.O000O0000O0oO = false;
                            }
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        if (O000O0000O0oO.this.O000O0000O0oO) {
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "notifyAll");
                            notifyAll();
                            O000O0000O0oO.this.O0000O000000oO(new float[]{O000O0000O0oO.this.O000O0000OOoO});
                            O000O0000O0oO.this.O000O0000O0oO = false;
                        }
                        throw th;
                    }
                }
            }
        };

        public O000O0000O0oO(Context context) {
            try {
                if (O000O0000OoO.this.O0000O000000oO == null) {
                    O000O0000OoO.this.O0000O000000oO = (SensorManager) context.getSystemService("sensor");
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Light", "get SENSOR_SERVICE failed: " + e.getMessage());
            }
        }

        private synchronized void O0000O000000oO() {
            O0000O000000oO((O000O00000oO) null);
        }

        private synchronized void O0000O000000oO(O000O00000oO o000O00000oO) {
            try {
                if (O000O0000OoO.this.O0000O000000oO != null) {
                    this.O0000O000000oO--;
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "registerSuccessedCount-1 = " + this.O0000O000000oO);
                    if (this.O0000O000000oO == 0) {
                        O000O0000OoO.this.O0000O000000oO.unregisterListener(this.O000O0000Oo0O);
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "unregisterListener");
                    }
                }
                if (o000O00000oO != null) {
                    this.O000O00000OoO.remove(o000O00000oO);
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor-Light", "unregister failed: " + e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void O0000O000000oO(float[] fArr) {
            for (O000O00000oO o000O00000oO : this.O000O00000OoO) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor-Light", "onUpdate begin");
                o000O00000oO.O0000O000000oO(fArr);
                O0000O000000oO();
            }
            this.O000O00000OoO.clear();
        }
    }

    private O000O0000OoO() {
        this.O0000O000000oO = null;
        if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO != null) {
            this.O0000O000000oO = (SensorManager) com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getSystemService("sensor");
        }
        this.O000O00000OoO = new O000O00000OoO(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO);
        this.O000O00000o0O = new O000O0000O0oO(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO);
        this.O000O00000oO = new O0000O000000oO(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO);
    }

    public static O000O0000OoO O0000O000000oO() {
        if (O000O0000O0oO == null) {
            synchronized (O000O0000OoO.class) {
                if (O000O0000O0oO == null) {
                    O000O0000O0oO = new O000O0000OoO();
                }
            }
        }
        return O000O0000O0oO;
    }

    public List<String> O000O00000OoO() {
        ArrayList arrayList = new ArrayList();
        try {
            for (Sensor sensor : this.O0000O000000oO.getSensorList(-1)) {
                arrayList.add(sensor.getType() + "," + sensor.getVendor());
            }
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor", "Get sensor info error", e);
        }
        return arrayList;
    }
}
