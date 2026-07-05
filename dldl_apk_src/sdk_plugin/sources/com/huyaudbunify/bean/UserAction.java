package com.huyaudbunify.bean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserAction {
    private String latitude;
    private String longitude;
    private String ssid;
    private List<UserActionBean> user_action = new CopyOnWriteArrayList();

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public List<UserActionBean> getUser_action() {
        return this.user_action;
    }

    public void setUser_action(List<UserActionBean> list) {
        this.user_action = list;
    }

    public static class UserActionBean {
        private String id;
        private String time;
        private String x;
        private String y;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getX() {
            return this.x;
        }

        public void setX(String str) {
            this.x = str;
        }

        public String getY() {
            return this.y;
        }

        public void setY(String str) {
            this.y = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public UserActionBean(String str, String str2, String str3, String str4) {
            this.id = str;
            this.x = str2;
            this.y = str3;
            this.time = str4;
        }
    }

    public UserAction(String str, String str2, String str3) {
        this.latitude = "0.0";
        this.longitude = "0.0";
        this.latitude = str2;
        this.longitude = str;
        this.ssid = str3;
    }
}
