package com.sqwan.liveshow.trackaction;

import android.content.Context;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.mod.liveshow.ILiveshowTrackManager;
import com.sqwan.common.util.VersionUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowTrackBaseManager extends BaseEnginHandler implements ILiveshowTrackManager {
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected String mSdkVersion;

    @Override // com.sqwan.common.mod.liveshow.ILiveshowTrackManager
    public void init(BaseBean baseBean) {
    }

    @Override // com.sqwan.base.BaseEnginHandler
    public void init(Context context) {
        super.init(context);
        this.mContext = context;
        this.mSdkVersion = VersionUtil.sdkVersion;
    }

    @Override // com.sqwan.common.mod.liveshow.ILiveshowTrackManager
    public void initContext(Context context) {
        if (this.context == null) {
            init(context);
        }
    }
}
