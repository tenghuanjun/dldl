package net.vidageek.O0000O000000oO.O0000O000000oO;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O;
import net.vidageek.O0000O000000oO.O000O0000OoO.O000O0000OOoO;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O000O00000OoO {
    private final InputStream O0000O000000oO;

    public O000O00000OoO(InputStream inputStream) {
        this.O0000O000000oO = inputStream;
    }

    private Map<O0000O000000oO, String> O0000O000000oO(InputStream inputStream) {
        HashMap map = new HashMap();
        map.put(O0000O000000oO.REFLECTION_PROVIDER, net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO.O0000O000000oO.class.getName());
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            for (O0000O000000oO o0000O000000oO : O0000O000000oO.values()) {
                if (properties.containsKey(o0000O000000oO.O0000O000000oO())) {
                    map.put(o0000O000000oO, properties.getProperty(o0000O000000oO.O0000O000000oO()).trim());
                }
            }
            return map;
        } catch (IOException e) {
            throw new net.vidageek.O0000O000000oO.O000O00000o0O.O0000O000000oO("could not ready file " + inputStream, e);
        }
    }

    public O000O0000OOoO O0000O000000oO() {
        InputStream inputStream = this.O0000O000000oO;
        if (inputStream == null) {
            return new net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO.O0000O000000oO();
        }
        return (O000O0000OOoO) new O000O00000o0O(new net.vidageek.O0000O000000oO.O000O0000OoO.O0000O000000oO.O0000O000000oO()).O000O00000OoO(O0000O000000oO(inputStream).get(O0000O000000oO.REFLECTION_PROVIDER)).O0000O000000oO().O0000O000000oO().O0000O000000oO();
    }
}
