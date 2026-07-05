package com.duowan.auk.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import com.huya.live.common.api.BaseApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BaseActivity extends Activity {
    private List<Runnable> mDelayRunnableList;
    private Handler mHandler;

    public void safeRunOnUiThread(Runnable runnable, int i) {
        if (this.mHandler == null) {
            initDelayRunnable();
        }
        this.mDelayRunnableList.add(runnable);
        this.mHandler.postDelayed(runnable, i);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        BaseApi.getSignalCenterApi().register(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        BaseApi.getSignalCenterApi().unregister(this);
        removeDelayRunnable();
        super.onPause();
    }

    private void initDelayRunnable() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mDelayRunnableList = new ArrayList();
    }

    private void removeDelayRunnable() {
        Iterator<Runnable> it = this.mDelayRunnableList.iterator();
        while (it.hasNext()) {
            this.mHandler.removeCallbacks(it.next());
        }
        this.mDelayRunnableList.clear();
    }
}
