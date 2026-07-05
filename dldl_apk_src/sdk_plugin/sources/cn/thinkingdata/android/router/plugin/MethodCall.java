package cn.thinkingdata.android.router.plugin;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MethodCall {
    public Map<String, Object> arguments = new HashMap();
    public String method;

    public <T> T argument(String str) {
        return (T) this.arguments.get(str);
    }
}
