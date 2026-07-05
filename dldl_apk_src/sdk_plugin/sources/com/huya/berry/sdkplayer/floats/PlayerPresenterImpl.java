package com.huya.berry.sdkplayer.floats;

import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.IPresenter;
import com.huya.berry.endlive.event.HuyaSdkInterface;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.utils.DigitUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.sdkplayer.floats.view.IPlayerView;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PlayerPresenterImpl implements IPresenter {
    private static final String TAG = "PlayerPresenterImpl";
    Runnable mDelayTask = new Runnable() { // from class: com.huya.berry.sdkplayer.floats.PlayerPresenterImpl.1
        @Override // java.lang.Runnable
        public void run() {
            TaskExecutor.uiHandler().removeCallbacks(PlayerPresenterImpl.this.mDelayTask);
            PlayerPresenterImpl.this.hideView();
        }
    };
    private WeakReference<IPlayerView> mView;

    public PlayerPresenterImpl(IPlayerView iPlayerView) {
        this.mView = new WeakReference<>(iPlayerView);
        TaskExecutor.uiHandler().postDelayed(this.mDelayTask, OAIDHelper.TIMEOUT);
        ArkUtils.register(this);
        L.info(TAG, "PlayerPresenterImpl init");
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onResume() {
        L.info(TAG, "onResume");
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onPause() {
        L.info(TAG, "onPause");
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onCreate() {
        L.info(TAG, "onCreate");
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onDestroy() {
        L.info(TAG, "onDestroy");
        ArkUtils.unregister(this);
        TaskExecutor.uiHandler().removeCallbacks(this.mDelayTask);
    }

    public void showView() {
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().showView();
        TaskExecutor.uiHandler().removeCallbacks(this.mDelayTask);
        TaskExecutor.uiHandler().postDelayed(this.mDelayTask, OAIDHelper.TIMEOUT);
    }

    public void hideView() {
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().hideView();
    }

    public void switchVoice() {
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().switchVoice();
    }

    public void switchDanmu() {
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().switchDanmu();
    }

    @IASlot(executorID = 1)
    public void onSwitchLive(HuyaSdkInterface.SwitchLive switchLive) {
        L.info(TAG, "onSwitchLive mView" + this.mView.get());
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().setTvRate();
    }

    @IASlot(executorID = 1)
    public void onSwitchRate(HuyaSdkInterface.SwitchRate switchRate) {
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference != null && weakReference.get() != null) {
            this.mView.get().setTvRate();
        }
        L.info(TAG, "onSwitchRate");
    }

    @IASlot(executorID = 1)
    public void onGetLiveUserCount(CommonEvent.OnLiveUserCount onLiveUserCount) {
        if (onLiveUserCount == null) {
            return;
        }
        PlayerHelper.audienceCount = DigitUtil.shortFormat(onLiveUserCount.count);
        WeakReference<IPlayerView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().setTvAudienceCount();
    }
}
