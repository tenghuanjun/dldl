package com.sy37sdk.account.trackaction;

import com.sqwan.common.track.SqTrackPage;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PageExposureAction {
    public static final String PAGE_VIEW_ACCOUNT_LOGIN = "view05";
    public static final String PAGE_VIEW_ACCOUNT_REG = "view06";
    public static final String PAGE_VIEW_FAST = "view01";
    public static final String PAGE_VIEW_PHONE = "view02";
    public static final String PAGE_VIEW_PHONE_CODE = "view03";
    public static final String PAGE_VIEW_PHONE_PWD = "view04";
    public static HashMap<String, String> pageExposure;
    public static HashMap<String, Long> pageExposureTime = new HashMap<>();

    static {
        HashMap<String, String> map = new HashMap<>();
        pageExposure = map;
        map.put("view01", SqTrackPage.SqTrackViewName.ali_fast);
        pageExposure.put("view02", SqTrackPage.SqTrackViewName.phone_input);
        pageExposure.put("view03", SqTrackPage.SqTrackViewName.phone_code);
        pageExposure.put("view04", "手机号填写密码码页面曝光");
        pageExposure.put("view05", SqTrackPage.SqTrackViewName.ACCOUNT_LOGIN);
        pageExposure.put("view06", SqTrackPage.SqTrackViewName.ACCOUNT_REGISTER);
    }
}
