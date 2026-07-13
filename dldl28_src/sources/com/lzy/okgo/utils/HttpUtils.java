package com.lzy.okgo.utils;

import android.text.TextUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class HttpUtils {
    public static String createUrlFromParams(String str, Map<String, List<String>> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (str.indexOf(38) > 0 || str.indexOf(63) > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    String strEncode = URLEncoder.encode(it.next(), "UTF-8");
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(strEncode);
                    sb.append("&");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            OkLogger.printStackTrace(e);
            return str;
        }
    }

    public static Request.Builder appendHeaders(Request.Builder builder, HttpHeaders httpHeaders) {
        if (httpHeaders.headersMap.isEmpty()) {
            return builder;
        }
        Headers.Builder builder2 = new Headers.Builder();
        try {
            for (Map.Entry<String, String> entry : httpHeaders.headersMap.entrySet()) {
                builder2.add(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
        builder.headers(builder2.build());
        return builder;
    }

    public static RequestBody generateMultipartRequestBody(HttpParams httpParams, boolean z) {
        if (httpParams.fileParamsMap.isEmpty() && !z) {
            FormBody.Builder builder = new FormBody.Builder();
            for (String str : httpParams.urlParamsMap.keySet()) {
                Iterator<String> it = httpParams.urlParamsMap.get(str).iterator();
                while (it.hasNext()) {
                    builder.add(str, it.next());
                }
            }
            return builder.build();
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!httpParams.urlParamsMap.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : httpParams.urlParamsMap.entrySet()) {
                Iterator<String> it2 = entry.getValue().iterator();
                while (it2.hasNext()) {
                    type.addFormDataPart(entry.getKey(), it2.next());
                }
            }
        }
        for (Map.Entry<String, List<HttpParams.FileWrapper>> entry2 : httpParams.fileParamsMap.entrySet()) {
            for (HttpParams.FileWrapper fileWrapper : entry2.getValue()) {
                type.addFormDataPart(entry2.getKey(), fileWrapper.fileName, RequestBody.create(fileWrapper.contentType, fileWrapper.file));
            }
        }
        return type.build();
    }

    public static String getNetFileName(Response response, String str) {
        String headerFileName = getHeaderFileName(response);
        if (TextUtils.isEmpty(headerFileName)) {
            headerFileName = getUrlFileName(str);
        }
        if (TextUtils.isEmpty(headerFileName)) {
            headerFileName = "unknownfile_" + System.currentTimeMillis();
        }
        try {
            return URLDecoder.decode(headerFileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            OkLogger.printStackTrace(e);
            return headerFileName;
        }
    }

    private static String getHeaderFileName(Response response) {
        String strHeader = response.header(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION);
        if (strHeader == null) {
            return null;
        }
        String strReplaceAll = strHeader.replaceAll("\"", "");
        int iIndexOf = strReplaceAll.indexOf("filename=");
        if (iIndexOf != -1) {
            return strReplaceAll.substring(iIndexOf + 9, strReplaceAll.length());
        }
        int iIndexOf2 = strReplaceAll.indexOf("filename*=");
        if (iIndexOf2 == -1) {
            return null;
        }
        String strSubstring = strReplaceAll.substring(iIndexOf2 + 10, strReplaceAll.length());
        return strSubstring.startsWith("UTF-8''") ? strSubstring.substring(7, strSubstring.length()) : strSubstring;
    }

    private static String getUrlFileName(String str) {
        int iIndexOf;
        String[] strArrSplit = str.split("/");
        for (String str2 : strArrSplit) {
            if (str2.contains("?") && (iIndexOf = str2.indexOf("?")) != -1) {
                return str2.substring(0, iIndexOf);
            }
        }
        if (strArrSplit.length > 0) {
            return strArrSplit[strArrSplit.length - 1];
        }
        return null;
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (!file.isFile()) {
            return false;
        }
        boolean zDelete = file.delete();
        OkLogger.e("deleteFile:" + zDelete + " path:" + str);
        return zDelete;
    }

    public static MediaType guessMimeType(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        if (contentTypeFor == null) {
            return HttpParams.MEDIA_TYPE_STREAM;
        }
        return MediaType.parse(contentTypeFor);
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static void runOnUiThread(Runnable runnable) {
        OkGo.getInstance().getDelivery().post(runnable);
    }
}
