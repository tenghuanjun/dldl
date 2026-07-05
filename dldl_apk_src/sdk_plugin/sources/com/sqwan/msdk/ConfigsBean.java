package com.sqwan.msdk;

import com.sqwan.common.util.LogUtil;
import java.util.Properties;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ConfigsBean {
    public static ConfigsBean configsBean = null;
    public static final String string_0 = "0";
    public static final String string_1 = "1";
    public boolean isCheckPermissionPreview;
    public boolean isPlayWithouPermission;
    public boolean isTvType;

    interface ConfigsBeanKey {
        public static final String isCheckPermissionPreview = "isCheckPermissionPreview";
        public static final String isPlayWithouPermission = "isPlayWithouPermission";
        public static final String isTvType = "isTvType";
    }

    public static ConfigsBean init(Properties properties) {
        configsBean = null;
        if (properties != null) {
            ConfigsBean configsBean2 = new ConfigsBean();
            configsBean = configsBean2;
            configsBean2.isCheckPermissionPreview = "1".equals(properties.getProperty(ConfigsBeanKey.isCheckPermissionPreview, "0"));
            configsBean.isTvType = "1".equals(properties.getProperty(ConfigsBeanKey.isTvType, "0"));
            configsBean.isPlayWithouPermission = "1".equals(properties.getProperty(ConfigsBeanKey.isPlayWithouPermission, "0"));
            LogUtil.d("ConfigsBean " + configsBean.toString());
        }
        return configsBean;
    }

    public String toString() {
        return "ConfigsBean{isCheckPermissionPreview=" + this.isCheckPermissionPreview + ", isTvType=" + this.isTvType + AbstractJsonLexerKt.END_OBJ;
    }
}
