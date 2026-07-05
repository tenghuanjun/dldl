package com.sqwan.liveshow.huya.bean;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RecommenedAnchorBean {
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return this.items;
    }

    public void setItems(List<ItemsBean> list) {
        this.items = list;
    }

    public static class ItemsBean {
        private String anchor_id;
        private int platform_id;
        private int rank;

        public int getPlatform_id() {
            return this.platform_id;
        }

        public void setPlatform_id(int i) {
            this.platform_id = i;
        }

        public String getAnchor_id() {
            return this.anchor_id;
        }

        public void setAnchor_id(String str) {
            this.anchor_id = str;
        }

        public int getRank() {
            return this.rank;
        }

        public void setRank(int i) {
            this.rank = i;
        }
    }
}
