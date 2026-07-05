package com.ishumei.O000O00000oO;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.ishumei.O000O00000OoO.O000O00000OoO;
import com.ishumei.dfp.SMSDK;
import com.sqwan.bugless.util.FileUtil;
import dalvik.system.BaseDexClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private static O0000O000000oO O000O00000OoO;
    private Context O0000O000000oO;

    O0000O000000oO() {
        this.O0000O000000oO = null;
        this.O0000O000000oO = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
    }

    public static O0000O000000oO O0000O000000oO() {
        if (O000O00000OoO == null) {
            synchronized (O0000O000000oO.class) {
                if (O000O00000OoO == null) {
                    O000O00000OoO = new O0000O000000oO();
                }
            }
        }
        return O000O00000OoO;
    }

    private boolean O0000O000000oO(ClassLoader classLoader, String str) {
        if (classLoader == null || !(classLoader instanceof BaseDexClassLoader)) {
            return false;
        }
        try {
            Class<?> cls = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9e93899694d18c868c8b9a92d1bb9a87af9e8b97b3968c8b"));
            Method method = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9e93899694d18c868c8b9a92d1bb9a87af9e8b97b3968c8bdbba939a929a918b")).getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8b90ac8b8d969198"), (Class[]) null);
            Field declaredField = cls.getDeclaredField(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9a87ba939a929a918b8c"));
            declaredField.setAccessible(true);
            Field declaredField2 = BaseDexClassLoader.class.getDeclaredField(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f9e8b97b3968c8b"));
            declaredField2.setAccessible(true);
            Object[] objArr = (Object[]) declaredField.get(declaredField2.get(classLoader));
            for (Object obj : objArr) {
                String str2 = (String) method.invoke(obj, (Object[]) null);
                if (str2 != null && str2.contains(str)) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private Class[] O0000O000000oO(List<String> list) throws ClassNotFoundException {
        Class<?> cls;
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            switch (str) {
                case "int":
                    cls = Integer.TYPE;
                    break;
                case "long":
                    cls = Long.TYPE;
                    break;
                case "boolean":
                    cls = Boolean.TYPE;
                    break;
                case "double":
                    cls = Double.TYPE;
                    break;
                case "float":
                    cls = Float.TYPE;
                    break;
                case "byte":
                    cls = Byte.TYPE;
                    break;
                case "char":
                    cls = Character.TYPE;
                    break;
                case "short":
                    cls = Short.TYPE;
                    break;
                default:
                    cls = Class.forName(str);
                    break;
            }
            arrayList.add(cls);
        }
        Class[] clsArr = new Class[arrayList.size()];
        arrayList.toArray(clsArr);
        return clsArr;
    }

    public String O0000O000000oO(String str) {
        String strO0000O000000oO;
        String string;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            ArrayList<O000O00000OoO.O0000O000000oO> arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string2 = jSONObject.getString("key");
                    String string3 = jSONObject.getString("clazz");
                    String string4 = jSONObject.getString("method");
                    JSONArray jSONArray2 = jSONObject.getJSONArray("param");
                    int i2 = jSONObject.getInt("type");
                    O000O00000OoO.O0000O000000oO o0000O000000oO = new O000O00000OoO.O0000O000000oO();
                    o0000O000000oO.O0000O000000oO(string2);
                    o0000O000000oO.O000O00000OoO(string3);
                    o0000O000000oO.O000O00000o0O(string4);
                    o0000O000000oO.O0000O000000oO(i2);
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        arrayList2.add(jSONArray2.getString(i3));
                    }
                    o0000O000000oO.O0000O000000oO(arrayList2);
                    arrayList.add(o0000O000000oO);
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
                }
            }
            if (arrayList.size() == 0) {
                return "";
            }
            HashMap map = new HashMap();
            for (O000O00000OoO.O0000O000000oO o0000O000000oO2 : arrayList) {
                try {
                    Class<?> cls = Class.forName(o0000O000000oO2.O000O00000OoO().replace("/", FileUtil.FILE_EXTENSION_SEPARATOR));
                    int iO000O0000O0oO = o0000O000000oO2.O000O0000O0oO();
                    List<String> listO000O00000oO = o0000O000000oO2.O000O00000oO();
                    int i4 = 1;
                    if (iO000O0000O0oO == 3) {
                        Constructor<?> constructor = (listO000O00000oO == null || listO000O00000oO.size() == 0) ? cls.getConstructor(new Class[0]) : cls.getConstructor(O0000O000000oO(listO000O00000oO));
                        strO0000O000000oO = o0000O000000oO2.O0000O000000oO();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        if (!Modifier.isNative(constructor.getModifiers())) {
                            i4 = 0;
                        }
                        sb.append(i4);
                        string = sb.toString();
                    } else {
                        Method declaredMethod = (listO000O00000oO == null || listO000O00000oO.size() == 0) ? cls.getDeclaredMethod(o0000O000000oO2.O000O00000o0O(), new Class[0]) : cls.getDeclaredMethod(o0000O000000oO2.O000O00000o0O(), O0000O000000oO(listO000O00000oO));
                        strO0000O000000oO = o0000O000000oO2.O0000O000000oO();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("");
                        if (!Modifier.isNative(declaredMethod.getModifiers())) {
                            i4 = 0;
                        }
                        sb2.append(i4);
                        string = sb2.toString();
                    }
                    map.put(strO0000O000000oO, string);
                } catch (Exception e2) {
                    map.put(o0000O000000oO2.O0000O000000oO(), e2.getClass().getSimpleName());
                }
            }
            return com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Map<?, ?>) map).toString();
        } catch (Exception e3) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e3);
            return "";
        }
    }

    public void O0000O000000oO(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        try {
            Object objInvoke = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19c90918b9a918bd1bc90918b9a878b")).getDeclaredMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac868c8b9a92ac9a8d89969c9a"), String.class).invoke(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO, com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e9c9c9a8c8c969d9693968b86"));
            Method declaredMethod = objInvoke.getClass().getDeclaredMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("968cba919e9d939a9b"), new Class[0]);
            Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bba919e9d939a9bbe9c9c9a8c8c969d9693968b86ac9a8d89969c9ab3968c8b"), Integer.TYPE);
            Object objInvoke2 = declaredMethod.invoke(objInvoke, new Object[0]);
            List list = (List) declaredMethod2.invoke(objInvoke, -1);
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                Object objInvoke3 = obj.getClass().getDeclaredMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb69b"), new Class[0]).invoke(obj, new Object[0]);
                if (objInvoke3 == null) {
                    Object objInvoke4 = obj.getClass().getDeclaredMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bad9a8c9093899ab6919990"), new Class[0]).invoke(obj, new Object[0]);
                    arrayList.add(objInvoke4 == null ? obj.toString() : objInvoke4.toString());
                } else {
                    arrayList.add((String) objInvoke3);
                }
            }
            map2.put("enable", ((Boolean) objInvoke2).booleanValue() ? "1" : "0");
            map2.put("service", arrayList);
            map2.put("suc", "1");
        } catch (Throwable th) {
            map2.put("e", "" + th.getMessage());
            map2.put("suc", "-1");
        }
        map.put("acc", map2);
    }

    public void O0000O000000oO(Map<String, Object> map, String str, boolean z) {
        O0000O000000oO(map, str, z, null, false);
    }

    public void O0000O000000oO(Map<String, Object> map, String str, boolean z, String str2, boolean z2) {
        try {
            String strY1 = SMSDK.y1(z, str2, z2);
            if (strY1 == null) {
                map.put(str, "");
                map.put("aenc", "0");
                return;
            }
            try {
                map.put(str, new JSONObject(strY1));
                map.put("aenc", "0");
            } catch (JSONException unused) {
                map.put(str, strY1);
                map.put("aenc", "4");
            } catch (Exception unused2) {
                map.put(str, strY1);
                map.put("aenc", "0");
            }
        } catch (Exception unused3) {
            map.put(str, "");
            map.put("aenc", "0");
        }
    }

    public List<String> O000O00000OoO() {
        InputMethodManager inputMethodManager;
        List<InputMethodInfo> inputMethodList;
        ArrayList arrayList = new ArrayList();
        try {
            Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
            if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null || (inputMethodList = inputMethodManager.getInputMethodList()) == null) {
                return arrayList;
            }
            Iterator<InputMethodInfo> it = inputMethodList.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public boolean O000O00000OoO(String str) {
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (O0000O000000oO(systemClassLoader, str) || O0000O000000oO(systemClassLoader.getParent(), str)) {
                return true;
            }
            ClassLoader classLoader = getClass().getClassLoader();
            if (O0000O000000oO(classLoader, str)) {
                return true;
            }
            return O0000O000000oO(classLoader.getParent(), str);
        } catch (Exception unused) {
            return false;
        }
    }

    public String O000O00000o0O() {
        try {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                Field declaredField = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19d938a9a8b90908b97d1bd938a9a8b90908b97be9b9e8f8b9a8d")).getDeclaredField(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("92ac9a8d89969c9a"));
                declaredField.setAccessible(true);
                Object obj = declaredField.get(defaultAdapter);
                if (obj == null) {
                    throw new Exception();
                }
                Object objInvoke = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97dbac8b8a9ddbaf8d908786")).getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbe9b9b8d9a8c8c"), (Class[]) null).invoke(obj, (Object[]) null);
                if (objInvoke == null || !(objInvoke instanceof String)) {
                    throw new Exception();
                }
                return (String) objInvoke;
            } catch (Exception unused) {
                return "";
            }
        } catch (Exception unused2) {
            Class<?> cls = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd1908cd1ac9a8d89969c9ab29e919e989a8d"));
            Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8d"));
            Class<?> cls2 = Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8ddbac8b8a9d"));
            Field field = cls2.getField(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b9b6adacaba0bcbeb3b3a0abadbeb1acbebcabb6b0b1"));
            IBinder iBinder = (IBinder) cls.getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac9a8d89969c9a"), String.class).invoke(null, com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d938a9a8b90908b97a0929e919e989a8d"));
            field.getInt(cls2);
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8d"));
                if (Build.VERSION.SDK_INT >= 21) {
                    iBinder.transact(5, parcelObtain, parcelObtain2, 0);
                } else {
                    iBinder.transact(10, parcelObtain, parcelObtain2, 0);
                }
                parcelObtain2.readException();
                String string = parcelObtain2.readString();
                return string == null ? "" : string;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public boolean O000O00000oO() {
        try {
            return O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("a78f908c9a9bbd8d969b989ad1959e8d"));
        } catch (Exception unused) {
            return false;
        }
    }
}
