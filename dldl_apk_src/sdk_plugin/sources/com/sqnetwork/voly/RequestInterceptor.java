package com.sqnetwork.voly;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RequestInterceptor {

    public static class SimpleRequestInterceptor implements RequestInterceptor {
        @Override // com.sqnetwork.voly.RequestInterceptor
        public void interceptError(Request<?> request, VolleyError error) {
        }

        @Override // com.sqnetwork.voly.RequestInterceptor
        public Response<?> interceptResponse(Request<?> request, NetworkResponse networkResponse, Response<?> response) throws VolleyError {
            return response;
        }
    }

    void interceptError(Request<?> request, VolleyError error);

    Response<?> interceptResponse(Request<?> request, NetworkResponse networkResponse, Response<?> response) throws VolleyError;
}
