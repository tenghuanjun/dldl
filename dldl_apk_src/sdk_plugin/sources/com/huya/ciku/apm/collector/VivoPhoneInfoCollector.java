package com.huya.ciku.apm.collector;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.text.TextUtils;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import com.huya.ciku.apm.MonitorCenter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VivoPhoneInfoCollector extends CycleCollector {
    private static final String KEY_FRAME_RATE = "3";
    private static final String KEY_LOSE_FRAME = "4";
    private static final String KEY_NETWORK_DELAY = "8";
    private static final String KEY_PHONE_FREQUENCY = "2";
    private static final String KEY_PHONE_TEMPERATURE = "1";
    private static final String KEY_SCENE = "2";
    private static final String KEY_SYSTEM_STATE_INFO = "10";
    public static final String METRIC_VIVO_PHONE_FRAME_RATE = "vivophoneframerate";
    public static final String METRIC_VIVO_PHONE_FREQUENCY = "vivophonefrequency";
    public static final String METRIC_VIVO_PHONE_LOSE_FRAME = "vivophonelostframe";
    public static final String METRIC_VIVO_PHONE_NETWORK_DELAY = "vivophonenetworkdelay";
    public static final String METRIC_VIVO_PHONE_TEMPERATURE = "vivophonetemperature";
    private static final String TAG = VivoPhoneInfoCollector.class.getSimpleName();
    private static final String VALUE_FRAME_RATE = "30";
    private static final String VALUE_LOST_FRAME = "1";
    private static final String VALUE_NETWORK_DELAY = "100";
    private InputStream inputStream;
    private boolean isOnlyLivingEnabled;
    private String mFrequency;
    private LocalSocket mLocalSocket;
    private String mScene;
    private OutputStream outputStream;

    public VivoPhoneInfoCollector() {
        super(2000L);
        this.isOnlyLivingEnabled = false;
        this.mScene = "0";
        this.mLocalSocket = new LocalSocket();
        connect();
    }

    public void setScene(String str) {
        this.mScene = str;
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        if (jSONObject != null) {
            this.isOnlyLivingEnabled = jSONObject.optBoolean("isOnlyLivingEnabled");
        }
        if (isEnabled()) {
            getInfoData();
        }
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        boolean z = this.isOnlyLivingEnabled;
        if (!z || (z && MonitorCenter.getInstance().isLiving())) {
            getVivoPhoneInfo();
        }
    }

    private void connect() {
        try {
            if (this.mLocalSocket.isConnected()) {
                return;
            }
            this.mLocalSocket.connect(new LocalSocketAddress("perfsdkmon"));
        } catch (IOException e) {
            MonitorLog.e(TAG, e.getMessage());
        }
    }

    private void getVivoPhoneInfo() {
        OutputStream outputStream;
        connect();
        try {
            outputStream = this.mLocalSocket.getOutputStream();
            this.outputStream = outputStream;
        } catch (Exception e) {
            MonitorLog.e(TAG, e.getMessage());
        }
        if (outputStream != null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("10", "1");
            jSONObject.put("2", this.mScene);
            this.outputStream.write(jSONObject.toString().getBytes());
            this.outputStream.flush();
            return;
        }
        return;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseJson(String str) {
        double d;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("1");
            String strOptString2 = jSONObject.optString("2");
            String strOptString3 = jSONObject.optString("3");
            String strOptString4 = jSONObject.optString("4");
            String strOptString5 = jSONObject.optString("8");
            if (TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                this.mFrequency = strOptString2;
                return;
            }
            double d2 = 0.0d;
            MonitorCenter.getInstance().request(METRIC_VIVO_PHONE_TEMPERATURE, TextUtils.isEmpty(strOptString) ? 0.0d : Double.parseDouble(strOptString), EUnit.EUnit_Count);
            MonitorCenter.getInstance().request(METRIC_VIVO_PHONE_FRAME_RATE, TextUtils.isEmpty(strOptString3) ? 0.0d : Double.parseDouble(strOptString3), EUnit.EUnit_Count);
            MonitorCenter.getInstance().request(METRIC_VIVO_PHONE_LOSE_FRAME, TextUtils.isEmpty(strOptString4) ? 0.0d : Double.parseDouble(strOptString4), EUnit.EUnit_Count);
            MonitorCenter.getInstance().request(METRIC_VIVO_PHONE_NETWORK_DELAY, TextUtils.isEmpty(strOptString5) ? 0.0d : Double.parseDouble(strOptString5), EUnit.EUnit_Milliseconds);
            if (!TextUtils.isEmpty(this.mFrequency)) {
                d = Double.parseDouble(this.mFrequency);
                this.mFrequency = strOptString2;
            } else {
                if (!TextUtils.isEmpty(strOptString2)) {
                    d2 = Double.parseDouble(strOptString2);
                }
                d = d2;
            }
            MonitorCenter.getInstance().request(METRIC_VIVO_PHONE_FREQUENCY, d, EUnit.EUnit_Count);
        } catch (Exception e) {
            MonitorLog.e(TAG, e.getMessage());
        }
    }

    private void getInfoData() {
        new Thread(new Runnable() { // from class: com.huya.ciku.apm.collector.VivoPhoneInfoCollector.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VivoPhoneInfoCollector.this.inputStream = VivoPhoneInfoCollector.this.mLocalSocket.getInputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = VivoPhoneInfoCollector.this.inputStream.read(bArr);
                        if (i == -1) {
                            return;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                        String str = new String(byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                        VivoPhoneInfoCollector.this.parseJson(str);
                    }
                } catch (Exception e) {
                    MonitorLog.e(VivoPhoneInfoCollector.TAG, e.getMessage());
                }
            }
        }).start();
    }
}
