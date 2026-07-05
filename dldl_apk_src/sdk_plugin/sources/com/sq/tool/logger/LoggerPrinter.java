package com.sq.tool.logger;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class LoggerPrinter implements Printer {
    private static final int JSON_INDENT = 2;
    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private final List<LogAdapter> logAdapters;

    public LoggerPrinter() {
        ArrayList arrayList = new ArrayList();
        this.logAdapters = arrayList;
        arrayList.add(new AndroidLogAdapter());
    }

    @Override // com.sq.tool.logger.Printer
    public Printer t(String tag) {
        if (tag != null) {
            this.localTag.set(tag);
        }
        return this;
    }

    @Override // com.sq.tool.logger.Printer
    public Printer m(String prefix) {
        return prefix == null ? this : new PrefixLoggerPrinter(prefix, this);
    }

    @Override // com.sq.tool.logger.Printer
    public void v(String message, Object... args) {
        log(2, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void v(Object object) {
        log(2, (Throwable) null, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void v(Object object, Throwable throwable) {
        log(2, throwable, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void vt(String tag, Object object) {
        log(2, tag, Utils.toString(object), (Throwable) null);
    }

    @Override // com.sq.tool.logger.Printer
    public void vt(String tag, Object object, Throwable throwable) {
        log(2, tag, Utils.toString(object), throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void d(String message, Object... args) {
        log(3, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void d(Object object) {
        log(3, (Throwable) null, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void d(Object object, Throwable throwable) {
        log(3, throwable, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void dt(String tag, Object object) {
        log(3, tag, Utils.toString(object), (Throwable) null);
    }

    @Override // com.sq.tool.logger.Printer
    public void dt(String tag, Object object, Throwable throwable) {
        log(3, tag, Utils.toString(object), throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void i(String message, Object... args) {
        log(4, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void i(Object object) {
        log(4, (Throwable) null, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void i(Object object, Throwable throwable) {
        log(4, throwable, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void it(String tag, Object object) {
        log(4, tag, Utils.toString(object), (Throwable) null);
    }

    @Override // com.sq.tool.logger.Printer
    public void it(String tag, Object object, Throwable throwable) {
        log(4, tag, Utils.toString(object), throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void w(String message, Object... args) {
        log(5, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void w(Object object) {
        log(5, (Throwable) null, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void w(Object object, Throwable throwable) {
        log(5, throwable, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void wt(String tag, Object object) {
        log(5, tag, Utils.toString(object), (Throwable) null);
    }

    @Override // com.sq.tool.logger.Printer
    public void wt(String tag, Object object, Throwable throwable) {
        log(5, tag, Utils.toString(object), throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void e(String message, Object... args) {
        log(6, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void e(Object object) {
        log(6, (Throwable) null, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void e(Object object, Throwable throwable) {
        log(6, throwable, Utils.toString(object), new Object[0]);
    }

    @Override // com.sq.tool.logger.Printer
    public void et(String tag, Object object) {
        log(6, tag, Utils.toString(object), (Throwable) null);
    }

    @Override // com.sq.tool.logger.Printer
    public void et(String tag, Object object, Throwable throwable) {
        log(6, tag, Utils.toString(object), throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void wtf(String message, Object... args) {
        log(7, (Throwable) null, message, args);
    }

    @Override // com.sq.tool.logger.Printer
    public void json(String json) {
        if (Utils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            String strTrim = json.trim();
            if (strTrim.startsWith("{")) {
                d(new JSONObject(strTrim).toString(2));
            } else if (strTrim.startsWith("[")) {
                d(new JSONArray(strTrim).toString(2));
            } else {
                e("Invalid Json");
            }
        } catch (JSONException unused) {
            e("Invalid Json");
        }
    }

    @Override // com.sq.tool.logger.Printer
    public void xml(String xml) {
        if (Utils.isEmpty(xml)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("indent", "yes");
            transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformerNewTransformer.transform(streamSource, streamResult);
            d(streamResult.getWriter().toString().replaceFirst(SimpleComparison.GREATER_THAN_OPERATION, ">\n"));
        } catch (TransformerException unused) {
            e("Invalid xml");
        }
    }

    @Override // com.sq.tool.logger.Printer
    public void log(int priority, String tag, String message, Throwable throwable) {
        if (throwable != null && message != null) {
            message = message + " : " + Utils.getStackTraceString(throwable);
        }
        if (throwable != null && message == null) {
            message = Utils.getStackTraceString(throwable);
        }
        if (Utils.isEmpty(message)) {
            message = "Empty/NULL log message";
        }
        for (LogAdapter logAdapter : this.logAdapters) {
            if (logAdapter.isLoggable(priority, tag)) {
                logAdapter.log(priority, tag, message);
            }
        }
    }

    @Override // com.sq.tool.logger.Printer
    public void clearLogAdapters() {
        this.logAdapters.clear();
    }

    @Override // com.sq.tool.logger.Printer
    public void addAdapter(LogAdapter adapter) {
        this.logAdapters.add((LogAdapter) Utils.checkNotNull(adapter));
    }

    @Override // com.sq.tool.logger.Printer
    public void replaceAdapter(LogAdapter adapter, Class<? extends LogAdapter> target) {
        Utils.checkNotNull(adapter);
        Utils.checkNotNull(target);
        int size = this.logAdapters.size();
        for (int i = 0; i < size; i++) {
            if (this.logAdapters.get(i).getClass().equals(target)) {
                this.logAdapters.set(i, adapter);
                return;
            }
        }
        throw new IllegalArgumentException("Cannot replace adapter. Not found adapter for type " + target.getSimpleName());
    }

    private void log(int priority, Throwable throwable, String msg, Object... args) {
        Utils.checkNotNull(msg);
        log(priority, getTag(), createMessage(msg, args), throwable);
    }

    private String getTag() {
        String str = this.localTag.get();
        if (str == null) {
            return null;
        }
        this.localTag.remove();
        return str;
    }

    private String createMessage(String message, Object... args) {
        return (args == null || args.length == 0) ? message : String.format(message, args);
    }
}
