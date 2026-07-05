package com.sqwan.liveshow.huya.skin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinHelper {
    private static final String TAG = "LiveshowUiHelper";
    private static final String UI_VERSION = "2";
    private static final String appsrcdirpath = "liveshowUI";
    private static final String colorConfigPath = "colorConfig";
    private static final String colorXmlFile = "colors.xml";
    private static final String pluginsrcdirpath = "liveshowUIplugin";
    private static List<String> appsrcnames = new ArrayList();
    private static List<String> pluginsrcnames = new ArrayList();
    private static Map<String, String> colorMap = new HashMap();
    private static String imgpostfix = ".png";
    private static String idnamepostfix = "_selector";
    private static String liveshow_ui_anchor = "sy37_liveshow";

    public static void init(Context context) {
        findSrcNames(context, getSrcDirPath(true), appsrcnames);
        findSrcNames(context, getSrcDirPath(false), pluginsrcnames);
        findColorXmlInHost(context);
    }

    private static void findSrcNames(Context context, String str, List<String> list) {
        if (list != null) {
            list.clear();
            try {
                for (String str2 : context.getAssets().list(str)) {
                    String str3 = str2.split(imgpostfix)[0];
                    LogUtil.d(TAG, String.format("name:%s _name:%s", str2, str3));
                    list.add(str3);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void findColorXmlInHost(Context context) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(colorConfigPath + File.separator + colorXmlFile);
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamOpen, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    if ("color".equals(xmlPullParserNewPullParser.getName())) {
                        colorMap.put(xmlPullParserNewPullParser.getAttributeValue(null, "name"), xmlPullParserNewPullParser.nextText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImage(View view, Drawable drawable) {
        if (drawable != null) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(drawable);
            } else {
                view.setBackground(drawable);
            }
        }
    }

    private static String getSrcDirPath(boolean z) {
        if (z) {
            return appsrcdirpath + File.separator + "2";
        }
        return pluginsrcdirpath + File.separator + "2";
    }

    private static String getSrcPath(boolean z, String str) {
        return getSrcDirPath(z) + File.separator + str;
    }

    private static Drawable getDrawableAsset(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            TypedValue typedValue = new TypedValue();
            typedValue.density = 480;
            return Drawable.createFromResourceStream(context.getResources(), typedValue, inputStreamOpen, "src", null);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Drawable getDrawableWrapper(Context context, String str) {
        return getDrawableAsset(context, str);
    }

    private static Drawable getDrawableHostAsset(Context context, String str) {
        return getDrawableWrapper(context, getSrcPath(true, str));
    }

    private static Drawable getDrawablePluginAsset(Context context, String str) {
        return getDrawableWrapper(context, getSrcPath(false, str));
    }

    private static Drawable getDrawablePlugin(Context context, String str) {
        try {
            return context.getResources().getDrawable(SqResUtils.getDrawableId(context, str));
        } catch (Exception unused) {
            return null;
        }
    }

    private static Drawable getDrawablePluginWrapper(Context context, String str) {
        try {
            return context.getResources().getDrawable(SqResUtils.getDrawableId(context, str));
        } catch (Exception unused) {
            return null;
        }
    }

    public static int getColorValue(Context context, String str, int i) {
        String colorValueFromHostAsset = getColorValueFromHostAsset(str);
        if (!TextUtils.isEmpty(colorValueFromHostAsset)) {
            try {
                return Color.parseColor(colorValueFromHostAsset);
            } catch (Exception unused) {
                return getColorValueFromPluginResource(context, str, i);
            }
        }
        return getColorValueFromPluginResource(context, str, i);
    }

    private static String getColorValueFromHostAsset(String str) {
        Map<String, String> map = colorMap;
        return map != null ? map.get(str) : "";
    }

    private static int getColorValueFromPluginResource(Context context, String str, int i) {
        try {
            return context.getResources().getColor(SqResUtils.getColorId(context, str));
        } catch (Exception unused) {
            return i;
        }
    }

    public static Drawable getDrawable(Context context, String str) {
        Drawable drawableHostAsset = getDrawableHostAsset(context, str + imgpostfix);
        if (drawableHostAsset == null) {
            drawableHostAsset = getDrawablePluginAsset(context, str + imgpostfix);
            if (drawableHostAsset == null) {
                drawableHostAsset = getDrawablePlugin(context, str);
            }
        }
        return setEmptyDrawable(drawableHostAsset);
    }

    public static boolean checkSelectorName(String str) {
        return str.endsWith(idnamepostfix) && str.startsWith(liveshow_ui_anchor);
    }

    private static Drawable setEmptyDrawable(Drawable drawable) {
        return drawable == null ? new BitmapDrawable() : drawable;
    }
}
