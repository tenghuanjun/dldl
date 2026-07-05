package com.sq.tool.logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SQLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static Printer printer = new LoggerPrinter();
    private static final Map<String, Printer> modulePrinters = new ConcurrentHashMap();

    private SQLog() {
    }

    public static void printer(Printer printer2) {
        printer = (Printer) Utils.checkNotNull(printer2);
    }

    public static void addLogAdapter(LogAdapter adapter) {
        printer.addAdapter((LogAdapter) Utils.checkNotNull(adapter));
    }

    public static void replaceLogAdapter(LogAdapter adapter, Class<? extends LogAdapter> target) {
        printer.replaceAdapter(adapter, target);
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    public static Printer t(String tag) {
        return printer.t(tag);
    }

    public static Printer m(String prefix) {
        if (prefix == null) {
            return printer;
        }
        if (modulePrinters.containsKey(prefix)) {
            return modulePrinters.get(prefix);
        }
        PrefixLoggerPrinter prefixLoggerPrinter = new PrefixLoggerPrinter(prefix, printer);
        modulePrinters.put(prefix, prefixLoggerPrinter);
        return prefixLoggerPrinter;
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void v(String message, Object... args) {
        printer.v(message, args);
    }

    public static void v(Object object) {
        printer.v(Utils.toString(object));
    }

    public static void v(Object object, Throwable throwable) {
        printer.log(2, null, Utils.toString(object), throwable);
    }

    public static void vt(String tag, Object object) {
        printer.log(2, tag, Utils.toString(object), null);
    }

    public static void vt(String tag, Object object, Throwable throwable) {
        printer.log(2, tag, Utils.toString(object), throwable);
    }

    public static void d(String message, Object... args) {
        printer.d(message, args);
    }

    public static void d(Object object) {
        printer.d(object);
    }

    public static void d(Object object, Throwable throwable) {
        printer.log(3, null, Utils.toString(object), throwable);
    }

    public static void dt(String tag, Object object) {
        printer.log(3, tag, Utils.toString(object), null);
    }

    public static void dt(String tag, Object object, Throwable throwable) {
        printer.log(3, tag, Utils.toString(object), throwable);
    }

    public static void i(String message, Object... args) {
        printer.i(message, args);
    }

    public static void i(Object object) {
        printer.i(Utils.toString(object));
    }

    public static void i(Object object, Throwable throwable) {
        printer.log(4, null, Utils.toString(object), throwable);
    }

    public static void it(String tag, Object object) {
        printer.log(4, tag, Utils.toString(object), null);
    }

    public static void it(String tag, Object object, Throwable throwable) {
        printer.log(4, tag, Utils.toString(object), throwable);
    }

    public static void w(String message, Object... args) {
        printer.w(message, args);
    }

    public static void w(Object object) {
        printer.w(Utils.toString(object));
    }

    public static void w(Object object, Throwable throwable) {
        printer.log(5, null, Utils.toString(object), throwable);
    }

    public static void wt(String tag, Object object) {
        printer.log(5, tag, Utils.toString(object), null);
    }

    public static void wt(String tag, Object object, Throwable throwable) {
        printer.log(5, tag, Utils.toString(object), throwable);
    }

    public static void e(String message, Object... args) {
        printer.e(message, args);
    }

    public static void e(Object object) {
        printer.e(Utils.toString(object));
    }

    public static void e(Object object, Throwable throwable) {
        printer.log(6, null, Utils.toString(object), throwable);
    }

    public static void et(String tag, Object object) {
        printer.log(6, tag, Utils.toString(object), null);
    }

    public static void et(String tag, Object object, Throwable throwable) {
        printer.log(6, tag, Utils.toString(object), throwable);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    public static void json(String json) {
        printer.json(json);
    }

    public static void xml(String xml) {
        printer.xml(xml);
    }

    public static String createMessage(String message, Object... args) {
        return (args == null || args.length == 0) ? message : String.format(message, args);
    }
}
