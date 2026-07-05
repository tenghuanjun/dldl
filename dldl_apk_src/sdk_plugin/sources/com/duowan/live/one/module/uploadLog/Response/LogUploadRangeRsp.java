package com.duowan.live.one.module.uploadLog.Response;

import com.duowan.auk.NoProguard;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogUploadRangeRsp implements NoProguard {
    private MetaData metadata;
    private List<String> range;
    private int status;

    public List<String> getRange() {
        return this.range;
    }

    public void setRange(List<String> list) {
        this.range = list;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public MetaData getMetadata() {
        return this.metadata;
    }

    public void setMetadata(MetaData metaData) {
        this.metadata = metaData;
    }

    public String toString() {
        return "LogUploadRangeRsp{range=" + this.range + ", status=" + this.status + ", metadata=" + this.metadata + AbstractJsonLexerKt.END_OBJ;
    }

    public static class MetaData implements NoProguard {
        private String md5;
        private String name;
        private String size;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getSize() {
            return this.size;
        }

        public void setSize(String str) {
            this.size = str;
        }

        public String getMd5() {
            return this.md5;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public String toString() {
            return "MetaData{name='" + this.name + "', size='" + this.size + "', md5='" + this.md5 + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
