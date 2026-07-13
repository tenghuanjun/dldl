package com.bytedance.applog.onekit;

import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.bytedance.bdtracker.x2;
import com.bytedance.bdtracker.y2;
import com.volcengine.onekit.component.Component;
import com.volcengine.onekit.component.ComponentContainer;
import com.volcengine.onekit.component.ComponentFactory;
import com.volcengine.onekit.component.ComponentRegistrar;
import com.volcengine.onekit.component.Dependency;
import com.volcengine.onekit.service.Analytics;
import com.volcengine.onekit.service.Device;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceComponentRegistrar implements ComponentRegistrar {

    public class a implements ComponentFactory<Device> {
        public a(DeviceComponentRegistrar deviceComponentRegistrar) {
        }

        public Object create(ComponentContainer componentContainer) {
            if (!TextUtils.isEmpty(AppLog.getDid())) {
                return new y2();
            }
            AppLog.addDataObserver(new x2(this, componentContainer));
            return null;
        }
    }

    public List<Component> getComponents() {
        return Arrays.asList(Component.builder(Device.class, new Class[0]).addDependency(Dependency.required(Analytics.class)).enablePrivacy().factory(new a(this)).build());
    }
}
