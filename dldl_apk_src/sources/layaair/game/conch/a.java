package layaair.game.conch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import layaair.game.browser.ConchJNI;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class a implements SensorEventListener {
    private /* synthetic */ LayaConch5 a;

    a(LayaConch5 layaConch5) {
        this.a = layaConch5;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        this.a.x = sensorEvent.values[0];
        this.a.y = sensorEvent.values[1];
        this.a.z = sensorEvent.values[2];
        if (type == 3) {
            ConchJNI.handleDeviceOrientationEvent(this.a.x, this.a.y, this.a.z);
        } else {
            ConchJNI.handleDeviceMotionEvent(0.0f, 0.0f, 0.0f, this.a.x, this.a.y, this.a.z, 0.0f, 0.0f, 0.0f, 1.0f);
        }
    }
}
