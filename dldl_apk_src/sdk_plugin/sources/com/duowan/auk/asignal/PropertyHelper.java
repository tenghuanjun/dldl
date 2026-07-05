package com.duowan.auk.asignal;

import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.signal.Slot;
import com.duowan.auk.util.L;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropertyHelper {
    public static void sOnConnect(Class<?> cls, Slot slot) {
        if (PropertySet.class.isAssignableFrom(cls)) {
            for (String str : slot.marks) {
                asyncInvoke(slot, str);
            }
        }
    }

    private static void asyncInvoke(final Slot slot, String str) {
        final Property property = Property.getMsListeningProperties().get(str);
        if (property == null) {
            L.error(Property.class, "can not listen no marks property: %s", str);
        } else {
            SignalCenter.getExecutor(slot.executorID).execute(new Runnable() { // from class: com.duowan.auk.asignal.PropertyHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    PropertyHelper.invoke(slot, property);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invoke(Slot slot, Property<?> property) {
        Object obj = slot.receiver;
        if (obj == null) {
            return;
        }
        try {
            slot.method.invoke(obj, new PropertySet(property.getDefaultValue(), property.get()));
        } catch (IllegalAccessException e) {
            L.error(Property.class, "first set property fail(IllegalAccessException): %s, %s", slot.marks, e.getCause());
        } catch (InvocationTargetException e2) {
            L.error(Property.class, "first set property fail(InvocationTargetException): %s, %s", slot.marks, e2.getCause());
        }
    }
}
