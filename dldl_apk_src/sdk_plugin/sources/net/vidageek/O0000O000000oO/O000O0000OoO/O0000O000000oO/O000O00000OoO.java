package net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
enum O000O00000OoO {
    VOID(Void.TYPE),
    BOOLEAN(Boolean.TYPE),
    BYTE(Byte.TYPE),
    SHORT(Short.TYPE),
    CHAR(Character.TYPE),
    INT(Integer.TYPE),
    LONG(Long.TYPE),
    FLOAT(Float.TYPE),
    DOUBLE(Double.TYPE);

    private final Class<?> O00O0000o00O;

    O000O00000OoO(Class cls) {
        this.O00O0000o00O = cls;
    }

    public static Class<?> O0000O000000oO(String str) {
        for (O000O00000OoO o000O00000OoO : values()) {
            if (o000O00000OoO.O00O0000o00O.toString().equals(str)) {
                return o000O00000OoO.O00O0000o00O;
            }
        }
        return null;
    }
}
