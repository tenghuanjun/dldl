package com.cy.yyjia.zhe28;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.cy.yyjia.zhe28.activity.MainActivity;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.IsPhoneTool;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer;
import com.tencent.bugly.crashreport.CrashReport;
import java.io.Serializable;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyApplication.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u0010R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/MyApplication;", "Landroid/app/Application;", "()V", "mActivity", "", "Landroid/app/Activity;", "getMActivity", "()Ljava/util/List;", "setMActivity", "(Ljava/util/List;)V", "dealCrash", "", "init", "onCreate", "toMain", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MyApplication extends Application {
    public static final int $stable = 8;
    private List<Activity> mActivity = new ArrayList();

    public final List<Activity> getMActivity() {
        return this.mActivity;
    }

    public final void setMActivity(List<Activity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mActivity = list;
    }

    @Override // android.app.Application
    public void onCreate() throws Throwable {
        super.onCreate();
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() { // from class: com.cy.yyjia.zhe28.MyApplication$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
            public final RefreshHeader createRefreshHeader(Context context, RefreshLayout refreshLayout) {
                return MyApplication.onCreate$lambda$0(context, refreshLayout);
            }
        });
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() { // from class: com.cy.yyjia.zhe28.MyApplication$$ExternalSyntheticLambda2
            @Override // com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer
            public final void initialize(Context context, RefreshLayout refreshLayout) {
                MyApplication.onCreate$lambda$1(context, refreshLayout);
            }
        });
        init();
        dealCrash();
        MyApplication myApplication = this;
        CrashReport.initCrashReport(myApplication, "d533c41117", true);
        CrashReport.setAppChannel(myApplication, IsPhoneTool.isEmulator() ? "mnq" : HintConstants.AUTOFILL_HINT_PHONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RefreshHeader onCreate$lambda$0(Context context, RefreshLayout refreshLayout) {
        return new ClassicsHeader(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(Context context, RefreshLayout layout) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(layout, "layout");
        layout.setDisableContentWhenRefresh(false);
    }

    private final void dealCrash() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.cy.yyjia.zhe28.MyApplication.dealCrash.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                MyApplication.this.getMActivity().add(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (Constant.INSTANCE.getDeleteApk()) {
                    for (DownloadTask downloadTask : OkDownload.restore(DownloadManager.getInstance().getAll())) {
                        if (downloadTask.progress.status == 5 && downloadTask.progress.extra1 != null) {
                            Serializable serializable = downloadTask.progress.extra1;
                            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
                            if (Util.isAPPInstalled(MyApplication.this, ((AppInfo) serializable).getPackage())) {
                                downloadTask.remove(true);
                            }
                        }
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                MyApplication.this.getMActivity().remove(activity);
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.cy.yyjia.zhe28.MyApplication$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                MyApplication.dealCrash$lambda$2(this.f$0, thread, th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealCrash$lambda$2(MyApplication this$0, Thread thread, Throwable e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        e.printStackTrace();
        Log.e("发生闪退", e.getLocalizedMessage(), e);
        Iterator<Activity> it = this$0.mActivity.iterator();
        while (it.hasNext()) {
            it.next().finish();
        }
        Process.killProcess(Process.myPid());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0179  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void init() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 652
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.MyApplication.init():void");
    }

    public static /* synthetic */ void toMain$default(MyApplication myApplication, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        myApplication.toMain(i);
    }

    public final void toMain(int position) {
        int size = this.mActivity.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (this.mActivity.get(size) instanceof MainActivity) {
                Activity activity = this.mActivity.get(size);
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.cy.yyjia.zhe28.activity.MainActivity");
                ((MainActivity) activity).select(position);
                return;
            }
            this.mActivity.get(size).finish();
        }
    }
}
