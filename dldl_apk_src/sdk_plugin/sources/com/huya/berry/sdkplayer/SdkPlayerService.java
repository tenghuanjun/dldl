package com.huya.berry.sdkplayer;

import android.app.Activity;
import android.widget.FrameLayout;
import com.duowan.ark.Ark;
import com.duowan.ark.util.Config;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.MmkvConfigImpl;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.huya.berry.endlive.api.ISdkPlayerService;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.sdkplayer.floats.FloatingVideoMgr;
import com.huya.berry.sdkplayer.floats.view.DanmuView;
import com.huya.berry.sdkplayer.floats.view.FlotingEditFragment;
import com.huya.berry.sdkplayer.line.LineListFragment;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SdkPlayerService extends AbsService implements ISdkPlayerService {
    public static final String TAG = "SdkPlayerService";
    private DanmuView mDanmuView;

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public void init() {
        BarrageContext.gContext = ArkValue.gContext;
        Config.init(ArkValue.gContext, new MmkvConfigImpl());
        Ark.init(ArkValue.gContext, 0);
        BarrageConfig.sBorderColor = -1;
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public void openLine(Activity activity) {
        if (activity == null) {
            L.error(TAG, "Activity is null");
        } else {
            LineListFragment.getInstance(activity.getFragmentManager()).show(activity.getFragmentManager(), LineListFragment.TAG);
        }
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public void openFloatEdit(Activity activity) {
        if (activity == null) {
            L.error(TAG, "Activity is null");
        } else {
            FlotingEditFragment.getInstance(activity.getFragmentManager()).show(activity.getFragmentManager(), FlotingEditFragment.TAG);
        }
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public void openRealFloat() {
        FloatingVideoMgr.getInstance().start(PlayerHelper.mScreenType);
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public void watchLive() {
        FloatingVideoMgr.getInstance().start(PlayerHelper.mScreenType);
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public boolean showDanmuView(FrameLayout frameLayout, long j) {
        if (this.mDanmuView != null) {
            return false;
        }
        DanmuView danmuView = new DanmuView(frameLayout);
        this.mDanmuView = danmuView;
        danmuView.show(j);
        return true;
    }

    @Override // com.huya.berry.endlive.api.ISdkPlayerService
    public boolean hideDanmuView(boolean z) {
        DanmuView danmuView = this.mDanmuView;
        if (danmuView == null) {
            return true;
        }
        danmuView.setReceiveDanmuData(z);
        this.mDanmuView.onDestroy();
        this.mDanmuView = null;
        return false;
    }
}
