package com.duowan.kiwi.barrage;

import android.content.Context;
import android.util.AttributeSet;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.base.smile.DefaultSmile;
import com.huya.berry.module.commonevent.Event_Biz;
import com.huya.component.login.LoginProperties;
import com.huya.mtp.utils.FP;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLSurfaceViewForLiveRoom extends BarrageGLSurfaceWithHuyaFace {
    private static final String TAG = BarrageGLSurfaceViewForLiveRoom.class.getSimpleName();
    private boolean mIsLiving;

    public BarrageGLSurfaceViewForLiveRoom(Context context) {
        super(context.getApplicationContext());
        this.mIsLiving = true;
    }

    public BarrageGLSurfaceViewForLiveRoom(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
        this.mIsLiving = true;
    }

    public void setIsLiving(boolean z) {
        L.info(TAG, "setIsLiving isLiving=%b", Boolean.valueOf(z));
        this.mIsLiving = z;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceWithHuyaFace, com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle, com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public void offerGunPowder(GunPowder gunPowder, int i) {
        super.offerGunPowder(gunPowder, i);
    }

    public void onTextAboutToSend(Event_Biz.TextAboutToSendV2 textAboutToSendV2) {
        if (!getRender().isBarrageOn() || textAboutToSendV2 == null) {
            L.info(TAG, "onTextAboutToSend isBarrageOn false");
            return;
        }
        L.debug(TAG, "onTextAboutToSend, %s", textAboutToSendV2.toString());
        String str = textAboutToSendV2.xxBarrageCmd;
        int i = textAboutToSendV2.bulletColor;
        String strPreProcessText = DefaultSmile.preProcessText(textAboutToSendV2.text);
        if (FP.empty(strPreProcessText)) {
            return;
        }
        if (textAboutToSendV2.isBulletFormatEnableUse()) {
            GunPowder gunPowder = new GunPowder(textAboutToSendV2.uid, textAboutToSendV2.nickname, strPreProcessText);
            gunPowder.mAttachObject = new BarrageEvent.BarrageWithAttach(textAboutToSendV2);
            getRender().offer(gunPowder, 1);
            fireIfNeed();
            return;
        }
        if (1 != getBarrageModel()) {
            str = null;
        }
        offerShell(strPreProcessText, 2, i, str);
        fireIfNeed();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceWithHuyaFace
    protected void onBarrageWithAttach(Object obj) {
        super.onBarrageWithAttach(obj);
        if (obj instanceof Event_Biz.BaseNobleBarrage) {
            onNobleBarrage((Event_Biz.BaseNobleBarrage) obj);
        }
    }

    private void onNobleBarrage(Event_Biz.BaseNobleBarrage baseNobleBarrage) {
        if (getBarrageModel() == 0) {
            return;
        }
        offerShell(baseNobleBarrage);
    }

    private void offerShell(Event_Biz.BaseNobleBarrage baseNobleBarrage) {
        offerShell(baseNobleBarrage.uid, baseNobleBarrage.nickname, DefaultSmile.preProcessText(baseNobleBarrage.text), baseNobleBarrage.uid == LoginProperties.uid.get().longValue() ? 4 : 3, baseNobleBarrage.bulletColor, baseNobleBarrage.speed);
        fireIfNeed();
    }
}
