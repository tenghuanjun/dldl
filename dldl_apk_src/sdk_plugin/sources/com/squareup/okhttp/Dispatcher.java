package com.squareup.okhttp;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Dispatcher {
    private ExecutorService executorService;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<Call.AsyncCall> readyCalls = new ArrayDeque();
    private final Deque<Call.AsyncCall> runningCalls = new ArrayDeque();

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Dispatcher() {
    }

    public synchronized ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    public synchronized void setMaxRequests(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.maxRequests = i;
        promoteCalls();
    }

    public synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    public synchronized void setMaxRequestsPerHost(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.maxRequestsPerHost = i;
        promoteCalls();
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    synchronized void enqueue(Call.AsyncCall asyncCall) {
        if (this.runningCalls.size() < this.maxRequests && runningCallsForHost(asyncCall) < this.maxRequestsPerHost) {
            this.runningCalls.add(asyncCall);
            getExecutorService().execute(asyncCall);
        } else {
            this.readyCalls.add(asyncCall);
        }
    }

    public synchronized void cancel(Object obj) {
        Iterator<Call.AsyncCall> it = this.readyCalls.iterator();
        while (it.hasNext()) {
            if (Util.equal(obj, it.next().tag())) {
                it.remove();
            }
        }
        for (Call.AsyncCall asyncCall : this.runningCalls) {
            if (Util.equal(obj, asyncCall.tag())) {
                asyncCall.get().canceled = true;
                HttpEngine httpEngine = asyncCall.get().engine;
                if (httpEngine != null) {
                    httpEngine.disconnect();
                }
            }
        }
    }

    synchronized void finished(Call.AsyncCall asyncCall) {
        if (!this.runningCalls.remove(asyncCall)) {
            throw new AssertionError("AsyncCall wasn't running!");
        }
        promoteCalls();
    }

    private void promoteCalls() {
        if (this.runningCalls.size() < this.maxRequests && !this.readyCalls.isEmpty()) {
            Iterator<Call.AsyncCall> it = this.readyCalls.iterator();
            while (it.hasNext()) {
                Call.AsyncCall next = it.next();
                if (runningCallsForHost(next) < this.maxRequestsPerHost) {
                    it.remove();
                    this.runningCalls.add(next);
                    getExecutorService().execute(next);
                }
                if (this.runningCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(Call.AsyncCall asyncCall) {
        Iterator<Call.AsyncCall> it = this.runningCalls.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().host().equals(asyncCall.host())) {
                i++;
            }
        }
        return i;
    }
}
