package com.nirvana.tools.crash;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class StackAnalyzer {
    private List<SdkInfo> filterSdks;
    private int packageCount = 0;
    private static final Pattern PATTERN = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*[.])+(?!java)([a-zA-Z_][a-zA-Z0-9_]+)");
    private static final Pattern NPL_PATTERN = Pattern.compile("(java.lang.NullPointerException: Attempt to invoke virtual method 'void )([a-zA-Z_][a-zA-Z0-9_]*[.])+(?!java)([a-zA-Z_][a-zA-Z0-9_]+)");

    StackAnalyzer() {
    }

    private boolean isClassInPackages(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && !list.isEmpty()) {
            for (String str2 : list) {
                if (str.startsWith(str2) || str2.startsWith(str) || str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSdkNullPointerException(String str, List<String> list) {
        if (!str.contains("java.lang.NullPointerException")) {
            return false;
        }
        Matcher matcher = NPL_PATTERN.matcher(str);
        if (!matcher.find()) {
            return false;
        }
        String strGroup = matcher.group();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (strGroup.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private LinkedHashSet<String> parseCrashClasses(String str) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        String[] strArrSplit = str.split("\tat");
        if (strArrSplit != null && strArrSplit.length > 0) {
            for (String str2 : strArrSplit) {
                Matcher matcher = PATTERN.matcher(str2);
                while (matcher.find()) {
                    String strGroup = matcher.group();
                    if (!TextUtils.isEmpty(strGroup)) {
                        String strSubstring = strGroup.substring(0, strGroup.lastIndexOf("."));
                        if (strSubstring.contains(".")) {
                            linkedHashSet.add(strSubstring);
                        }
                    }
                }
            }
        }
        return linkedHashSet;
    }

    public SdkInfo checkJavaCrashInSdk(String str) {
        for (SdkInfo sdkInfo : this.filterSdks) {
            if (isJavaCrashInSdk(str, sdkInfo.getPackageNames(), sdkInfo.getSdkInterfaces())) {
                return sdkInfo;
            }
        }
        return null;
    }

    public SdkInfo checkNativeCrashInSdk(String str) {
        for (SdkInfo sdkInfo : this.filterSdks) {
            if (isNativeCrashInSdk(str, sdkInfo.getNativeLibraries())) {
                return sdkInfo;
            }
        }
        return null;
    }

    public void initAddSdkConfig(SdkInfo sdkInfo) {
        if (this.filterSdks == null) {
            this.filterSdks = new ArrayList();
        }
        this.filterSdks.add(sdkInfo);
        this.packageCount += (sdkInfo.getPackageNames() == null || sdkInfo.getPackageNames().isEmpty()) ? 0 : sdkInfo.getPackageNames().size();
    }

    public boolean isJavaCrashInSdk(String str, List<String> list, List<String> list2) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str) || isSdkNullPointerException(str, list2)) {
            return false;
        }
        Iterator<String> it = parseCrashClasses(str).iterator();
        while (it.hasNext()) {
            if (isClassInPackages(it.next(), list)) {
                return true;
            }
        }
        return false;
    }

    public boolean isJavaCrashInSdk(Throwable th, List<String> list, List<String> list2) {
        return isJavaCrashInSdk(Log.getStackTraceString(th), list, list2);
    }

    public boolean isNativeCrashInSdk(String str, List<String> list) {
        if (list != null && !list.isEmpty()) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }
}
