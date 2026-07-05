package com.sqwan.liveshow.huya.skin.inflater;

import android.content.Context;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import com.taptap.sdk.kit.internal.p000const.TrackAction;
import java.lang.reflect.Constructor;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class AbsLayoutInflater implements InflaterInterface {
    protected final Object[] mConstructorArgs = new Object[2];
    protected static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    protected static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    protected static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();

    protected View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (TrackAction.VIEW.equals(str)) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return createView(context, str, null);
            }
            for (int i = 0; i < sClassPrefixList.length; i++) {
                View viewCreateView = createView(context, str, sClassPrefixList[i]);
                if (viewCreateView != null) {
                    return viewCreateView;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = null;
            objArr[1] = null;
        }
    }

    private View createView(Context context, String str, String str2) throws InflateException, ClassNotFoundException {
        String str3;
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }
}
