package com.huya.berry.forcelive;

import android.app.Activity;
import com.huya.berry.forcelive.api.IForceLiveService;
import com.huya.berry.forcelive.api.IForceLiveStream;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ForceLiveService extends AbsService implements IForceLiveService {
    public static final String TAG = "ForceLiveService";
    private IForceLiveStream mForceLiveStream;

    @Override // com.huya.berry.forcelive.api.IForceLiveService
    public IForceLiveStream getForceLiveStream(Activity activity) {
        ForceLiveStream forceLiveStream = new ForceLiveStream(activity);
        this.mForceLiveStream = forceLiveStream;
        return forceLiveStream;
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveService
    public void uninit() {
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream != null) {
            iForceLiveStream.uninit();
        }
    }
}
