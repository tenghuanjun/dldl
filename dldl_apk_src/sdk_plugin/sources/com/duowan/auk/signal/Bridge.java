package com.duowan.auk.signal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Bridge {
    static final int DefaultExecutorID = -1;
    public static final String InvalidSlotMark = "";
    private Executor mBridgeExecutor;
    private final List<OnConnectListener> mConnectListeners;
    private final Map<Class<?>, List<Slot<?>>> mConnectMap;
    private final List<OnExceptionListener> mExceptionListeners;
    private Map<Integer, Executor> mExecutorMap;

    public interface OnConnectListener {
        void onConnect(Class<?> cls, Slot slot);
    }

    public interface OnExceptionListener {
        void onInvokeSlotFail(Throwable th, Slot slot);

        void onNullExecutor(Slot slot);

        void onNullReceiver(Slot slot);
    }

    public static class Builder {
        private Executor mBridgeExecutor;
        private final Map<Integer, Executor> mExecutorMap = new HashMap();

        public Builder bridgeExecutor(Executor executor) {
            this.mBridgeExecutor = executor;
            return this;
        }

        public Builder putExecutor(int i, Executor executor) {
            if (i == -1) {
                throw new IllegalArgumentException(String.format(Locale.CHINA, "executorID can not be %d", -1));
            }
            this.mExecutorMap.put(Integer.valueOf(i), executor);
            return this;
        }

        public Bridge build() {
            if (this.mBridgeExecutor == null) {
                this.mBridgeExecutor = new SyncExecutor();
            }
            return new Bridge(this.mBridgeExecutor, this.mExecutorMap);
        }
    }

    private Bridge(Executor executor, Map<Integer, Executor> map) {
        this.mConnectListeners = new CopyOnWriteArrayList();
        this.mExceptionListeners = new ArrayList();
        this.mConnectMap = new HashMap();
        this.mBridgeExecutor = executor;
        HashMap map2 = new HashMap(map);
        this.mExecutorMap = map2;
        map2.put(-1, new SyncExecutor());
    }

    public <T> void register(final T t) {
        this.mBridgeExecutor.execute(new Runnable() { // from class: com.duowan.auk.signal.Bridge.1
            @Override // java.lang.Runnable
            public void run() {
                Bridge.this.pRegister(t, null);
            }
        });
    }

    public <T> void unregister(final T t) {
        this.mBridgeExecutor.execute(new Runnable() { // from class: com.duowan.auk.signal.Bridge.2
            @Override // java.lang.Runnable
            public void run() {
                Bridge.this.pUnregister(t);
            }
        });
    }

    public <T> void send(T t) {
        send((Object) t, SignalType.Normal);
    }

    public <T> void send(T t, Object obj) {
        send(t, SignalType.Normal, "", obj);
    }

    public <T> void send(T t, SignalType signalType) {
        send(t, signalType, "", null);
    }

    public <T> void send(T t, SignalType signalType, String str) {
        send(t, signalType, str, null);
    }

    public <T> void send(final T t, final SignalType signalType, final String str, final Object obj) {
        this.mBridgeExecutor.execute(new Runnable() { // from class: com.duowan.auk.signal.Bridge.3
            @Override // java.lang.Runnable
            public void run() {
                Bridge.this.pSend(t, signalType, str, obj);
            }
        });
    }

    public void addOnConnectListener(OnConnectListener onConnectListener) {
        this.mConnectListeners.add(onConnectListener);
    }

    public void removeOnConnectListener(OnConnectListener onConnectListener) {
        this.mConnectListeners.remove(onConnectListener);
    }

    public void addOnExceptionListener(OnExceptionListener onExceptionListener) {
        this.mExceptionListeners.add(onExceptionListener);
    }

    public void removeOnExceptionListener(OnExceptionListener onExceptionListener) {
        this.mExceptionListeners.remove(onExceptionListener);
    }

    public Executor getExecutor(int i) {
        return this.mExecutorMap.get(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void pRegister(T t, IASlot iASlot) {
        Class<?> cls = t.getClass();
        IASlot iASlot2 = (IASlot) cls.getAnnotation(IASlot.class);
        for (Method method : cls.getMethods()) {
            IASlot iASlot3 = (IASlot) method.getAnnotation(IASlot.class);
            if (iASlot3 != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    connect(parameterTypes[0], new Slot(getSlotMark(iASlot, iASlot3, iASlot2), getSlotType(iASlot, iASlot3, iASlot2), getExecutorID(iASlot, iASlot3, iASlot2), t, method));
                }
            }
        }
    }

    private String[] getSlotMark(IASlot... iASlotArr) {
        for (IASlot iASlot : iASlotArr) {
            if (iASlot != null) {
                String[] strArrMark = iASlot.mark();
                if (!strArrMark[0].equals("")) {
                    return strArrMark;
                }
            }
        }
        return new String[]{""};
    }

    private SlotType getSlotType(IASlot... iASlotArr) {
        SlotType slotTypeType;
        for (IASlot iASlot : iASlotArr) {
            if (iASlot != null && (slotTypeType = iASlot.type()) != SlotType.Invalid) {
                return slotTypeType;
            }
        }
        return SlotType.Normal;
    }

    private int getExecutorID(IASlot... iASlotArr) {
        int iExecutorID;
        for (IASlot iASlot : iASlotArr) {
            if (iASlot != null && (iExecutorID = iASlot.executorID()) != -1) {
                return iExecutorID;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void pUnregister(T t) {
        Iterator<List<Slot<?>>> it = this.mConnectMap.values().iterator();
        while (it.hasNext()) {
            Iterator<Slot<?>> it2 = it.next().iterator();
            while (it2.hasNext()) {
                if (it2.next().receiver == t) {
                    it2.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void pSend(T t, SignalType signalType, String str, Object obj) {
        List<Slot<?>> list = this.mConnectMap.get(t.getClass());
        if (list == null) {
            return;
        }
        Iterator<Slot<?>> it = list.iterator();
        while (it.hasNext()) {
            Slot<?> next = it.next();
            if (obj == null || next.receiver == obj) {
                if (contain(next.marks, str)) {
                    if (!checkAndInvoke(next, t) || next.type == SlotType.Once) {
                        it.remove();
                    }
                    if (signalType == SignalType.Once) {
                        return;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private <T> boolean checkAndInvoke(final Slot<?> slot, final T t) {
        if (slot.receiver == null) {
            nullReceiver(slot);
            return false;
        }
        Executor executor = this.mExecutorMap.get(Integer.valueOf(slot.executorID));
        if (executor == null) {
            Iterator<OnExceptionListener> it = this.mExceptionListeners.iterator();
            while (it.hasNext()) {
                it.next().onNullExecutor(slot);
            }
            return false;
        }
        executor.execute(new Runnable() { // from class: com.duowan.auk.signal.Bridge.4
            @Override // java.lang.Runnable
            public void run() {
                Bridge.this.invoke(slot, t);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void invoke(Slot<?> slot, T t) {
        T t2 = slot.receiver;
        if (t2 == null) {
            nullReceiver(slot);
            return;
        }
        try {
            slot.method.invoke(t2, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Iterator<OnExceptionListener> it = this.mExceptionListeners.iterator();
            while (it.hasNext()) {
                it.next().onInvokeSlotFail(e, slot);
            }
        }
    }

    private void connect(Class<?> cls, Slot slot) {
        List<Slot<?>> arrayList = this.mConnectMap.get(cls);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mConnectMap.put(cls, arrayList);
        }
        arrayList.add(slot);
        Iterator<OnConnectListener> it = this.mConnectListeners.iterator();
        while (it.hasNext()) {
            it.next().onConnect(cls, slot);
        }
    }

    private void nullReceiver(Slot slot) {
        Iterator<OnExceptionListener> it = this.mExceptionListeners.iterator();
        while (it.hasNext()) {
            it.next().onNullReceiver(slot);
        }
    }

    private boolean contain(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
