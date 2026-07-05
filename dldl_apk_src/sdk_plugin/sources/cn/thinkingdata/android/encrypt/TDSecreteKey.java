package cn.thinkingdata.android.encrypt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDSecreteKey {
    public String asymmetricEncryption;
    public String publicKey;
    public String symmetricEncryption;
    public int version;

    public TDSecreteKey() {
    }

    public TDSecreteKey(String str, int i, String str2, String str3) {
        this.publicKey = str;
        this.version = i;
        this.symmetricEncryption = str2;
        this.asymmetricEncryption = str3;
    }
}
