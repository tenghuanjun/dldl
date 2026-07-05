package com.huya.mtp.hyns.wup;

import com.duowan.jce.wup.UniPacket;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.hyns.NSException;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSHttpProtocol;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSRequest;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSResult;
import com.huya.mtp.hyns.NSTransporter;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UnipacketProtocol extends NSHttpProtocol {
    private static final String CONTENT_TYPE = "application/multipart-formdata; charset=UTF-8";
    private static final String TAG = "NetService-UnipacketProtocol";
    private UnipacketProtocolConfig mUnipacketProtocolConfig = new UnipacketProtocolConfig();

    public interface UrlGetter {
        String getUrl(String str, String str2);
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public boolean accept(Class<?> cls) {
        return true;
    }

    public static class UnipacketProtocolConfig {
        private UrlGetter urlGetter;
    }

    public void setUrlGetter(UrlGetter urlGetter) {
        this.mUnipacketProtocolConfig.urlGetter = urlGetter;
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSMethod getMethod(Class<T> cls, Object obj, Method method, Object[] objArr) {
        return new UnipacketMethod(this.mUnipacketProtocolConfig, cls, obj, method, objArr);
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        return new NSFunction<>(nSMethod, nSTransporter);
    }

    public static class UnipacketMethod extends NSMethod implements WupFuncApi {
        private String mFuncName;
        private NSRequest mRequest;
        private UniPacket mRequestPacket;
        private String mServantName;
        private UrlGetter mUrlGetter;

        public String getCodeKey() {
            return "";
        }

        private UnipacketMethod(UnipacketProtocolConfig unipacketProtocolConfig, Class<?> cls, Object obj, Method method, Object[] objArr) {
            super(cls, obj, method, objArr);
            this.mServantName = "";
            this.mFuncName = "";
            if (unipacketProtocolConfig != null) {
                this.mUrlGetter = unipacketProtocolConfig.urlGetter;
            }
            if (objArr[0] instanceof UniPacket) {
                UniPacket uniPacket = (UniPacket) objArr[0];
                this.mRequestPacket = uniPacket;
                this.mServantName = uniPacket.getServantName();
                this.mFuncName = this.mRequestPacket.getFuncName();
                return;
            }
            throw new RuntimeException("first param must be UniPacket.");
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public NSRequest readRequest() {
            if (this.mRequest == null) {
                packageRequest();
            }
            return this.mRequest;
        }

        private void packageRequest() {
            UrlGetter urlGetter = this.mUrlGetter;
            this.mRequest = NSRequest.builder().url(urlGetter != null ? urlGetter.getUrl(this.mServantName, this.mFuncName) : "").paramEncode(new NSRequest.OnParamEncode() { // from class: com.huya.mtp.hyns.wup.UnipacketProtocol.UnipacketMethod.1
                @Override // com.huya.mtp.hyns.NSRequest.OnParamEncode
                public byte[] onEncode() {
                    return UnipacketMethod.this.mRequestPacket.encode();
                }
            }).cgi("/" + this.mRequestPacket.getServantName() + "/" + this.mRequestPacket.getFuncName()).contentType(UnipacketProtocol.CONTENT_TYPE).method(1).build();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huya.mtp.hyns.NSMethod
        public NSResponse<?> readResponse(NSResult nSResult, DataListener dataListener) throws NSException {
            try {
                return read((NetworkResponse) nSResult.mRsp, dataListener);
            } catch (DataException e) {
                for (Throwable cause = e; cause != null; cause = cause.getCause()) {
                    MTPApi.LOGGER.error(UnipacketProtocol.TAG, cause);
                }
                throw new NSException("read response failed", e);
            }
        }

        private NSResponse<?> read(NetworkResponse networkResponse, DataListener dataListener) throws DataException {
            if (dataListener != null) {
                dataListener.onProducerEvent(106);
            }
            UniPacket uniPacket = getUniPacket(networkResponse);
            if (dataListener != null) {
                dataListener.onProducerEvent(107);
            }
            int code = WupUtil.getCode(uniPacket, getCodeKey());
            if (code != 0) {
                throw new WupError("server return code:" + code + " when executing function:" + uniPacket.getFuncName(), null, code, uniPacket.getFuncName(), null, false);
            }
            if (dataListener != null) {
                dataListener.onProducerEvent(108);
            }
            return new NSResponse<>(uniPacket, networkResponse, code, Integer.valueOf(code));
        }

        private UniPacket getUniPacket(NetworkResponse networkResponse) {
            UniPacket uniPacket = new UniPacket();
            uniPacket.decode(networkResponse.data);
            return uniPacket;
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public Object getIdentifier() {
            Object[] objArr = new Object[2];
            UniPacket uniPacket = this.mRequestPacket;
            objArr[0] = uniPacket != null ? uniPacket.getServantName() : null;
            UniPacket uniPacket2 = this.mRequestPacket;
            objArr[1] = uniPacket2 != null ? uniPacket2.getFuncName() : null;
            return String.format("%s#%s", objArr);
        }

        @Override // com.huya.mtp.hyns.wup.WupFuncApi
        public String getServantName() {
            return this.mServantName;
        }

        @Override // com.huya.mtp.hyns.wup.WupFuncApi
        public String getFuncName() {
            return this.mFuncName;
        }
    }
}
