package com.huya.mtp.utils.bind;

import android.os.Looper;
import android.util.Pair;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.bind.DependencyProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BindingManager {
    private static final String TAG = "BindingManager";
    private Map<Pair<?, DependencyProperty.Entity>, DependencyProperty.Observer> mBindings = new ConcurrentHashMap();

    public <T, O> void bind(final T t, DependencyProperty.Entity<O> entity, final ViewBinder<? super T, ? super O> viewBinder) {
        bind(new Pair<>(t, entity), new DependencyProperty.Observer<O>() { // from class: com.huya.mtp.utils.bind.BindingManager.1
            @Override // com.huya.mtp.utils.bind.DependencyProperty.Observer
            public void onPropChange(O o) {
                viewBinder.bindView(t, o);
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Observer
            public Looper getDeliverLooper() {
                return viewBinder.getDeliverLooper();
            }
        });
    }

    public <T, O> void bind(T t, DependencyProperty.Entity<O> entity, DependencyProperty.Observer<? super O> observer) {
        bind(new Pair<>(t, entity), observer);
    }

    public <O> DependencyProperty.Observer<O> getBindingObserver(Object obj, DependencyProperty.Entity<O> entity) {
        return getBindingObserver(new Pair<>(obj, entity));
    }

    private <O> DependencyProperty.Observer<O> getBindingObserver(Pair<?, DependencyProperty.Entity> pair) {
        return this.mBindings.get(pair);
    }

    private <T, O> void bind(Pair<T, DependencyProperty.Entity> pair, DependencyProperty.Observer<O> observer) {
        DependencyProperty.Observer<T> observer2 = this.mBindings.get(pair);
        DependencyProperty.Entity entity = (DependencyProperty.Entity) pair.second;
        if (observer2 != null) {
            MTPApi.LOGGER.fatal(TAG, "[bind]%s has already bound to %s, unbind first", pair, observer);
            entity.unbind(observer2);
        }
        this.mBindings.put(pair, observer);
        entity.bind(observer);
    }

    public <T, O> DependencyProperty.Observer<O> unbind(T t, DependencyProperty.Entity<O> entity) {
        return unbind(new Pair<>(t, entity));
    }

    private <O> DependencyProperty.Observer<O> unbind(Pair<?, DependencyProperty.Entity> pair) {
        DependencyProperty.Observer<O> observerRemove = this.mBindings.remove(pair);
        if (observerRemove != null) {
            ((DependencyProperty.Entity) pair.second).unbind(observerRemove);
        } else {
            MTPApi.LOGGER.fatal(TAG, "[unbind]cannot find observer bound to the key:%s", pair);
        }
        return observerRemove;
    }

    public Collection<DependencyProperty.Observer> unbindAll() {
        ArrayList arrayList = new ArrayList(this.mBindings.values());
        Iterator<Map.Entry<Pair<?, DependencyProperty.Entity>, DependencyProperty.Observer>> it = this.mBindings.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Pair<?, DependencyProperty.Entity>, DependencyProperty.Observer> next = it.next();
            ((DependencyProperty.Entity) next.getKey().second).unbind(next.getValue());
            it.remove();
        }
        return arrayList;
    }
}
