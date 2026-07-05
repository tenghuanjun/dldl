package com.huya.berry.gamesdk.widgets;

import android.app.Activity;
import android.text.Html;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.LiveAlert;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HtmlAlertDialog {
    Activity mActivity;
    String mContent;

    public HtmlAlertDialog(Activity activity) {
        this.mActivity = activity;
    }

    public HtmlAlertDialog setContent(String str) {
        this.mContent = str;
        return this;
    }

    public String getContent() {
        return this.mContent;
    }

    public void show() {
        LiveAlert.Builder builder = new LiveAlert.Builder(this.mActivity);
        builder.message(Html.fromHtml(this.mContent));
        builder.positive(ResourceUtil.getStringResIDByName("hyberry_confirm"));
        builder.create().show();
    }

    public static boolean isHtmlAlertDialog(String str) {
        try {
            return str.matches(".*<([^>]*)>.*");
        } catch (Exception unused) {
            return false;
        }
    }
}
