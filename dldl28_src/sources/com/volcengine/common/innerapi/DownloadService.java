package com.volcengine.common.innerapi;

/* JADX INFO: loaded from: classes3.dex */
public interface DownloadService {

    public interface Callback {
        void onFailure(Response response, int i, String str);

        void onProgress(Response response, int i);

        void onSuccess(Response response);

        void onTick(Response response, String str);
    }

    public static class Response {
        private final String fileName;
        private final String md5;
        private final String savePath;
        private final String url;

        public Response(String str, String str2, String str3, String str4) {
            this.url = str;
            this.fileName = str2;
            this.savePath = str3;
            this.md5 = str4;
        }

        public String fileName() {
            return this.fileName;
        }

        public String md5() {
            return this.md5;
        }

        public String savePath() {
            return this.savePath;
        }

        public String url() {
            return this.url;
        }
    }

    void cancel(String str, String str2, String str3);

    void cancelAll();

    void downloadFile(String str, String str2, String str3, String str4, Callback callback);
}
