package cn.thinkingdata.android.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public enum m {
    TRACK("track"),
    TRACK_UPDATE("track_update"),
    TRACK_OVERWRITE("track_overwrite"),
    USER_ADD("user_add"),
    USER_SET("user_set"),
    USER_SET_ONCE("user_setOnce"),
    USER_UNSET("user_unset"),
    USER_APPEND("user_append"),
    USER_DEL("user_del"),
    USER_UNIQ_APPEND("user_uniq_append");

    private static final Map<String, m> l = new HashMap();
    private final String a;

    static {
        for (m mVar : values()) {
            l.put(mVar.a(), mVar);
        }
    }

    m(String str) {
        this.a = str;
    }

    public static m a(String str) {
        return l.get(str);
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this == TRACK || this == TRACK_OVERWRITE || this == TRACK_UPDATE;
    }
}
