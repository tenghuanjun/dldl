package com.sdk.sq.net.gateway;

import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import com.sq.tools.report.exception.IExceptionReporter;
import com.sqnetwork.voly.VolleyLog;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GateWayEncryptInterceptor implements Interceptor {
    private final IExceptionReporter mExceptionReporter;
    private final Provider mProvider;

    public interface Provider {
        String provideKey();

        HashSet<String> provideWhiteList();

        String provideXRequestVersion();
    }

    public GateWayEncryptInterceptor(Provider keyProvider, IExceptionReporter exceptionReporter) {
        this.mProvider = keyProvider;
        this.mExceptionReporter = exceptionReporter;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String string = Uri.parse(chain.request().url().toString()).buildUpon().clearQuery().toString();
        if (this.mProvider.provideWhiteList().contains(string)) {
            try {
                Request requestEncryptRequest = encryptRequest(request);
                return decryptResponse(chain.proceed(requestEncryptRequest), requestEncryptRequest);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                IExceptionReporter iExceptionReporter = this.mExceptionReporter;
                if (iExceptionReporter != null) {
                    iExceptionReporter.reportException(e, 399, "统一网关加密异常", buildErrorData(string));
                }
                return chain.proceed(request);
            }
        }
        return chain.proceed(request);
    }

    @Deprecated
    private Request downgradeRequest(Request request) {
        String strReplace = request.url().toString().replace("-secure", "");
        Request.Builder builderNewBuilder = request.newBuilder();
        String strHeader = request.header("Host");
        if (!TextUtils.isEmpty(strHeader)) {
            builderNewBuilder.header("Host", strHeader.replace("-secure", ""));
        }
        builderNewBuilder.url(strReplace);
        return builderNewBuilder.build();
    }

    private String buildErrorData(String url) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", url);
            return jSONObject.toString();
        } catch (Exception e) {
            return "build data error " + e;
        }
    }

    private Request encryptRequest(Request originalRequest) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Request.Builder builderNewBuilder = originalRequest.newBuilder();
        String strObtainXRequestId = obtainXRequestId(originalRequest);
        String strBuildNonceStr = buildNonceStr(originalRequest, strObtainXRequestId);
        builderNewBuilder.header("x-request-id", strObtainXRequestId);
        builderNewBuilder.header("X-Request-Nonce-Str", strBuildNonceStr);
        builderNewBuilder.header("x-request-version", this.mProvider.provideXRequestVersion());
        encryptQueryParams(strBuildNonceStr, originalRequest, builderNewBuilder);
        encryptBody(strBuildNonceStr, originalRequest, builderNewBuilder);
        builderNewBuilder.removeHeader("Request-Id");
        return builderNewBuilder.build();
    }

    private Response decryptResponse(Response encyrptResponse, Request encryptRequest) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        String strHeader = encyrptResponse.header("x-response-nonce-str");
        String strHeader2 = encryptRequest.header("X-Request-Nonce-Str");
        if (TextUtils.isEmpty(strHeader2) || TextUtils.isEmpty(strHeader)) {
            return encyrptResponse;
        }
        String str = this.mProvider.provideKey() + strHeader2.substring(0, 8) + strHeader.substring(0, 8);
        IvParameterSpec ivParameterSpecGenerateIv = GateWayUtils.generateIv(strHeader.substring(8, 24));
        if (encyrptResponse.body() == null) {
            return encyrptResponse;
        }
        return encyrptResponse.newBuilder().body(ResponseBody.create(encyrptResponse.body().contentType(), GateWayUtils.decryptUrlSafe(encyrptResponse.body().string(), str, ivParameterSpecGenerateIv))).build();
    }

    private void encryptQueryParams(String nonceStr, Request originalRequest, Request.Builder encryptRequestBuilder) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String strQuery = originalRequest.url().query();
        if (TextUtils.isEmpty(strQuery)) {
            return;
        }
        String strEncrypt = encrypt(nonceStr, strQuery);
        encryptRequestBuilder.url(Uri.parse(originalRequest.url().toString()).buildUpon().clearQuery().toString() + "?" + strEncrypt);
    }

    private void encryptBody(String nonceStr, Request originalRequest, Request.Builder encryptRequestBuilder) {
        RequestBody requestBodyBody = originalRequest.body();
        if (requestBodyBody != null) {
            try {
                Buffer buffer = new Buffer();
                requestBodyBody.writeTo(buffer);
                encryptRequestBuilder.method(originalRequest.method(), RequestBody.create(requestBodyBody.contentType(), encrypt(nonceStr, buffer.readUtf8())));
            } catch (Exception e) {
                VolleyLog.e("GateWayEncryptInterceptor encryptBody error : " + e.getMessage(), new Object[0]);
            }
        }
    }

    private String encrypt(String nonceStr, String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return GateWayUtils.toBase64Url(GateWayUtils.encrypt(text, this.mProvider.provideKey() + nonceStr.substring(0, 16), GateWayUtils.generateIv(nonceStr.substring(nonceStr.length() - 16))));
    }

    private String buildNonceStr(Request request, String xRequestId) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.method());
        String strQuery = request.url().query();
        if (!TextUtils.isEmpty(strQuery)) {
            sb.append(strQuery);
        }
        try {
            if (request.body() != null) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                sb.append(buffer.readUtf8());
            }
        } catch (Exception unused) {
            sb.append("");
        }
        String strHeader = request.header("Cookie");
        if (!TextUtils.isEmpty(strHeader)) {
            sb.append(strHeader);
        }
        String strHeader2 = request.header("Authorization");
        if (!TextUtils.isEmpty(strHeader2)) {
            sb.append(strHeader2);
        }
        sb.append(xRequestId);
        return GateWayUtils.md5(sb.toString()).substring(0, 32);
    }

    private String obtainXRequestId(Request request) {
        String strHeader = request.header("Request-Id");
        if (TextUtils.isEmpty(strHeader)) {
            strHeader = generateRequestId();
        }
        return GateWayUtils.md5(strHeader);
    }

    private String generateRequestId() {
        return "android-" + Process.myPid() + "-" + System.currentTimeMillis() + "-" + GateWayUtils.randomData(16);
    }
}
