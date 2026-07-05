package com.huya.berry.module.pubtext;

import com.duowan.HUYA.DecorationInfo;
import com.huya.berry.module.commonevent.Event_Biz;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ChatText extends Event_Biz.BaseNobleBarrage {
    public int color;
    public boolean fromSystem;
    public String mDebugInfo = "";
    public List<DecorationInfo> mPrefixDecorations;
    public List<DecorationInfo> mSuffixDecorations;
    public boolean showBarrage;
    public boolean showBullet;
    public boolean systemStyle;
    public String timeLime;
    public long timestamp;
}
