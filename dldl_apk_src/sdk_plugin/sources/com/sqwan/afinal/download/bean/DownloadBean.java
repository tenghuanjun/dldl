package com.sqwan.afinal.download.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadBean {
    public static int defualt_margin = -9999;
    private String background_img_url;
    private int close_button_right_margin;
    private int close_button_top_margin;
    private String close_button_url;
    private String download_apk_md5;
    private String download_apk_size;
    private String download_apk_url;
    private int download_button_bottom_margin;
    private String download_button_url;
    private int id;
    private String name;
    private String package_name;
    private int tgid;

    public DownloadBean() {
        int i = defualt_margin;
        this.close_button_top_margin = i;
        this.close_button_right_margin = i;
        this.download_button_bottom_margin = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getTgid() {
        return this.tgid;
    }

    public void setTgid(int i) {
        this.tgid = i;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    public void setPackage_name(String str) {
        this.package_name = str;
    }

    public String getBackground_img_url() {
        return this.background_img_url;
    }

    public void setBackground_img_url(String str) {
        this.background_img_url = str;
    }

    public String getDownload_button_url() {
        return this.download_button_url;
    }

    public void setDownload_button_url(String str) {
        this.download_button_url = str;
    }

    public String getClose_button_url() {
        return this.close_button_url;
    }

    public void setClose_button_url(String str) {
        this.close_button_url = str;
    }

    public String getDownload_apk_url() {
        return this.download_apk_url;
    }

    public void setDownload_apk_url(String str) {
        this.download_apk_url = str;
    }

    public String getDownload_apk_md5() {
        return this.download_apk_md5;
    }

    public void setDownload_apk_md5(String str) {
        this.download_apk_md5 = str;
    }

    public String getDownload_apk_size() {
        return this.download_apk_size;
    }

    public void setDownload_apk_size(String str) {
        this.download_apk_size = str;
    }

    public int getClose_button_top_margin() {
        return this.close_button_top_margin;
    }

    public void setClose_button_top_margin(int i) {
        this.close_button_top_margin = i;
    }

    public int getClose_button_right_margin() {
        return this.close_button_right_margin;
    }

    public void setClose_button_right_margin(int i) {
        this.close_button_right_margin = i;
    }

    public int getDownload_button_bottom_margin() {
        return this.download_button_bottom_margin;
    }

    public void setDownload_button_bottom_margin(int i) {
        this.download_button_bottom_margin = i;
    }
}
