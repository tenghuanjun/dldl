package com.duowan.kiwi.barrage.stencil;

import com.duowan.ark.ArkUtils;
import com.duowan.ark.util.FP;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StencilConfig {
    private static final String TAG = StencilConfig.class.getSimpleName();
    private static StencilConfig sInstance = new StencilConfig();
    private List<String> mGameIdList = new ArrayList();

    private StencilConfig() {
    }

    public static StencilConfig getInstance() {
        return sInstance;
    }

    public synchronized boolean isStencilAble(String str) {
        for (int i = 0; i < this.mGameIdList.size(); i++) {
            if (this.mGameIdList.get(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void setGameIdList(String str) {
        this.mGameIdList.clear();
        if (FP.empty(str)) {
            return;
        }
        try {
            this.mGameIdList.addAll((List) new Gson().fromJson(str, new TypeToken<ArrayList<String>>() { // from class: com.duowan.kiwi.barrage.stencil.StencilConfig.1
            }.getType()));
            BarrageLog.debug(TAG, "onDynamicConfig  = %s", Arrays.toString(this.mGameIdList.toArray()));
        } catch (Exception e) {
            BarrageLog.error(TAG, "parse gameid list error", e);
            ArkUtils.crashIfDebug(TAG, e);
        }
    }
}
