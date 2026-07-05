package android.arch.lifecycle;

import android.app.Application;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AndroidViewModel extends ViewModel {
    private Application mApplication;

    public AndroidViewModel(Application application) {
        this.mApplication = application;
    }

    public <T extends Application> T getApplication() {
        return (T) this.mApplication;
    }
}
