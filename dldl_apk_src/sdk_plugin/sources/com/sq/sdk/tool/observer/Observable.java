package com.sq.sdk.tool.observer;

import com.sq.sdk.tool.app.SqThreadHelper;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Observable<S, F, C> implements Runnable {
    private static ExecutorService sExecutor = Executors.newFixedThreadPool(3);
    private final ArrayList<Observer<S, F, C>> mObservers = new ArrayList<>();
    private ThreadModel mObserverThread = ThreadModel.CURRENT_THREAD;

    public Observable<S, F, C> registerObserver(Observer<S, F, C> observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null. ");
        }
        synchronized (this.mObservers) {
            if (this.mObservers.contains(observer)) {
                throw new IllegalStateException("The observer " + observer + "is already registered. ");
            }
            this.mObservers.add(observer);
        }
        return this;
    }

    public Observable<S, F, C> unregisterObserver(Observer<S, F, C> observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null. ");
        }
        synchronized (this.mObservers) {
            int iIndexOf = this.mObservers.indexOf(observer);
            if (iIndexOf == -1) {
                throw new IllegalStateException("The observer " + observer + " was not registered. ");
            }
            this.mObservers.remove(iIndexOf);
        }
        return this;
    }

    public Observable<S, F, C> unregisterAll() {
        synchronized (this.mObservers) {
            this.mObservers.clear();
        }
        return this;
    }

    public Observable<S, F, C> observerOn(ThreadModel threadModel) {
        this.mObserverThread = threadModel;
        return this;
    }

    public void execute() {
        sExecutor.execute(this);
    }

    public void cancel() {
        if (this.mObservers.isEmpty()) {
            return;
        }
        this.mObservers.clear();
    }

    protected void handleSuccess(S s) {
        handleObserver(new ObserverHandler(s, 1));
    }

    protected void handleFail(F f) {
        handleObserver(new ObserverHandler(f, 2));
    }

    protected void handleComplete(C c) {
        handleObserver(new ObserverHandler(c, 3));
    }

    /* JADX INFO: renamed from: com.sq.sdk.tool.observer.Observable$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sq$sdk$tool$observer$ThreadModel;

        static {
            int[] iArr = new int[ThreadModel.values().length];
            $SwitchMap$com$sq$sdk$tool$observer$ThreadModel = iArr;
            try {
                iArr[ThreadModel.MAIN_THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void handleObserver(ObserverHandler observerHandler) {
        if (AnonymousClass1.$SwitchMap$com$sq$sdk$tool$observer$ThreadModel[this.mObserverThread.ordinal()] == 1) {
            SqThreadHelper.postRunInUiThread(observerHandler);
        } else {
            observerHandler.run();
        }
    }

    private class ObserverHandler<V> implements Runnable {
        private static final int TYPE_COMPLETE = 3;
        private static final int TYPE_FAIL = 2;
        private static final int TYPE_SUCCESS = 1;
        private V data;
        private int type;

        ObserverHandler(V v, int i) {
            this.data = v;
            this.type = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (Observer observer : Observable.this.mObservers) {
                int i = this.type;
                if (i == 1) {
                    observer.onSuccess(this.data);
                } else if (i == 2) {
                    observer.onFail(this.data);
                } else if (i == 3) {
                    observer.onComplete(this.data);
                }
            }
        }
    }
}
