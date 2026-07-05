package com.sqwan.common.mod;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ModManager {
    private static volatile ModManager sInstance;
    private Context mContext;
    private final Map<Class<? extends IModBase>, IModBase> mModMap = new HashMap();
    private final Map<Class<? extends IModBase>, ModConfig> mModConfigMap = new HashMap();

    public static ModManager getInstance() {
        if (sInstance == null) {
            synchronized (ModManager.class) {
                if (sInstance == null) {
                    sInstance = new ModManager();
                }
            }
        }
        return sInstance;
    }

    private ModManager() {
    }

    public void clearMod() {
        this.mModConfigMap.clear();
    }

    public void putMod(Class<? extends IModBase> cls, ModConfig modConfig) {
        if (cls == null || modConfig == null) {
            return;
        }
        this.mModConfigMap.put(cls, modConfig);
    }

    public void loadMod(Context context) {
        this.mContext = context;
        Iterator<Map.Entry<Class<? extends IModBase>, ModConfig>> it = this.mModConfigMap.entrySet().iterator();
        while (it.hasNext()) {
            getMod(it.next().getKey());
        }
    }

    public IModBase getMod(Class<? extends IModBase> cls) {
        IModBase iModBase;
        IModBase iModBase2 = this.mModMap.get(cls);
        if (iModBase2 != null) {
            return iModBase2;
        }
        ModConfig modConfig = this.mModConfigMap.get(cls);
        if (modConfig == null) {
            SQLog.w("模块 " + cls.getName() + " 不存在");
            return null;
        }
        String str = modConfig.modImpString;
        boolean z = modConfig.initWithContext;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalStateException("未找到模块配置!");
        }
        try {
            Class<?> clsLoadClass = this.mContext.getClassLoader().loadClass(str);
            if (!z) {
                Constructor<?> declaredConstructor = clsLoadClass.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                iModBase = (IModBase) declaredConstructor.newInstance(new Object[0]);
            } else {
                Constructor<?> declaredConstructor2 = clsLoadClass.getDeclaredConstructor(Context.class);
                declaredConstructor2.setAccessible(true);
                iModBase = (IModBase) declaredConstructor2.newInstance(this.mContext);
            }
            iModBase2 = iModBase;
            SQLog.i("模块 " + clsLoadClass.getName() + " 存在");
            this.mModMap.put(cls, iModBase2);
        } catch (Throwable th) {
            SQLog.e("模块 " + str + " 创建异常", th);
        }
        return iModBase2;
    }

    public static class ModConfig {
        final boolean initWithContext;
        final String modImpString;

        public ModConfig(String str) {
            this(str, false);
        }

        public ModConfig(String str, boolean z) {
            this.modImpString = str;
            this.initWithContext = z;
        }
    }
}
