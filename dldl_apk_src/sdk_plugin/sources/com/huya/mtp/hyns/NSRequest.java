package com.huya.mtp.hyns;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class NSRequest {
    private byte[] mBody;
    private String mBodyContentType;
    private String mCgi;
    private Map<String, String> mHeaders;
    private int mMethod;
    private OnParamEncode mOnParamEncode;
    private Object mTag;
    private String mUrl;

    public interface OnParamEncode {
        byte[] onEncode();
    }

    public String getCgi() {
        return this.mCgi;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getBodyContentType() {
        return this.mBodyContentType;
    }

    public byte[] getBody() {
        byte[] bArr = this.mBody;
        if (bArr != null) {
            return bArr;
        }
        OnParamEncode onParamEncode = this.mOnParamEncode;
        if (onParamEncode == null) {
            return null;
        }
        byte[] bArrOnEncode = onParamEncode.onEncode();
        this.mBody = bArrOnEncode;
        return bArrOnEncode;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Object getTag() {
        return this.mTag;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String mBodyContentType;
        private String mCgi;
        private Map<String, String> mHeaders;
        private int mMethod;
        private OnParamEncode mOnParamEncode;
        private Object mTag;
        private String mUrl;

        private Builder() {
        }

        public Builder url(String str) {
            this.mUrl = str;
            return this;
        }

        public Builder cgi(String str) {
            this.mCgi = str;
            return this;
        }

        public Builder headers(Map<String, String> map) {
            this.mHeaders = map;
            return this;
        }

        public Builder contentType(String str) {
            this.mBodyContentType = str;
            return this;
        }

        public Builder paramEncode(OnParamEncode onParamEncode) {
            this.mOnParamEncode = onParamEncode;
            return this;
        }

        public Builder method(int i) {
            this.mMethod = i;
            return this;
        }

        public Builder tag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public NSRequest build() {
            NSRequest nSRequest = new NSRequest();
            nSRequest.mUrl = this.mUrl;
            nSRequest.mBodyContentType = this.mBodyContentType;
            nSRequest.mTag = this.mTag;
            nSRequest.mHeaders = this.mHeaders;
            nSRequest.mMethod = this.mMethod;
            nSRequest.mCgi = this.mCgi;
            nSRequest.mOnParamEncode = this.mOnParamEncode;
            return nSRequest;
        }
    }
}
