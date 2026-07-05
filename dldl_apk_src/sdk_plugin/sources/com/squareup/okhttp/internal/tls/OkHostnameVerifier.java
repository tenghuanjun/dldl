package com.squareup.okhttp.internal.tls;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private OkHostnameVerifier() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }

    public boolean verify(String str, X509Certificate x509Certificate) {
        return verifyAsIpAddress(str) ? verifyIpAddress(str, x509Certificate) : verifyHostName(str, x509Certificate);
    }

    static boolean verifyAsIpAddress(String str) {
        return VERIFY_AS_IP_ADDRESS.matcher(str).matches();
    }

    private boolean verifyIpAddress(String str, X509Certificate x509Certificate) {
        Iterator<String> it = getSubjectAltNames(x509Certificate, 7).iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyHostName(String str, X509Certificate x509Certificate) {
        String strFindMostSpecific;
        String lowerCase = str.toLowerCase(Locale.US);
        Iterator<String> it = getSubjectAltNames(x509Certificate, 2).iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (verifyHostName(lowerCase, it.next())) {
                return true;
            }
            z = true;
        }
        if (z || (strFindMostSpecific = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn")) == null) {
            return false;
        }
        return verifyHostName(lowerCase, strFindMostSpecific);
    }

    private List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    public boolean verifyHostName(String str, String str2) {
        int i;
        int length;
        int length2;
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains(Marker.ANY_MARKER)) {
            return str.equals(lowerCase);
        }
        if (lowerCase.startsWith("*.") && str.regionMatches(0, lowerCase, 2, lowerCase.length() - 2)) {
            return true;
        }
        int iIndexOf = lowerCase.indexOf(42);
        return iIndexOf <= lowerCase.indexOf(46) && str.regionMatches(0, lowerCase, 0, iIndexOf) && str.indexOf(46, iIndexOf) >= (length2 = str.length() - (length = lowerCase.length() - (i = iIndexOf + 1))) && str.regionMatches(length2, lowerCase, i, length);
    }
}
