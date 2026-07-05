package hy.org.webrtc.voiceengine;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Process;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class WebRtcAudioRecord {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final int SAMPLE_RATE_HZ = 44100;
    private static final String TAG = "[AudioRecord]";
    private final AudioManager audioManager;
    private ByteBuffer byteBuffer;
    private int bytesPerBuffer;
    private int channelCount;
    private final Context context;
    private int framesPerBuffer;
    private final long nativeAudioRecord;
    private int sampleRate;
    private AudioRecord audioRecord = null;
    private AudioRecordThread audioThread = null;
    private AcousticEchoCanceler aec = null;
    private boolean useBuiltInAEC = false;
    private final Set<Long> threadIds = new HashSet();

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDataIsRecorded(int i, long j);

    private static boolean runningOnJellyBeanOrHigher() {
        return Build.VERSION.SDK_INT >= 16;
    }

    private static boolean runningOnJellyBeanMR1OrHigher() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasRecPermission(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private class AudioRecordThread extends Thread {
        private volatile boolean keepAlive;

        public AudioRecordThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x010f, code lost:
        
            r11.this$0.audioRecord.stop();
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0119, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x011a, code lost:
        
            r11.this$0.DoLogErr("AudioRecord.stop failed: " + r0.getMessage());
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 356
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: hy.org.webrtc.voiceengine.WebRtcAudioRecord.AudioRecordThread.run():void");
        }

        public void joinThread() {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    WebRtcAudioRecord(Context context, long j) {
        DoLog("ctor" + getThreadInfo());
        this.context = context;
        this.nativeAudioRecord = j;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.sampleRate = 0;
        this.bytesPerBuffer = 0;
        this.framesPerBuffer = 0;
    }

    private int GetNativeSampleRate() {
        String property;
        if (runningOnJellyBeanMR1OrHigher() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE")) != null) {
            return Integer.parseInt(property);
        }
        return 44100;
    }

    public static boolean BuiltInAECIsAvailable() {
        if (runningOnJellyBeanOrHigher()) {
            return AcousticEchoCanceler.isAvailable();
        }
        return false;
    }

    private boolean EnableBuiltInAEC(boolean z) {
        DoLog("EnableBuiltInAEC(" + z + ')');
        if (!runningOnJellyBeanOrHigher()) {
            return false;
        }
        this.useBuiltInAEC = z;
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler == null) {
            return true;
        }
        if (acousticEchoCanceler.setEnabled(z) != 0) {
            DoLogErr("AcousticEchoCanceler.setEnabled failed");
            return false;
        }
        DoLog("AcousticEchoCanceler.getEnabled: " + this.aec.getEnabled());
        return true;
    }

    private int InitRecording(int i, int i2) {
        int i3;
        this.sampleRate = i;
        if (i2 == 256) {
            this.channelCount = 2;
            i3 = 12;
        } else {
            this.channelCount = 1;
            i3 = 16;
        }
        int i4 = this.channelCount * 2;
        int i5 = this.sampleRate;
        int i6 = i4 * (i5 / 100);
        this.bytesPerBuffer = i6;
        this.framesPerBuffer = i5 / 100;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i6);
        this.byteBuffer = byteBufferAllocateDirect;
        nativeCacheDirectBufferAddress(byteBufferAllocateDirect, this.nativeAudioRecord);
        DoLog("InitRecording(sampleRate=" + this.sampleRate + ")");
        int minBufferSize = AudioRecord.getMinBufferSize(this.sampleRate, i3, 2);
        DoLog("AudioRecord.getMinBufferSize: " + minBufferSize);
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.release();
            this.audioRecord = null;
        }
        int iMax = Math.max(this.byteBuffer.capacity(), minBufferSize * 2);
        DoLog("bufferSizeInBytes: " + iMax + ", " + this.byteBuffer.capacity() + ", " + minBufferSize);
        StringBuilder sb = new StringBuilder();
        sb.append("audioSource: ");
        sb.append(i2);
        DoLog(sb.toString());
        try {
            AudioRecord audioRecord2 = new AudioRecord(i2 == 256 ? 1 : i2, this.sampleRate, i3, 2, iMax);
            this.audioRecord = audioRecord2;
            if (audioRecord2.getState() != 1) {
                return -1;
            }
            DoLog("AudioRecord audio format: " + this.audioRecord.getAudioFormat() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
            return this.framesPerBuffer;
        } catch (IllegalArgumentException e) {
            DoLog(e.getMessage());
            return -1;
        }
    }

    private boolean StartRecording() {
        DoLog("StartRecording");
        if (this.audioRecord == null) {
            DoLogErr("start() called before init()");
            return false;
        }
        if (this.audioThread != null) {
            DoLogErr("start() was already called");
            return false;
        }
        AudioRecordThread audioRecordThread = new AudioRecordThread("AudioRecordJavaThread");
        this.audioThread = audioRecordThread;
        audioRecordThread.start();
        return true;
    }

    private boolean StopRecording() {
        DoLog("StopRecording");
        AudioRecordThread audioRecordThread = this.audioThread;
        if (audioRecordThread == null) {
            DoLogErr("start() was never called, or stop() was already called");
            return false;
        }
        audioRecordThread.joinThread();
        this.audioThread = null;
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.aec = null;
        }
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord == null) {
            return true;
        }
        audioRecord.release();
        this.audioRecord = null;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void DoLog(String str) {
        AudioManagerAndroid.DoLog(TAG + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void DoLogErr(String str) {
        AudioManagerAndroid.DoLog("[AudioRecord][Error]" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    private static void assertIsTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private void AddThreadId() {
        this.threadIds.add(Long.valueOf(Thread.currentThread().getId()));
        DoLog("threadIds: " + this.threadIds + " (#threads=" + this.threadIds.size() + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RemoveThreadId() {
        this.threadIds.remove(Long.valueOf(Thread.currentThread().getId()));
        DoLog("threadIds: " + this.threadIds + " (#threads=" + this.threadIds.size() + ")");
    }
}
