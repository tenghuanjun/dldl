package com.sqwan.liveshow.huya.bean;

import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ConfigBean {
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return this.items;
    }

    public void setItems(List<ItemsBean> list) {
        this.items = list;
    }

    public static class ItemsBean {
        private String app_id;
        private String app_key;
        private String game_id;
        private int platform_id;
        private int times;

        public int getPlatform_id() {
            return this.platform_id;
        }

        public void setPlatform_id(int i) {
            this.platform_id = i;
        }

        public String getGame_id() {
            return this.game_id;
        }

        public void setGame_id(String str) {
            this.game_id = str;
        }

        public String getApp_id() {
            return this.app_id;
        }

        public void setApp_id(String str) {
            this.app_id = str;
        }

        public String getApp_key() {
            return this.app_key;
        }

        public void setApp_key(String str) {
            this.app_key = str;
        }

        public int getTimes() {
            return this.times;
        }

        public void setTimes(int i) {
            this.times = i;
        }
    }

    public String toString() {
        return "ConfigBean{items=" + this.items + AbstractJsonLexerKt.END_OBJ;
    }
}
