package com.sqwan.liveshow.huya.bean;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveMenuBean {
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return this.items;
    }

    public void setItems(List<ItemsBean> list) {
        this.items = list;
    }

    public static class ItemsBean {
        private String icon_url;
        private int platform_id;
        private String platform_name;
        private int rank;
        private List<String> whitelist;

        public int getPlatform_id() {
            return this.platform_id;
        }

        public void setPlatform_id(int i) {
            this.platform_id = i;
        }

        public String getPlatform_name() {
            return this.platform_name;
        }

        public void setPlatform_name(String str) {
            this.platform_name = str;
        }

        public String getIcon_url() {
            return this.icon_url;
        }

        public void setIcon_url(String str) {
            this.icon_url = str;
        }

        public int getRank() {
            return this.rank;
        }

        public void setRank(int i) {
            this.rank = i;
        }

        public List<String> getWhitelist() {
            return this.whitelist;
        }

        public void setWhitelist(List<String> list) {
            this.whitelist = list;
        }
    }
}
