package com.plugin.core.manifest;

import android.content.Context;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IComponentStrategy<T> {
    String getXmlTag();

    void initializing(Context context, ClassLoader classLoader, T t);

    T parseFromXml(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException;
}
