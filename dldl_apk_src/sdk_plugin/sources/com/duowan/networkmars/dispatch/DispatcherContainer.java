package com.duowan.networkmars.dispatch;

import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DispatcherContainer<Dispatcher, T> {
    private static final String TAG = "DispatcherContainer";
    private Map<T, List<Dispatcher>> dispatchersByEvent = new HashMap();

    public synchronized void subscribe(Dispatcher dispatcher, T t) {
        if (isSubscribe(dispatcher, t)) {
            L.info(TAG, "subscribe key has subscribed!!");
            return;
        }
        List<Dispatcher> arrayList = this.dispatchersByEvent.get(t);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.dispatchersByEvent.put(t, arrayList);
        }
        arrayList.add(dispatcher);
    }

    public boolean isSubscribe(Dispatcher dispatcher, T t) {
        List<Dispatcher> list = this.dispatchersByEvent.get(t);
        if (list == null) {
            return false;
        }
        Iterator<Dispatcher> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == dispatcher) {
                return true;
            }
        }
        return false;
    }

    public synchronized void unSubscribe(Dispatcher dispatcher, T t) {
        if (this.dispatchersByEvent.keySet().contains(t)) {
            List<Dispatcher> list = this.dispatchersByEvent.get(t);
            if (list == null) {
                this.dispatchersByEvent.remove(t);
                return;
            }
            Iterator<Dispatcher> it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == dispatcher) {
                    it.remove();
                }
            }
            if (list.size() == 0) {
                this.dispatchersByEvent.remove(t);
            }
        }
    }

    public List<Dispatcher> getDispatchers(T t) {
        Map<T, List<Dispatcher>> map = this.dispatchersByEvent;
        if (map == null) {
            return null;
        }
        return map.get(t);
    }
}
