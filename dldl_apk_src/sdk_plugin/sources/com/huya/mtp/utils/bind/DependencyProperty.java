package com.huya.mtp.utils.bind;

import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DependencyProperty<T> {
    private final T mDefaultValue;
    private final Entity<T> mEntity;
    private final Set<Observer<T>> mHandlers;
    private volatile T mValue;

    public interface Entity<T> {
        void bind(Observer<T> observer);

        T get();

        boolean isDefault();

        void subscribe(Observer<T> observer);

        void unbind(Observer<T> observer);

        void unsubscribe(Observer<T> observer);
    }

    public DependencyProperty() {
        this(null);
    }

    public DependencyProperty(T t) {
        this.mHandlers = new HashSet();
        this.mDefaultValue = t;
        this.mValue = t;
        this.mEntity = new Entity<T>() { // from class: com.huya.mtp.utils.bind.DependencyProperty.1
            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public T get() {
                return (T) DependencyProperty.this.get();
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public boolean isDefault() {
                return DependencyProperty.this.isDefault();
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public void bind(Observer<T> observer) {
                DependencyProperty.this.bind(observer);
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public void unbind(Observer<T> observer) {
                DependencyProperty.this.unbind(observer);
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public void subscribe(Observer<T> observer) {
                DependencyProperty.this.subscribe(observer);
            }

            @Override // com.huya.mtp.utils.bind.DependencyProperty.Entity
            public void unsubscribe(Observer<T> observer) {
                DependencyProperty.this.unsubscribe(observer);
            }
        };
    }

    public void set(T t) {
        boolean zNeedNotify = needNotify(t);
        this.mValue = t;
        if (zNeedNotify) {
            notifyPropChange(t);
        }
    }

    public Entity<T> getEntity() {
        return this.mEntity;
    }

    public void reset() {
        set(this.mDefaultValue);
    }

    public void reNotify() {
        notifyPropChange(this.mValue);
    }

    public T get() {
        return this.mValue;
    }

    public boolean isDefault() {
        T t = this.mValue;
        return t == null ? this.mDefaultValue == null : t.equals(this.mDefaultValue);
    }

    public String toString() {
        return String.format("Dp@%s[%s]", Integer.toHexString(hashCode()), String.valueOf(this.mValue));
    }

    protected boolean needNotify(T t) {
        return !Objects.equals(t, this.mValue);
    }

    private void notifyPropChange(T t) {
        onPropChange(getCopyHandlers(), t);
    }

    private Observer<T>[] getCopyHandlers() {
        Observer<T>[] observerArr;
        synchronized (this.mHandlers) {
            observerArr = new Observer[this.mHandlers.size()];
            this.mHandlers.toArray(observerArr);
        }
        return observerArr;
    }

    private void onPropChange(Observer<T>[] observerArr, T t) {
        for (Observer<T> observer : observerArr) {
            observer.onDeliverPropChange(t);
        }
    }

    public void bind(Observer<T> observer) {
        observer.onDeliverPropChange(get());
        subscribe(observer);
    }

    public void unbind(Observer<T> observer) {
        unsubscribe(observer);
    }

    public void subscribe(Observer<T> observer) {
        synchronized (this.mHandlers) {
            this.mHandlers.add(observer);
        }
    }

    public void unsubscribe(Observer<T> observer) {
        synchronized (this.mHandlers) {
            this.mHandlers.remove(observer);
        }
    }

    public boolean hasObservers() {
        boolean z;
        synchronized (this.mHandlers) {
            z = this.mHandlers.size() > 0;
        }
        return z;
    }

    public Observer<T>[] getObservers() {
        return getCopyHandlers();
    }

    public static abstract class Observer<T> {
        private T mCache;
        private Handler mHandler;
        private boolean mHasCache;
        private boolean mPaused;

        public abstract void onPropChange(T t);

        public Looper getDeliverLooper() {
            return Looper.getMainLooper();
        }

        private Handler ensureDeliverHandler() {
            if (this.mHandler == null) {
                this.mHandler = new Handler(getDeliverLooper());
            }
            return this.mHandler;
        }

        public synchronized void pause() {
            if (this.mPaused) {
                return;
            }
            this.mPaused = true;
        }

        public boolean isPaused() {
            return this.mPaused;
        }

        public synchronized void resume() {
            if (this.mPaused) {
                this.mPaused = false;
                if (this.mHasCache) {
                    onDeliverPropChange(this.mCache);
                    this.mCache = null;
                    this.mHasCache = false;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDeliverPropChange(final T t) {
            synchronized (this) {
                if (this.mPaused) {
                    this.mCache = t;
                    this.mHasCache = true;
                } else if (getDeliverLooper() == null) {
                    onPropChange(t);
                } else {
                    ensureDeliverHandler().post(new Runnable() { // from class: com.huya.mtp.utils.bind.DependencyProperty.Observer.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            Observer.this.onPropChange(t);
                        }
                    });
                }
            }
        }
    }
}
