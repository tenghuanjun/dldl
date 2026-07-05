package notchtools.geek.com.notchtools.helper;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DeviceBrandTools {
    private static DeviceBrandTools sDeviceBrandTools;

    public static DeviceBrandTools getInstance() {
        if (sDeviceBrandTools == null) {
            synchronized (DeviceBrandTools.class) {
                if (sDeviceBrandTools == null) {
                    sDeviceBrandTools = new DeviceBrandTools();
                }
            }
        }
        return sDeviceBrandTools;
    }

    public final boolean isHuaWei() {
        String str = Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) && str.contains("HUAWEI");
    }

    public final boolean isMiui() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    public final boolean isOppo() {
        return "oppo".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public final boolean isVivo() {
        return !TextUtils.isEmpty(getSystemProperty("ro.vivo.os.name"));
    }

    public final boolean isSamsung() {
        return "samsung".equalsIgnoreCase(Build.MANUFACTURER);
    }

    private String getSystemProperty(String str) {
        return SystemProperties.getInstance().get(str);
    }
}
