package com.youme.voiceengine;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.youme.im.CommonConst;
import com.youme.voiceengine.YouMeConst;
import com.youme.voiceengine.mgr.YouMeManager;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioMgr {
    private static final int audioPermissionRequestCode = 1;
    private static AudioManager mAudioManager;
    private static Boolean mSpeakerOnBoolean = false;
    private static Boolean mIsOutputToSpeaker = false;
    private static int mMode = -1;
    private static Boolean mHasChangedBoolean = false;
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static BroadcastReceiver mReceiver = null;
    private static Context mContext = null;
    private static Boolean mIsBluetoothScoOn = false;
    private static boolean mHasHeadSet = false;
    private static boolean mIsBluetoothOn = false;
    private static int requestPermissionCount = 0;
    private static boolean isStopedByExternalNotify = false;
    private static boolean isCheckedPermission = false;
    public static boolean audioFocusLoss = false;
    public static AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.youme.voiceengine.AudioMgr.2
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            int mode = AudioMgr.mAudioManager.getMode();
            if (i == -2) {
                Log.d("AudioMgr", "phone AUDIOFOCUS_LOSS_TRANSIENT mode:" + mode);
                if (AudioMgr.audioFocusLoss || mode != 3) {
                    return;
                }
                AudioMgr.audioFocusLoss = true;
                api.pauseChannel();
                return;
            }
            if (i != 1) {
                if (i == -1) {
                    AudioMgr.mAudioManager.abandonAudioFocus(AudioMgr.afChangeListener);
                    Log.d("AudioMgr", "phone AUDIOFOCUS_LOSS mode:" + mode);
                    return;
                }
                return;
            }
            Log.d("AudioMgr", "phone AUDIOFOCUS_GAIN mode:" + mode);
            if (AudioMgr.audioFocusLoss) {
                api.resumeChannel();
                AudioMgr.audioFocusLoss = false;
            }
        }
    };
    private static PermissionCheckThread mPermissionCheckThread = null;

    static /* synthetic */ int access$508() {
        int i = requestPermissionCount;
        requestPermissionCount = i + 1;
        return i;
    }

    public static void init(Context context) {
        if (mContext != null) {
            if (context instanceof Activity) {
                mContext = context;
                return;
            }
            return;
        }
        mContext = context;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        mAudioManager = audioManager;
        mIsOutputToSpeaker = Boolean.valueOf(audioManager.isSpeakerphoneOn());
        AppPara.onNetWorkChange(NetUtil.getNetworkState(mContext));
        mReceiver = new BroadcastReceiver() { // from class: com.youme.voiceengine.AudioMgr.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                try {
                    if (YouMeManager.mInited.booleanValue() && api.isJoined()) {
                        AudioManager audioManager2 = (AudioManager) AudioMgr.mContext.getSystemService("audio");
                        String action = intent.getAction();
                        Log.i("AudioMgr", "onReceive action: " + action + "  state: " + intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1));
                        if (action.equals(AudioMgr.NET_CHANGE_ACTION)) {
                            AppPara.onNetWorkChange(NetUtil.getNetworkState(AudioMgr.mContext));
                        }
                        if (action.equals("android.intent.action.HEADSET_PLUG")) {
                            boolean unused = AudioMgr.mHasHeadSet = intent.getIntExtra("state", 0) != 0;
                            AudioMgr.OnHeadsetChange(audioManager2, Boolean.valueOf(AudioMgr.mHasHeadSet), Boolean.valueOf(AudioMgr.mIsBluetoothOn));
                        }
                        if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            if (intExtra == 2) {
                                Log.i("AudioMgr", "BluetoothProfile.STATE_CONNECTED");
                                boolean unused2 = AudioMgr.mIsBluetoothOn = true;
                                AudioMgr.OnHeadsetChange(audioManager2, Boolean.valueOf(AudioMgr.mHasHeadSet), Boolean.valueOf(AudioMgr.mIsBluetoothOn));
                                return;
                            } else {
                                if (intExtra == 1) {
                                    Log.i("AudioMgr", "BluetoothProfile.STATE_CONNECTING");
                                    return;
                                }
                                if (intExtra == 0) {
                                    Log.i("AudioMgr", "BluetoothProfile.STATE_DISCONNECTED");
                                    boolean unused3 = AudioMgr.mIsBluetoothOn = false;
                                    AudioMgr.OnHeadsetChange(audioManager2, Boolean.valueOf(AudioMgr.mHasHeadSet), Boolean.valueOf(AudioMgr.mIsBluetoothOn));
                                    return;
                                } else {
                                    if (intExtra == 3) {
                                        Log.i("AudioMgr", "BluetoothProfile.STATE_DISCONNECTING");
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                        if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            if (intExtra2 == 12) {
                                Log.i("AudioMgr", "BluetoothHeadset.STATE_AUDIO_CONNECTED");
                                boolean unused4 = AudioMgr.mIsBluetoothOn = true;
                                AudioMgr.OnHeadsetChange(audioManager2, Boolean.valueOf(AudioMgr.mHasHeadSet), Boolean.valueOf(AudioMgr.mIsBluetoothOn));
                                return;
                            } else if (intExtra2 == 10) {
                                Log.i("AudioMgr", "BluetoothHeadset.STATE_AUDIO_DISCONNECTED");
                                boolean unused5 = AudioMgr.mIsBluetoothOn = false;
                                AudioMgr.OnHeadsetChange(audioManager2, Boolean.valueOf(AudioMgr.mHasHeadSet), Boolean.valueOf(AudioMgr.mIsBluetoothOn));
                                return;
                            } else {
                                if (intExtra2 == 11) {
                                    Log.i("AudioMgr", "BluetoothHeadset.STATE_AUDIO_CONNECTING");
                                    return;
                                }
                                return;
                            }
                        }
                        if (action.equals("android.intent.action.PHONE_STATE")) {
                            String string = intent.getExtras().getString("state");
                            Log.i("AudioMgr", "stateStr:" + string);
                            if (string.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                                api.resumeChannel();
                            } else if (string.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                                api.pauseChannel();
                            } else if (string.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                                api.pauseChannel();
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        try {
            context.registerReceiver(mReceiver, intentFilter);
        } catch (Throwable th) {
            Log.e("AudioMgr", "registerReceiver fail:");
            th.printStackTrace();
        }
    }

    public static void uinit() {
        Log.i("AudioMgr", "uinit");
        mContext.unregisterReceiver(mReceiver);
        mContext = null;
        mReceiver = null;
    }

    public static void setVoiceModeYouMeCoutum() {
        try {
            if (mAudioManager == null) {
                Log.e("AudioMgr", "mAudioManager is null");
                return;
            }
            mSpeakerOnBoolean = Boolean.valueOf(mAudioManager.isSpeakerphoneOn());
            mIsBluetoothScoOn = Boolean.valueOf(mAudioManager.isBluetoothScoOn());
            int i = Build.VERSION.SDK_INT >= 11 ? 3 : 2;
            if (Build.VERSION.SDK_INT >= 23) {
                Log.e("AudioMgr", "request audio focus result:" + mAudioManager.requestAudioFocus(afChangeListener, i, 1));
            }
            Log.i("AudioMgr", "==mMode:" + mMode + " mSpeakerOnBoolean:" + mSpeakerOnBoolean + " mIsBluetoothScoOn:" + mIsBluetoothScoOn + " isBluetoothA2dpOn:" + mAudioManager.isBluetoothA2dpOn());
            if (mMode == -1) {
                int mode = mAudioManager.getMode();
                mMode = mode;
                if (mode != i) {
                    Log.i("AudioMgr", "start setmode:" + i);
                    mAudioManager.setMode(i);
                } else {
                    Log.w("AudioMgr", "Already in MODE_IN_COMMUNICATION");
                }
            }
            if (mAudioManager.isBluetoothA2dpOn() || mIsBluetoothScoOn.booleanValue()) {
                mAudioManager.startBluetoothSco();
                mAudioManager.setSpeakerphoneOn(false);
                mAudioManager.setBluetoothScoOn(true);
                Log.i("AudioMgr", "to bluetooth");
            } else {
                boolean z = mAudioManager.isWiredHeadsetOn() ? false : true;
                Log.i("AudioMgr", "isToSpeaker:" + z);
                mAudioManager.setSpeakerphoneOn(z);
            }
            Log.i("AudioMgr", "设置communication 模式");
            mHasChangedBoolean = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void restoreOldMode() {
        try {
            if (mAudioManager == null) {
                return;
            }
            mIsOutputToSpeaker = mSpeakerOnBoolean;
            if (mHasChangedBoolean.booleanValue()) {
                mHasChangedBoolean = false;
                int mode = mAudioManager.getMode();
                Log.i("AudioMgr", "restoreOldMode:" + mMode);
                if (mode != mMode && mMode > -1) {
                    Log.i("AudioMgr", "stop setmode:" + mMode + " setSpeakerphoneOn:" + mSpeakerOnBoolean);
                    mAudioManager.setMode(mMode);
                    mAudioManager.setSpeakerphoneOn(mSpeakerOnBoolean.booleanValue());
                }
                mMode = -1;
                if (mIsBluetoothScoOn.booleanValue()) {
                    return;
                }
                Log.i("AudioMgr", "restoreOldMode stop BluetoothSco");
                mAudioManager.stopBluetoothSco();
                mAudioManager.setBluetoothScoOn(false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean hasChangedCoutum() {
        return mHasChangedBoolean.booleanValue();
    }

    public static void initAudioSettings(boolean z) {
        boolean zIsWiredHeadsetOn = mAudioManager.isWiredHeadsetOn();
        boolean zIsBluetoothScoOn = mAudioManager.isBluetoothScoOn();
        boolean zIsBluetoothA2dpOn = mAudioManager.isBluetoothA2dpOn();
        mIsOutputToSpeaker = Boolean.valueOf(z);
        try {
            if (zIsWiredHeadsetOn || zIsBluetoothScoOn || zIsBluetoothA2dpOn) {
                mAudioManager.setSpeakerphoneOn(false);
                Log.i("AudioMgr", "initAudioSettings setSpeakerphoneOn:false (isWiredHeadsetOn:" + zIsWiredHeadsetOn + " isBluetoothScoOn:" + zIsBluetoothScoOn + " isBluetoothA2dpOn:" + zIsBluetoothA2dpOn + ")");
            } else {
                Log.i("AudioMgr", "initAudioSetting setSpeakerphoneOn:" + z);
                mAudioManager.setSpeakerphoneOn(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static class PermissionCheckThread extends Thread {
        private PermissionCheckThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Log.i("AudioMgr", "PermissionCheck starts...");
                while (true) {
                    if (!Thread.interrupted()) {
                        AudioMgr.access$508();
                        Thread.sleep(OAIDHelper.TIMEOUT);
                        if (AudioMgr.mContext == null || !(AudioMgr.mContext instanceof Activity) || ContextCompat.checkSelfPermission((Activity) AudioMgr.mContext, "android.permission.RECORD_AUDIO") != 0) {
                            if (AudioMgr.requestPermissionCount > 3) {
                                int unused = AudioMgr.requestPermissionCount = 0;
                                NativeEngine.callbackPermissionStatus(YouMeConst.YouMeErrorCode.YOUME_ERROR_REC_NO_PERMISSION);
                                break;
                            }
                        } else {
                            NativeEngine.resetMicrophone();
                            break;
                        }
                    }
                }
                break;
            } catch (InterruptedException unused2) {
                Log.i("AudioMgr", "PermissionCheck interrupted");
            } catch (Throwable th) {
                Log.e("AudioMgr", "PermissionCheck caught a throwable:" + th.getMessage());
            }
            Log.i("AudioMgr", "PermissionCheck exit");
        }
    }

    public static boolean startRequestPermissionForApi23() {
        boolean z = true;
        isCheckedPermission = true;
        if (isStopedByExternalNotify) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 23 && mContext != null && (mContext instanceof Activity)) {
                if (mContext.getApplicationInfo().targetSdkVersion >= 23) {
                    try {
                        if (ContextCompat.checkSelfPermission((Activity) mContext, "android.permission.RECORD_AUDIO") != 0) {
                            Log.e("AudioMgr", "Request for record permission");
                            ActivityCompat.requestPermissions((Activity) mContext, new String[]{"android.permission.RECORD_AUDIO"}, 1);
                            if (mPermissionCheckThread != null) {
                                mPermissionCheckThread.interrupt();
                                mPermissionCheckThread.join(2000L);
                            }
                            PermissionCheckThread permissionCheckThread = new PermissionCheckThread();
                            mPermissionCheckThread = permissionCheckThread;
                            if (permissionCheckThread == null) {
                                return true;
                            }
                            permissionCheckThread.start();
                            return true;
                        }
                        Log.i("AudioMgr", "Already got record permission");
                        return true;
                    } catch (Throwable th) {
                        th = th;
                        Log.e("AudioMgr", "Exception for startRequirePermiForApi23");
                        th.printStackTrace();
                        return z;
                    }
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
    }

    public static void OnReqeustPermissionResult(int i, String[] strArr, int[] iArr) {
        if (iArr.length > 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (strArr[i2].equals("android.permission.RECORD_AUDIO")) {
                    isStopedByExternalNotify = true;
                    stopRequestPermissionForApi23();
                    if (iArr[i2] == 0) {
                        Log.i("AudioMgr", "OnReqeustPermissionResult Already got record permission");
                        stopRequestPermissionForApi23();
                        NativeEngine.resetMicrophone();
                    } else {
                        Log.i("AudioMgr", "OnReqeustPermissionResult user not granted permission");
                        stopRequestPermissionForApi23();
                        NativeEngine.resetMicrophone();
                    }
                }
            }
        }
    }

    public static void stopRequestPermissionForApi23() {
        try {
            if (mPermissionCheckThread != null) {
                mPermissionCheckThread.interrupt();
                mPermissionCheckThread.join(2000L);
                mPermissionCheckThread = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int isWiredHeadsetOn() {
        AudioManager audioManager = mAudioManager;
        if (audioManager != null) {
            return audioManager.isWiredHeadsetOn() ? 1 : 0;
        }
        return 0;
    }

    public static int getOutputSampleRate() {
        AudioManager audioManager = mAudioManager;
        if (audioManager == null) {
            return 0;
        }
        try {
            String property = audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if (property == null) {
                return CommonConst.SAMPLERATE_44K;
            }
            int i = Integer.parseInt(property);
            return i == 0 ? CommonConst.SAMPLERATE_44K : i;
        } catch (Throwable th) {
            Log.e("AudioMgr", "Could not get device defaults: " + th.toString());
            return CommonConst.SAMPLERATE_44K;
        }
    }

    public static int getOutputFramesPerBuffer() {
        AudioManager audioManager = mAudioManager;
        if (audioManager == null) {
            return 0;
        }
        try {
            String property = audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
            if (property == null) {
                return 256;
            }
            int i = Integer.parseInt(property);
            if (i == 0) {
                return 256;
            }
            return i;
        } catch (Throwable th) {
            Log.e("AudioMgr", "Could not get device defaults: " + th.toString());
            return 256;
        }
    }

    public static void OnHeadsetChange(final AudioManager audioManager, final Boolean bool, final Boolean bool2) {
        Log.i("AudioMgr", "isBluetoothScoOn:" + audioManager.isBluetoothScoOn() + "  isBluetoothA2dpOn:" + audioManager.isBluetoothA2dpOn());
        if (bool.booleanValue()) {
            audioManager.setSpeakerphoneOn(false);
            audioManager.setBluetoothScoOn(false);
            AppPara.onHeadSetPlugin(1);
            Log.i("AudioMgr", "hasHeadSet:" + bool + " isBluetoothOn:" + bool2);
            return;
        }
        if (bool2.booleanValue()) {
            new Timer().schedule(new TimerTask() { // from class: com.youme.voiceengine.AudioMgr.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        if (!audioManager.isBluetoothScoOn() && AudioMgr.mMode > -1) {
                            Log.i("AudioMgr", "not isBluetoothScoOn, need to startBluetoothSco");
                            audioManager.startBluetoothSco();
                            audioManager.setSpeakerphoneOn(false);
                            audioManager.setBluetoothScoOn(true);
                        }
                        AppPara.onHeadSetPlugin(0);
                        Log.i("AudioMgr", "hasHeadSet:" + bool + " isBluetoothOn:" + bool2);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, 500L);
            return;
        }
        audioManager.setBluetoothScoOn(false);
        audioManager.setSpeakerphoneOn(mIsOutputToSpeaker.booleanValue());
        AppPara.onHeadSetPlugin(0);
        Log.i("AudioMgr", "hasHeadSet:" + bool + " isBluetoothOn:" + bool2 + " output2Speaker:" + mIsOutputToSpeaker);
    }
}
