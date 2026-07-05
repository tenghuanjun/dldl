package com.getui.gtc.base.http;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.getui.gtc.base.http.Interceptor;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.Response;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BridgeInterceptor implements Interceptor {
    @Override // com.getui.gtc.base.http.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builderAddHeader;
        String str;
        Request request = chain.request();
        Request.Builder builder = new Request.Builder(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection) request.url().openConnection();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody != null) {
            MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (mediaTypeContentType != null) {
                builder.addHeader("Content-Type", mediaTypeContentType.toString());
            }
            long jContentLength = requestBodyBody.contentLength();
            if (jContentLength != -1) {
                builderAddHeader = builder.addHeader("Content-Length", Long.toString(jContentLength));
                str = DownloadUtils.TRANSFER_ENCODING;
            } else {
                builderAddHeader = builder.addHeader(DownloadUtils.TRANSFER_ENCODING, DownloadUtils.VALUE_CHUNKED);
                str = "Content-Length";
            }
            builderAddHeader.removeHeader(str);
        }
        if (request.header(HttpHeaders.HOST) == null) {
            builder.addHeader(HttpHeaders.HOST, request.url().getHost());
        }
        if (request.header("Connection") == null) {
            builder.addHeader("Connection", "Keep-Alive");
        }
        boolean z = false;
        if (request.header("Accept-Encoding") == null && request.header(HttpHeaders.RANGE) == null) {
            z = true;
            builder.addHeader("Accept-Encoding", "gzip");
        }
        Response responseProceed = ((RealInterceptorChain) chain).proceed(builder.build(), httpURLConnection);
        Response.Builder builderRequest = new Response.Builder(responseProceed).request(request);
        if (z && "gzip".equalsIgnoreCase(responseProceed.header(HttpHeaders.CONTENT_ENCODING)) && responseProceed.body() != null) {
            builderRequest.body(ResponseBody.create(responseProceed.body().contentType(), -1L, new GZIPInputStream(responseProceed.body().byteStream()))).removeHeader(HttpHeaders.CONTENT_ENCODING).removeHeader("Content-Length");
        }
        return builderRequest.build();
    }
}
