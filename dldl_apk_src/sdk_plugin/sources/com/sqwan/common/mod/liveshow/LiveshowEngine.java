package com.sqwan.common.mod.liveshow;

import android.content.Context;
import com.sqwan.common.mod.IModBase;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.liveshow.LiveShowCallbackConfig;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.tool.ILiveshow;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveshowEngine implements ILiveshow, IModBase {
    private static final LiveshowEngine ourInstance = new LiveshowEngine();
    private boolean hasInited;
    private ILiveshowManager iLiveshowManager;
    private ILiveshowTrackManager iLiveshowTrackManager;
    private Context mContext;
    private Map<LiveShowCallbackConfig.LiveShowCallbackType, LiveShowCallbackConfig> map = new HashMap();

    private boolean isLiveShowTypeNone() {
        return false;
    }

    public boolean isLiveshowTypeHy() {
        return true;
    }

    public static LiveshowEngine getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        if (isLiveShowTypeNone() || this.mContext != null) {
            return;
        }
        this.mContext = context;
        initLiveshowManager();
        initLiveshowTrackManager();
        ILiveshowManager iLiveshowManager = this.iLiveshowManager;
        if (iLiveshowManager != null) {
            iLiveshowManager.initSkin(context);
        }
    }

    private void initLiveshowTrackManager() {
        Context context;
        if (this.iLiveshowTrackManager == null) {
            if (isLiveshowTypeHy()) {
                this.iLiveshowTrackManager = (ILiveshowTrackManager) ModHelper.get(IHyLiveshowTrackManager.class);
            }
            ILiveshowTrackManager iLiveshowTrackManager = this.iLiveshowTrackManager;
            if (iLiveshowTrackManager == null || (context = this.mContext) == null) {
                return;
            }
            iLiveshowTrackManager.initContext(context);
        }
    }

    private void initLiveshowManager() {
        Context context;
        if (this.iLiveshowManager == null) {
            if (isLiveshowTypeHy()) {
                this.iLiveshowManager = (ILiveshowManager) ModHelper.get(IHyLiveshowManager.class);
            }
            ILiveshowManager iLiveshowManager = this.iLiveshowManager;
            if (iLiveshowManager == null || (context = this.mContext) == null) {
                return;
            }
            iLiveshowManager.initContext(context);
        }
    }

    public ILiveshowTrackManager getLiveshowTrackManager() {
        return this.iLiveshowTrackManager;
    }

    public ILiveshowManager getLiveshowManager() {
        return this.iLiveshowManager;
    }

    public void setHasInited(boolean z) {
        if (isLiveShowTypeNone()) {
            return;
        }
        this.hasInited = z;
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public boolean isSupportLiveVideo() {
        return isLiveshowTypeHy();
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        ILiveshowManager iLiveshowManager;
        if (isLiveShowTypeNone() || !this.hasInited || (iLiveshowManager = this.iLiveshowManager) == null) {
            return;
        }
        iLiveshowManager.joinLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        ILiveshowManager iLiveshowManager;
        if (isLiveShowTypeNone() || !this.hasInited || (iLiveshowManager = this.iLiveshowManager) == null) {
            return;
        }
        iLiveshowManager.leaveLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowDestroyCallback(SQResultListener sQResultListener) {
        if (isLiveShowTypeNone()) {
            return;
        }
        if (this.hasInited) {
            ILiveshowManager iLiveshowManager = this.iLiveshowManager;
            if (iLiveshowManager != null) {
                iLiveshowManager.setLiveshowDestroyCallback(sQResultListener);
                return;
            }
            return;
        }
        this.map.put(LiveShowCallbackConfig.LiveShowCallbackType.destroy, new LiveShowCallbackConfig(null, sQResultListener));
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener) {
        if (isLiveShowTypeNone()) {
            return;
        }
        if (this.hasInited) {
            ILiveshowManager iLiveshowManager = this.iLiveshowManager;
            if (iLiveshowManager != null) {
                iLiveshowManager.setLiveshowVoiceChangeCallback(sQResultListener);
                return;
            }
            return;
        }
        this.map.put(LiveShowCallbackConfig.LiveShowCallbackType.changeVoice, new LiveShowCallbackConfig(null, sQResultListener));
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
        ILiveshowManager iLiveshowManager;
        if (isLiveShowTypeNone() || !this.hasInited || (iLiveshowManager = this.iLiveshowManager) == null) {
            return;
        }
        iLiveshowManager.performLiveshowFeature(map, sQResultListener);
    }

    public void handleCallback() {
        if (isLiveShowTypeNone()) {
            return;
        }
        for (LiveShowCallbackConfig.LiveShowCallbackType liveShowCallbackType : this.map.keySet()) {
            LiveShowCallbackConfig liveShowCallbackConfig = this.map.get(liveShowCallbackType);
            if (liveShowCallbackType == LiveShowCallbackConfig.LiveShowCallbackType.changeVoice) {
                setLiveshowVoiceChangeCallback(liveShowCallbackConfig.sqResultListener);
            } else if (liveShowCallbackType == LiveShowCallbackConfig.LiveShowCallbackType.destroy) {
                setLiveshowDestroyCallback(liveShowCallbackConfig.sqResultListener);
            }
        }
        this.map.clear();
    }
}
