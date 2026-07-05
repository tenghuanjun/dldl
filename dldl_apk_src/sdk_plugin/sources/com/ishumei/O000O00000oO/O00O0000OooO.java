package com.ishumei.O000O00000oO;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000OooO {
    private Map<String, O000O0000O0oO> O0000O000000oO;
    private SensorManager O000O00000OoO;

    private abstract class O0000O000000oO implements O000O0000O0oO {
        private String O000O00000OoO;
        private int O000O00000o0O;
        private int O000O00000oO;
        private int O000O0000O0oO;
        private Map<String, Map<Long, Object>> O000O0000OOoO;
        private volatile float[] O000O0000Oo0O;

        private O0000O000000oO() {
            this.O000O00000OoO = "Sensor_" + O000O0000OOoO();
            this.O000O00000o0O = 1;
            this.O000O00000oO = 3;
            this.O000O0000O0oO = 0;
            this.O000O0000OOoO = new ConcurrentHashMap();
            this.O000O0000Oo0O = null;
        }

        protected abstract SensorEventListener O0000O000000oO();

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public Map<Long, Object> O0000O000000oO(String str) {
            Map<Long, Object> map = this.O000O0000OOoO.get(str);
            if (map == null) {
                return null;
            }
            HashMap map2 = new HashMap(map);
            map.clear();
            return map2;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public void O0000O000000oO(int i, int i2) {
            this.O000O00000o0O = i;
            this.O000O00000oO = i2;
        }

        protected void O0000O000000oO(SensorEvent sensorEvent) {
            if (sensorEvent == null || sensorEvent.values == null || sensorEvent.values.length == 0) {
                return;
            }
            this.O000O0000Oo0O = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
        }

        protected abstract List<Sensor> O000O00000OoO();

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public void O000O00000OoO(final String str) {
            com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(new Runnable() { // from class: com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO.1
                @Override // java.lang.Runnable
                public void run() {
                    Object objO000O0000O0oO = O0000O000000oO.this.O000O0000O0oO();
                    Map concurrentHashMap = (Map) O0000O000000oO.this.O000O0000OOoO.get(str);
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                        O0000O000000oO.this.O000O0000OOoO.put(str, concurrentHashMap);
                    }
                    concurrentHashMap.put(Long.valueOf(System.currentTimeMillis()), objO000O0000O0oO);
                    if (concurrentHashMap.size() < O0000O000000oO.this.O000O00000o0O) {
                        com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(this, 5, O0000O000000oO.this.O000O00000oO, false);
                    }
                }
            }, 5, this.O000O00000oO, false);
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public synchronized void O000O00000o0O() {
            try {
                if (O00O0000OooO.this.O000O00000OoO != null) {
                    if (this.O000O0000O0oO == 0) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(this.O000O00000OoO, "register listener");
                        List<Sensor> listO000O00000OoO = O000O00000OoO();
                        if (listO000O00000OoO != null && listO000O00000OoO.size() != 0) {
                            int i = this.O000O00000oO * 1000 < 200000 ? this.O000O00000oO * 1000 : 3;
                            Iterator<Sensor> it = listO000O00000OoO.iterator();
                            while (it.hasNext()) {
                                if (!O00O0000OooO.this.O000O00000OoO.registerListener(O0000O000000oO(), it.next(), i)) {
                                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO(this.O000O00000OoO, "sensor registerListener SENSOR_DELAY_NORMAL failed.");
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    this.O000O0000O0oO++;
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(this.O000O00000OoO, "registerSuccessedCount+1 = " + this.O000O0000O0oO);
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO(this.O000O00000OoO, "register failed: " + e.getMessage());
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x005d A[DONT_GENERATE] */
        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void O000O00000oO() {
            /*
                r4 = this;
                monitor-enter(r4)
                com.ishumei.O000O00000oO.O00O0000OooO r0 = com.ishumei.O000O00000oO.O00O0000OooO.this     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                android.hardware.SensorManager r0 = com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO(r0)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                if (r0 == 0) goto L5d
                int r0 = r4.O000O0000O0oO     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                int r0 = r0 + (-1)
                r4.O000O0000O0oO = r0     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.String r0 = r4.O000O00000OoO     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                r1.<init>()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.String r2 = "registerSuccessedCount-1 = "
                r1.append(r2)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                int r2 = r4.O000O0000O0oO     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                r1.append(r2)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(r0, r1)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                int r0 = r4.O000O0000O0oO     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                if (r0 != 0) goto L5d
                com.ishumei.O000O00000oO.O00O0000OooO r0 = com.ishumei.O000O00000oO.O00O0000OooO.this     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                android.hardware.SensorManager r0 = com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO(r0)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                android.hardware.SensorEventListener r1 = r4.O0000O000000oO()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                r0.unregisterListener(r1)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.String r0 = r4.O000O00000OoO     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                java.lang.String r1 = "unregisterListener"
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(r0, r1)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
                goto L5d
            L40:
                r0 = move-exception
                goto L5f
            L42:
                r0 = move-exception
                java.lang.String r1 = r4.O000O00000OoO     // Catch: java.lang.Throwable -> L40
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L40
                r2.<init>()     // Catch: java.lang.Throwable -> L40
                java.lang.String r3 = "unregister failed: "
                r2.append(r3)     // Catch: java.lang.Throwable -> L40
                java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L40
                r2.append(r0)     // Catch: java.lang.Throwable -> L40
                java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L40
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO(r1, r0)     // Catch: java.lang.Throwable -> L40
            L5d:
                monitor-exit(r4)
                return
            L5f:
                monitor-exit(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO.O000O00000oO():void");
        }

        protected Object O000O0000O0oO() {
            return this.O000O0000Oo0O == null ? new float[]{-1.0f, -1.0f, -1.0f} : Arrays.copyOf(this.O000O0000Oo0O, this.O000O0000Oo0O.length);
        }
    }

    private class O000O00000OoO extends O0000O000000oO {
        private int O000O00000o0O;
        private volatile int O000O00000oO;
        private BroadcastReceiver O000O0000O0oO;

        private O000O00000OoO() {
            super();
            this.O000O00000o0O = 0;
            this.O000O00000oO = -1;
            this.O000O0000O0oO = new BroadcastReceiver() { // from class: com.ishumei.O000O00000oO.O00O0000OooO.O000O00000OoO.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                        O000O00000OoO.this.O000O00000oO = intent.getIntExtra("voltage", 0);
                    }
                }
            };
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected SensorEventListener O0000O000000oO() {
            return null;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected List<Sensor> O000O00000OoO() {
            return null;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO, com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public synchronized void O000O00000o0O() {
            try {
                Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
                if (context != null) {
                    if (this.O000O00000o0O == 0) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Battery", "register listener");
                        context.registerReceiver(this.O000O0000O0oO, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                    }
                    this.O000O00000o0O++;
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Battery", "registerSuccessedCount+1 = " + this.O000O00000o0O);
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Battery", "register failed: " + e.getMessage());
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0051 A[DONT_GENERATE] */
        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO, com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void O000O00000oO() {
            /*
                r4 = this;
                monitor-enter(r4)
                android.content.Context r0 = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                if (r0 == 0) goto L51
                int r1 = r4.O000O00000o0O     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                int r1 = r1 + (-1)
                r4.O000O00000o0O = r1     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r1 = "Battery"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                r2.<init>()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r3 = "registerSuccessedCount-1 = "
                r2.append(r3)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                int r3 = r4.O000O00000o0O     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                r2.append(r3)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(r1, r2)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                int r1 = r4.O000O00000o0O     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                if (r1 != 0) goto L51
                android.content.BroadcastReceiver r1 = r4.O000O0000O0oO     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                r0.unregisterReceiver(r1)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                java.lang.String r0 = "Battery"
                java.lang.String r1 = "unregisterListener"
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(r0, r1)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
                goto L51
            L34:
                r0 = move-exception
                goto L53
            L36:
                r0 = move-exception
                java.lang.String r1 = "Battery"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34
                r2.<init>()     // Catch: java.lang.Throwable -> L34
                java.lang.String r3 = "unregister failed: "
                r2.append(r3)     // Catch: java.lang.Throwable -> L34
                java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L34
                r2.append(r0)     // Catch: java.lang.Throwable -> L34
                java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L34
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO(r1, r0)     // Catch: java.lang.Throwable -> L34
            L51:
                monitor-exit(r4)
                return
            L53:
                monitor-exit(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O00000oO.O00O0000OooO.O000O00000OoO.O000O00000oO():void");
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected Object O000O0000O0oO() {
            return new int[]{this.O000O00000oO};
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public String O000O0000OOoO() {
            return "battery";
        }
    }

    private class O000O00000o0O extends O0000O000000oO {
        private SensorEventListener O000O00000o0O;

        private O000O00000o0O() {
            super();
            this.O000O00000o0O = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O00O0000OooO.O000O00000o0O.1
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    try {
                        if (sensorEvent.values == null || sensorEvent.values.length == 0 || sensorEvent.sensor.getType() != 9) {
                            return;
                        }
                        O000O00000o0O.this.O0000O000000oO(sensorEvent);
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor_gravity", "onSensorChanged failed: " + e.getMessage());
                    }
                }
            };
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected SensorEventListener O0000O000000oO() {
            return this.O000O00000o0O;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected List<Sensor> O000O00000OoO() {
            return O00O0000OooO.this.O000O00000OoO == null ? Collections.emptyList() : Collections.singletonList(O00O0000OooO.this.O000O00000OoO.getDefaultSensor(9));
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public String O000O0000OOoO() {
            return "gravity";
        }
    }

    private class O000O00000oO extends O0000O000000oO {
        private volatile float[] O000O00000o0O;
        private volatile float[] O000O00000oO;
        private SensorEventListener O000O0000O0oO;

        private O000O00000oO() {
            super();
            this.O000O00000o0O = null;
            this.O000O00000oO = null;
            this.O000O0000O0oO = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O00O0000OooO.O000O00000oO.1
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    try {
                        if (sensorEvent.values != null && sensorEvent.values.length != 0) {
                            if (sensorEvent.sensor.getType() == 2) {
                                O000O00000oO.this.O000O00000oO = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
                            }
                            if (sensorEvent.sensor.getType() == 1) {
                                O000O00000oO.this.O000O00000o0O = Arrays.copyOf(sensorEvent.values, sensorEvent.values.length);
                            }
                        }
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor_gyro", "gyro onSensorChanged failed: " + e.getMessage());
                    }
                }
            };
        }

        private float[] O000O0000Oo0O() {
            if (this.O000O00000o0O == null || this.O000O00000oO == null) {
                return new float[]{-1.0f, -1.0f, -1.0f};
            }
            float[] fArr = new float[9];
            float[] fArrCopyOf = Arrays.copyOf(this.O000O00000o0O, this.O000O00000o0O.length);
            float[] fArrCopyOf2 = Arrays.copyOf(this.O000O00000oO, this.O000O00000oO.length);
            this.O000O00000o0O = null;
            this.O000O00000oO = null;
            SensorManager.getRotationMatrix(fArr, null, fArrCopyOf, fArrCopyOf2);
            SensorManager.getOrientation(fArr, new float[3]);
            float degrees = (float) Math.toDegrees(r0[0]);
            float degrees2 = (float) Math.toDegrees(r0[1]);
            float degrees3 = (float) Math.toDegrees(r0[2]);
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("Sensor_gyro", "" + degrees + " " + degrees2 + " " + degrees3);
            return new float[]{degrees, degrees2, degrees3};
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected SensorEventListener O0000O000000oO() {
            return this.O000O0000O0oO;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected List<Sensor> O000O00000OoO() {
            return O00O0000OooO.this.O000O00000OoO == null ? Collections.emptyList() : Arrays.asList(O00O0000OooO.this.O000O00000OoO.getDefaultSensor(1), O00O0000OooO.this.O000O00000OoO.getDefaultSensor(2));
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected Object O000O0000O0oO() {
            return O000O0000Oo0O();
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public String O000O0000OOoO() {
            return "gyro";
        }
    }

    public interface O000O0000O0oO {
        Map<Long, Object> O0000O000000oO(String str);

        void O0000O000000oO(int i, int i2);

        void O000O00000OoO(String str);

        void O000O00000o0O();

        void O000O00000oO();

        String O000O0000OOoO();
    }

    private class O000O0000OOoO extends O0000O000000oO {
        private SensorEventListener O000O00000o0O;

        private O000O0000OOoO() {
            super();
            this.O000O00000o0O = new SensorEventListener() { // from class: com.ishumei.O000O00000oO.O00O0000OooO.O000O0000OOoO.1
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    try {
                        if (sensorEvent.values == null || sensorEvent.values.length == 0 || sensorEvent.sensor.getType() != 5) {
                            return;
                        }
                        O000O0000OOoO.this.O0000O000000oO(sensorEvent);
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Sensor_light", "onSensorChanged failed: " + e.getMessage());
                    }
                }
            };
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected SensorEventListener O0000O000000oO() {
            return this.O000O00000o0O;
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O0000O000000oO
        protected List<Sensor> O000O00000OoO() {
            return O00O0000OooO.this.O000O00000OoO == null ? Collections.emptyList() : Collections.singletonList(O00O0000OooO.this.O000O00000OoO.getDefaultSensor(5));
        }

        @Override // com.ishumei.O000O00000oO.O00O0000OooO.O000O0000O0oO
        public String O000O0000OOoO() {
            return "light";
        }
    }

    private static class O000O0000Oo0O {
        private static final O00O0000OooO O0000O000000oO = new O00O0000OooO();
    }

    private O00O0000OooO() {
        this.O000O00000OoO = null;
        HashMap map = new HashMap();
        this.O0000O000000oO = map;
        map.put("gyro", new O000O00000oO());
        this.O0000O000000oO.put("light", new O000O0000OOoO());
        this.O0000O000000oO.put("gravity", new O000O00000o0O());
        this.O0000O000000oO.put("battery", new O000O00000OoO());
        Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        if (context != null) {
            this.O000O00000OoO = (SensorManager) context.getSystemService("sensor");
        }
    }

    public static O00O0000OooO O0000O000000oO() {
        return O000O0000Oo0O.O0000O000000oO;
    }

    public O000O0000O0oO O0000O000000oO(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.O0000O000000oO.get(str);
    }
}
