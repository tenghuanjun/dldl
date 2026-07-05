package com.huya.mtp.utils.bind;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DataConverter<Target, Source> {

    public static class SimpleConverter<Data> implements DataConverter<Data, Data> {
        @Override // com.huya.mtp.utils.bind.DataConverter
        public Data convert(Data data) {
            return data;
        }
    }

    Target convert(Source source);
}
