package layaair.game.PlatformInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaPlatformFactory {
    private static LayaPlatformFactory sInstance;

    public static void DelInstance() {
        sInstance = null;
    }

    public static LayaPlatformFactory GetInstance() {
        if (sInstance == null) {
            sInstance = new LayaPlatformFactory();
        }
        return sInstance;
    }

    public LayaPlatformInterface CreateInterface(String str) {
        try {
            return (LayaPlatformInterface) Class.forName(str).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
