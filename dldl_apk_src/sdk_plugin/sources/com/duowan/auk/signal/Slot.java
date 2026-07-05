package com.duowan.auk.signal;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Slot<T> {
    public int executorID;
    public String[] marks;
    public Method method;
    public T receiver;
    public SlotType type;

    public Slot(String[] strArr, SlotType slotType, int i, T t, Method method) {
        this.marks = strArr;
        this.type = slotType;
        this.executorID = i;
        this.receiver = t;
        method.setAccessible(true);
        this.method = method;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.method != null) {
            sb.append("signal:");
            sb.append(this.method.getParameterTypes()[0].getName());
        }
        if (this.receiver != null) {
            sb.append(" receiver:");
            sb.append(this.receiver.getClass().getName());
        }
        if (this.method != null) {
            sb.append(" methodName:");
            sb.append(this.method.getName());
        }
        sb.append("}");
        return sb.toString();
    }
}
