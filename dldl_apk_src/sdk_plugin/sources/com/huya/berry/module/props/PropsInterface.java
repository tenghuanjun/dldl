package com.huya.berry.module.props;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropsInterface {

    public static class InitProps {
    }

    public static class UpdateProps {
        public int gameId;
        public long sid;
        public long subSid;
        public long uid;

        public UpdateProps(long j, long j2, long j3, int i) {
            this.uid = j;
            this.sid = j2;
            this.subSid = j3;
            this.gameId = i;
        }
    }
}
