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

/* JADX INFO: loaded from: classes3.dex */
public class SDKSwitchSettingImpl implements SDKSwitchSetting {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, Boolean> f1116a = new HashMap();
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
            this.f1116a.put(switchItem.name, Boolean.valueOf(switchItem.enable));
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
        return this.f1116a.containsKey(str) ? this.f1116a.get(str).booleanValue() : z;
    }
}
