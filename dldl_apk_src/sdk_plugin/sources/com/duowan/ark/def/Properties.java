package com.duowan.ark.def;

import com.duowan.ark.ArkProperties;
import com.duowan.ark.ArkUtils;
import com.duowan.ark.bind.DependencyProperty;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Properties {
    public static final DependencyProperty<Boolean> networkAvailable = new DependencyProperty<Boolean>(true) { // from class: com.duowan.ark.def.Properties.1
        @Override // com.duowan.ark.bind.DependencyProperty
        public synchronized void set(Boolean bool) {
            boolean zNeedNotify = needNotify(bool);
            super.set(bool);
            if (zNeedNotify) {
                ArkUtils.send(new ArkProperties.NetworkAvailableSet(get(), bool));
            }
        }
    };
    public static final DependencyProperty<String> networkType = new DependencyProperty<String>("unknown") { // from class: com.duowan.ark.def.Properties.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.duowan.ark.bind.DependencyProperty
        public boolean needNotify(String str) {
            return true;
        }
    };
}
