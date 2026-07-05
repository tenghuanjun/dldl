package com.huya.mtp.hyns;

import android.os.Looper;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.NSCallAdapter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class NSHttpProtocol extends NSProtocol {
    private NSTransporter mTransporter;
    private List<NSCallAdapter.Factory> mCallAdapterFactories = new ArrayList<NSCallAdapter.Factory>() { // from class: com.huya.mtp.hyns.NSHttpProtocol.1
        {
            add(new NSDefaultCallAdapterFactory());
        }
    };
    private final Map<Method, NSCallAdapter> mAdapterCache = new ConcurrentHashMap();

    public abstract <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter);

    public abstract <T> NSMethod getMethod(Class<T> cls, Object obj, Method method, Object[] objArr);

    public void addCallAdapterFactory(NSCallAdapter.Factory factory) {
        this.mCallAdapterFactories.add(factory);
        if (factory != null) {
            MTPApi.LOGGER.info(NSConstants.NSTAG, String.format("addCallAdapterFactory: %s", factory.getClass().toString()));
        }
    }

    public void setTransporter(NSTransporter nSTransporter) {
        this.mTransporter = nSTransporter;
        if (nSTransporter != null) {
            MTPApi.LOGGER.info(NSConstants.NSTAG, String.format("setTransporter: %s", nSTransporter.getClass().toString()));
        }
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public <T> T get(final Class<T> cls) {
        if (cls != null) {
            return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.huya.mtp.hyns.NSHttpProtocol.2
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    NSMethod method2 = NSHttpProtocol.this.getMethod(cls, obj, method, objArr);
                    if (NSHttpProtocol.this.mTransporter == null && NS.getLazyTransporter() != null) {
                        NSHttpProtocol.this.mTransporter = NS.getLazyTransporter().getTransporter(NSHttpProtocol.this);
                    }
                    return NSHttpProtocol.this.getCallAdapter(method).adapt(new NSCallImpl(method2, NSHttpProtocol.this.mTransporter, NSHttpProtocol.this));
                }
            });
        }
        throw new NullPointerException("class is null when get delegate");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NSCallAdapter getCallAdapter(Method method) {
        NSCallAdapter adapter;
        NSCallAdapter nSCallAdapter = this.mAdapterCache.get(method);
        if (nSCallAdapter != null) {
            return nSCallAdapter;
        }
        synchronized (this.mAdapterCache) {
            adapter = this.mAdapterCache.get(method);
            if (adapter == null) {
                Iterator<NSCallAdapter.Factory> it = this.mCallAdapterFactories.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    adapter = it.next().getAdapter(method);
                    if (adapter != null) {
                        this.mAdapterCache.put(method, adapter);
                        break;
                    }
                }
            }
        }
        if (adapter == null) {
            StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
            sb.append(method);
            sb.append(".\n");
            sb.append("  Tried:");
            for (NSCallAdapter.Factory factory : this.mCallAdapterFactories) {
                sb.append("\n   * ");
                sb.append(factory.getClass().getName());
            }
        }
        return adapter;
    }

    private static class NSCallImpl<T> implements NSCall<T> {
        private ConcurrentLinkedQueue<NSFunction<T>> mHttpFunctionList = new ConcurrentLinkedQueue<>();
        private NSHttpProtocol mHttpProtocol;
        private NSTransporter mHttpTransporter;
        private NSMethod mMethod;

        public NSCallImpl(NSMethod nSMethod, NSTransporter nSTransporter, NSHttpProtocol nSHttpProtocol) {
            this.mMethod = nSMethod;
            this.mHttpTransporter = nSTransporter;
            this.mHttpProtocol = nSHttpProtocol;
        }

        @Override // com.huya.mtp.hyns.NSCall
        public NSResponse<T> execute() throws NSException {
            return execute(NSSettings.DEFAULT_SETTINGS);
        }

        @Override // com.huya.mtp.hyns.NSCall
        public NSResponse<T> execute(NSSettings nSSettings) throws NSException {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                MTPApi.DEBUGGER.crashIfDebug("Cannot call sync execute method in main thread!", new Object[0]);
            }
            return new AsyncToSync<NSSettings, NSResponse<T>, NSException>() { // from class: com.huya.mtp.hyns.NSHttpProtocol.NSCallImpl.1
                @Override // com.huya.mtp.hyns.AsyncToSync
                public void executeAsync(NSSettings nSSettings2) {
                    NSCallImpl.this.enqueue(new NSCallback<T>() { // from class: com.huya.mtp.hyns.NSHttpProtocol.NSCallImpl.1.1
                        @Override // com.huya.mtp.hyns.NSCallback
                        public void onResponse(NSResponse<T> nSResponse) {
                            onTaskSucceed(nSResponse);
                        }

                        @Override // com.huya.mtp.hyns.NSCallback
                        public void onError(NSException nSException) {
                            onTaskFailed(nSException);
                        }

                        @Override // com.huya.mtp.hyns.NSCallback
                        public void onCancelled() {
                            onTaskCancelled();
                        }
                    }, nSSettings2);
                }
            }.execute(nSSettings);
        }

        @Override // com.huya.mtp.hyns.NSCall
        public void enqueue(final NSCallback<T> nSCallback, NSSettings nSSettings) {
            final NSFunction<T> function = this.mHttpProtocol.getFunction(this.mMethod, this.mHttpTransporter);
            this.mHttpFunctionList.add(function);
            function.setCallback(new NSCallback<T>() { // from class: com.huya.mtp.hyns.NSHttpProtocol.NSCallImpl.2
                @Override // com.huya.mtp.hyns.NSCallback
                public void onResponse(NSResponse<T> nSResponse) {
                    NSCallImpl.this.mHttpFunctionList.remove(function);
                    NSCallback nSCallback2 = nSCallback;
                    if (nSCallback2 != null) {
                        nSCallback2.onResponse(nSResponse);
                    }
                }

                @Override // com.huya.mtp.hyns.NSCallback
                public void onError(NSException nSException) {
                    NSCallImpl.this.mHttpFunctionList.remove(function);
                    NSCallback nSCallback2 = nSCallback;
                    if (nSCallback2 != null) {
                        nSCallback2.onError(nSException);
                    }
                }

                @Override // com.huya.mtp.hyns.NSCallback
                public void onCancelled() {
                    NSCallImpl.this.mHttpFunctionList.remove(function);
                    NSCallback nSCallback2 = nSCallback;
                    if (nSCallback2 != null) {
                        nSCallback2.onCancelled();
                    }
                }
            });
            function.setSettings(nSSettings);
            function.execute();
        }

        @Override // com.huya.mtp.hyns.NSCall
        public void enqueue(NSCallback<T> nSCallback) {
            enqueue(nSCallback, NSSettings.DEFAULT_SETTINGS);
        }

        @Override // com.huya.mtp.hyns.NSCall
        public void enqueue() {
            enqueue(null, NSSettings.DEFAULT_SETTINGS);
        }

        @Override // com.huya.mtp.hyns.NSCall
        public void cancel() {
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue(this.mHttpFunctionList);
            this.mHttpFunctionList.clear();
            Iterator it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                ((NSFunction) it.next()).cancel();
            }
            concurrentLinkedQueue.clear();
        }

        @Override // com.huya.mtp.hyns.NSCall
        public boolean isCanceled() {
            return this.mHttpFunctionList.isEmpty();
        }

        @Override // com.huya.mtp.hyns.NSCall
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public NSCall<T> m18clone() {
            return new NSCallImpl(this.mMethod, this.mHttpTransporter, this.mHttpProtocol);
        }
    }
}
