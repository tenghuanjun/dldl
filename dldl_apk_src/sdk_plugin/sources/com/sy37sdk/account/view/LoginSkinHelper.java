package com.sy37sdk.account.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.msdk.config.MultiConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginSkinHelper {
    private static String skinType = "";

    public static void init() {
        String skinType2 = MultiConfigManager.getInstance().getSkinType();
        if (TextUtils.isEmpty(skinType2)) {
            return;
        }
        skinType = skinType2;
    }

    public static int getPhoneLoginBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_bg_login_phone");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_bg_login") : drawableId;
    }

    public static int getHistoryLoginBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_bg_login_history");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_bg_login") : drawableId;
    }

    public static int getAccountLoginBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_bg_login_account");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_bg_login") : drawableId;
    }

    public static int getPhoneCodeLoginBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_bg_login_phone_code");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_bg_login") : drawableId;
    }

    public static int getPhonePwdLoginBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_bg_login_phone_pwd");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_bg_login") : drawableId;
    }

    public static int getLoginBtnBackgroundResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy_" + skinType + "_dialog_login_btn_bg");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sy_sq_dialog_login_btn_bg") : drawableId;
    }

    public static int getLoginBtnTextColor(Context context) {
        int colorId = SqResUtils.getColorId(context, "sy_" + skinType + "_login_btn_text_color");
        if (colorId == 0) {
            colorId = SqResUtils.getColorId(context, "sy_sq_login_btn_text_color");
        }
        return context.getResources().getColor(colorId);
    }

    public static int getPrimaryTextColor(Context context) {
        int colorId = SqResUtils.getColorId(context, "sy_" + skinType + "_dialog_text_primary");
        if (colorId == 0) {
            colorId = SqResUtils.getColorId(context, "sy_sq_dialog_text_primary");
        }
        return context.getResources().getColor(colorId);
    }

    public static int getFastLoginPrimaryTextColor(Context context) {
        try {
            if (TextUtils.isEmpty(skinType)) {
                return Color.parseColor("#333333");
            }
            return context.getResources().getColor(SqResUtils.getColorId(context, "sy_" + skinType + "_dialog_text_primary"));
        } catch (Exception unused) {
            return Color.parseColor("#333333");
        }
    }

    public static String getFastLoginBtnBackgroundResString(Context context) {
        try {
            if (TextUtils.isEmpty(skinType)) {
                return "sysq_dialog_login_btn_bg";
            }
            return "sy_" + skinType + "_dialog_login_btn_bg";
        } catch (Exception unused) {
            return "sysq_dialog_login_btn_bg";
        }
    }

    public static int getFastLoginBtnTextColor(Context context) {
        try {
            if (TextUtils.isEmpty(skinType)) {
                return Color.parseColor("#ffffff");
            }
            return context.getResources().getColor(SqResUtils.getColorId(context, "sy_" + skinType + "_login_btn_text_color"));
        } catch (Exception unused) {
            return Color.parseColor("#ffffff");
        }
    }

    public static String getPhoneLoginBackgroundResString(Context context) {
        try {
            if (TextUtils.isEmpty(skinType)) {
                return "sysq_dialog_login_bg";
            }
            return "sy_" + skinType + "_bg_login_fast";
        } catch (Exception unused) {
            return "sysq_dialog_login_bg";
        }
    }

    public static int getTipInputColor(Context context) {
        int colorId = SqResUtils.getColorId(context, "sy" + skinType + "_dialog_login_text_primary");
        if (colorId == 0) {
            colorId = SqResUtils.getColorId(context, "sysq_dialog_login_text_primary");
        }
        return context.getResources().getColor(colorId);
    }

    public static int getLoginTextAccentColor(Context context) {
        int colorId = SqResUtils.getColorId(context, "sy" + skinType + "_dialog_login_text_accent");
        if (colorId == 0) {
            colorId = SqResUtils.getColorId(context, "sysq_dialog_login_text_accent");
        }
        return context.getResources().getColor(colorId);
    }

    public static int getLoginTextHintColor(Context context) {
        int colorId = SqResUtils.getColorId(context, "sy" + skinType + "_dialog_login_text_hint");
        if (colorId == 0) {
            colorId = SqResUtils.getColorId(context, "sysq_dialog_login_text_hint");
        }
        return context.getResources().getColor(colorId);
    }

    public static int getCloseIconResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy" + skinType + "_ic_close");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sysq_ic_close") : drawableId;
    }

    public static int getBackIconResId(Context context) {
        int drawableId = SqResUtils.getDrawableId(context, "sy" + skinType + "_ic_back");
        return drawableId == 0 ? SqResUtils.getDrawableId(context, "sysq_ic_back") : drawableId;
    }
}
