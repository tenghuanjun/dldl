package com.ishumei.O000O00000oO;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000o0OO {
    private static O00O0000o0OO O000O00000o0O;
    private Object O0000O000000oO;
    private Context O000O00000OoO;

    private O00O0000o0OO() {
        this.O0000O000000oO = null;
        this.O000O00000OoO = null;
        try {
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO != null) {
                this.O000O00000OoO = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
                this.O0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac868c8b9a92ac9a8d89969c9a")).O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f9790919a"));
            }
        } catch (Exception unused) {
        }
    }

    private int O0000O000000oO(Object obj, String str, Object... objArr) throws NoSuchMethodException {
        Class<?> cls = obj.getClass();
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0140, code lost:
    
        if (r8 != 4) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0143, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:?, code lost:
    
        return r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x013a A[PHI: r6 r7 r8
  0x013a: PHI (r6v11 android.telephony.gsm.GsmCellLocation) = 
  (r6v1 android.telephony.gsm.GsmCellLocation)
  (r6v1 android.telephony.gsm.GsmCellLocation)
  (r6v1 android.telephony.gsm.GsmCellLocation)
  (r6v8 android.telephony.gsm.GsmCellLocation)
  (r6v1 android.telephony.gsm.GsmCellLocation)
  (r6v1 android.telephony.gsm.GsmCellLocation)
 binds: [B:11:0x0020, B:72:0x013a, B:25:0x006e, B:56:0x0113, B:48:0x00ec, B:38:0x009d] A[DONT_GENERATE, DONT_INLINE]
  0x013a: PHI (r7v17 android.telephony.cdma.CdmaCellLocation) = 
  (r7v1 android.telephony.cdma.CdmaCellLocation)
  (r7v1 android.telephony.cdma.CdmaCellLocation)
  (r7v1 android.telephony.cdma.CdmaCellLocation)
  (r7v1 android.telephony.cdma.CdmaCellLocation)
  (r7v3 android.telephony.cdma.CdmaCellLocation)
  (r7v1 android.telephony.cdma.CdmaCellLocation)
 binds: [B:11:0x0020, B:72:0x013a, B:25:0x006e, B:56:0x0113, B:48:0x00ec, B:38:0x009d] A[DONT_GENERATE, DONT_INLINE]
  0x013a: PHI (r8v11 char) = (r8v1 char), (r8v1 char), (r8v8 char), (r8v8 char), (r8v8 char), (r8v8 char) binds: [B:11:0x0020, B:72:0x013a, B:25:0x006e, B:56:0x0113, B:48:0x00ec, B:38:0x009d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.telephony.CellLocation O0000O000000oO(java.util.List<?> r17) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O00000oO.O00O0000o0OO.O0000O000000oO(java.util.List):android.telephony.CellLocation");
    }

    public static O00O0000o0OO O0000O000000oO() {
        if (O000O00000o0O == null) {
            synchronized (O00O0000o0OO.class) {
                if (O000O00000o0O == null) {
                    O000O00000o0O = new O00O0000o0OO();
                }
            }
        }
        return O000O00000o0O;
    }

    private Object O0000O000000oO(String str) {
        try {
            if (this.O000O00000OoO != null) {
                return new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000OoO.getApplicationContext()).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac868c8b9a92ac9a8d89969c9a")).O0000O000000oO(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private Object O000O00000OoO(Object obj, String str, Object... objArr) throws NoSuchMethodException {
        Class<?> cls = obj.getClass();
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    private int O00O0000OooO() {
        try {
            try {
                Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd18b9a939a8f97909186d1b2ac9692ab9a939a8f97909186b29e919e989a8d"));
                return 1;
            } catch (Exception unused) {
                Class.forName(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8dcd"));
                return 2;
            }
        } catch (SecurityException unused2) {
            return -1001;
        } catch (Exception unused3) {
            return 0;
        }
    }

    private Object O00O0000o00O() {
        String str;
        int iO00O0000OooO = O00O0000OooO();
        if (iO00O0000OooO == 0) {
            str = "8f9790919a";
        } else if (iO00O0000OooO == 1) {
            str = "8f9790919aa0928c9692";
        } else {
            if (iO00O0000OooO != 2) {
                return null;
            }
            str = "8f9790919acd";
        }
        return O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O(str));
    }

    private Class<?> O00O0000o0O() {
        String str;
        String strO000O0000Oo0O;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int iO00O0000OooO = O00O0000OooO();
        try {
            if (iO00O0000OooO == 0) {
                str = "9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8d";
            } else if (iO00O0000OooO == 1) {
                str = "9e919b8d90969bd18b9a939a8f97909186d1b2ac9692ab9a939a8f97909186b29e919e989a8d";
            } else {
                if (iO00O0000OooO != 2) {
                    strO000O0000Oo0O = null;
                    return systemClassLoader.loadClass(strO000O0000Oo0O);
                }
                str = "9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8dcd";
            }
            return systemClassLoader.loadClass(strO000O0000Oo0O);
        } catch (Exception unused) {
            return null;
        }
        strO000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O(str);
    }

    public String O0000O000000oO(int i) {
        try {
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            return this.O0000O000000oO != null ? (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbb9a89969c9ab69b")).O0000O000000oO(Integer.valueOf(i)) : "";
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO | Exception unused) {
            return "";
        }
    }

    public String O000O00000OoO() {
        try {
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb396919aceb18a929d9a8d")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO | Exception unused) {
            return "";
        }
    }

    public String O000O00000o0O() {
        try {
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbb9a89969c9ab69b")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO | Exception unused) {
            return "";
        }
    }

    public String O000O00000oO() {
        try {
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac9692b08f9a8d9e8b908d")).O0000O000000oO();
            if (str != null) {
                try {
                    if (str.isEmpty()) {
                    }
                } catch (Exception unused) {
                }
                return str;
            }
            String str2 = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb19a8b88908d94b08f9a8d9e8b908db19e929a")).O0000O000000oO();
            return str2 == null ? "" : str2;
        } catch (Exception unused2) {
            return "";
        }
    }

    public String O000O0000O0oO() {
        try {
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac8a9d8c9c8d969d9a8db69b")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO | Exception unused) {
            return "";
        }
    }

    public String O000O0000OOoO() {
        try {
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac9692ac9a8d969e93b18a929d9a8d")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO | Exception unused) {
            return "";
        }
    }

    public CellLocation O000O0000Oo0O() throws NoSuchMethodException {
        Object objO000O00000OoO;
        List<?> list;
        try {
            Object objO00O0000o00O = O00O0000o00O();
            if (objO00O0000o00O == null) {
                return null;
            }
            Class<?> clsO00O0000o0O = O00O0000o0O();
            if (clsO00O0000o0O.isInstance(objO00O0000o00O)) {
                Object objCast = clsO00O0000o0O.cast(objO00O0000o00O);
                String strO000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbc9a9393b3909c9e8b969091");
                try {
                    objO000O00000OoO = O000O00000OoO(objCast, strO000O0000Oo0O, new Object[0]);
                } catch (Exception unused) {
                    objO000O00000OoO = null;
                }
                if (objO000O00000OoO == null) {
                    try {
                        objO000O00000OoO = O000O00000OoO(objCast, strO000O0000Oo0O, 0);
                        if (objO000O00000OoO == null) {
                            objO000O00000OoO = O000O00000OoO(objCast, strO000O0000Oo0O, 1);
                        }
                    } catch (Exception e) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
                    }
                }
                if (objO000O00000OoO == null) {
                    try {
                        objO000O00000OoO = O000O00000OoO(objCast, com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbc9a9393b3909c9e8b969091b89a92969196"), 1);
                    } catch (Exception unused2) {
                    }
                }
                if (objO000O00000OoO == null) {
                    try {
                        list = (List) O000O00000OoO(objCast, com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbe9393bc9a9393b6919990"), new Object[0]);
                    } catch (Exception unused3) {
                        list = null;
                    }
                    objO000O00000OoO = O0000O000000oO(list);
                }
            } else {
                objO000O00000OoO = null;
            }
            if (objO000O00000OoO != null) {
                return (CellLocation) objO000O00000OoO;
            }
            return null;
        } catch (Exception unused4) {
            return null;
        }
    }

    public HashMap<String, String> O000O0000OoO() {
        String strO000O0000Oo0O;
        int baseStationLongitude;
        HashMap<String, String> map = new HashMap<>();
        String strO000O0000Oo0O2 = "";
        try {
            int iCheckCallingPermission = this.O000O00000OoO.checkCallingPermission("android.permission.ACCESS_FINE_LOCATION");
            int iCheckCallingPermission2 = this.O000O00000OoO.checkCallingPermission("android.permission.ACCESS_COARSE_LOCATION");
            if (iCheckCallingPermission != 0 && iCheckCallingPermission2 != 0) {
                return map;
            }
            strO000O0000Oo0O2 = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b1b0afbaadb2");
            CellLocation cellLocationO000O0000Oo0O = O000O0000Oo0O();
            if (cellLocationO000O0000Oo0O == null && this.O0000O000000oO != null) {
                cellLocationO000O0000Oo0O = (CellLocation) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbc9a9393b3909c9e8b969091")).O0000O000000oO();
            }
            if (cellLocationO000O0000Oo0O != null) {
                if (cellLocationO000O0000Oo0O instanceof GsmCellLocation) {
                    map.put("type", com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("988c92"));
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocationO000O0000Oo0O;
                    map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c969b"), String.valueOf(gsmCellLocation.getCid()));
                    strO000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("939e9c");
                    baseStationLongitude = gsmCellLocation.getLac();
                } else if (cellLocationO000O0000Oo0O instanceof CdmaCellLocation) {
                    map.put("type", com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c9b929e"));
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocationO000O0000Oo0O;
                    map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d969b"), String.valueOf(cdmaCellLocation.getBaseStationId()));
                    map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("91969b"), String.valueOf(cdmaCellLocation.getNetworkId()));
                    map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c969b"), String.valueOf(cdmaCellLocation.getSystemId()));
                    map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("939e8b"), String.valueOf(cdmaCellLocation.getBaseStationLatitude()));
                    strO000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("939198");
                    baseStationLongitude = cdmaCellLocation.getBaseStationLongitude();
                }
                map.put(strO000O0000Oo0O, String.valueOf(baseStationLongitude));
            }
        } catch (net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO unused) {
            map.put("type", strO000O0000Oo0O2);
        } catch (Exception unused2) {
        }
        return map;
    }
}
