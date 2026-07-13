package com.volcengine.common.mountservice;

import android.content.Context;
import com.volcengine.androidcloud.common.api.MountService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MountServiceManager implements MountService {
    private final List<MountService> mServiceList;

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static MountServiceManager f1119a = new MountServiceManager();
    }

    private MountServiceManager() {
        this.mServiceList = new ArrayList();
    }

    public static MountServiceManager getInstance() {
        return b.f1119a;
    }

    public void attachMountService(MountService mountService) {
        this.mServiceList.add(mountService);
    }

    public void detachMountService(MountService mountService) {
        if (this.mServiceList.contains(mountService)) {
            this.mServiceList.remove(mountService);
        }
    }

    public List<MountService> getServiceList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mServiceList);
        return arrayList;
    }

    @Override // com.volcengine.androidcloud.common.api.MountService
    public void init(Context context, Map<String, Object> map) {
        Iterator<MountService> it = getServiceList().iterator();
        while (it.hasNext()) {
            it.next().init(context, map);
        }
    }

    @Override // com.volcengine.androidcloud.common.api.MountService
    public void release() {
        Iterator<MountService> it = getServiceList().iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }
}
