package layaair.game.config;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class config {
    private static config ms_config;
    public boolean m_bCheckNetwork = false;
    public String m_sWebviewUrl = null;
    public String m_sConchGameUrl = null;
    public boolean m_bBackkeyWebviewHide = false;
    private Properties m_pProperties = null;
    public int m_nMarketWaitScreenBKColor = -1;

    public static void DelInstance() {
        ms_config = null;
    }

    public static config GetInstance() {
        if (ms_config == null) {
            ms_config = new config();
        }
        return ms_config;
    }

    private int getColor(String str) {
        if (str == null) {
            return 0;
        }
        boolean z = str.length() <= 6;
        int i = (int) Long.parseLong(str, 16);
        return z ? i | (-16777216) : i;
    }

    public String getProperty(String str) {
        Properties properties = this.m_pProperties;
        if (properties != null) {
            return properties.getProperty(str);
        }
        Log.e("LayaBox", "getProperty: error m_pProperties==null name=" + str);
        return null;
    }

    public String getProperty(String str, String str2) {
        Properties properties = this.m_pProperties;
        if (properties != null) {
            return properties.getProperty(str, str2);
        }
        Log.e("LayaBox", "getProperty: error m_pProperties==null name=" + str);
        return null;
    }

    public boolean init(InputStream inputStream) {
        if (this.m_pProperties == null) {
            this.m_pProperties = new Properties();
        }
        if (inputStream == null) {
            return false;
        }
        try {
            this.m_pProperties.load(inputStream);
            this.m_bCheckNetwork = !"0".equals(this.m_pProperties.getProperty("CheckNetwork", "0"));
            this.m_sWebviewUrl = this.m_pProperties.getProperty("WebviewUrl");
            this.m_sConchGameUrl = this.m_pProperties.getProperty("ConchGameUrl");
            this.m_bBackkeyWebviewHide = !"0".equals(this.m_pProperties.getProperty("BackKeyWebviewHide", "0"));
            return true;
        } catch (IOException e) {
            this.m_pProperties = null;
            e.printStackTrace();
            return false;
        }
    }
}
