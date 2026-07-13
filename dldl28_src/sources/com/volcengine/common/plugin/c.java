package com.volcengine.common.plugin;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.contant.CommonErrorCode;
import com.volcengine.common.contant.InternalConstants;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.DownloadService;
import com.volcengine.common.innerapi.IJsonConverter;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.j.h;
import com.volcengine.j.i;
import com.volcengine.j.k;
import com.volcengine.j.l;
import com.volcengine.j.p;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class c implements PluginService, ConfigService.ConfigObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicInteger f1123a;
    private final Map<String, Integer> b;
    private DownloadService c;
    private final String d;
    private final String e;
    private final String f;
    private final List<PluginConfig> g;
    private final String h;
    private final Map<String, String> i;
    private final ClassLoader j;
    private final List<PluginService.ILoadResultListener> k;
    private List<PluginConfig> l;

    class a implements ParameterizedType {
        a(c cVar) {
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return new Type[]{PluginConfig.class};
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return null;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return List.class;
        }
    }

    class b implements DownloadService.Callback {
        b() {
        }

        @Override // com.volcengine.common.innerapi.DownloadService.Callback
        public void onFailure(DownloadService.Response response, int i, String str) {
            c cVar = c.this;
            String strFileName = response.fileName();
            Pair<Integer, String> pair = CommonErrorCode.ERROR_DOWNLOAD_PLUGIN_FAILED;
            cVar.a(strFileName, ((Integer) pair.first).intValue(), (String) pair.second);
        }

        @Override // com.volcengine.common.innerapi.DownloadService.Callback
        public void onProgress(DownloadService.Response response, int i) {
        }

        @Override // com.volcengine.common.innerapi.DownloadService.Callback
        public void onSuccess(DownloadService.Response response) {
            PluginConfig pluginConfigA = c.this.a(response.fileName(), (List<PluginConfig>) c.this.g);
            if (pluginConfigA == null) {
                return;
            }
            if (c.this.d() == 2) {
                AcLog.v(PluginService.TAG_PLUGIN, "ignoreDownloadCompleted " + pluginConfigA.plugin_name);
                return;
            }
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            String str = response.savePath() + File.separator + response.fileName();
            try {
                concurrentHashMap.put(CommonConstants.KEY_PLUGIN_ZIP_PATH, str);
                concurrentHashMap.put(CommonConstants.KEY_PLUGIN_URL, response.url());
                Iterator<String> it = pluginConfigA.dex_list.iterator();
                while (it.hasNext()) {
                    p.a(str, c.this.b(), it.next());
                }
                Iterator<String> it2 = pluginConfigA.so_list.iterator();
                while (it2.hasNext()) {
                    p.a(str, c.this.f(), it2.next());
                }
            } catch (IOException e) {
                AcLog.e(PluginService.TAG_PLUGIN, Log.getStackTraceString(e));
                concurrentHashMap.put(CommonConstants.KEY_PLUGIN_UNZIP_EXCEPTION, e.getMessage());
            }
            Pair<Integer, String> pairB = com.volcengine.common.plugin.b.a(c.this).b(pluginConfigA, concurrentHashMap);
            if (pairB == null) {
                c.this.e(pluginConfigA.plugin_name);
            } else {
                c.this.a(pluginConfigA.plugin_name, ((Integer) pairB.first).intValue(), (String) pairB.second);
            }
        }

        @Override // com.volcengine.common.innerapi.DownloadService.Callback
        public void onTick(DownloadService.Response response, String str) {
        }
    }

    /* JADX INFO: renamed from: com.volcengine.common.plugin.c$c, reason: collision with other inner class name */
    private static class C0445c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final c f1125a = new c(null);
    }

    private c() {
        String str;
        this.f1123a = new AtomicInteger(0);
        this.b = new ConcurrentHashMap();
        HashMap map = new HashMap();
        this.i = map;
        map.put(InternalConstants.ABI_arm64_v8a_str, "config_arm64_v8a.json");
        map.put(InternalConstants.ABI_armeabi_v7a_str, "config_armeabi_v7a.json");
        this.k = new ArrayList();
        this.g = new ArrayList();
        Context context = SDKContext.getContext();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(absolutePath);
        String str2 = File.separator;
        sb.append(str2);
        sb.append("vedex");
        sb.append(str2);
        String string = sb.toString();
        this.d = string;
        String str3 = absolutePath + str2 + "veso" + str2;
        this.e = str3;
        String str4 = absolutePath + str2 + "veplugin" + str2;
        this.f = str4;
        i.a(string);
        i.a(str3);
        i.a(str4);
        if (com.volcengine.j.c.j()) {
            this.j = new com.volcengine.common.plugin.a(str4, string, str3, context.getClassLoader());
            str = "use PluginClassLoader";
        } else {
            this.j = SDKContext.getContext().getClassLoader();
            str = "use appClassLoader";
        }
        AcLog.d(PluginService.TAG_PLUGIN, str);
        this.h = l.a();
        SDKContext.getConfigService().register("plugin_config", this);
    }

    /* synthetic */ c(a aVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginConfig a(String str, List<PluginConfig> list) {
        for (PluginConfig pluginConfig : list) {
            if (TextUtils.equals(pluginConfig.plugin_name, str)) {
                return pluginConfig;
            }
        }
        return null;
    }

    private List<PluginConfig> a(String str) {
        return (List) SDKContext.getJsonConverter().fromJson(str, new a(this));
    }

    private List<PluginConfig> a(List<PluginConfig> list, List<PluginConfig> list2) {
        int i;
        Map<String, Integer> map;
        String str;
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        this.b.clear();
        for (PluginConfig pluginConfig : list) {
            if (!pluginConfig.checkHasInstalled(this.j, null)) {
                if (pluginConfig.checkPluginFilesExist(this.d, this.e, b(pluginConfig.plugin_name, list2))) {
                    if (com.volcengine.common.plugin.b.a(this).b(pluginConfig, Collections.EMPTY_MAP) != null) {
                        map = this.b;
                        str = pluginConfig.plugin_name;
                        i = 2;
                    }
                    map.put(str, Integer.valueOf(i));
                } else {
                    pluginConfig.deletePluginFiles(this.d, this.e);
                    this.b.put(pluginConfig.plugin_name, 0);
                    arrayList.add(pluginConfig);
                    sb.append(pluginConfig.plugin_name);
                    sb.append(",");
                }
            }
            map = this.b;
            str = pluginConfig.plugin_name;
            i = 1;
            map.put(str, Integer.valueOf(i));
        }
        AcLog.v(PluginService.TAG_PLUGIN, "initPluginDownloadList: " + ((Object) sb));
        return arrayList;
    }

    private void a(int i) {
        this.f1123a.set(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        Iterator it = new ArrayList(this.k).iterator();
        while (it.hasNext()) {
            ((PluginService.ILoadResultListener) it.next()).onLoadFailed(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str, String str2) {
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_loadPluginFailed, new com.volcengine.i.b().a(i).a(str).c(str2).b(CommonConstants.VALUE_LEVEL_WARNING).a());
        Iterator it = new ArrayList(this.k).iterator();
        while (it.hasNext()) {
            ((PluginService.ILoadResultListener) it.next()).onLoadFailed(i, str);
        }
    }

    private void a(PluginConfig pluginConfig) {
        this.c.downloadFile(pluginConfig.download_url, pluginConfig.plugin_name, this.f, pluginConfig.md5, new b());
    }

    private void a(String str, int i) {
        boolean zContainsKey = this.b.containsKey(str);
        AcLog.v(PluginService.TAG_PLUGIN, "setPluginStatus pluginName: " + str + ", status: " + i + ", contain: " + zContainsKey);
        if (zContainsKey) {
            this.b.put(str, Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(String str, final int i, final String str2) {
        com.volcengine.i.b bVar = new com.volcengine.i.b();
        Pair<Integer, String> pair = CommonErrorCode.ERROR_LOAD_PLUGIN_FAILED;
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_loadPluginFailed, bVar.a(((Integer) pair.first).intValue()).a((String) pair.second).b(CommonConstants.VALUE_LEVEL_WARNING).b(i).c(str2).a(CommonConstants.KEY_PLUGIN_NAME, str).a());
        if (d() == 3) {
            return;
        }
        a(3);
        SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.common.plugin.c$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, str2);
            }
        });
    }

    private void a(List<PluginConfig> list) {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        ArrayList<File> arrayList = new ArrayList();
        File file = new File(this.d);
        if (i.f(file) && (fileArrListFiles2 = file.listFiles()) != null) {
            arrayList.addAll(Arrays.asList(fileArrListFiles2));
        }
        File file2 = new File(this.e);
        if (i.f(file2) && (fileArrListFiles = file2.listFiles()) != null) {
            arrayList.addAll(Arrays.asList(fileArrListFiles));
        }
        ArrayList arrayList2 = new ArrayList();
        for (PluginConfig pluginConfig : list) {
            List<String> list2 = pluginConfig.dex_list;
            if (list2 != null) {
                arrayList2.addAll(list2);
            }
            List<String> list3 = pluginConfig.so_list;
            if (list3 != null) {
                arrayList2.addAll(list3);
            }
        }
        for (File file3 : arrayList) {
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    AcLog.v(PluginService.TAG_PLUGIN, "delete: " + file3);
                    i.c(file3);
                    break;
                }
                if (TextUtils.equals(file3.getName(), (String) it.next())) {
                    break;
                }
            }
        }
    }

    private boolean a() {
        return (this.b.containsValue(0) || this.b.containsValue(2)) ? false : true;
    }

    private String b(String str, List<PluginConfig> list) {
        PluginConfig pluginConfigA = a(str, list);
        if (pluginConfigA == null) {
            return null;
        }
        return pluginConfigA.md5;
    }

    private List<PluginConfig> b(List<PluginConfig> list, List<PluginConfig> list2) {
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>(list2);
        } else {
            for (PluginConfig pluginConfig : list2) {
                if (a(pluginConfig.plugin_name, list) == null) {
                    list.add(pluginConfig);
                }
            }
        }
        AcLog.v(PluginService.TAG_PLUGIN, "mergeConfigs: " + list);
        return list;
    }

    private void b(final int i, final String str, final String str2) {
        a(3);
        SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.common.plugin.c$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, str, str2);
            }
        });
    }

    private void b(String str) {
        PluginConfig pluginConfigA;
        if (!str.startsWith("com.volcengine.cloudcore") || (pluginConfigA = a("CloudCore", this.g)) == null) {
            return;
        }
        pluginConfigA.deletePluginFiles(this.d, this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        c(b((List<PluginConfig>) list, i()));
    }

    public static c c() {
        return C0445c.f1125a;
    }

    private void c(List<PluginConfig> list) {
        this.g.clear();
        String str = this.f + "ve_plugin_config.json";
        List<PluginConfig> listD = d(str);
        List<PluginConfig> listA = a(list, listD);
        for (Map.Entry<String, Integer> entry : this.b.entrySet()) {
            if (entry.getValue().intValue() == 2) {
                Pair<Integer, String> pair = CommonErrorCode.ERROR_LOAD_PLUGIN_FAILED;
                a(entry.getKey(), ((Integer) pair.first).intValue(), (String) pair.second);
                return;
            }
        }
        if (listA.isEmpty()) {
            h();
            return;
        }
        this.g.addAll(list);
        a(list);
        Iterator<PluginConfig> it = listA.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        h.a(str, SDKContext.getJsonConverter().toJson(list));
        IJsonConverter jsonConverter = SDKContext.getJsonConverter();
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_processPluginConfig, new com.volcengine.i.b().a(CommonConstants.KEY_PLUGIN_DOWNLOAD_LIST, jsonConverter.toJson(listA)).a(CommonConstants.KEY_LOCAL_PLUGIN_LIST, jsonConverter.toJson(listD)).a(CommonConstants.KEY_PLUGIN_CONFIG_LIST, jsonConverter.toJson(list)).a());
    }

    private boolean c(String str) {
        return TextUtils.equals(str, InternalConstants.ABI_armeabi_v7a_str) || TextUtils.equals(str, InternalConstants.ABI_arm64_v8a_str);
    }

    private List<PluginConfig> d(String str) {
        List<PluginConfig> arrayList = new ArrayList<>();
        try {
            if (i.d(str)) {
                arrayList = a(h.b(str));
            }
        } catch (Throwable th) {
            AcLog.e(PluginService.TAG_PLUGIN, "load plugin config failed: " + th.getMessage());
            i.b(str);
        }
        return arrayList == null ? new ArrayList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e(String str) {
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_loadPluginSucceed, new com.volcengine.i.b().a(CommonConstants.KEY_PLUGIN_NAME, str).a());
        a(str, 1);
        if (a()) {
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        Iterator it = new ArrayList(this.k).iterator();
        while (it.hasNext()) {
            ((PluginService.ILoadResultListener) it.next()).onLoadSuccess();
        }
    }

    private void h() {
        AcLog.v(PluginService.TAG_PLUGIN, "onAllPluginLoaded");
        a(2);
        SDKContext.getMonitorService().reportOnlyEvent(CommonConstants.event_loadPluginOverallSucceed);
        SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.common.plugin.c$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.g();
            }
        });
    }

    private List<PluginConfig> i() {
        if (this.l == null) {
            String strA = h.a(this.i.get(this.h));
            this.l = a(strA);
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_loadDefaultPluginConfig, Collections.singletonMap("plugin_config", strA));
        }
        return this.l;
    }

    @Override // com.volcengine.common.innerapi.PluginService
    public void addLoadResultListener(PluginService.ILoadResultListener iLoadResultListener) {
        if (d() == 2) {
            iLoadResultListener.onLoadSuccess();
        } else {
            if (this.k.contains(iLoadResultListener)) {
                return;
            }
            this.k.add(iLoadResultListener);
        }
    }

    public String b() {
        return this.d;
    }

    public int d() {
        return this.f1123a.get();
    }

    public ClassLoader e() {
        return this.j;
    }

    public String f() {
        return this.e;
    }

    @Override // com.volcengine.common.innerapi.PluginService
    public boolean isLoadedClass(String str) {
        try {
            k.a(this.j, str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.volcengine.common.innerapi.PluginService
    public void load(Context context) {
        Objects.requireNonNull(context, "context is null");
        if (d() == 1) {
            AcLog.w(PluginService.TAG_PLUGIN, "plugin is loading");
            return;
        }
        a(1);
        if (!c(this.h)) {
            Pair<Integer, String> pair = CommonErrorCode.ERROR_ABI_IS_NOT_SUPPORT;
            b(((Integer) pair.first).intValue(), (String) pair.second, "");
        } else {
            System.currentTimeMillis();
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_pluginManagerStart, Collections.singletonMap(CommonConstants.KEY_ABI, this.h));
            this.c = SDKContext.getDownloadService();
            ((com.volcengine.common.config.a) SDKContext.getConfigService()).c();
        }
    }

    @Override // com.volcengine.common.innerapi.PluginService
    public <T> T loadClass(String str, Object... objArr) {
        String str2;
        Constructor<?> constructor;
        T t = null;
        try {
            Class<?> cls = Class.forName(str, true, this.j);
            if (objArr == null || objArr.length <= 0) {
                constructor = cls.getConstructor(null);
            } else {
                Class<?>[] clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
                constructor = cls.getConstructor(clsArr);
            }
            constructor.setAccessible(true);
            t = (T) constructor.newInstance(objArr);
            str2 = null;
        } catch (Throwable th) {
            str2 = Log.getStackTraceString(th) + StringUtils.LF + str;
            AcLog.d(PluginService.TAG_PLUGIN, "loadClass ：" + str2);
        }
        if (t == null) {
            b(str);
            Pair<Integer, String> pair = CommonErrorCode.ERROR_LOAD_CLASS_ERROR;
            b(((Integer) pair.first).intValue(), (String) pair.second, str2);
        }
        return t;
    }

    @Override // com.volcengine.common.innerapi.ConfigService.ConfigObserver
    public void onReceiveConfig(String str, String str2) {
        final List<PluginConfig> listA;
        AcLog.v(PluginService.TAG_PLUGIN, "onReceiveConfig: configName = [" + str + "], config = [" + str2 + "]");
        if ("plugin_config".equals(str)) {
            boolean zIsEmptyConfig = SDKContext.isEmptyConfig(str2);
            if (zIsEmptyConfig) {
                str2 = SDKContext.getConfigService().getConfig(str);
                zIsEmptyConfig = SDKContext.isEmptyConfig(str2);
            }
            if (zIsEmptyConfig) {
                listA = null;
            } else {
                listA = a(str2);
                if (listA != null && !listA.isEmpty()) {
                    Iterator<PluginConfig> it = listA.iterator();
                    boolean z = false;
                    while (it.hasNext()) {
                        if (!it.next().checkValidity()) {
                            it.remove();
                            z = true;
                        }
                    }
                    if (z) {
                        SDKContext.getMonitorService().reportCategory(CommonConstants.event_checkRemotePluginConfigFailed, Collections.singletonMap("plugin_config", str2));
                    }
                }
            }
            SDKContext.getExecutorsService().executeIO(new Runnable() { // from class: com.volcengine.common.plugin.c$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.b(listA);
                }
            });
        }
    }

    @Override // com.volcengine.common.innerapi.PluginService
    public void removeLoadResultListener(PluginService.ILoadResultListener iLoadResultListener) {
        this.k.remove(iLoadResultListener);
    }
}
