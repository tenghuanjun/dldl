package com.volcengine.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.androidcloud.common.log.AcLogConfig;
import com.volcengine.cloudphone.apiservice.outinterface.InitListener;
import com.volcengine.common.config.a;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.contant.CommonErrorCode;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.j.b;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public class InitHelper {
    public static final int INIT_STATUS_FAILED_TO_INITIALIZED = 2;
    public static final int INIT_STATUS_HAS_BEEN_INITIALIZED = 1;
    public static final int INIT_STATUS_NOT_INITIALIZED = 0;
    private static final int MESSAGE_PROCESS_STATE_CHANGE = 1;
    private static final String TAG = "InitHelper";
    private static final AtomicInteger mInitializedStatus = new AtomicInteger(0);
    private InitListener mInitListener;
    private StartCallBack mStartCallBack;
    private final boolean mSupportMultiProcess;
    private final PluginService mPluginService = SDKContext.getPluginService();
    private final Handler.Callback mCallback = new Handler.Callback() { // from class: com.volcengine.common.InitHelper$$ExternalSyntheticLambda0
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f$0.m6692lambda$new$0$comvolcenginecommonInitHelper(message);
        }
    };

    public interface StartCallBack {
        void onFail(int i, String str);

        void onPrepareStart();
    }

    public InitHelper(Context context, int i, String str, String str2, boolean z, InitListener initListener) {
        this.mInitListener = initListener;
        this.mSupportMultiProcess = z;
        initOnceOnly(context, i, str, str2);
        initPlugin();
    }

    private void downloadPlugin() {
        AcLog.d(b.g(), "start to load plugin");
        this.mPluginService.addLoadResultListener(new PluginService.ILoadResultListener() { // from class: com.volcengine.common.InitHelper.1
            @Override // com.volcengine.common.innerapi.PluginService.ILoadResultListener
            public void onLoadFailed(int i, String str) {
                AcLog.d(b.g(), "load error" + i + str);
                InitHelper.mInitializedStatus.set(2);
                if (InitHelper.this.mInitListener != null) {
                    InitHelper.this.mInitListener.initFail(i, str);
                }
                if (InitHelper.this.mStartCallBack != null) {
                    InitHelper.this.mStartCallBack.onFail(i, str);
                }
            }

            @Override // com.volcengine.common.innerapi.PluginService.ILoadResultListener
            public void onLoadSuccess() {
                AcLog.d(b.g(), "load success");
                InitHelper.this.onPluginLoaded();
                InitHelper.mInitializedStatus.set(1);
                if (InitHelper.this.mInitListener != null) {
                    InitHelper.this.mInitListener.initSuccess();
                }
                if (InitHelper.this.mStartCallBack != null) {
                    InitHelper.this.mStartCallBack.onPrepareStart();
                }
                if (InitHelper.this.mSupportMultiProcess && b.f()) {
                    AcLog.d(b.g(), "Main process raise signal");
                    new com.volcengine.h.b(SDKContext.getContext()).b();
                }
            }
        });
        this.mPluginService.load(SDKContext.getContext());
    }

    private void initOnceOnly(Context context, int i, String str, String str2) {
        if (SDKContext.isInited()) {
            return;
        }
        SDKContext.init(context);
        SDKContext.setPluginConfigVersion(str);
        SDKContext.setSdkVersion(str2);
        SDKContext.getMonitorService().init(i);
        SDKContext.getHttpService().init(i);
        a.b().a(i);
        onInitStarted();
    }

    private void initPlugin() {
        AcLog.d(b.g(), "support multi-process is " + this.mSupportMultiProcess);
        if (!b.f()) {
            if (!this.mSupportMultiProcess) {
                AcLog.d(b.g(), "Not support multi-process and return fail");
                InitListener initListener = this.mInitListener;
                if (initListener != null) {
                    Pair<Integer, String> pair = CommonErrorCode.ERROR_MULTIPLE_PROCESS_UNSUPPORTED;
                    initListener.initFail(((Integer) pair.first).intValue(), (String) pair.second);
                    return;
                }
                return;
            }
            if (!new com.volcengine.h.a(SDKContext.getContext(), this.mCallback).a()) {
                AcLog.d(b.g(), "Start to waiting for main process' signal");
                return;
            }
        }
        downloadPlugin();
    }

    private void onInitStarted() {
        AcLogConfig.init(SDKContext.getContext());
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_init, Collections.singletonMap(CommonConstants.key_cpuABIType, SDKContext.getHostAbi()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPluginLoaded() {
        mInitializedStatus.get();
    }

    public synchronized void clearPlayerListener() {
        this.mStartCallBack = null;
    }

    public int getInitStatus() {
        return mInitializedStatus.get();
    }

    /* JADX INFO: renamed from: lambda$new$0$com-volcengine-common-InitHelper, reason: not valid java name */
    /* synthetic */ boolean m6692lambda$new$0$comvolcenginecommonInitHelper(Message message) {
        AcLog.d(b.g(), "Receive signal from Main process");
        if (message.what != 1) {
            return false;
        }
        downloadPlugin();
        return false;
    }

    public void retryInit() {
        AcLog.d(TAG, "provider retry init ");
        AtomicInteger atomicInteger = mInitializedStatus;
        if (atomicInteger.get() == 2) {
            this.mPluginService.load(SDKContext.getContext());
            return;
        }
        AcLog.w(TAG, "warning: retryInit is not available, because current status is: " + atomicInteger.get());
    }

    public synchronized void setInitListener(InitListener initListener) {
        this.mInitListener = initListener;
    }

    public synchronized void setPlayListener(StartCallBack startCallBack) {
        this.mStartCallBack = startCallBack;
    }
}
