package com.sqwan.liveshow.bean;

import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowChannelInfo {
    private List<ChannelsBean> channels;

    public List<ChannelsBean> getChannels() {
        return this.channels;
    }

    public void setChannels(List<ChannelsBean> list) {
        this.channels = list;
    }

    public static class ChannelsBean {
        private String anchor_avatar;
        private String anchor_id;
        private String anchor_name;
        private String app_name;
        private String cid;
        private String cname;

        public String getCid() {
            return this.cid;
        }

        public void setCid(String str) {
            this.cid = str;
        }

        public String getCname() {
            return this.cname;
        }

        public void setCname(String str) {
            this.cname = str;
        }

        public String getAnchor_id() {
            return this.anchor_id;
        }

        public void setAnchor_id(String str) {
            this.anchor_id = str;
        }

        public String getAnchor_name() {
            return this.anchor_name;
        }

        public void setAnchor_name(String str) {
            this.anchor_name = str;
        }

        public String getAnchor_avatar() {
            return this.anchor_avatar;
        }

        public void setAnchor_avatar(String str) {
            this.anchor_avatar = str;
        }

        public String getApp_name() {
            return this.app_name;
        }

        public void setApp_name(String str) {
            this.app_name = str;
        }

        public String toString() {
            return "ChannelsBean{cid='" + this.cid + "', cname='" + this.cname + "', anchor_id='" + this.anchor_id + "', anchor_name='" + this.anchor_name + "', anchor_avatar='" + this.anchor_avatar + "', app_name='" + this.app_name + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "LiveshowChannelInfo{channels=" + this.channels + AbstractJsonLexerKt.END_OBJ;
    }
}
