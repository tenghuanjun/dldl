package com.volcengine.common.config;

import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.SDKSwitchSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SDKSwitchSettingImpl implements SDKSwitchSetting {
    private final Map<String, Boolean> a = new HashMap();
    private final List<SDKSwitchSetting.SwitchSettingLoadedListener> b = new ArrayList();
    private boolean c = false;

    public static class SwitchItem {
        public boolean enable;
        public String name;
    }

    public static class SwitchSetting {
        public List<SwitchItem> items;
    }

    public SDKSwitchSettingImpl() {
        AcLog.d("SDKSwitchSettingImpl", "SDKSwitchSettingImpl()");
        SDKContext.getConfigService().register(ConfigService.switch_config, new ConfigService.ConfigObserver() { // from class: com.volcengine.common.config.SDKSwitchSettingImpl$$ExternalSyntheticLambda0
            @Override // com.volcengine.common.innerapi.ConfigService.ConfigObserver
            public final void onReceiveConfig(String str, String str2) {
                this.f$0.a(str, str2);
            }
        });
    }

    private synchronized void a(String str) {
        for (SwitchItem switchItem : ((SwitchSetting) SDKContext.getJsonConverter().fromJson(str, SwitchSetting.class)).items) {
            this.a.put(switchItem.name, Boolean.valueOf(switchItem.enable));
        }
        this.c = true;
        Iterator<SDKSwitchSetting.SwitchSettingLoadedListener> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onSwitchSettingLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2) {
        AcLog.d("SDKSwitchSettingImpl", "SDK setting" + str2);
        a(str2);
    }

    @Override // com.volcengine.common.innerapi.SDKSwitchSetting
    public void addSwitchSettingLoadedListener(SDKSwitchSetting.SwitchSettingLoadedListener switchSettingLoadedListener) {
        if (this.c) {
            switchSettingLoadedListener.onSwitchSettingLoaded();
        } else {
            this.b.add(switchSettingLoadedListener);
        }
    }

    @Override // com.volcengine.common.innerapi.SDKSwitchSetting
    public boolean getEnable(String str, boolean z) {
        return this.a.containsKey(str) ? this.a.get(str).booleanValue() : z;
    }
}
