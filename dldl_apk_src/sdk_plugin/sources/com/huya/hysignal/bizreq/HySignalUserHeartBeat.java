package com.huya.hysignal.bizreq;

import android.os.Handler;
import android.os.Message;
import com.huya.hysignal.core.HySignalClient;
import com.huya.hysignal.core.Response;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.mtp.hyns.api.Request;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalUserHeartBeat {
    private static final int DURATION = 60000;
    private static final int HEART_BEAT_CMDID = 20;
    private static final int SKIP_DURATION = 10000;
    private static final String TAG = "HySignalUserHeartBeat";
    private static long mLastHeartTime;
    private static Runnable sHearBeatTask = new Runnable() { // from class: com.huya.hysignal.bizreq.HySignalUserHeartBeat.1
        @Override // java.lang.Runnable
        public void run() {
            HySignalUserHeartBeat.requestNewHeartBeat();
        }
    };
    private static boolean sInited;

    private HySignalUserHeartBeat() {
    }

    public static void init() {
        if (sInited) {
            return;
        }
        new Handler() { // from class: com.huya.hysignal.bizreq.HySignalUserHeartBeat.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (HySignalUserHeartBeat.sHearBeatTask != null) {
                    HySignalUserHeartBeat.sHearBeatTask.run();
                }
                sendEmptyMessageDelayed(0, 60000L);
            }
        }.sendEmptyMessage(0);
        sInited = true;
    }

    public static synchronized void onForeground() {
        if (!sInited) {
            HySignalLog.error(TAG, "foreground need init");
        } else {
            requestNewHeartBeat();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestNewHeartBeat() {
        if (sInited) {
            ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HySignalUserHeartBeat.3
                @Override // java.lang.Runnable
                public void run() {
                    long jCurrentTimeMillis = System.currentTimeMillis() - HySignalUserHeartBeat.mLastHeartTime;
                    HySignalLog.info(HySignalUserHeartBeat.TAG, "heartbeat pend: %d", Long.valueOf(jCurrentTimeMillis));
                    if (jCurrentTimeMillis < 10000) {
                        HySignalLog.warn(HySignalUserHeartBeat.TAG, "heartbeat pend: %d < SKIP_DURATION: %d, skip", Long.valueOf(jCurrentTimeMillis), 10000);
                    } else {
                        HySignalClient.getInstance().requestAllDefaultChannel(new Request.Builder().cmdId(20).cgi("/cmdid/20").limitFrequency(false).body("".getBytes()).build(), new HySignalClient.RequestAllCallback() { // from class: com.huya.hysignal.bizreq.HySignalUserHeartBeat.3.1
                            @Override // com.huya.hysignal.core.HySignalClient.RequestAllCallback
                            public void onResp(String str, Response response) {
                                if (response == null) {
                                    HySignalLog.error("hysignal heartbeat channel: " + str + " rsp is empty");
                                    return;
                                }
                                HySignalLog.info(HySignalUserHeartBeat.TAG, "hysignal heartbeat channel: " + str + "result: errType=" + response.error.getErrType() + " errCode=" + response.error.getErrCode());
                            }
                        });
                        long unused = HySignalUserHeartBeat.mLastHeartTime = System.currentTimeMillis();
                    }
                }
            });
        }
    }
}
