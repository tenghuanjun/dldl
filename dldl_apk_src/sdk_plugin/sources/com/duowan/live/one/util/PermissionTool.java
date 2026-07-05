package com.duowan.live.one.util;

import android.media.AudioRecord;
import com.duowan.auk.util.L;
import com.youme.im.CommonConst;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PermissionTool {
    private static final String TAG = "PermissionTool";

    public static boolean checkAVPermission() {
        int minBufferSize = AudioRecord.getMinBufferSize(CommonConst.SAMPLERATE_44K, 12, 2);
        boolean z = false;
        if (minBufferSize > 0) {
            try {
                AudioRecord audioRecord = new AudioRecord(1, CommonConst.SAMPLERATE_44K, 12, 2, minBufferSize);
                boolean z2 = audioRecord.getState() == 1;
                try {
                    audioRecord.startRecording();
                    if (audioRecord.getRecordingState() != 3) {
                        L.error(TAG, "no recoud permission");
                    } else {
                        z = true;
                    }
                    audioRecord.stop();
                    audioRecord.release();
                } catch (Exception e) {
                    e = e;
                    z = z2;
                    L.error(TAG, "" + e);
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        L.info(TAG, "checkAVPermission->:" + z);
        return z;
    }
}
