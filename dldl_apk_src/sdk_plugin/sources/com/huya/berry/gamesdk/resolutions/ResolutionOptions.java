package com.huya.berry.gamesdk.resolutions;

import android.text.TextUtils;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ResolutionOptions {
    public static final int BLUE3_RESOLUTION = 8;
    public static final int BLUE4_RESOLUTION = 16;
    public static final int DEFINITION_COMMON = 2;
    public static final int DEFINITION_LIVE = 0;
    public static final int DEFINITION_PC = 1;
    public static final int HIGHDEF_RESOLUTION = 2;
    public static final int OHTER_RESOLUTION = 0;
    public static final int SMOOTH_RESOLUTION = 1;
    public static final int SUPERDEF_RESOLUTION = 4;
    private static final String TAG = "ResolutionOptions";
    private static ResolutionOptions mInstance = new ResolutionOptions();
    private LivingParams mCustomizeParams;
    private boolean mIsCustomize = false;
    private boolean isShowParam = false;
    private List<LivingParams> mOptionList = new ArrayList();

    private ResolutionOptions() {
        LivingParams livingParamsBuild = new LivingParams.Builder().resolution(2).videoWidth(864).videoHeight(480).videoBitrate(1200).maxVideoBitrate(1200).minVideoBitrate(800).videoFrameRate(24).name("高清").tips("高清（需要2M以上宽带）").build();
        this.mCustomizeParams = livingParamsBuild;
        this.mOptionList.add(livingParamsBuild);
    }

    public static ResolutionOptions getInstance() {
        return mInstance;
    }

    public synchronized boolean hasBlueResolution() {
        for (LivingParams livingParams : this.mOptionList) {
            if (livingParams.getResolution() == 8 || livingParams.getResolution() == 16) {
                return true;
            }
        }
        return false;
    }

    public synchronized void setResolution(List<LivingParams> list) {
        this.mOptionList.clear();
        this.mOptionList.addAll(list);
    }

    public synchronized void resetResolution() {
        this.mOptionList.clear();
        this.mOptionList.add(this.mCustomizeParams);
    }

    public synchronized boolean containResolution(int i) {
        boolean z;
        z = false;
        Iterator<LivingParams> it = this.mOptionList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().getResolution() == i) {
                z = true;
                break;
            }
        }
        return z;
    }

    private LivingParams getLivingParamsOnList(int i, LivingParams livingParams) {
        for (LivingParams livingParams2 : this.mOptionList) {
            if (livingParams2.getResolution() == i) {
                return livingParams2.m17clone();
            }
        }
        return livingParams.m17clone();
    }

    public LivingParams getLivingParams(int i, boolean z) {
        if (z) {
            return this.mCustomizeParams.m17clone();
        }
        return getLivingParamsOnList(i, this.mCustomizeParams);
    }

    public LivingParams getCurLivingParams() {
        LivingParams livingParams = getLivingParams(SdkProperties.resolution.get().intValue(), false);
        if (livingParams != null && !CommonUtil.isScreenLandScape()) {
            int videoHeight = livingParams.getVideoHeight();
            int videoWidth = livingParams.getVideoWidth();
            livingParams.setVideoWidth(videoHeight);
            livingParams.setVideoHeight(videoWidth);
        }
        return livingParams;
    }

    public List<LivingParams> getResolutionSettingList() {
        ArrayList arrayList = new ArrayList();
        Iterator<LivingParams> it = this.mOptionList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().m17clone());
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public LivingParams changeDefinition(LivingParams livingParams, int i) {
        LivingParams livingParams2 = getLivingParams(i, false);
        livingParams.setResolution(livingParams2.getResolution());
        livingParams.setVideoWidth(livingParams2.getVideoWidth());
        livingParams.setVideoHeight(livingParams2.getVideoHeight());
        livingParams.setVideoFrameRate(livingParams2.getVideoFrameRate());
        livingParams.setVideoBitrate(livingParams2.getVideoBitrate());
        livingParams.setMaxVideoBitrate(livingParams2.getMaxVideoBitrate());
        livingParams.setMinVideoBitrate(livingParams2.getMinVideoBitrate());
        return livingParams;
    }

    public LivingParams getCustomizeParams() {
        return this.mCustomizeParams;
    }

    public void setCustomizeParams(LivingParams livingParams) {
        this.mCustomizeParams = livingParams;
    }

    public boolean isIsCustomize() {
        return this.mIsCustomize;
    }

    public void setIsCustomize(boolean z) {
        this.mIsCustomize = z;
    }

    public boolean isShowParam() {
        return this.isShowParam;
    }

    public void setShowParam(boolean z) {
        this.isShowParam = z;
    }

    public static LivingParams getResoltionValue(String str) {
        int i;
        int i2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.split("\\|").length != 8 && str.split("\\|").length != 9) {
            return null;
        }
        LivingParams.Builder builder = new LivingParams.Builder();
        String[] strArrSplit = str.split("\\|");
        int resolutionInt = parseResolutionInt(strArrSplit[1]);
        int resolutionInt2 = parseResolutionInt(strArrSplit[2]);
        if (SdkProperties.enableFullSreenSupport.get().booleanValue() && SdkProperties.sdkMode.get() == SdkProperties.SDKMode.CAPTURE_BY_SCREEN) {
            if (resolutionInt2 > resolutionInt) {
                resolutionInt2 = UIUtil.getFullScreenLength(resolutionInt2, resolutionInt);
            } else {
                resolutionInt = UIUtil.getFullScreenLength(resolutionInt, resolutionInt2);
            }
            L.info(TAG, "FullScreen: width:" + resolutionInt2 + ",height:" + resolutionInt);
        }
        if (resolutionInt2 > resolutionInt) {
            i2 = (resolutionInt2 * 9) / 16;
            i = resolutionInt2;
        } else {
            i = (resolutionInt * 9) / 16;
            i2 = resolutionInt;
        }
        builder.resolution(parseResolutionInt(strArrSplit[0]));
        builder.videoHeight(resolutionInt);
        builder.videoWidth(resolutionInt2);
        builder.paramsVideoHeight(i2);
        builder.paramsVideoWidth(i);
        builder.videoBitrate(parseResolutionInt(strArrSplit[3]));
        builder.maxVideoBitrate(parseResolutionInt(strArrSplit[4]));
        builder.minVideoBitrate(parseResolutionInt(strArrSplit[5]));
        builder.videoFrameRate(parseResolutionInt(strArrSplit[6]));
        builder.name(strArrSplit[7]);
        if (strArrSplit.length == 9) {
            builder.tips(strArrSplit[8]);
        }
        return builder.build();
    }

    private static int parseResolutionInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
