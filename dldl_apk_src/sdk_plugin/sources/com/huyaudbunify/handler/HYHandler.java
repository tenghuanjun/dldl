package com.huyaudbunify.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HYHandler extends Handler {
    private volatile SparseArray<Method> mHandlerMap;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MessageHandler {
        int message();
    }

    public HYHandler(Looper looper) {
        super(looper);
        this.mHandlerMap = new SparseArray<>();
        init();
    }

    private void init() {
        for (Method method : getClass().getDeclaredMethods()) {
            MessageHandler messageHandler = (MessageHandler) method.getAnnotation(MessageHandler.class);
            if (messageHandler != null) {
                this.mHandlerMap.put(messageHandler.message(), method);
            }
        }
    }

    private Method getMessageHandler(int i) {
        return this.mHandlerMap.get(i);
    }

    public boolean canHandleMessage(int i) {
        return getMessageHandler(i) != null;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            Method messageHandler = getMessageHandler(message.what);
            Object[] objArr = (Object[]) message.obj;
            if (objArr != null) {
                messageHandler.invoke(this, objArr);
            } else {
                messageHandler.invoke(this, (Object[]) null);
            }
        } catch (Exception unused) {
        }
    }

    private String getLog(Message message, Object[] objArr, Method method) {
        try {
            StringBuilder sb = new StringBuilder("handle msg ");
            sb.append(message.what);
            sb.append(" error, params = [");
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(objArr[i]);
                }
            }
            sb.append("], ");
            sb.append("handler = ");
            sb.append(method);
            return sb.toString();
        } catch (Exception unused) {
            return "generate error log failed";
        }
    }
}
