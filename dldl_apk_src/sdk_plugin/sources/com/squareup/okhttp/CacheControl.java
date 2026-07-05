package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.HeaderParser;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class CacheControl {
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, int i3, int i4, boolean z5) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPublic = z3;
        this.mustRevalidate = z4;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z5;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public static CacheControl parse(Headers headers) {
        int iSkipUntil;
        String strTrim;
        boolean z = false;
        boolean z2 = false;
        int seconds = -1;
        int seconds2 = -1;
        boolean z3 = false;
        boolean z4 = false;
        int seconds3 = -1;
        int seconds4 = -1;
        boolean z5 = false;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase("Cache-Control") || headers.name(i).equalsIgnoreCase("Pragma")) {
                String strValue = headers.value(i);
                for (int i2 = 0; i2 < strValue.length(); i2 = iSkipUntil) {
                    int iSkipUntil2 = HeaderParser.skipUntil(strValue, i2, "=,;");
                    String strTrim2 = strValue.substring(i2, iSkipUntil2).trim();
                    if (iSkipUntil2 == strValue.length() || strValue.charAt(iSkipUntil2) == ',' || strValue.charAt(iSkipUntil2) == ';') {
                        iSkipUntil = iSkipUntil2 + 1;
                        strTrim = null;
                    } else {
                        int iSkipWhitespace = HeaderParser.skipWhitespace(strValue, iSkipUntil2 + 1);
                        if (iSkipWhitespace < strValue.length() && strValue.charAt(iSkipWhitespace) == '\"') {
                            int i3 = iSkipWhitespace + 1;
                            int iSkipUntil3 = HeaderParser.skipUntil(strValue, i3, "\"");
                            strTrim = strValue.substring(i3, iSkipUntil3);
                            iSkipUntil = iSkipUntil3 + 1;
                        } else {
                            iSkipUntil = HeaderParser.skipUntil(strValue, iSkipWhitespace, ",;");
                            strTrim = strValue.substring(iSkipWhitespace, iSkipUntil).trim();
                        }
                    }
                    if ("no-cache".equalsIgnoreCase(strTrim2)) {
                        z = true;
                    } else if ("no-store".equalsIgnoreCase(strTrim2)) {
                        z2 = true;
                    } else if ("max-age".equalsIgnoreCase(strTrim2)) {
                        seconds = HeaderParser.parseSeconds(strTrim);
                    } else if ("s-maxage".equalsIgnoreCase(strTrim2)) {
                        seconds2 = HeaderParser.parseSeconds(strTrim);
                    } else if ("public".equalsIgnoreCase(strTrim2)) {
                        z3 = true;
                    } else if ("must-revalidate".equalsIgnoreCase(strTrim2)) {
                        z4 = true;
                    } else if ("max-stale".equalsIgnoreCase(strTrim2)) {
                        seconds3 = HeaderParser.parseSeconds(strTrim);
                    } else if ("min-fresh".equalsIgnoreCase(strTrim2)) {
                        seconds4 = HeaderParser.parseSeconds(strTrim);
                    } else if ("only-if-cached".equalsIgnoreCase(strTrim2)) {
                        z5 = true;
                    }
                }
            }
        }
        return new CacheControl(z, z2, seconds, seconds2, z3, z4, seconds3, seconds4, z5);
    }
}
