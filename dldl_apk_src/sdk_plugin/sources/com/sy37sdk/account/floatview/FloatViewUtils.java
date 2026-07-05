package com.sy37sdk.account.floatview;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.sq.tools.utils.ScreenUtils;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.NavigationUtils;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.SqResUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import notchtools.geek.com.notchtools.NotchTools;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatViewUtils {

    public interface FloatViewSpkey {
        public static final String key_floatview_position = "key_floatview_position";
        public static final String key_shake_tips = "key_shake_tips";
    }

    public static boolean isUiFlagHideNavigation(Context context) {
        return (context instanceof Activity) && (((Activity) context).getWindow().getDecorView().getSystemUiVisibility() & 2) == 2;
    }

    private static String setKeyWithViewTag(View view, String str) {
        return view.getClass().getSimpleName() + str;
    }

    public static boolean isShakeTips(Context context) {
        return SpUtils.get(context).getBoolean(FloatViewSpkey.key_shake_tips, true);
    }

    public static void setShankeTips(Context context, boolean z) {
        SpUtils.get(context).put(FloatViewSpkey.key_shake_tips, z);
    }

    public static void setFloatViewPos(Context context, View view, int i, int i2, int i3) {
        SpUtils.get(context).put(setKeyWithViewTag(view, FloatViewSpkey.key_floatview_position), new FloatViewPosConfig(i, i2, i3).toJsonString());
    }

    public static void resetFloatViewPos(Context context, View view) {
        SpUtils.get(context).put(setKeyWithViewTag(view, FloatViewSpkey.key_floatview_position), "");
    }

    public static FloatViewPosConfig getFloatViewPositionConfig(Context context, View view) {
        String string = SpUtils.get(context).getString(setKeyWithViewTag(view, FloatViewSpkey.key_floatview_position), "");
        if (TextUtils.isEmpty(string)) {
            return new FloatViewPosConfig();
        }
        try {
            Object objNewInstance = FloatViewPosConfig.class.getConstructor(new Class[0]).newInstance(new Object[0]);
            JSONObject jSONObject = new JSONObject(string);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                FloatViewPosConfig.class.getField(next).set(objNewInstance, Integer.valueOf(jSONObject.optInt(next)));
            }
            return (FloatViewPosConfig) objNewInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return new FloatViewPosConfig();
        }
    }

    public static int getBottomDeleteHeight(Context context) {
        if (DisplayUtil.isLandscape(context)) {
            return SqResUtils.getDimensionPixelSize(context, "sysq_float_bottomdelete_height_landscape");
        }
        return SqResUtils.getDimensionPixelSize(context, "sysq_float_bottomdelete_height_portrait");
    }

    public static int getBottomDeleteWidth(Context context) {
        float screenWidth;
        float f;
        if (DisplayUtil.isLandscape(context)) {
            screenWidth = ScreenUtils.getScreenWidth(context);
            f = 0.55f;
        } else {
            screenWidth = ScreenUtils.getScreenWidth(context);
            f = 0.88f;
        }
        return (int) (screenWidth * f);
    }

    public static int getViewXOnScreen(View view) {
        return getViewLocationOnScreen(view)[0];
    }

    public static int getViewYOnScreen(View view) {
        return getViewLocationOnScreen(view)[1];
    }

    public static int[] getViewLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    public static class FloatViewPosConfig {
        public int orientationType;
        public int x;
        public int y;

        public FloatViewPosConfig(int i, int i2, int i3) {
            this.x = -1;
            this.y = -1;
            this.orientationType = 0;
            this.x = i;
            this.y = i2;
            this.orientationType = i3;
        }

        public FloatViewPosConfig() {
            this.x = -1;
            this.y = -1;
            this.orientationType = 0;
        }

        public String toJsonString() {
            try {
                HashMap map = new HashMap();
                for (Field field : getClass().getDeclaredFields()) {
                    map.put(field.getName(), field.get(this));
                }
                return new JSONObject(map).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static class FloatViewConfig {
        public String TAG = getClass().getSimpleName();
        public int _notchHeight;
        public View contentView;
        public View decorView;
        public boolean ignoreNotchScreen;
        public boolean isNotchScreen;
        public boolean isUiFlagHideNavigation;
        public int marginLeft;
        public int marginTop;
        public int navigationBarHeight;
        public int regionHeight;
        public int regionWidth;
        public int screenType;

        public String toString() {
            return "FloatViewConfig{, regionWidth=" + this.regionWidth + ", regionHeight=" + this.regionHeight + ", marginLeft=" + this.marginLeft + ", marginTop=" + this.marginTop + ", screenType=" + this.screenType + ", isNotchScreen=" + this.isNotchScreen + ", _notchHeight=" + this._notchHeight + ", ignoreNotchScreen=" + this.ignoreNotchScreen + ", navigationBarHeight=" + this.navigationBarHeight + ", isUiFlagHideNavigation=" + this.isUiFlagHideNavigation + AbstractJsonLexerKt.END_OBJ;
        }

        public FloatViewConfig(Context context) {
            this.screenType = ScreenOrientationHelper.getOrientationType(context);
            buildConfig(context);
            LogUtil.i(this.TAG, toString());
        }

        private void buildConfig(Context context) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                View decorView = window.getDecorView();
                this.decorView = decorView;
                this.contentView = decorView.findViewById(R.id.content);
                int viewXOnScreen = FloatViewUtils.getViewXOnScreen(this.decorView);
                int viewYOnScreen = FloatViewUtils.getViewYOnScreen(this.decorView);
                int viewXOnScreen2 = FloatViewUtils.getViewXOnScreen(this.contentView);
                int viewYOnScreen2 = FloatViewUtils.getViewYOnScreen(this.contentView);
                LogUtil.i(this.TAG, String.format("decorViewx:%d,decorViewy:%d,contentViewx:%d,contentViewy:%d", Integer.valueOf(viewXOnScreen), Integer.valueOf(viewYOnScreen), Integer.valueOf(viewXOnScreen2), Integer.valueOf(viewYOnScreen2)));
                LogUtil.i(this.TAG, String.format("decorViewWidth:%d,decorViewHeight:%d,contentViewWidth:%d,contentViewHeight:%d", Integer.valueOf(this.decorView.getWidth()), Integer.valueOf(this.decorView.getHeight()), Integer.valueOf(this.contentView.getWidth()), Integer.valueOf(this.contentView.getHeight())));
                this.navigationBarHeight = NavigationUtils.getNavigationBarHeight(context);
                this.isNotchScreen = NotchTools.getFullScreenTools().isNotchScreen(window);
                this._notchHeight = NotchTools.getFullScreenTools().getNotchHeight(window);
                this.ignoreNotchScreen = NotchTools.getFullScreenTools().ignoreNotchScreen(window, this.decorView.getWidth(), this.decorView.getHeight());
                this.regionWidth = this.decorView.getWidth();
                this.regionHeight = this.decorView.getHeight();
                this.marginLeft = viewXOnScreen2 - viewXOnScreen;
                this.marginTop = viewYOnScreen2 - viewYOnScreen;
                this.isUiFlagHideNavigation = FloatViewUtils.isUiFlagHideNavigation(context);
                int i = this.screenType;
                if (i == 270) {
                    if (this.isNotchScreen && !this.ignoreNotchScreen) {
                        this.marginLeft += this._notchHeight;
                    }
                    if (!NavigationUtils.hasNavigationBar(context) || this.isUiFlagHideNavigation) {
                        return;
                    }
                    this.regionWidth -= this.navigationBarHeight;
                    return;
                }
                if (i == 90) {
                    if (this.isNotchScreen && !this.ignoreNotchScreen) {
                        this.regionWidth -= this._notchHeight;
                    }
                    if (!NavigationUtils.hasNavigationBar(context) || this.isUiFlagHideNavigation) {
                        return;
                    }
                    this.marginLeft += this.navigationBarHeight;
                    return;
                }
                if (i == 0 && NavigationUtils.hasNavigationBar(context) && !this.isUiFlagHideNavigation) {
                    this.regionHeight -= this.navigationBarHeight;
                }
            }
        }
    }

    public static boolean isTouchPointInView(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        return i2 >= i4 && i2 <= view.getMeasuredHeight() + i4 && i >= i3 && i <= view.getMeasuredWidth() + i3;
    }
}
