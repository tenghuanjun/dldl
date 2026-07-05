package com.duowan.kiwi.barrage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;
import com.duowan.kiwi.base.smile.DefaultSmile;
import com.huya.berry.gamesdk.utils.SystemUI;
import com.huya.berry.module.pubtext.ChatText;
import com.huya.berry.sdkplayer.floats.data.GiftBarrageWithBitmap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLSurfaceWithHuyaFace extends BarrageGLSurfaceViewWithLifeCycle {
    private TextView mFaceBarrageView;

    protected void onBarrageWithAttach(Object obj) {
    }

    public BarrageGLSurfaceWithHuyaFace(Context context) {
        super(context.getApplicationContext());
    }

    public BarrageGLSurfaceWithHuyaFace(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
    }

    public void addBarrageWithFace(ChatText chatText) {
        GunPowder gunPowder = new GunPowder(chatText.uid, chatText.nickname, chatText.text);
        gunPowder.mAttachObject = new BarrageEvent.BarrageWithAttach(chatText);
        addBarrageWithAttach(new BarrageEvent.BarrageWithAttach(new BarrageEvent.BarrageWithFace(gunPowder)));
    }

    public void addBarrageWithAttach(BarrageEvent.BarrageWithAttach barrageWithAttach) {
        if (getBarrageModel() == 0) {
            return;
        }
        Object obj = barrageWithAttach.mAttach;
        if (obj instanceof BarrageEvent.BarrageWithFace) {
            onReciveBarrageWithFace((BarrageEvent.BarrageWithFace) obj);
        } else {
            onBarrageWithAttach(obj);
        }
    }

    private void onReciveBarrageWithFace(BarrageEvent.BarrageWithFace barrageWithFace) {
        if (this.mFaceBarrageView == null) {
            addFaceBarrageView();
        }
        if (this.mFaceBarrageView == null) {
            L.error("[Barrage]view", "addFaceBarrageView Failed!");
        } else {
            createFaceBarrageBitmap(barrageWithFace);
        }
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle, com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public void offerGunPowder(GunPowder gunPowder, int i) {
        super.offerGunPowder(gunPowder, i);
    }

    private void addFaceBarrageView() {
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            TextView textView = new TextView(ArkValue.gContext);
            this.mFaceBarrageView = textView;
            textView.setVisibility(4);
            this.mFaceBarrageView.setSingleLine(true);
            this.mFaceBarrageView.setPadding(4, 0, 4, 0);
            ((ViewGroup) parent).addView(this.mFaceBarrageView, new ViewGroup.LayoutParams(-2, -2));
        }
    }

    public static SpannableString getSpannaleText(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(i), 0, str.length(), 18);
        return spannableString;
    }

    private void createFaceBarrageBitmap(BarrageEvent.BarrageWithFace barrageWithFace) {
        if (this.mFaceBarrageView == null || barrageWithFace == null) {
            return;
        }
        GunPowder gunPowder = barrageWithFace.mGunPowder;
        String strPreProcessText = DefaultSmile.preProcessText(gunPowder.mPowder);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannaleText = getSpannaleText(-1, strPreProcessText);
        DefaultSmile.matchText(ArkValue.gContext, spannaleText);
        spannableStringBuilder.append((CharSequence) spannaleText);
        this.mFaceBarrageView.setShadowLayer(BarrageConfig.ShadowRadius, 2.5f, 2.0f, -822083584);
        this.mFaceBarrageView.setTextSize(0, BarrageConfig.LANDSCAPE_SIZE_BIG);
        this.mFaceBarrageView.setTypeface(Typeface.defaultFromStyle(1));
        this.mFaceBarrageView.setText(spannableStringBuilder);
        this.mFaceBarrageView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mFaceBarrageView.setLongClickable(false);
        Bitmap bitmapConvertViewToBitmap = SystemUI.convertViewToBitmap(this.mFaceBarrageView);
        if (bitmapConvertViewToBitmap != null) {
            L.debug("testBitmap", "onReciveBarrageWithFace, height = " + bitmapConvertViewToBitmap.getHeight() + " , width = " + bitmapConvertViewToBitmap.getWidth() + ", isRecycled = " + bitmapConvertViewToBitmap.isRecycled());
            showBitmapBarrage(new GiftBarrageWithBitmap(bitmapConvertViewToBitmap, gunPowder));
        }
    }

    protected void showBitmapBarrage(GiftBarrageWithBitmap giftBarrageWithBitmap) {
        if (giftBarrageWithBitmap == null || giftBarrageWithBitmap.mBitmap == null || giftBarrageWithBitmap.mBitmap.isRecycled()) {
            return;
        }
        AbsDrawingCache.GLDrawingCache gLDrawingCache = new AbsDrawingCache.GLDrawingCache(giftBarrageWithBitmap.mBitmap);
        int i = 2;
        if (giftBarrageWithBitmap.mGunPowder != null && giftBarrageWithBitmap.mGunPowder.mExplosive > 2) {
            i = giftBarrageWithBitmap.mGunPowder.mExplosive;
        }
        offerGunPowder(new GunPowder(giftBarrageWithBitmap.mGunPowder, gLDrawingCache, i, BarrageConfig.DEFAULT_DURATION), 1);
        fireIfNeed();
    }
}
