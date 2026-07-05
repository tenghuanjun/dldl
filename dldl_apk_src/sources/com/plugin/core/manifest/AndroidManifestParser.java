package com.plugin.core.manifest;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.plugin.core.manifest.AndroidManifestInfo;
import com.plugin.core.tool.PluginLog;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class AndroidManifestParser {
    private static final ReceiverStrategy RECEIVER_STRATEGY = new ReceiverStrategy();

    public static AndroidManifestInfo parseAndroidManifest(Context context, int i) throws XmlPullParserException, IOException {
        AndroidManifestInfo.ReceiverInfo fromXml;
        AndroidManifestInfo androidManifestInfo = new AndroidManifestInfo();
        XmlResourceParser xmlResourceParserOpenXmlResourceParser = context.getAssets().openXmlResourceParser(i, AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME);
        do {
            if (xmlResourceParserOpenXmlResourceParser.getEventType() == 2 && RECEIVER_STRATEGY.getXmlTag().equals(xmlResourceParserOpenXmlResourceParser.getName()) && (fromXml = RECEIVER_STRATEGY.parseFromXml(xmlResourceParserOpenXmlResourceParser)) != null) {
                if (androidManifestInfo.receivers == null) {
                    androidManifestInfo.receivers = new ArrayList();
                }
                androidManifestInfo.receivers.add(fromXml);
            }
        } while (xmlResourceParserOpenXmlResourceParser.next() != 1);
        return androidManifestInfo;
    }

    public static void initializingComponent(Context context, ClassLoader classLoader, AndroidManifestInfo androidManifestInfo) {
        if (androidManifestInfo.receivers != null) {
            for (int i = 0; i < androidManifestInfo.receivers.size(); i++) {
                AndroidManifestInfo.ReceiverInfo receiverInfo = androidManifestInfo.receivers.get(i);
                if (receiverInfo != null) {
                    PluginLog.d("[Manifest]初始化广播组件: " + receiverInfo.name);
                    RECEIVER_STRATEGY.initializing(context, classLoader, receiverInfo);
                }
            }
        }
    }
}
