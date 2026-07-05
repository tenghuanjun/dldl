package com.huya.mtp.hyns.wup;

import android.text.TextUtils;
import com.duowan.jce.wup.UniPacket;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.hyns.NSException;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSHttpProtocol;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSRequest;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSResult;
import com.huya.mtp.hyns.NSTransporter;
import com.huya.mtp.hyns.utils.Reflect;
import com.huya.mtp.hyns.utils.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WupProtocol extends NSHttpProtocol {
    private static final String CONTENT_TYPE = "application/multipart-formdata; charset=UTF-8";
    private static final String DEFAULT_REQ_KEY = "tReq";
    private static final String DEFAULT_RSP_KEY = "tRsp";
    private static final String TAG = "NetService-WupProtocol";
    protected WupProtocolConfig mWupProtocolConfig = new WupProtocolConfig();

    public interface OnRspListener {
        void onBizError(String str, String str2, int i);

        void onBizSuccess(String str, String str2, Object obj);
    }

    public interface OnWupCodeParse {
        boolean isReturnOnResponse(String str, String str2, int i);
    }

    public interface UniPacketGetter {
        UniPacket getUnipacket();
    }

    public interface UrlGetter {
        String getUrl(String str, String str2);
    }

    public static class WupProtocolConfig {
        private boolean isNeedDecodeUnipack = true;
        private OnRspListener onRspListener;
        private OnWupCodeParse onWupCodeParse;
        private UniPacketGetter uniPacketGetter;
        private UrlGetter urlGetter;
    }

    public void setUrlGetter(UrlGetter urlGetter) {
        this.mWupProtocolConfig.urlGetter = urlGetter;
    }

    public void setUniPacketGetter(UniPacketGetter uniPacketGetter) {
        this.mWupProtocolConfig.uniPacketGetter = uniPacketGetter;
    }

    public void setOnRspListener(OnRspListener onRspListener) {
        this.mWupProtocolConfig.onRspListener = onRspListener;
    }

    public void setOnWupCodeParse(OnWupCodeParse onWupCodeParse) {
        this.mWupProtocolConfig.onWupCodeParse = onWupCodeParse;
    }

    public void isNeedCheckUnipack(boolean z) {
        this.mWupProtocolConfig.isNeedDecodeUnipack = z;
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSMethod getMethod(Class<T> cls, Object obj, Method method, Object[] objArr) {
        return new WupMethod(this.mWupProtocolConfig, cls, obj, method, objArr);
    }

    @Override // com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        return new NSFunction<>(nSMethod, nSTransporter);
    }

    public static class WupMethod extends NSMethod implements WupFuncApi {
        private boolean isNeedCheckUnipack;
        int mActiveParamCount;
        private String mFuncName;
        private OnRspListener mOnRspListener;
        private OnWupCodeParse mOnWupCodeParse;
        private NSRequest mRequest;
        private UniPacket mRequestPacket;
        private String mServantName;
        String mServantReqKey;
        String[] mServantRspKey;
        private UniPacketGetter mUniPacketGetter;
        private UrlGetter mUrlGetter;

        @Override // com.huya.mtp.hyns.wup.WupFuncApi
        public String getServantName() {
            return this.mServantName;
        }

        @Override // com.huya.mtp.hyns.wup.WupFuncApi
        public String getFuncName() {
            return this.mFuncName;
        }

        private WupMethod(WupProtocolConfig wupProtocolConfig, Class<?> cls, Object obj, Method method, Object[] objArr) {
            super(cls, obj, method, objArr);
            this.mServantName = "";
            this.mFuncName = "";
            this.mServantReqKey = "";
            this.mServantRspKey = null;
            this.mActiveParamCount = -1;
            this.mUniPacketGetter = wupProtocolConfig.uniPacketGetter;
            this.mUrlGetter = wupProtocolConfig.urlGetter;
            this.mOnRspListener = wupProtocolConfig.onRspListener;
            this.mOnWupCodeParse = wupProtocolConfig.onWupCodeParse;
            this.isNeedCheckUnipack = wupProtocolConfig.isNeedDecodeUnipack;
            initServantKey(cls);
        }

        private void initServantKey(Class<?> cls) {
            WupReq wupReq = (WupReq) cls.getAnnotation(WupReq.class);
            WupRsp wupRsp = (WupRsp) cls.getAnnotation(WupRsp.class);
            if (wupReq != null) {
                this.mServantReqKey = wupReq.value();
            }
            if (wupRsp != null) {
                this.mServantRspKey = wupRsp.keys();
            }
        }

        private UniPacket getUniPacket() {
            UniPacketGetter uniPacketGetter = this.mUniPacketGetter;
            if (uniPacketGetter != null) {
                return uniPacketGetter.getUnipacket();
            }
            return new UniPacket();
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public NSRequest readRequest() {
            if (this.mRequest == null) {
                packageRequest();
            }
            return this.mRequest;
        }

        private void packageRequest() {
            String strValue;
            boolean z;
            WupFunc wupFunc = (WupFunc) this.mMethod.getAnnotation(WupFunc.class);
            WupParamCount wupParamCount = (WupParamCount) this.mMethod.getAnnotation(WupParamCount.class);
            this.mServantName = getClassServant(this.mProxyCls);
            this.mFuncName = this.mMethod.getName();
            if (wupFunc != null) {
                if (!TextUtils.isEmpty(wupFunc.servant())) {
                    this.mServantName = wupFunc.servant();
                }
                if (!TextUtils.isEmpty(wupFunc.value())) {
                    this.mFuncName = wupFunc.value();
                }
            }
            if (wupParamCount != null) {
                this.mActiveParamCount = wupParamCount.value();
            }
            Annotation[][] parameterAnnotations = this.mMethod.getParameterAnnotations();
            final UniPacket uniPacket = getUniPacket();
            uniPacket.useVersion3();
            uniPacket.setFuncName(this.mFuncName);
            uniPacket.setServantName(this.mServantName);
            if (needPrintEntity()) {
                MTPApi.LOGGER.info(WupProtocol.TAG, "servant name = %s", this.mServantName);
                MTPApi.LOGGER.info(WupProtocol.TAG, "func name = %s", this.mFuncName);
            }
            int length = this.mArgs == null ? 0 : this.mArgs.length;
            int i = this.mActiveParamCount;
            if (i != -1) {
                length = i;
            }
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = this.mArgs[i2];
                Annotation[] annotationArr = parameterAnnotations[i2];
                int length2 = annotationArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        strValue = WupProtocol.DEFAULT_REQ_KEY;
                        z = false;
                        break;
                    }
                    Annotation annotation = annotationArr[i3];
                    if (annotation.annotationType() == WupReq.class) {
                        strValue = ((WupReq) annotation).value();
                        z = true;
                        break;
                    }
                    i3++;
                }
                if (!z && !TextUtils.isEmpty(this.mServantReqKey)) {
                    strValue = this.mServantReqKey;
                }
                if (uniPacket.containsKey(strValue)) {
                    MTPApi.LOGGER.error(WupProtocol.TAG, "error!!: redundant key : '" + strValue + "' for method " + this.mMethod.getName());
                }
                uniPacket.put(strValue, obj);
                if (needPrintEntity()) {
                    MTPApi.LOGGER.info(WupProtocol.TAG, "WupReq - 【functionName:%s】, key:%s, class:%s, parameter:%s", String.valueOf(this.mFuncName), strValue, String.valueOf(obj.getClass()), obj);
                }
            }
            this.mRequestPacket = uniPacket;
            UrlGetter urlGetter = this.mUrlGetter;
            this.mRequest = NSRequest.builder().url(urlGetter != null ? urlGetter.getUrl(this.mServantName, this.mFuncName) : null).paramEncode(new NSRequest.OnParamEncode() { // from class: com.huya.mtp.hyns.wup.WupProtocol.WupMethod.1
                @Override // com.huya.mtp.hyns.NSRequest.OnParamEncode
                public byte[] onEncode() {
                    return uniPacket.encode();
                }
            }).cgi("/" + uniPacket.getServantName() + "/" + uniPacket.getFuncName()).contentType(WupProtocol.CONTENT_TYPE).method(1).build();
        }

        protected boolean needPrintEntity() {
            return MTPApi.LOGGER.isLogLevelEnabled(3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huya.mtp.hyns.NSMethod
        public NSResponse<?> readResponse(NSResult nSResult, DataListener dataListener) throws NSException {
            try {
                if (!this.isNeedCheckUnipack && nSResult.mRsp != 0) {
                    return new NSResponse<>(nSResult.mRsp, (NetworkResponse) nSResult.mRsp, ((NetworkResponse) nSResult.mRsp).statusCode, null);
                }
                return read((NetworkResponse) nSResult.mRsp, dataListener);
            } catch (DataException e) {
                for (Throwable cause = e; cause != null; cause = cause.getCause()) {
                    MTPApi.LOGGER.error(WupProtocol.TAG, cause);
                }
                throw new NSException("read response failed", e);
            }
        }

        private RspInfo[] getResponseInfo() throws ParseException {
            String[] strArrKeys;
            Class<?>[] clsArrClasses;
            Method method = getMethod();
            Class<?> actualReturnType = getActualReturnType(method.getGenericReturnType());
            WupRsp wupRsp = (WupRsp) method.getAnnotation(WupRsp.class);
            if (wupRsp != null) {
                clsArrClasses = wupRsp.classes();
                strArrKeys = wupRsp.keys();
            } else {
                strArrKeys = this.mServantRspKey;
                if (strArrKeys != null) {
                    clsArrClasses = new Class[]{actualReturnType};
                } else {
                    strArrKeys = new String[]{WupProtocol.DEFAULT_RSP_KEY};
                    clsArrClasses = new Class[]{actualReturnType};
                }
            }
            if (strArrKeys.length != clsArrClasses.length) {
                throw new ParseException(String.format("Array length didn't match, keys:%s, classes:%s in %s#%s", Arrays.toString(strArrKeys), Arrays.toString(clsArrClasses), getProxyCls().getName(), getMethod().getName()));
            }
            if (clsArrClasses.length == 0 && !Void.class.isAssignableFrom(actualReturnType)) {
                throw new ParseException(String.format("WupRsp classes length is 0 while actual return type %s is not void in %s#%s", actualReturnType.getName(), getProxyCls().getName(), getMethod().getName()));
            }
            if (clsArrClasses.length > 1 && !actualReturnType.isArray()) {
                throw new ParseException(String.format("Return type is not an array while classes length > 1, keys:%s, classes:%s in %s#%s", Arrays.toString(strArrKeys), Arrays.toString(clsArrClasses), getProxyCls().getName(), getMethod().getName()));
            }
            if (actualReturnType.isArray()) {
                actualReturnType = actualReturnType.getComponentType();
            }
            RspInfo[] rspInfoArr = new RspInfo[clsArrClasses.length];
            for (int i = 0; i < clsArrClasses.length; i++) {
                if (clsArrClasses[i] == Object.class) {
                    clsArrClasses[i] = actualReturnType;
                } else {
                    Class<?> cls = clsArrClasses[i];
                    if (!checkIsAssignable(actualReturnType, cls)) {
                        throw new ParseException(String.format("ret:%s is not the super class of rsp:%s in WupRsp classes index:%d, in %s#%s", actualReturnType.getName(), cls.getName(), Integer.valueOf(i), getProxyCls().getName(), getMethod().getName()));
                    }
                }
                rspInfoArr[i] = new RspInfo(strArrKeys[i], clsArrClasses[i]);
            }
            return rspInfoArr;
        }

        private boolean checkIsAssignable(Class<?> cls, Class<?> cls2) {
            if (cls2.isPrimitive()) {
                return Object.class.equals(cls);
            }
            return cls.isAssignableFrom(cls2);
        }

        private Class<?> getActualReturnType(Type type) throws ParseException {
            if (!(type instanceof ParameterizedType)) {
                throw new ParseException(String.format("Return type %s is not parameterized in %s#%s", type, getProxyCls().getName(), getMethod().getName()));
            }
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments == null || actualTypeArguments.length != 1) {
                throw new ParseException(String.format("Return type %s parameter length is not 1 in %s#%s", type, getProxyCls().getName(), getMethod().getName()));
            }
            if (TypeUtils.getRawType(actualTypeArguments[0]) == NSResponse.class) {
                return getActualReturnType(actualTypeArguments[0]);
            }
            return (Class) actualTypeArguments[0];
        }

        private static class RspInfo {
            final Class<?> mCls;
            final String mKey;

            RspInfo(String str, Class<?> cls) {
                this.mKey = str;
                this.mCls = cls;
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
            RspInfo[] responseInfo = getResponseInfo();
            int length = responseInfo.length;
            Object[] responseByInfo = getResponseByInfo(uniPacket, responseInfo);
            RspInfo codeInfo = getCodeInfo();
            Object obj = responseByInfo;
            if (length == 1) {
                obj = responseByInfo[0];
            }
            if (Integer.TYPE.equals(codeInfo.mCls) || Integer.class.equals(codeInfo.mCls)) {
                int code = WupUtil.getCode(uniPacket, codeInfo.mKey);
                OnWupCodeParse onWupCodeParse = this.mOnWupCodeParse;
                boolean zIsReturnOnResponse = onWupCodeParse != null ? onWupCodeParse.isReturnOnResponse(this.mServantName, this.mFuncName, code) : false;
                if (code != 0 && !zIsReturnOnResponse) {
                    String str = "server return code:" + code + " when executing function:" + uniPacket.getFuncName();
                    JceStruct jceStruct = obj instanceof JceStruct ? (JceStruct) obj : null;
                    OnRspListener onRspListener = this.mOnRspListener;
                    if (onRspListener != null) {
                        onRspListener.onBizError(this.mServantName, this.mFuncName, code);
                    }
                    throw new WupError(str, null, code, uniPacket.getFuncName(), jceStruct, false);
                }
                return getNSResponse(networkResponse, dataListener, obj, code);
            }
            return getNSResponse(networkResponse, dataListener, getObjectFromUniPacket(uniPacket, codeInfo.mKey, codeInfo.mCls), 0);
        }

        private NSResponse<?> getNSResponse(NetworkResponse networkResponse, DataListener dataListener, Object obj, int i) {
            if (dataListener != null) {
                dataListener.onProducerEvent(108);
            }
            OnRspListener onRspListener = this.mOnRspListener;
            if (onRspListener != null) {
                onRspListener.onBizSuccess(this.mServantName, this.mFuncName, obj);
            }
            return new NSResponse<>(obj, networkResponse, i, Integer.valueOf(i));
        }

        private RspInfo getCodeInfo() {
            WupCode wupCode = (WupCode) getMethod().getAnnotation(WupCode.class);
            if (wupCode == null) {
                return new RspInfo("", Integer.TYPE);
            }
            return new RspInfo(wupCode.key(), wupCode.cls());
        }

        private Object[] getResponseByInfo(UniPacket uniPacket, RspInfo[] rspInfoArr) throws ParseException {
            int length = rspInfoArr.length;
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                RspInfo rspInfo = rspInfoArr[i];
                String str = rspInfo.mKey;
                Class<?> cls = rspInfo.mCls;
                if (Void.class.isAssignableFrom(cls)) {
                    objArr[i] = null;
                } else {
                    objArr[i] = getObjectFromUniPacket(uniPacket, str, cls);
                }
                if (needPrintEntity()) {
                    MTPApi.LOGGER.info(WupProtocol.TAG, "WupRsp - 【functionName:%s】, key:%s, class:%s, result:%s", String.valueOf(this.mFuncName), str, cls, objArr[i]);
                }
            }
            return objArr;
        }

        private Object getObjectFromUniPacket(UniPacket uniPacket, String str, Class<?> cls) throws ParseException {
            try {
                return WupUtil.getObject(uniPacket, str, Reflect.on(cls).createAuto(false).get());
            } catch (Exception e) {
                throw new ParseException("Cannot initialize proxy", e);
            }
        }

        private UniPacket getUniPacket(NetworkResponse networkResponse) {
            UniPacket uniPacket = new UniPacket();
            uniPacket.decode(networkResponse.data);
            return uniPacket;
        }

        @Override // com.huya.mtp.hyns.NSMethod
        public Object getIdentifier() {
            return String.format("%s#%s", this.mRequestPacket.getServantName(), this.mRequestPacket.getFuncName());
        }

        private String getClassServant(Class<?> cls) {
            WupServant wupServant = (WupServant) cls.getAnnotation(WupServant.class);
            String strValue = wupServant != null ? wupServant.value() : null;
            return TextUtils.isEmpty(strValue) ? cls.getSimpleName().toLowerCase() : strValue;
        }
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public boolean accept(Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            int length = parameterTypes.length;
            WupParamCount wupParamCount = (WupParamCount) method.getAnnotation(WupParamCount.class);
            int iValue = wupParamCount != null ? wupParamCount.value() : -1;
            if (iValue != -1) {
                length = iValue;
            }
            if (length != 0) {
                if (length == 1) {
                    if (!isWupAcceptObject(parameterTypes[0])) {
                        return false;
                    }
                } else {
                    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                    for (int i = 0; i < length; i++) {
                        if (parameterAnnotations[i] == null || parameterAnnotations[i].length == 0 || !isWupAcceptObject(parameterTypes[i])) {
                            return false;
                        }
                    }
                    for (Class<?> cls2 : parameterTypes) {
                        if (!isWupAcceptObject(cls2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isWupAcceptObject(Class<?> cls) {
        if (cls.isPrimitive() || cls.isArray() || Collection.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls)) {
            return true;
        }
        return JceStruct.class.isAssignableFrom(cls);
    }
}
