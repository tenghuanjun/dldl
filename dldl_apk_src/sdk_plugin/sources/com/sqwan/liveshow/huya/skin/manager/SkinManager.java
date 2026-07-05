package com.sqwan.liveshow.huya.skin.manager;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import com.sqwan.liveshow.huya.danmu.view.ChooseView;
import com.sqwan.liveshow.huya.danmu.view.InputAndCountDownView;
import com.sqwan.liveshow.huya.danmu.view.InputView;
import com.sqwan.liveshow.huya.skin.inflater.SkinInflaterFactory;
import com.sqwan.liveshow.huya.skin.view.SkinEditText;
import com.sqwan.liveshow.huya.skin.view.SkinFrameLayout;
import com.sqwan.liveshow.huya.skin.view.SkinImageView;
import com.sqwan.liveshow.huya.skin.view.SkinLinearLayout;
import com.sqwan.liveshow.huya.skin.view.SkinRelativeLayout;
import com.sqwan.liveshow.huya.skin.view.SkinTextView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinManager {
    public static final String TAG = "SkinManager";
    private Map<String, String> skinInflaterMap = new ArrayMap();

    public static SkinManager getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final SkinManager INSTANCE = new SkinManager();

        private Singleton() {
        }
    }

    public SkinManager registSkinViewConvertMap(String str) {
        if (!TextUtils.isEmpty(str) && !this.skinInflaterMap.containsKey(str)) {
            this.skinInflaterMap.put(str, str);
        }
        return this;
    }

    public boolean canSkinViewConvert(String str) {
        return this.skinInflaterMap.containsKey(str);
    }

    public String findSkinInflaterViewClassName(String str) {
        return this.skinInflaterMap.get(str);
    }

    public void inject(Context context) {
        registSkinViewConvertMap(SkinTextView.class.getName());
        registSkinViewConvertMap(SkinImageView.class.getName());
        registSkinViewConvertMap(SkinEditText.class.getName());
        registSkinViewConvertMap(SkinRelativeLayout.class.getName());
        registSkinViewConvertMap(SkinLinearLayout.class.getName());
        registSkinViewConvertMap(SkinFrameLayout.class.getName());
        registSkinViewConvertMap(InputAndCountDownView.class.getName());
        registSkinViewConvertMap(ChooseView.class.getName());
        registSkinViewConvertMap(InputView.class.getName());
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(context), new SkinInflaterFactory());
    }
}
