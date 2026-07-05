package com.sqwan.engine;

import android.app.Activity;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.util.task.Task;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CoreEngineHandler extends BaseEnginHandler {
    private static final CoreEngineHandler ourInstance = new CoreEngineHandler();
    private LoadingDialog loadingDialog;

    public static CoreEngineHandler getInstance() {
        return ourInstance;
    }

    private CoreEngineHandler() {
    }

    public void showInitLoading() {
        Task.post(new Runnable() { // from class: com.sqwan.engine.CoreEngineHandler.1
            @Override // java.lang.Runnable
            public void run() {
                Activity activityCheckValid;
                if (CoreEngineHandler.this.loadingDialog == null && (activityCheckValid = CoreEngineHandler.this.checkValid()) != null) {
                    CoreEngineHandler.this.loadingDialog = new LoadingDialog(activityCheckValid);
                }
                CoreEngineHandler.this.loadingDialog.show();
            }
        });
    }

    public void dismissInitLoading() {
        Task.post(new Runnable() { // from class: com.sqwan.engine.CoreEngineHandler.2
            @Override // java.lang.Runnable
            public void run() {
                if (CoreEngineHandler.this.loadingDialog != null) {
                    CoreEngineHandler.this.loadingDialog.dismiss();
                }
            }
        });
    }
}
