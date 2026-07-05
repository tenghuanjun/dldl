package com.duowan.auk.module;

import android.os.Bundle;
import com.duowan.auk.Ark;
import com.duowan.auk.NoProguard;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.signal.IASlot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@IASlot(executorID = 2)
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
        SignalCenter.register(this);
    }

    public void onStop() {
        SignalCenter.unregister(this);
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
            list = toList(iAModule.depend());
        }
        this.mDependModules = list;
    }

    private <T> List<T> toList(T[] tArr) {
        ArrayList arrayList = new ArrayList();
        if (!empty(tArr)) {
            arrayList.addAll(Arrays.asList(tArr));
        }
        return arrayList;
    }

    private <T> boolean empty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }
}
