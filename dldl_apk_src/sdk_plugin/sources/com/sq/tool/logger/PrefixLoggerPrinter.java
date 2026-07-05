package com.sq.tool.logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class PrefixLoggerPrinter implements Printer {
    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private final String prefix;
    private final Printer printer;

    public PrefixLoggerPrinter(String prefix, Printer printer) {
        this.prefix = (String) Utils.checkNotNull(prefix);
        this.printer = (Printer) Utils.checkNotNull(printer);
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
        throw new UnsupportedOperationException("m() is not supported in PrefixLoggerPrinter");
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
        this.printer.json(json);
    }

    @Override // com.sq.tool.logger.Printer
    public void xml(String xml) {
        this.printer.xml(xml);
    }

    @Override // com.sq.tool.logger.Printer
    public synchronized void log(int priority, String tag, String message, Throwable throwable) {
        if (this.prefix != null && !this.prefix.isEmpty()) {
            if (message != null) {
                message = this.prefix + message;
            } else {
                message = this.prefix;
            }
        }
        this.printer.log(priority, tag, message, throwable);
    }

    @Override // com.sq.tool.logger.Printer
    public void clearLogAdapters() {
        this.printer.clearLogAdapters();
    }

    @Override // com.sq.tool.logger.Printer
    public void addAdapter(LogAdapter adapter) {
        this.printer.addAdapter((LogAdapter) Utils.checkNotNull(adapter));
    }

    @Override // com.sq.tool.logger.Printer
    public void replaceAdapter(LogAdapter adapter, Class<? extends LogAdapter> target) {
        this.printer.replaceAdapter(adapter, target);
    }

    private synchronized void log(int priority, Throwable throwable, String msg, Object... args) {
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
