package com.bytedance.frameworks.core.encrypt;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.frameworks.encryptor.EncryptorUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RequestEncryptUtils {
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    private static final String KEY_DEVICE_BRAND = "device_brand";
    private static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_DEVICE_TYPE = "device_type";
    private static final String KEY_OPENUDID = "openudid";
    private static final String KEY_SS_ENCRYPT = "ss_encrypt";
    private static final String KEY_SS_QUERIES = "ss_queries";
    private static final String KEY_SS_QUERIES_HEADER = "X-SS-QUERIES";
    private static final String KEY_UUID = "uuid";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String TAG = "RequestEncryptUtils";
    private static boolean sDebug = false;
    private static IEncryptConfig sEncryptConfig;

    public interface IEncryptConfig {
        boolean isSsQueriesHeaderOpen();

        boolean isSsQueriesOpen();

        boolean isSsQueriesPlaintextOpen();
    }

    public static void setEncryptConfig(IEncryptConfig iEncryptConfig) {
        sEncryptConfig = iEncryptConfig;
    }

    public static void setDebug(boolean z) {
        sDebug = z;
    }

    public static String tryEncryptRequest(String str, List<Pair<String, String>> list) {
        if (TextUtils.isEmpty(str) || sEncryptConfig == null || sDebug) {
            return str;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Uri uri = Uri.parse(str);
            parseQueries(uri, linkedHashMap);
            LinkedList linkedList = new LinkedList();
            tryAddQuery(linkedHashMap, linkedList, "device_id");
            tryAddQuery(linkedHashMap, linkedList, KEY_DEVICE_TYPE);
            tryAddQuery(linkedHashMap, linkedList, KEY_DEVICE_BRAND);
            tryAddQuery(linkedHashMap, linkedList, KEY_UUID);
            tryAddQuery(linkedHashMap, linkedList, KEY_OPENUDID);
            String str2 = format(linkedList, false, "UTF-8");
            if (TextUtils.isEmpty(str2)) {
                return str;
            }
            byte[] bytes = str2.getBytes();
            byte[] bArrEncrypt = EncryptorUtil.encrypt(bytes, bytes.length);
            if (bArrEncrypt == null) {
                return str;
            }
            String strEncodeToString = Base64.encodeToString(bArrEncrypt, 2);
            LinkedList linkedList2 = new LinkedList();
            if (sEncryptConfig.isSsQueriesOpen()) {
                LinkedList linkedList3 = new LinkedList();
                linkedList3.add(strEncodeToString);
                linkedList2.add(new Pair(KEY_SS_QUERIES, linkedList3));
            }
            if (sEncryptConfig.isSsQueriesHeaderOpen() && list != null) {
                list.add(new Pair<>(KEY_SS_QUERIES_HEADER, encode(strEncodeToString, "UTF-8")));
            }
            if (!linkedHashMap.isEmpty()) {
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    if (entry != null) {
                        linkedList2.add(new Pair(entry.getKey(), entry.getValue()));
                    }
                }
            }
            return createUri(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getPath(), format(linkedList2, true, "UTF-8"), uri.getFragment()).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static byte[] tryEncryptRequestBody(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || sDebug) {
            return bArr;
        }
        try {
            return EncryptorUtil.encrypt(bArr, bArr.length);
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private static boolean tryAddQuery(Map<String, List<String>> map, List<Pair<String, List<String>>> list, String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str) || list == null || !map.containsKey(str)) {
            return false;
        }
        list.add(new Pair<>(str, map.get(str)));
        if (sEncryptConfig.isSsQueriesPlaintextOpen()) {
            return true;
        }
        map.remove(str);
        return true;
    }

    public static void parseQueries(Uri uri, Map<String, List<String>> map) throws IOException {
        if (uri == null) {
            throw new IOException("parseUrl url is null !!!");
        }
        if (map != null) {
            try {
                String encodedQuery = uri.getEncodedQuery();
                if (encodedQuery != null) {
                    for (String str : encodedQuery.split(PARAMETER_SEPARATOR)) {
                        int iIndexOf = str.indexOf(NAME_VALUE_SEPARATOR);
                        if (iIndexOf >= 0) {
                            String strDecode = URLDecoder.decode(str.substring(0, iIndexOf), "UTF-8");
                            List<String> linkedList = map.get(strDecode);
                            if (linkedList == null) {
                                linkedList = new LinkedList<>();
                            }
                            linkedList.add(URLDecoder.decode(str.substring(iIndexOf + 1), "UTF-8"));
                            map.put(strDecode, linkedList);
                        } else {
                            String strDecode2 = URLDecoder.decode(str, "UTF-8");
                            List<String> linkedList2 = map.get(strDecode2);
                            if (linkedList2 == null) {
                                linkedList2 = new LinkedList<>();
                            }
                            linkedList2.add("");
                            map.put(strDecode2, linkedList2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                throw new IOException("parseUrl url is null !!!");
            }
        }
    }

    private static Uri createUri(String str, String str2, int i, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            if (str != null) {
                sb.append(str);
                sb.append("://");
            }
            sb.append(str2);
            if (i > 0) {
                sb.append(':');
                sb.append(i);
            }
        }
        if (str3 == null || !str3.startsWith("/")) {
            sb.append('/');
        }
        if (str3 != null) {
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append('?');
            sb.append(str4);
        }
        if (str5 != null) {
            sb.append('#');
            sb.append(str5);
        }
        return Uri.parse(sb.toString());
    }

    private static String format(List<Pair<String, List<String>>> list, boolean z, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, List<String>> pair : list) {
            String strEncode = (String) pair.first;
            if (z) {
                strEncode = encode((String) pair.first, str);
            }
            List<String> list2 = (List) pair.second;
            if (list2 != null && list2.size() > 0) {
                for (String strEncode2 : list2) {
                    if (z) {
                        strEncode2 = strEncode2 != null ? encode(strEncode2, str) : "";
                    }
                    if (sb.length() > 0) {
                        sb.append(PARAMETER_SEPARATOR);
                    }
                    sb.append(strEncode);
                    sb.append(NAME_VALUE_SEPARATOR);
                    sb.append(strEncode2);
                }
            }
        }
        return sb.toString();
    }

    private static String encode(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
