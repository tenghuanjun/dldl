package com.sqnetwork.voly;

import com.sqnetwork.voly.Cache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Response<T> {
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    private NetworkResponse networkResponse;
    public final T result;

    public interface ErrorListener {
        void onErrorResponse(VolleyError error);
    }

    public interface Listener<T> {
        void onResponse(Response<T> raw, T response);
    }

    public static <T> Response<T> success(T result, Cache.Entry cacheEntry) {
        return new Response<>(result, cacheEntry);
    }

    public static <T> Response<T> error(VolleyError error) {
        Response<T> response = new Response<>(error);
        response.setNetworkResponse(error.networkResponse);
        return response;
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    private Response(T result, Cache.Entry cacheEntry) {
        this.intermediate = false;
        this.result = result;
        this.cacheEntry = cacheEntry;
        this.error = null;
    }

    private Response(VolleyError error) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = error;
    }

    public NetworkResponse getNetworkResponse() {
        return this.networkResponse;
    }

    public void setNetworkResponse(NetworkResponse networkResponse) {
        this.networkResponse = networkResponse;
    }
}
