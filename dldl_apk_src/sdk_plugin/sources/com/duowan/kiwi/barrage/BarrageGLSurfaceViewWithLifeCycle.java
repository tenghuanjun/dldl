package com.duowan.kiwi.barrage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.GLBarrageAdapter;
import com.duowan.kiwi.barrage.report.BarrageCacheForReport;
import com.duowan.kiwi.base.smile.DefaultSmile;
import com.huya.berry.endlive.api.DebugConfig;
import com.huya.berry.endlive.event.OnChannelPageConfigurationChanged;
import com.huya.berry.module.pubtext.ChatText;
import com.huya.berry.sdkplayer.common.pubtext.XXBarrageParser;
import com.huya.berry.sdkplayer.common.widgets.AbsLifeCycleView;
import com.huya.berry.sdkplayer.common.widgets.LifeCycle;
import com.huya.berry.sdkplayer.common.widgets.LifeCycleViewActivity;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.UserProperties;
import com.huya.mtp.utils.FP;
import com.huya.mtp.utils.ThreadUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLSurfaceViewWithLifeCycle extends BarrageGLSurfaceView implements AbsLifeCycleView {
    protected LifeCycle mLifeCycleImpl;

    protected boolean isFromFloating() {
        return true;
    }

    public BarrageGLSurfaceViewWithLifeCycle(Context context) {
        super(context);
        this.mLifeCycleImpl = null;
        initGLBarrageView(context);
    }

    public BarrageGLSurfaceViewWithLifeCycle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLifeCycleImpl = null;
        initGLBarrageView(context);
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView
    protected void initGLBarrageView(Context context) {
        super.initGLBarrageView(context);
        L.info("[Barrage]view", "initGLBarrageView");
        if (context instanceof LifeCycleViewActivity) {
            this.mLifeCycleImpl = new LifeCycleImpl((LifeCycleViewActivity) context, this);
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        L.info("[Barrage]view", "surfaceChanged orientation " + getResources().getConfiguration().orientation);
        getRender().setOrientation(getResources().getConfiguration().orientation, isFromFloating());
        super.surfaceChanged(surfaceHolder, i, i2, i3);
    }

    @Override // com.huya.berry.sdkplayer.common.widgets.AbsLifeCycleView
    public LifeCycle getLifeCycle() {
        return this.mLifeCycleImpl;
    }

    public void onScreenOrientationChange(OnChannelPageConfigurationChanged onChannelPageConfigurationChanged) {
        getRender().setOrientation(onChannelPageConfigurationChanged.arg0.intValue(), isFromFloating());
    }

    public void onPubText(ChatText chatText) {
        if (getRender().isBarrageOn()) {
            if (chatText.text != null && chatText.showBullet && !chatText.fromSystem && !chatText.text.isEmpty()) {
                String strPreProcessText = DefaultSmile.preProcessText(chatText.text);
                if (!FP.empty(strPreProcessText)) {
                    if (chatText.isBulletFormatEnableUse()) {
                        GunPowder gunPowder = new GunPowder(chatText.uid, chatText.nickname, strPreProcessText);
                        gunPowder.mAttachObject = new BarrageEvent.BarrageWithAttach(chatText);
                        super.offerGunPowder(gunPowder, 1);
                    } else {
                        offerShell(chatText.uid, chatText.nickname, strPreProcessText, 1, chatText.bulletColor, chatText.speed);
                        if (DebugConfig.isBarragePrintFourth()) {
                            offerShell(chatText.uid, chatText.nickname, strPreProcessText, 1, chatText.bulletColor, chatText.speed);
                            offerShell(chatText.uid, chatText.nickname, strPreProcessText, 1, chatText.bulletColor, chatText.speed);
                            offerShell(chatText.uid, chatText.nickname, strPreProcessText, 1, chatText.bulletColor, chatText.speed);
                        }
                    }
                }
            }
            fireIfNeed();
        }
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public void offerGunPowder(GunPowder gunPowder, int i) {
        if (-8947849 == gunPowder.mColor) {
            gunPowder.mColor = BarrageConfig.DefaultColor;
        }
        super.offerGunPowder(gunPowder, i);
    }

    protected void offerShell(long j, String str, String str2, int i, int i2, int i3) {
        offerGunPowder(new GunPowder(j, str, str2, i, i2, 0, XXBarrageParser.getInstance().getDurationFromSpeed(i3, getResources().getConfiguration().orientation == 2)), 1);
    }

    protected void offerShell(String str, int i, int i2, String str2) {
        int i3 = getResources().getConfiguration().orientation == 2 ? BarrageConfig.DEFAULT_DURATION : BarrageConfig.VERTICAL_DEFAULT_DURATION;
        if (str2 == null || str2.isEmpty()) {
            offerGunPowder(new GunPowder(LoginProperties.uid.get().longValue(), UserProperties.nickName.get(), str, i, i2, 0, i3), 1);
            return;
        }
        int[] cmd = XXBarrageParser.getInstance().parseCmd(str2, 1, i2, i3);
        if (256 == cmd[0] && (BarrageConfig.DEFAULT_DURATION == cmd[2] || 6000 == cmd[2])) {
            cmd[2] = 900;
        }
        offerGunPowder(new GunPowder(LoginProperties.uid.get().longValue(), UserProperties.nickName.get(), str, i, cmd[1], 16 == cmd[0] ? 1 : 0, cmd[2]), cmd[0]);
    }

    public void onRequireMarqueeInSurface(final BarrageEvent.RequireMarqueeInSurface requireMarqueeInSurface) {
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle.1
            @Override // java.lang.Runnable
            public void run() {
                BarrageGLSurfaceViewWithLifeCycle.this.getRender().onRequireMarqueeInSurface(requireMarqueeInSurface.bitmap, BarrageGLSurfaceViewWithLifeCycle.this.getResources().getDisplayMetrics().widthPixels, requireMarqueeInSurface.duration);
                if (BarrageGLSurfaceViewWithLifeCycle.this.isRenderOpen()) {
                    return;
                }
                BarrageGLSurfaceViewWithLifeCycle.this.switchRender(true);
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public synchronized void switchRender(boolean z) {
        L.info("[Barrage]view", "switchRender %b", Boolean.valueOf(z));
        if (z) {
            getRender().resetSmooth();
            if (DebugConfig.isBarrageOn()) {
                setRenderMode(1);
            }
        } else {
            setRenderMode(0);
            requestRender();
        }
        getRender().setBarrageRenderOn(z);
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IGLBarrageView
    public void showToast(String str) {
        ArkToast.show(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pause() {
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle.2
            @Override // java.lang.Runnable
            public void run() {
                BarrageGLSurfaceViewWithLifeCycle.this.getRender().ceaseFire(true);
                BarrageGLSurfaceViewWithLifeCycle.this.switchRender(false);
            }
        });
    }

    protected int getBarrageConfigModel() {
        return BarrageConfig.getBarrageModel();
    }

    public class LifeCycleImpl extends LifeCycle {
        private Runnable mDelayRegisterRunnable;
        private boolean mIsStopped;

        public LifeCycleImpl(LifeCycleViewActivity lifeCycleViewActivity, BarrageGLSurfaceViewWithLifeCycle barrageGLSurfaceViewWithLifeCycle) {
            super(lifeCycleViewActivity);
            this.mIsStopped = false;
            this.mDelayRegisterRunnable = new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle.LifeCycleImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (LifeCycleImpl.this.mIsStopped) {
                        return;
                    }
                    L.info("[Barrage]view", "call ArkUtils.register(BaseGLBarrageView.this); ");
                    ArkUtils.register(BarrageGLSurfaceViewWithLifeCycle.this);
                    BarrageGLSurfaceViewWithLifeCycle.this.updateBarrageModel(BarrageGLSurfaceViewWithLifeCycle.this.getBarrageConfigModel());
                }
            };
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onPause() {
            L.info("[Barrage]view", "GLBarrageView onPause");
            if (BarrageGLSurfaceViewWithLifeCycle.this.isFromFloating() || !GLBarrageAdapter.isOpenGLBarrageProblemSystem()) {
                return;
            }
            L.info("[Barrage]view", "unregister GLBarrageView");
            ArkUtils.unregister(BarrageGLSurfaceViewWithLifeCycle.this);
            this.mIsStopped = true;
            BarrageGLSurfaceViewWithLifeCycle.this.pause();
            BarrageGLSurfaceViewWithLifeCycle.this.onPause();
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onResume() {
            L.info("[Barrage]view", "GLBarrageView onResume");
            if (!BarrageGLSurfaceViewWithLifeCycle.this.isFromFloating() && GLBarrageAdapter.isOpenGLBarrageProblemSystem()) {
                L.info("[Barrage]view", "register GLBarrageView");
                this.mIsStopped = false;
                ThreadUtils.runOnMainThread(this.mDelayRegisterRunnable, 2000L);
                BarrageGLSurfaceViewWithLifeCycle.this.onResume();
                BarrageGLSurfaceViewWithLifeCycle.this.ceaseFire(true);
            }
            BarrageGLSurfaceViewWithLifeCycle barrageGLSurfaceViewWithLifeCycle = BarrageGLSurfaceViewWithLifeCycle.this;
            barrageGLSurfaceViewWithLifeCycle.updateBarrageModel(barrageGLSurfaceViewWithLifeCycle.getBarrageConfigModel());
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onStart() {
            L.info("[Barrage]view", "GLBarrageView onStart");
            if (BarrageGLSurfaceViewWithLifeCycle.this.isFromFloating() || !GLBarrageAdapter.isOpenGLBarrageProblemSystem()) {
                L.info("[Barrage]view", "register GLBarrageView");
                ArkUtils.register(BarrageGLSurfaceViewWithLifeCycle.this);
                BarrageGLSurfaceViewWithLifeCycle.this.onResume();
                BarrageGLSurfaceViewWithLifeCycle.this.ceaseFire(true);
            }
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onStop() {
            L.info("[Barrage]view", "GLBarrageView onStop");
            if (BarrageGLSurfaceViewWithLifeCycle.this.isFromFloating() || !GLBarrageAdapter.isOpenGLBarrageProblemSystem()) {
                L.info("[Barrage]view", "unregister GLBarrageView");
                ArkUtils.unregister(BarrageGLSurfaceViewWithLifeCycle.this);
                BarrageGLSurfaceViewWithLifeCycle.this.ceaseFire(true);
                BarrageGLSurfaceViewWithLifeCycle.this.onPause();
            }
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onCreate() {
            L.info("[Barrage]view", "register onCreate");
        }

        @Override // com.huya.berry.sdkplayer.common.widgets.LifeCycle
        public void onDestroy() {
            L.info("[Barrage]view", "unregister GLBarrageView");
            BarrageCacheForReport.getInstance().clear();
        }
    }
}
