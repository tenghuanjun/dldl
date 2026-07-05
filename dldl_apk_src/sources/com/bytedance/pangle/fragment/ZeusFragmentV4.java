package com.bytedance.pangle.fragment;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZeusFragmentV4 extends Fragment {
    Application.ActivityLifecycleCallbacks callbacks = new b(this);

    public ZeusFragmentV4() {
        a.a(getClass());
    }

    @Override // android.support.v4.app.Fragment
    public Context getContext() {
        try {
            return ZeusTransformUtils.wrapperContext(super.getContext(), (String) MethodUtils.invokeStaticMethod(getClass(), "_GET_PLUGIN_PKG", new Object[0]));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return super.getContext();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return super.getContext();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return super.getContext();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Zeus.getAppApplication().registerActivityLifecycleCallbacks(this.callbacks);
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        Zeus.getAppApplication().unregisterActivityLifecycleCallbacks(this.callbacks);
        super.onDetach();
    }
}
