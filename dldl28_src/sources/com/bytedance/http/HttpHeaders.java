package com.bytedance.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class HttpHeaders {
    private final String[] mNamesAndValues;

    public static final class Builder {
        final List namesAndValues = new ArrayList(20);

        public final Builder add(String str) {
            int iIndexOf = str.indexOf(":");
            if (iIndexOf != -1) {
                return add(str.substring(0, iIndexOf).trim(), str.substring(iIndexOf + 1));
            }
            throw new IllegalArgumentException("Unexpected header: " + str);
        }

        public final Builder add(String str, String str2) {
            return addLenient(str, str2);
        }

        public final Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }

        public final HttpHeaders build() {
            return new HttpHeaders(this);
        }

        public final String get(String str) {
            for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase((String) this.namesAndValues.get(size))) {
                    return (String) this.namesAndValues.get(size + 1);
                }
            }
            return null;
        }

        public final Builder removeAll(String str) {
            int i = 0;
            while (i < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase((String) this.namesAndValues.get(i))) {
                    this.namesAndValues.remove(i);
                    this.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }
    }

    HttpHeaders(Builder builder) {
        this.mNamesAndValues = (String[]) builder.namesAndValues.toArray(new String[0]);
    }

    private HttpHeaders(String[] strArr) {
        this.mNamesAndValues = strArr;
    }

    private static String get(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static HttpHeaders of(Map map) {
        if (map == null) {
            throw new NullPointerException("headers == null");
        }
        String[] strArr = new String[map.size() << 1];
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String strTrim = ((String) entry.getKey()).trim();
            String strTrim2 = ((String) entry.getValue()).trim();
            if (strTrim.length() == 0 || strTrim.indexOf(0) != -1 || strTrim2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + strTrim + ": " + strTrim2);
            }
            strArr[i] = strTrim;
            strArr[i + 1] = strTrim2;
            i += 2;
        }
        return new HttpHeaders(strArr);
    }

    public static HttpHeaders of(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        }
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i = 0; i < strArr2.length; i++) {
            String str = strArr2[i];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i] = str.trim();
        }
        for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
            String str2 = strArr2[i2];
            String str3 = strArr2[i2 + 1];
            if (str2.length() == 0 || str2.indexOf(0) != -1 || str3.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + str2 + ": " + str3);
            }
        }
        return new HttpHeaders(strArr2);
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpHeaders) && Arrays.equals(((HttpHeaders) obj).mNamesAndValues, this.mNamesAndValues);
    }

    public String get(String str) {
        return get(this.mNamesAndValues, str);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mNamesAndValues);
    }

    public String name(int i) {
        return this.mNamesAndValues[i << 1];
    }

    public Set names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i = 0; i < size; i++) {
            treeSet.add(name(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.mNamesAndValues);
        return builder;
    }

    public int size() {
        return this.mNamesAndValues.length / 2;
    }

    public Map toMap() {
        HashMap map = new HashMap();
        for (String str : names()) {
            map.put(str, get(str));
        }
        return map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            sb.append(name(i));
            sb.append(": ");
            sb.append(value(i));
            sb.append(StringUtils.LF);
        }
        return sb.toString();
    }

    public String value(int i) {
        return this.mNamesAndValues[(i << 1) + 1];
    }
}
