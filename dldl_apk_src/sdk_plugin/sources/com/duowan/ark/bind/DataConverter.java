package com.duowan.ark.bind;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface DataConverter<Target, Source> {

    public static class SimpleConverter<Data> implements DataConverter<Data, Data> {
        @Override // com.duowan.ark.bind.DataConverter
        public Data convert(Data data) {
            return data;
        }
    }

    Target convert(Source source);
}
