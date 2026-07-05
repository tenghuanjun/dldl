package com.getui.gtc.base.http.crypt;

import android.util.Base64;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.http.Interceptor;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.Response;
import com.getui.gtc.base.http.ResponseBody;
import com.getui.gtc.base.http.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PtRASCryptoInterceptor implements Interceptor {
    private String keyId;
    private String publicKeyStr;

    public PtRASCryptoInterceptor(String str, String str2) {
        this.keyId = str;
        this.publicKeyStr = str2;
    }

    @Override // com.getui.gtc.base.http.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody == null) {
            throw new RuntimeException("PtRASCryptoInterceptor Error: request body is null");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        requestBodyBody.writeTo(byteArrayOutputStream);
        Util.closeQuietly(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String str = new String(byteArray, requestBodyBody.contentType().charset());
        try {
            builderNewBuilder.addHeader("X-TP", request.url().toString().toLowerCase().startsWith("https") ? "4" : "3").addHeader("X-KD", this.keyId).addHeader("X-V", "1.0.0.0");
            PublicKey publicKey = CryptTools.parsePublicKey("RSA", this.publicKeyStr);
            SecretKey secretKeyGenerateKey = CryptTools.generateKey("AES", 128);
            builderNewBuilder.addHeader("X-AK", Base64.encodeToString(CryptTools.encrypt("RSA/NONE/OAEPWithSHA1AndMGF1Padding", publicKey, secretKeyGenerateKey.getEncoded()), 2));
            String strEncodeToString = Base64.encodeToString(CryptTools.digest("SHA256", (this.keyId + Base64.encodeToString(secretKeyGenerateKey.getEncoded(), 2) + str).getBytes()), 2);
            builderNewBuilder.addHeader("X-SG", strEncodeToString);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(CryptTools.digest("md5", strEncodeToString.getBytes()));
            builderNewBuilder.body(RequestBody.create(requestBodyBody.contentType(), CryptTools.encrypt("AES/CFB/NoPadding", secretKeyGenerateKey, ivParameterSpec, byteArray)));
            Response responseProceed = chain.proceed(builderNewBuilder.build());
            Response.Builder builderRequest = responseProceed.newBuilder().request(request);
            builderRequest.body(ResponseBody.create(responseProceed.body().contentType(), CryptTools.decrypt("AES/CFB/NoPadding", secretKeyGenerateKey, ivParameterSpec, responseProceed.body().bytes())));
            return builderRequest.build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("PtRASCryptoInterceptor Error", e);
        }
    }
}
