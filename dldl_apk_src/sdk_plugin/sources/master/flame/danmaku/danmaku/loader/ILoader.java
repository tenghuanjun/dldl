package master.flame.danmaku.danmaku.loader;

import java.io.InputStream;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ILoader {
    IDataSource<?> getDataSource();

    void load(InputStream inputStream) throws IllegalDataException;

    void load(String str) throws IllegalDataException;
}
