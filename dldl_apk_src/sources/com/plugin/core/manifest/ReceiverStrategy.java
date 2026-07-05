package com.plugin.core.manifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.plugin.core.manifest.AndroidManifestInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class ReceiverStrategy implements IComponentStrategy<AndroidManifestInfo.ReceiverInfo> {
    @Override // com.plugin.core.manifest.IComponentStrategy
    public String getXmlTag() {
        return "receiver";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.plugin.core.manifest.IComponentStrategy
    public AndroidManifestInfo.ReceiverInfo parseFromXml(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        AndroidManifestInfo.ReceiverInfo receiverInfo = new AndroidManifestInfo.ReceiverInfo();
        receiverInfo.name = xmlResourceParser.getAttributeValue(AndroidManifestConstants.ANDROID_NAMESPACE_URI, "name");
        while (xmlResourceParser.next() != 1) {
            String name = xmlResourceParser.getName();
            if (xmlResourceParser.getEventType() != 2) {
                if (getXmlTag().equals(name) || AndroidManifestConstants.TAG_INTENT_FILTER.equals(name)) {
                    break;
                }
            } else {
                if (AndroidManifestConstants.TAG_INTENT_FILTER.equals(name)) {
                    receiverInfo.priority = xmlResourceParser.getAttributeIntValue(AndroidManifestConstants.ANDROID_NAMESPACE_URI, AndroidManifestConstants.ATTR_PRIORITY, 0);
                }
                if ("action".equals(name)) {
                    if (receiverInfo.actions == null) {
                        receiverInfo.actions = new ArrayList();
                    }
                    String attributeValue = xmlResourceParser.getAttributeValue(AndroidManifestConstants.ANDROID_NAMESPACE_URI, "name");
                    if (!TextUtils.isEmpty(attributeValue)) {
                        receiverInfo.actions.add(attributeValue);
                    }
                }
            }
        }
        return receiverInfo;
    }

    @Override // com.plugin.core.manifest.IComponentStrategy
    public void initializing(Context context, ClassLoader classLoader, AndroidManifestInfo.ReceiverInfo receiverInfo) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(receiverInfo.priority);
            if (receiverInfo.actions == null || receiverInfo.actions.isEmpty()) {
                return;
            }
            Iterator<String> it = receiverInfo.actions.iterator();
            while (it.hasNext()) {
                intentFilter.addAction(it.next());
            }
            context.registerReceiver((BroadcastReceiver) classLoader.loadClass(receiverInfo.name).newInstance(), intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
