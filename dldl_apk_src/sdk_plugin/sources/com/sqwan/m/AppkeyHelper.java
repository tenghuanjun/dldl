package com.sqwan.m;

import android.content.Context;
import com.duowan.HUYA.SecPackType;
import com.sqwan.bugless.core.ParamsManager;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import kotlin.time.DurationKt;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppkeyHelper {
    private static final String MULTI_SDK = "multi_sdk";
    private static final String PRO_KEY_CONFIG = "config";
    private static Map appkeys;

    static {
        HashMap map = new HashMap();
        appkeys = map;
        map.put(Integer.valueOf(DurationKt.NANOS_IN_MILLIS), "KJn383*52^&*.,");
        appkeys.put(Integer.valueOf(SecPackType._KSecPackTypeMLiveComment), "7Q/Rh-p_goN,zd?");
        appkeys.put(Integer.valueOf(SecPackType._KSecPackTypeMLiveEndLive), "uUKlGp6W0mE$qHnw3f+vSo1rTc4e5FIM");
        appkeys.put(1011958, "gEBn7bws0cI24R83D*jePtrp6UXK.N5S");
        appkeys.put(1012567, "slDMjCNm0na18Jdxq79HgocQTf:GvF/W");
        appkeys.put(1012578, "gEBn7bws0cI24R83D*jePtrp6UXK.N5S");
        appkeys.put(1009416, "gsIfWNBvMbJr96c!uHk3jlxPDGVK8T.z");
        appkeys.put(1006697, "MnJ9KOzcrDw8yf4GCeI6.3?kPNl5Vmp0");
        appkeys.put(1014489, "MnJ9KOzcrDw8yf4GCeI6.3?kPNl5Vmp0");
        appkeys.put(1013951, "F@9hLZMlSDCsGb6/EuimBY5R3WH4XvQx");
        appkeys.put(1004620, "CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1");
        appkeys.put(1016744, "DBbog$5cvVyfTAZx*eSadrFi9uw86ICU");
        appkeys.put(1012710, "gsIfWNBvMbJr96c!uHk3jlxPDGVK8T.z");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Properties readProperties(android.content.Context r3) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.Resources r3 = r3.getResources()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            java.lang.String r1 = "multi_sdk"
            java.io.InputStream r3 = r3.open(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            java.util.Properties r1 = new java.util.Properties     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L3b
            r1.<init>()     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L3b
            r1.load(r3)     // Catch: java.io.IOException -> L22 java.lang.Throwable -> L3b
            if (r3 == 0) goto L3a
            r3.close()     // Catch: java.io.IOException -> L1d
            goto L3a
        L1d:
            r3 = move-exception
            r3.printStackTrace()
            goto L3a
        L22:
            r0 = move-exception
            goto L32
        L24:
            r1 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L32
        L29:
            r3 = move-exception
            r2 = r0
            r0 = r3
            r3 = r2
            goto L3c
        L2e:
            r3 = move-exception
            r1 = r0
            r0 = r3
            r3 = r1
        L32:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3b
            if (r3 == 0) goto L3a
            r3.close()     // Catch: java.io.IOException -> L1d
        L3a:
            return r1
        L3b:
            r0 = move-exception
        L3c:
            if (r3 == 0) goto L46
            r3.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r3 = move-exception
            r3.printStackTrace()
        L46:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.m.AppkeyHelper.readProperties(android.content.Context):java.util.Properties");
    }

    private static String getConfigFileName(Context context) throws Throwable {
        Properties properties = readProperties(context);
        return properties == null ? "" : properties.getProperty(PRO_KEY_CONFIG);
    }

    public static String getAppkey(Context context) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(getConfigFileName(context));
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamOpen, "utf-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2 && xmlPullParserNewPullParser.getName().equals(ParamsManager.CONFIG_NODE_GAMEID)) {
                    return (String) appkeys.get(Integer.valueOf(Integer.parseInt(xmlPullParserNewPullParser.nextText().trim())));
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
