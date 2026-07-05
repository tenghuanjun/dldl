package com.duowan.ark.module;

import android.os.Bundle;
import com.duowan.ark.Ark;
import com.duowan.ark.ArkUtils;
import com.duowan.ark.NoProguard;
import com.duowan.ark.util.FP;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkModule implements NoProguard {
    private Bundle mArguments = new Bundle();
    private List<Class<? extends ArkModule>> mDependModules;

    public void setArguments(Bundle bundle) {
        this.mArguments = bundle;
    }

    public Bundle getArguments() {
        return this.mArguments;
    }

    public void onStart() {
        startDependModule();
        ArkUtils.register(this);
    }

    public void onStop() {
        ArkUtils.unregister(this);
        stopDependModule();
    }

    private void startDependModule() {
        initDependModulesIfNeed();
        Iterator<Class<? extends ArkModule>> it = this.mDependModules.iterator();
        while (it.hasNext()) {
            Ark.startModule(it.next());
        }
    }

    private void stopDependModule() {
        Iterator<Class<? extends ArkModule>> it = this.mDependModules.iterator();
        while (it.hasNext()) {
            Ark.stopModule(it.next());
        }
    }

    private void initDependModulesIfNeed() {
        List<Class<? extends ArkModule>> list;
        if (this.mDependModules != null) {
            return;
        }
        IAModule iAModule = (IAModule) getClass().getAnnotation(IAModule.class);
        if (iAModule == null) {
            list = Collections.emptyList();
        } else {
            list = FP.toList((Object[]) iAModule.depend());
        }
        this.mDependModules = list;
    }
}
