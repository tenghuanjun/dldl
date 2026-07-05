package com.huya.berry.gamesdk.certicate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CertificateCallback {

    public static class GetCertificateParams {
        public int result;

        public GetCertificateParams(int i) {
            this.result = i;
        }
    }

    public static class CertificateFinish {
        public String message;
        public int resultCode;

        public CertificateFinish(int i, String str) {
            this.resultCode = i;
            this.message = str;
        }
    }
}
