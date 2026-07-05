package com.sqwan.liveshow.huya.danmu;

import android.graphics.Color;
import android.view.WindowManager;
import com.sqwan.base.L;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg;
import com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsgFactory;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanMuController {
    private DanmakuContext danmakuContext;
    private long lastLiveDanMu = 0;
    public DanmakuView mDanMuView;
    private BaseDanmakuParser parser;

    public DanMuController(DanmakuView danmakuView) {
        this.mDanMuView = danmakuView;
    }

    public void init() {
        HashMap map = new HashMap();
        map.put(1, 20);
        HashMap map2 = new HashMap();
        map2.put(1, true);
        map2.put(5, true);
        this.danmakuContext = DanmakuContext.create();
        this.danmakuContext.setFrameUpateRate((int) (1000.0f / ((WindowManager) this.mDanMuView.getContext().getSystemService("window")).getDefaultDisplay().getRefreshRate()));
        this.danmakuContext.setDanmakuStyle(2, 3.0f).setDuplicateMergingEnabled(false).setScrollSpeedFactor(2.0f).setScaleTextSize(1.1f).setMaximumLines(map).preventOverlapping(map2).setDanmakuMargin(40);
        BaseDanmakuParser baseDanmakuParser = new BaseDanmakuParser() { // from class: com.sqwan.liveshow.huya.danmu.DanMuController.1
            @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
            protected IDanmakus parse() {
                return new Danmakus();
            }
        };
        this.parser = baseDanmakuParser;
        this.mDanMuView.prepare(baseDanmakuParser, this.danmakuContext);
        this.mDanMuView.setCallback(new DrawHandler.Callback() { // from class: com.sqwan.liveshow.huya.danmu.DanMuController.2
            @Override // master.flame.danmaku.controller.DrawHandler.Callback
            public void danmakuShown(BaseDanmaku baseDanmaku) {
            }

            @Override // master.flame.danmaku.controller.DrawHandler.Callback
            public void drawingFinished() {
            }

            @Override // master.flame.danmaku.controller.DrawHandler.Callback
            public void updateTimer(DanmakuTimer danmakuTimer) {
            }

            @Override // master.flame.danmaku.controller.DrawHandler.Callback
            public void prepared() {
                DanMuController.this.mDanMuView.start();
            }
        });
    }

    private void addDanmaku(boolean z, CharSequence charSequence) {
        long currentTime;
        long currentTime2;
        if (z) {
            currentTime2 = this.mDanMuView.getCurrentTime() + 500 + 500;
            this.lastLiveDanMu = currentTime2;
        } else {
            if (this.lastLiveDanMu > this.mDanMuView.getCurrentTime()) {
                currentTime = this.lastLiveDanMu;
            } else {
                currentTime = this.mDanMuView.getCurrentTime();
            }
            currentTime2 = currentTime + 500;
        }
        BaseDanmaku baseDanmakuCreateDanmaku = this.danmakuContext.mDanmakuFactory.createDanmaku(1);
        if (baseDanmakuCreateDanmaku == null || this.mDanMuView == null) {
            return;
        }
        baseDanmakuCreateDanmaku.text = charSequence;
        baseDanmakuCreateDanmaku.padding = 5;
        baseDanmakuCreateDanmaku.isLive = z;
        baseDanmakuCreateDanmaku.textSize = (this.parser.getDisplayer().getDensity() - 0.6f) * 15.0f;
        baseDanmakuCreateDanmaku.textColor = SkinHelper.getColorValue(L.getActivity(), SqR.color.sy37_liveshow_float_cover_view_sv_danmaku_text_color, -1);
        baseDanmakuCreateDanmaku.textShadowColor = SkinHelper.getColorValue(L.getActivity(), SqR.color.sy37_liveshow_float_cover_view_sv_danmaku_stroke_color, -16777216);
        baseDanmakuCreateDanmaku.setTime(currentTime2);
        if (z) {
            baseDanmakuCreateDanmaku.priority = (byte) 1;
            baseDanmakuCreateDanmaku.borderColor = SkinHelper.getColorValue(L.getActivity(), SqR.color.sy37_liveshow_float_cover_view_sv_danmaku_border_color, Color.parseColor("#61C757"));
        } else {
            baseDanmakuCreateDanmaku.priority = (byte) 0;
        }
        this.mDanMuView.addDanmaku(baseDanmakuCreateDanmaku);
    }

    public void onPause() {
        DanmakuView danmakuView = this.mDanMuView;
        if (danmakuView == null || !danmakuView.isPrepared()) {
            return;
        }
        this.mDanMuView.pause();
    }

    public void onResume() {
        DanmakuView danmakuView = this.mDanMuView;
        if (danmakuView != null && danmakuView.isPrepared() && this.mDanMuView.isPaused()) {
            this.mDanMuView.resume();
        }
    }

    public void finish() {
        DanmakuView danmakuView = this.mDanMuView;
        if (danmakuView != null) {
            danmakuView.release();
            this.mDanMuView = null;
        }
    }

    public void sendDanMuData(FetchImRspBean.ImMsg imMsg) {
        AImMsg aImMsgConvert = new AImMsgFactory().convert(imMsg);
        if (aImMsgConvert != null) {
            if (imMsg.isSelf()) {
                sendDanMuBySelf(aImMsgConvert.getContent());
            } else {
                sendDanMuData(aImMsgConvert.getContent());
            }
        }
    }

    public void sendDanMuData(CharSequence charSequence) {
        addDanmaku(false, charSequence);
    }

    public void sendDanMuBySelf(CharSequence charSequence) {
        addDanmaku(true, charSequence);
    }

    public void sendDanMuDataList(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addDanmaku(false, it.next());
        }
    }
}
