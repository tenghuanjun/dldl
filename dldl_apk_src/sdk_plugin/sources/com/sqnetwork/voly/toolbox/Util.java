package com.sqnetwork.voly.toolbox;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.sq.tools.network.httpdns.SqHttpDns;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okio.Buffer;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Util {
    static final String FORM_ENCODE_SET = " !\"#$&'()+,/:;<=>?@[\\]^`{|}~";
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    private static final Pattern VERIFY_IPV4 = Pattern.compile("([\\d.]+)");
    private static final Pattern VERIFY_IPV6 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)");
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    public static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static X509TrustManager platformTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw assertionError("No System TLS", e);
        }
    }

    public static AssertionError assertionError(String message, Exception e) {
        AssertionError assertionError = new AssertionError(message);
        try {
            assertionError.initCause(e);
        } catch (IllegalStateException unused) {
        }
        return assertionError;
    }

    static String percentDecode(String encoded, boolean plusIsSpace) {
        return percentDecode(encoded, 0, encoded.length(), plusIsSpace);
    }

    private List<String> percentDecode(List<String> list, boolean plusIsSpace) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? percentDecode(str, plusIsSpace) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String percentDecode(String encoded, int pos, int limit, boolean plusIsSpace) {
        for (int i = pos; i < limit; i++) {
            char cCharAt = encoded.charAt(i);
            if (cCharAt == '%' || (cCharAt == '+' && plusIsSpace)) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(encoded, pos, i);
                percentDecode(buffer, encoded, i, limit, plusIsSpace);
                return buffer.readUtf8();
            }
        }
        return encoded.substring(pos, limit);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void percentDecode(okio.Buffer r5, java.lang.String r6, int r7, int r8, boolean r9) {
        /*
        L0:
            if (r7 >= r8) goto L42
            int r0 = r6.codePointAt(r7)
            r1 = 37
            if (r0 != r1) goto L2d
            int r1 = r7 + 2
            if (r1 >= r8) goto L2d
            int r2 = r7 + 1
            char r2 = r6.charAt(r2)
            int r2 = decodeHexDigit(r2)
            char r3 = r6.charAt(r1)
            int r3 = decodeHexDigit(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.writeByte(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.writeByte(r1)
            goto L3c
        L39:
            r5.writeUtf8CodePoint(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqnetwork.voly.toolbox.Util.percentDecode(okio.Buffer, java.lang.String, int, int, boolean):void");
    }

    static boolean percentEncoded(String encoded, int pos, int limit) {
        int i = pos + 2;
        return i < limit && encoded.charAt(pos) == '%' && decodeHexDigit(encoded.charAt(pos + 1)) != -1 && decodeHexDigit(encoded.charAt(i)) != -1;
    }

    static String canonicalize(String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, Charset charset) {
        int iCharCount = pos;
        while (iCharCount < limit) {
            int iCodePointAt = input.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !asciiOnly)) {
                if (encodeSet.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (alreadyEncoded && (!strict || percentEncoded(input, iCharCount, limit)))) && (iCodePointAt != 43 || !plusIsSpace))) {
                    iCharCount += Character.charCount(iCodePointAt);
                } else {
                    Buffer buffer = new Buffer();
                    buffer.writeUtf8(input, pos, iCharCount);
                    canonicalize(buffer, input, iCharCount, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset);
                    return buffer.readUtf8();
                }
            } else {
                Buffer buffer2 = new Buffer();
                buffer2.writeUtf8(input, pos, iCharCount);
                canonicalize(buffer2, input, iCharCount, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset);
                return buffer2.readUtf8();
            }
        }
        return input.substring(pos, limit);
    }

    static void canonicalize(Buffer out, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, Charset charset) {
        Buffer buffer = null;
        while (pos < limit) {
            int iCodePointAt = input.codePointAt(pos);
            if (!alreadyEncoded || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && plusIsSpace) {
                    out.writeUtf8(alreadyEncoded ? Marker.ANY_NON_NULL_MARKER : "%2B");
                } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && asciiOnly) || encodeSet.indexOf(iCodePointAt) != -1 || (iCodePointAt == 37 && (!alreadyEncoded || (strict && !percentEncoded(input, pos, limit)))))) {
                    if (buffer == null) {
                        buffer = new Buffer();
                    }
                    if (charset == null || charset.equals(okhttp3.internal.Util.UTF_8)) {
                        buffer.writeUtf8CodePoint(iCodePointAt);
                    } else {
                        buffer.writeString(input, pos, Character.charCount(iCodePointAt) + pos, charset);
                    }
                    while (!buffer.exhausted()) {
                        int i = buffer.readByte() & 255;
                        out.writeByte(37);
                        out.writeByte((int) HEX_DIGITS[(i >> 4) & 15]);
                        out.writeByte((int) HEX_DIGITS[i & 15]);
                    }
                } else {
                    out.writeUtf8CodePoint(iCodePointAt);
                }
            }
            pos += Character.charCount(iCodePointAt);
        }
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, Charset charset) {
        return canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset);
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly) {
        return canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, null);
    }

    public static boolean isInHttpDnsBlacklist(String urlStr) {
        try {
            return isInBlacklist(urlStr, SqHttpDns.getInstance().getReportBlackList());
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isInBlacklist(String urlStr, List<String> blacklist) {
        String strEncodedPath;
        if (urlStr != null && !urlStr.isEmpty() && blacklist != null && !blacklist.isEmpty()) {
            HttpUrl httpUrl = HttpUrl.parse(urlStr);
            if (httpUrl == null) {
                if (urlStr.contains("/")) {
                    strEncodedPath = urlStr;
                    urlStr = null;
                } else {
                    strEncodedPath = null;
                }
            } else {
                urlStr = httpUrl.host();
                strEncodedPath = httpUrl.encodedPath();
            }
            for (String str : blacklist) {
                if (str != null && !str.isEmpty()) {
                    if (str.contains("/")) {
                        if (strEncodedPath != null && strEncodedPath.contains(str)) {
                            return true;
                        }
                    } else if (str.equals(urlStr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isIpV4Address(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        return VERIFY_IPV4.matcher(address).matches();
    }

    public static boolean isIpV6Address(String address) {
        return !TextUtils.isEmpty(address) && VERIFY_IPV6.matcher(address).matches() && address.startsWith("2");
    }

    private static boolean isIpV6Address(InetAddress inetAddress) {
        String hostAddress;
        return (inetAddress instanceof Inet6Address) && (hostAddress = inetAddress.getHostAddress()) != null && hostAddress.startsWith("2");
    }

    public static String getActiveIp(Context context) {
        Network activeNetwork;
        LinkProperties linkProperties;
        List<LinkAddress> linkAddresses;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && Build.VERSION.SDK_INT >= 23 && (activeNetwork = connectivityManager.getActiveNetwork()) != null && (linkProperties = connectivityManager.getLinkProperties(activeNetwork)) != null && (linkAddresses = linkProperties.getLinkAddresses()) != null && !linkAddresses.isEmpty()) {
            Iterator<LinkAddress> it = linkAddresses.iterator();
            while (it.hasNext()) {
                InetAddress address = it.next().getAddress();
                if (isIpV6Address(address)) {
                    return address.getHostAddress();
                }
            }
            Iterator<LinkAddress> it2 = linkAddresses.iterator();
            while (it2.hasNext()) {
                String hostAddress = it2.next().getAddress().getHostAddress();
                if (isIpV4Address(hostAddress) && !"0.0.0.0".equals(hostAddress) && !"127.0.0.1".equals(hostAddress)) {
                    return hostAddress;
                }
            }
        }
        return "";
    }
}
