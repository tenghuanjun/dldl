package com.shuyu.gsyvideoplayer.utils;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.fragment.app.FragmentActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.io.File;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class CommonUtil {
    public static String stringForTime(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        return j5 > 0 ? formatter.format("%d:%02d:%02d", Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)).toString() : formatter.format("%02d:%02d", Long.valueOf(j4), Long.valueOf(j3)).toString();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof TintContextWrapper) {
            return scanForActivity(((TintContextWrapper) context).getBaseContext());
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getActionBarHeight(Activity activity) {
        TypedValue typedValue = new TypedValue();
        if (activity.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static void hideSupportActionBar(Context context, boolean z, boolean z2) {
        AppCompatActivity appCompActivity;
        ActionBar supportActionBar;
        if (z && (appCompActivity = getAppCompActivity(context)) != null && (supportActionBar = appCompActivity.getSupportActionBar()) != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.hide();
        }
        if (z2) {
            if (context instanceof FragmentActivity) {
                ((FragmentActivity) context).getWindow().setFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            } else if (context instanceof Activity) {
                ((Activity) context).getWindow().setFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            } else {
                getAppCompActivity(context).getWindow().setFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            }
        }
    }

    public static void showSupportActionBar(Context context, boolean z, boolean z2) {
        AppCompatActivity appCompActivity;
        ActionBar supportActionBar;
        if (z && (appCompActivity = getAppCompActivity(context)) != null && (supportActionBar = appCompActivity.getSupportActionBar()) != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.show();
        }
        if (z2) {
            if (context instanceof FragmentActivity) {
                ((FragmentActivity) context).getWindow().clearFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            } else if (context instanceof Activity) {
                ((Activity) context).getWindow().clearFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            } else {
                getAppCompActivity(context).getWindow().clearFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            }
        }
    }

    public static void hideNavKey(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(3074);
        } else {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(2562);
        }
    }

    public static void showNavKey(Context context, int i) {
        ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(i);
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static String getTextSpeed(long j) {
        if (j >= 0 && j < ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
            return j + " KB/s";
        }
        if (j >= ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS && j < 1048576) {
            return Long.toString(j / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + " KB/s";
        }
        if (j >= 1048576 && j < 1073741824) {
            return Long.toString(j / 1048576) + " MB/s";
        }
        return "";
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            for (String str2 : file.list()) {
                deleteFile(str + File.separator + str2);
            }
            file.delete();
        }
    }

    public static Activity getActivityContext(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof TintContextWrapper) {
            return scanForActivity(((TintContextWrapper) context).getBaseContext());
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static boolean getCurrentScreenLand(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getRotation() == 1 || activity.getWindowManager().getDefaultDisplay().getRotation() == 3;
    }
}
