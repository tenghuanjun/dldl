package com.sq.tool.logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Printer {
    void addAdapter(LogAdapter adapter);

    void clearLogAdapters();

    void d(Object object);

    void d(Object object, Throwable throwable);

    void d(String message, Object... args);

    void dt(String tag, Object object);

    void dt(String tag, Object object, Throwable throwable);

    void e(Object object);

    void e(Object object, Throwable throwable);

    void e(String message, Object... args);

    void et(String tag, Object object);

    void et(String tag, Object object, Throwable throwable);

    void i(Object object);

    void i(Object object, Throwable throwable);

    void i(String message, Object... args);

    void it(String tag, Object object);

    void it(String tag, Object object, Throwable throwable);

    void json(String json);

    void log(int priority, String tag, String message, Throwable throwable);

    Printer m(String prefix);

    void replaceAdapter(LogAdapter adapter, Class<? extends LogAdapter> target);

    Printer t(String tag);

    void v(Object object);

    void v(Object object, Throwable throwable);

    void v(String message, Object... args);

    void vt(String tag, Object object);

    void vt(String tag, Object object, Throwable throwable);

    void w(Object object);

    void w(Object object, Throwable throwable);

    void w(String message, Object... args);

    void wt(String tag, Object object);

    void wt(String tag, Object object, Throwable throwable);

    void wtf(String message, Object... args);

    void xml(String xml);
}
