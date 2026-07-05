package com.huya.mtp.hyns.volley;

import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSHttpProtocol;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSRequest;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSResult;
import com.huya.mtp.hyns.NSTransporter;
import com.huya.mtp.hyns.volley.HttpUrlApi;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpUrlProtocol extends NSHttpProtocol {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE_GET = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private static final String PROTOCOL_CONTENT_TYPE_POST = String.format("application/x-www-form-urlencoded ; charset=%s", PROTOCOL_CHARSET);
    public static final String TAG = "NetService-HttpUrlProtocol";
    private static MtpVolleyTransporter mtpVolleyTransporter;

    @Override // com.huya.mtp.hyns.NSProtocol
    public boolean accept(Class<?> cls) {
        return true;
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSMethod getMethod(Class<T> cls, Object obj, Method method, Object[] objArr) {
        return new UrlMethod(cls, obj, method, objArr);
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        if (mtpVolleyTransporter == null) {
            mtpVolleyTransporter = new MtpVolleyTransporter();
        }
        return new NSFunction<T>(nSMethod, mtpVolleyTransporter) { // from class: com.huya.mtp.hyns.volley.HttpUrlProtocol.1
            @Override // com.huya.mtp.hyns.NSFunction, com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
            public Map<String, String> getHeaders() {
                return super.getHeaders();
            }
        };
    }

    public static class UrlMethod extends NSMethod {
        HttpUrlApi.HttpRequest httpRequest;
        private Map<String, String> mHeaderMap;
        private int mMethodType;
        private Map<String, String> mParamMap;
        private Object mReq;
        private Class mRspClz;
        private HttpUrlApi.REQUEST_RULE mRule;
        private String mUrl;
        private NSRequest nsRequest;

        public UrlMethod(Class<?> cls, Object obj, Method method, Object[] objArr) {
            super(cls, obj, method, objArr);
            this.mMethodType = 0;
            if (cls != HttpUrlApi.class || objArr == null || objArr.length < 2) {
                throw new RuntimeException("proxyCls != HttpUrlApi.class, please check your Protocol.");
            }
            if ("get".equals(method.getName())) {
                this.mMethodType = 0;
            } else {
                this.mMethodType = 1;
            }
            if ((objArr[0] instanceof HttpUrlApi.HttpRequest) && objArr[0] != null) {
                HttpUrlApi.HttpRequest httpRequest = (HttpUrlApi.HttpRequest) objArr[0];
                this.httpRequest = httpRequest;
                this.mUrl = httpRequest.url;
                this.mReq = this.httpRequest.req;
                this.mParamMap = this.httpRequest.params;
                this.mHeaderMap = this.httpRequest.header;
                this.mRule = this.httpRequest.rule;
                MTPApi.LOGGER.debug(HttpUrlProtocol.TAG, "mHttpRequest: %s", this.httpRequest.toString());
            }
            if (objArr[1] != null) {
                this.mRspClz = (Class) objArr[1];
            }
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public NSRequest readRequest() {
            NSRequest nSRequestBuild = NSRequest.builder().contentType(this.mMethodType == 0 ? HttpUrlProtocol.PROTOCOL_CONTENT_TYPE_GET : HttpUrlProtocol.PROTOCOL_CONTENT_TYPE_POST).url(this.mUrl).method(this.mMethodType).paramEncode(new NSRequest.OnParamEncode() { // from class: com.huya.mtp.hyns.volley.HttpUrlProtocol.UrlMethod.1
                @Override // com.huya.mtp.hyns.NSRequest.OnParamEncode
                public byte[] onEncode() {
                    if (UrlMethod.this.mRule != HttpUrlApi.REQUEST_RULE.Body) {
                        if (UrlMethod.this.mRule == HttpUrlApi.REQUEST_RULE.Jce_Body) {
                            if (UrlMethod.this.mReq instanceof JceStruct) {
                                UrlMethod urlMethod = UrlMethod.this;
                                return urlMethod.jceToByteArray((JceStruct) urlMethod.mReq);
                            }
                            throw new RuntimeException(String.format("%s, rule = HttpUrlApi.REQUEST_RULE.Jce_Body, but req not JceStruct", HttpUrlProtocol.TAG));
                        }
                        return UrlMethod.parseParams(UrlMethod.this.mParamMap, "PROTOCOL_CHARSET").getBytes();
                    }
                    UrlMethod urlMethod2 = UrlMethod.this;
                    return urlMethod2.toByteArray(urlMethod2.mReq);
                }
            }).headers(this.mHeaderMap).build();
            this.nsRequest = nSRequestBuild;
            return nSRequestBuild;
        }

        public byte[] jceToByteArray(JceStruct jceStruct) {
            JceOutputStream jceOutputStream = new JceOutputStream();
            jceStruct.writeTo(jceOutputStream);
            return jceOutputStream.toByteArray();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huya.mtp.hyns.NSMethod
        public NSResponse<?> readResponse(NSResult nSResult, DataListener dataListener) {
            return new NSResponse<>(parseJson(new String(((NetworkResponse) nSResult.mRsp).data), this.mRspClz), (NetworkResponse) nSResult.mRsp, ((NetworkResponse) nSResult.mRsp).statusCode, null);
        }

        public byte[] toByteArray(Object obj) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byteArray = null;
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                byteArray = byteArrayOutputStream.toByteArray();
                objectOutputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                e.printStackTrace();
                return byteArray;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String parseParams(Map<String, String> map, String str) {
            if (map != null && !map.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        sb.append(URLEncoder.encode(entry.getKey(), str));
                        sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                        sb.append(URLEncoder.encode(entry.getValue(), str));
                        sb.append("&");
                    }
                    return sb.substring(0, sb.length() - 1);
                } catch (UnsupportedEncodingException unused) {
                }
            }
            return "";
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public Object getIdentifier() {
            return this.mUrl;
        }

        protected <E> E parseJson(String str, Class<? extends E> cls) {
            try {
                return (E) JsonUtils.parseJsonWithThrowable(str, cls);
            } catch (Throwable th) {
                MTPApi.LOGGER.error(HttpUrlProtocol.TAG, th);
                return null;
            }
        }
    }
}
