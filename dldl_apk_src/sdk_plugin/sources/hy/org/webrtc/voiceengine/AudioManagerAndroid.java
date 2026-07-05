package hy.org.webrtc.voiceengine;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class AudioManagerAndroid {
    private static final String ACTION_USB_PERMISSION = "com.duowan.live.USB_PERMISSION";
    private static final int DEFAULT_FRAMES_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLING_RATE = 44100;
    private static final String TAG = "Audio";
    private static final String TAG1 = "AudioManagerAndroid";
    private static String filesDir;
    private static Context mCtx;
    private int mAudioLowLatencyOutputFrameSize;
    private boolean mAudioLowLatencySupported;
    private final AudioManager mAudioManager;
    private final Context mContext;
    private int mNativeOutputSampleRate;
    private final UsbManager mUsbManager;
    private final String scoAudioStateIntent;
    private boolean initialized = false;
    private boolean mIsHeadsetPlugReceiverRegistered = false;
    private BroadcastReceiver mHeadsetPlugReceiver = new BroadcastReceiver() { // from class: hy.org.webrtc.voiceengine.AudioManagerAndroid.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("state")) {
                int intExtra = intent.getIntExtra("state", 0);
                AudioManagerAndroid.this.nativeNotifyHeadsetPlug(intExtra);
                if (intExtra == 0) {
                    Log.i(AudioManagerAndroid.TAG, "headset not connected");
                } else if (intExtra == 1) {
                    Log.i(AudioManagerAndroid.TAG, "headset connected");
                }
            }
        }
    };
    private boolean UsbReceiverRegistered = false;
    private BroadcastReceiver UsbReceiver = new BroadcastReceiver() { // from class: hy.org.webrtc.voiceengine.AudioManagerAndroid.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean z = false;
            if (action.equals(AudioManagerAndroid.ACTION_USB_PERMISSION)) {
                synchronized (this) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    if (!intent.getBooleanExtra("permission", false)) {
                        AudioManagerAndroid.DoLog("usb permission is denied");
                    } else if (usbDevice != null) {
                        AudioManagerAndroid.DoLog("usb permission is granted" + AudioManagerAndroid.this.collectDeviceInfo(usbDevice));
                    }
                }
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice2 != null) {
                    AudioManagerAndroid.DoLog("usb attached" + AudioManagerAndroid.this.collectDeviceInfo(usbDevice2));
                    if (usbDevice2.getDeviceClass() == 1) {
                        z = true;
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        int i = 0;
                        boolean z2 = false;
                        while (true) {
                            if (i >= usbDevice2.getConfigurationCount()) {
                                break;
                            }
                            UsbConfiguration configuration = usbDevice2.getConfiguration(i);
                            if (configuration == null) {
                                AudioManagerAndroid.DoLog("null usb configuration");
                                break;
                            }
                            int i2 = 0;
                            while (true) {
                                if (i2 < configuration.getInterfaceCount()) {
                                    UsbInterface usbInterface = configuration.getInterface(i2);
                                    if (usbInterface != null && usbInterface.getInterfaceClass() == 1) {
                                        z2 = true;
                                        break;
                                    }
                                    i2++;
                                } else {
                                    break;
                                }
                            }
                            if (z2) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        z = z2;
                    }
                    if (z) {
                        AudioManagerAndroid.this.nativeNotifyUsbAudioPlug(1);
                        AudioManagerAndroid.DoLog("usb audio device plugged in");
                        return;
                    }
                    return;
                }
                AudioManagerAndroid.DoLog("null usb device");
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                AudioManagerAndroid.this.nativeNotifyUsbAudioPlug(0);
                AudioManagerAndroid.DoLog("usb audio device plugged out");
            }
        }
    };
    private boolean BluetoothReceiverRegistered = false;
    private BroadcastReceiver BluetoothReceiver = new BroadcastReceiver() { // from class: hy.org.webrtc.voiceengine.AudioManagerAndroid.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(AudioManagerAndroid.this.bluetoothHeadsetIntent)) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                if (intExtra == 2) {
                    AudioManagerAndroid.this.nativeNotifyBluetoothPlug(1);
                } else if (intExtra == 0) {
                    AudioManagerAndroid.this.nativeNotifyBluetoothPlug(0);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                if (intExtra2 == 10) {
                    AudioManagerAndroid.DoLog("onReceive close bluetooth adapter successfully");
                } else {
                    if (intExtra2 != 12) {
                        return;
                    }
                    AudioManagerAndroid.DoLog("onReceive open bluetooth adapter successfully");
                }
            }
        }
    };
    String url = "https://sdkconf.msstatic.com/AudioengineConfig";
    String filename = "AudioengineConfig";
    private int[] config = {-128, -128, -128, -128, -128, -128, -128, -128, -128, -128, -128, -128, 0};
    private String doc = "{\n\t\"xiaomi-mi 2s\":        [0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"xiaomi-mi pad\":       [0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"xiaomi-mi pad 2\":     [0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"xiaomi-hm\":           [3   ,7   ,0   ,0   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"xiaomi-mi 2a\":        [0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"coolpad-coolpad 8690\":[0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"huawei-huawei p7-l09\":[0   ,1   ,3   ,3   ,7   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"huawei-pe-ul00\":      [-128,-128,-128,-128,-128,-128,0   ,1   ,3   ,0   ,-128,-128,0],\n\t\"xiaomi-mi note lte\":  [-128,-128,-128,-128,-128,-128,0   ,7   ,3   ,0   ,-128,-128,0],\n\t\"xiaomi-mi 4lte\":      [-128,-128,-128,-128,-128,-128,0   ,7   ,3   ,0   ,-128,-128,0],\n\t\"zte-n918st\":          [-128,-128,-128,-128,-128,-128,0   ,7   ,3   ,0   ,-128,-128,0],\n\t\"samsung-sm-p350\":     [3   ,7   ,0   ,0   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"samsung-sm-p550\":     [0   ,1   ,3   ,3   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"samsung-sm-t530\":     [3   ,7   ,0   ,0   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"samsung-sm-n9006\":    [3   ,7   ,0   ,0   ,1   ,0   ,-128,-128,-128,0   ,-128,-128,0],\n\t\"default\":             [-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,0]\n}\n";
    private final String bluetoothHeadsetIntent = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";

    private static native void nativeLogDebugInfo(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeNotifyBluetoothPlug(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeNotifyHeadsetPlug(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeNotifyUsbAudioPlug(int i);

    private AudioManagerAndroid(Context context) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 14) {
            this.scoAudioStateIntent = "android.media.ACTION_SCO_AUDIO_STATE_UPDATED";
        } else {
            this.scoAudioStateIntent = "android.media.SCO_AUDIO_STATE_CHANGED";
        }
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mNativeOutputSampleRate = 44100;
        this.mAudioLowLatencyOutputFrameSize = 256;
        if (Build.VERSION.SDK_INT >= 17) {
            String property = audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if (property != null) {
                this.mNativeOutputSampleRate = Integer.parseInt(property);
            }
            String property2 = audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
            if (property2 != null) {
                this.mAudioLowLatencyOutputFrameSize = Integer.parseInt(property2);
            }
        }
        try {
            this.mAudioLowLatencySupported = context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
        } catch (RuntimeException unused) {
            this.mAudioLowLatencySupported = false;
        }
    }

    private void init(Context context) {
        if (this.initialized) {
            return;
        }
        Log.d(TAG, "audio mode is: " + this.mAudioManager.getMode());
        this.initialized = true;
    }

    private int getNativeOutputSampleRate() {
        return this.mNativeOutputSampleRate;
    }

    private boolean isAudioLowLatencySupported() {
        return this.mAudioLowLatencySupported;
    }

    private int getAudioLowLatencyOutputFrameSize() {
        return this.mAudioLowLatencyOutputFrameSize;
    }

    private static int getMode(Context context) {
        return ((AudioManager) context.getSystemService("audio")).getMode();
    }

    private static int isSpeakerphoneOn(Context context) {
        return ((AudioManager) context.getSystemService("audio")).isSpeakerphoneOn() ? 1 : 0;
    }

    private static void setMode(Context context, int i) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        audioManager.setMode(i);
        DoLog("AudioManagerAndroid setMode: " + audioManager.getMode());
    }

    private static void setSpeakerphoneOn(Context context, boolean z) {
        ((AudioManager) context.getSystemService("audio")).setSpeakerphoneOn(z);
        DoLog("AudioManagerAndroid setSpeakerphoneOn: " + String.valueOf(z));
    }

    public static void DoLog(String str) {
        try {
            nativeLogDebugInfo(str);
        } catch (UnsatisfiedLinkError unused) {
            Log.e(TAG, str);
        }
    }

    private void SetNotify(int i) {
        DoLog("AudioManagerAndroid SetNotify");
        if (i == 1) {
            registerBluetoothReceiver();
            registerHeadsetPlugReceiver();
            registerUsbAudioReceiver();
        } else {
            unregisterBluetoothReceiver();
            unRegisterHeadsetPlugReceiver();
            unRegisterUsbAudioReceiver();
        }
    }

    private void registerHeadsetPlugReceiver() {
        if (this.mIsHeadsetPlugReceiverRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        this.mContext.registerReceiver(this.mHeadsetPlugReceiver, intentFilter);
        this.mIsHeadsetPlugReceiverRegistered = true;
    }

    private void unRegisterHeadsetPlugReceiver() {
        Log.i(TAG, "unRegisterHeadsetPlugReceiver");
        if (this.mIsHeadsetPlugReceiverRegistered) {
            this.mContext.unregisterReceiver(this.mHeadsetPlugReceiver);
            this.mIsHeadsetPlugReceiverRegistered = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String collectDeviceInfo(UsbDevice usbDevice) {
        return "DeviceID: " + usbDevice.getDeviceId() + "\nDeviceName: " + usbDevice.getDeviceName() + "\nDeviceClass: " + usbDevice.getDeviceClass() + "\nDeviceSubClass: " + usbDevice.getDeviceSubclass() + "\nVendorID: " + usbDevice.getVendorId() + "\nProductID: " + usbDevice.getProductId() + ShellAdbUtils.COMMAND_LINE_END;
    }

    public boolean isUsbAudioDeviceConnected() {
        boolean z;
        try {
        } catch (Exception e) {
            Log.w(TAG, "detect usb device error", e);
        }
        for (UsbDevice usbDevice : this.mUsbManager.getDeviceList().values()) {
            if (this.mUsbManager.hasPermission(usbDevice)) {
                if (usbDevice.getDeviceClass() == 1) {
                    z = true;
                } else if (Build.VERSION.SDK_INT >= 21) {
                    int i = 0;
                    z = false;
                    while (true) {
                        if (i >= usbDevice.getConfigurationCount()) {
                            break;
                        }
                        UsbConfiguration configuration = usbDevice.getConfiguration(i);
                        if (configuration == null) {
                            DoLog("null usb configuration");
                            break;
                        }
                        int i2 = 0;
                        while (true) {
                            if (i2 >= configuration.getInterfaceCount()) {
                                break;
                            }
                            UsbInterface usbInterface = configuration.getInterface(i2);
                            if (usbInterface != null && usbInterface.getInterfaceClass() == 1) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                        if (z) {
                            break;
                        }
                        i++;
                    }
                } else {
                    z = false;
                }
                if (z) {
                    DoLog("Usb Audio Device Connected");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private void registerUsbAudioReceiver() {
        DoLog("registerUsbAudioReceiver");
        try {
            if (!this.UsbReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
                intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
                intentFilter.addAction(ACTION_USB_PERMISSION);
                this.mContext.registerReceiver(this.UsbReceiver, intentFilter);
                this.UsbReceiverRegistered = true;
                DoLog("Usb Receiver Registered");
            }
        } catch (Exception unused) {
            Log.e(TAG, "registerUsbReceiver error");
        }
        if (isUsbAudioDeviceConnected()) {
            nativeNotifyUsbAudioPlug(1);
        } else {
            nativeNotifyUsbAudioPlug(0);
        }
    }

    private void unRegisterUsbAudioReceiver() {
        if (this.UsbReceiverRegistered) {
            this.mContext.unregisterReceiver(this.UsbReceiver);
            this.UsbReceiverRegistered = false;
        }
    }

    public boolean isBluetoothHeadsetConnected() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || !this.mAudioManager.isBluetoothScoAvailableOffCall()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 14) {
                if (2 == defaultAdapter.getProfileConnectionState(1)) {
                    return true;
                }
            } else if (!BluetoothAdapter.getDefaultAdapter().getBondedDevices().isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            Log.w(TAG, "detect bluetooth error", e);
        }
        return false;
    }

    private void registerBluetoothReceiver() {
        Log.v(TAG, "registerBluetoothReceiver()");
        try {
            if (!this.BluetoothReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(this.bluetoothHeadsetIntent);
                intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
                this.mContext.registerReceiver(this.BluetoothReceiver, intentFilter);
                this.BluetoothReceiverRegistered = true;
            }
        } catch (Exception unused) {
            Log.e(TAG, "registerBluetoothReceiver error");
        }
        if (isBluetoothHeadsetConnected()) {
            nativeNotifyBluetoothPlug(1);
        } else {
            nativeNotifyBluetoothPlug(0);
        }
    }

    private void unregisterBluetoothReceiver() {
        if (this.BluetoothReceiverRegistered) {
            this.mContext.unregisterReceiver(this.BluetoothReceiver);
            this.BluetoothReceiverRegistered = false;
        }
    }

    private static void setBluetoothScoOn(Context context, int i) {
        ((AudioManager) context.getSystemService("audio")).setBluetoothScoOn(i > 0);
        DoLog("AudioManagerAndroid setBluetoothScoOn: " + i);
    }

    private static void startOrStopBluetoothSco(Context context, int i) {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (i > 0) {
                audioManager.startBluetoothSco();
            } else {
                audioManager.stopBluetoothSco();
            }
            DoLog("AudioManagerAndroid startOrStopBluetoothSco: " + i);
        } catch (Exception e) {
            DoLog("AudioManagerAndroid startOrStopBluetoothSco exception: " + e);
        }
    }

    private static int getAndroidOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    public int[] AsyncLoad(Context context) throws InterruptedException {
        mCtx = context;
        try {
            filesDir = context.getFilesDir().getAbsolutePath();
        } catch (Exception unused) {
            Log.d(TAG, "AudioEngineConfig AsyncLoad bug");
        }
        readLocalAudioEngineConfig();
        new Thread(new LoadThread()).start();
        Log.d(TAG, "AudioEngineConfig Param:" + Build.MODEL + ":" + Arrays.toString(this.config));
        return this.config;
    }

    private void readLocalAudioEngineConfig() {
        Log.i(TAG1, "read audio engine file");
        File file = new File(filesDir + "/" + this.filename);
        if (file.exists()) {
            try {
                Log.i(TAG1, "file input start");
                FileInputStream fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 1024);
                    if (i <= 0) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
                fileInputStream.close();
                this.doc = byteArrayOutputStream.toString();
                Log.i(TAG1, "file input end");
            } catch (Exception unused) {
                Log.e(TAG1, "file input error");
            }
        }
        Log.i(TAG1, "parse json start :" + this.doc);
        parseJson(this.doc);
        Log.i(TAG1, "parse json end");
    }

    private void parseJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = Build.MODEL;
            int i = 0;
            if (jSONObject.has(str2)) {
                Log.i(TAG, "AudioEngineConfig get json has mode");
                JSONArray jSONArray = jSONObject.getJSONArray(str2);
                this.config = new int[jSONArray.length()];
                while (i < jSONArray.length()) {
                    this.config[i] = jSONArray.getInt(i);
                    i++;
                }
                Log.i(TAG, "AudioEngineConfig get json model in" + Arrays.toString(this.config));
                return;
            }
            if (jSONObject.has("default")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("default");
                this.config = new int[jSONArray2.length()];
                while (i < jSONArray2.length()) {
                    this.config[i] = jSONArray2.getInt(i);
                    i++;
                }
                Log.i(TAG, "AudioEngineConfig Param get default mode :" + Arrays.toString(this.config));
            }
        } catch (Exception e) {
            Log.i("AudioEngineConfig", "Param Load Error " + e.getMessage());
        }
    }

    class LoadThread implements Runnable {
        LoadThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            updateLocalAudioEngineConfig();
        }

        void updateLocalAudioEngineConfig() {
            try {
                File file = new File(AudioManagerAndroid.filesDir + "/" + AudioManagerAndroid.this.filename);
                if (file.exists() && AudioManagerAndroid.InToday(file.lastModified())) {
                    return;
                }
                try {
                    AudioManagerAndroid.this.doc = AudioManagerAndroid.get(AudioManagerAndroid.this.url);
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(AudioManagerAndroid.this.doc);
                    fileWriter.close();
                    Log.i(AudioManagerAndroid.TAG1, "AudioEngineConfig get json finish");
                } catch (Exception e) {
                    Log.i(AudioManagerAndroid.TAG1, "getJsonConfig fail " + e.getMessage());
                }
            } catch (Exception e2) {
                Log.i(AudioManagerAndroid.TAG1, "getJsonConfig fail " + e2.getMessage());
            }
        }
    }

    static boolean InToday(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return j > calendar.getTimeInMillis();
    }

    static String get(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            } else {
                bufferedReader.close();
                httpURLConnection.disconnect();
                return sb.toString();
            }
        }
    }
}
