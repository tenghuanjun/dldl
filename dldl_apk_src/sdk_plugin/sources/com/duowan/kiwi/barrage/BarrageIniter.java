package com.duowan.kiwi.barrage;

import android.content.Context;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.duowan.kiwi.barrage.config.BarrageLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageIniter {
    public static void init(Context context, BarrageLog.IBarrageLog iBarrageLog) {
        BarrageContext.gContext = context;
        BarrageLog.setBarrageLog(iBarrageLog);
    }
}
